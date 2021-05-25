package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.Administrator;
import com.online.shopping_gui.model.User;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;


/**
 * A Database Manager Class for managing an embedded 
 * database to store details of Administrator users.
 * Applies the singleton design pattern.
 * 
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.1.0
 * @since 23/05/2021
 */
public final class AdminDBManager {

    private static final String USER_NAME = "pdc"; // DB username
    private static final String PASSWORD = "pdc"; // DB password
    private static final String URL = "jdbc:derby:Administrator_EDB; create=true"; // Embedded database for Admin.

    private static Connection connectionInstance;

    private AdminDBManager() { }

    public static synchronized Connection getConnection() {
        if (connectionInstance == null) {
            try {
                connectionInstance = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " CONNECTED....");
            } catch (SQLException e) {
                closeConnections();
                JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        return connectionInstance;
    }

    public static void closeConnections() {
        if (connectionInstance != null) {
            try {
                connectionInstance.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public static ResultSet queryDB(String sql) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int updateDB(String sql) {
        Statement statement = null;

        try {
            statement = getConnection().createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return 0;
        }
    }
    
    public static void dropTableIfExists(String tableName) {
        try {
            DatabaseMetaData databaseMetadata = getConnection().getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(null, null, tableName, null);
            if (resultSet.next()) { // If next returns true/next table...
                System.err.println(tableName + " TABLE ALREADY EXISTS"); // Means that table already exists.
                String dropTable = "DROP TABLE "+ tableName;
                updateDB(dropTable);
            } 
        } catch (SQLException e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public static boolean tableExists(String tableName) {
        try {
            DatabaseMetaData databaseMetadata = getConnection().getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(null, null, tableName, null);
            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    
    public static void createTable() {
        String createTbl = "CREATE TABLE ADMINISTRATOR("
                + "LOGINID VARCHAR(50), "
                + "PASSWORD VARCHAR(200), "
                + "NAME VARCHAR(50), "
                + "EMAIL VARCHAR(50))";
        
        if(tableExists("ADMINISTRATOR")) {
            dropTableIfExists("ADMINISTRATOR");
        }
        
        updateDB(createTbl);
    }
    
    public static HashMap<String, User> importData() {
        HashMap<String, User> administrators = new HashMap<>();
        
        String query = "SELECT * FROM ADMINISTRATOR";
        
        try {
            ResultSet rs = queryDB(query); // Query the admin database for details.
            String loginID, password, name, email;
            
            while(rs.next()) // While loop to pull data from resultString..
            {
                loginID = rs.getString("LOGINID");
                password = rs.getString("PASSWORD");
                name = rs.getString("NAME");
                email = rs.getString("EMAIL");
                administrators.put(loginID, new Administrator(loginID, Utilities.decrypt(password), name, email)); // Adds the admin to the hashmap.
            }
        } catch(Exception e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return administrators;
    }
    
    public static boolean rowExists(String loginID) {
        String query = "SELECT * FROM ADMINISTRATOR WHERE LOGINID = \'" + loginID + "\'";
        
        try {
            ResultSet rs = queryDB(query); // Query the admin database for a specific Admin.
            
            if(rs.next()) { // If rs.next has something...
                return true; // Row exists.
            }
        } catch(Exception e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false; // Row does not exist, hence, safe to add.
    }
    
    public static void exportData(HashMap<String, User> users) {
        try {
            if(!tableExists("ADMINISTRATOR")) { // If table does not exist..
                createTable(); // Create table.
            }
            
            // Writes data from the passed in Hash Map onto the file specified.          
            for (Map.Entry<String, User> user : users.entrySet()) {
                if (user.getValue() instanceof Administrator) { // Check if user is an instance of an Administrator...
                    String[] data = user.getValue().toString().split(",");

                    if(!rowExists(data[0])) { // If the admin is non-existent in the table...
                        String insertValue = "INSERT INTO ADMINISTRATOR VALUES (\'" 
                                + data[0] + "\', \'" // Login ID
                                + data[1] + "\', \'" // Encrypted Password
                                + data[2] + "\', \'" // Name
                                + data[3] + "\')";   // Email
                        
                        updateDB(insertValue);
                    } else {
                        System.err.println("User " + data[0] + " exists!");
                    }
                }
            }
        } catch(Exception e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    @Override //Override the Object clone method to prevent cloning
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}

package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.Customer;
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
 * database to store details of Customer-typed users.
 * Applies the singleton design pattern.
 * 
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.1.1
 * @since 23/05/2021
 */
public final class CustomerDBManager {

    private static final String USER_NAME = "pdc"; // DB username
    private static final String PASSWORD = "pdc"; // DB password
    private static final String URL = "jdbc:derby:Customer_EDB; create=true"; // Embedded database for Customers.
    private static Connection connectionInstance;

    private CustomerDBManager() { }

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
        String createTbl = "CREATE TABLE CUSTOMER("
                + "LOGINID VARCHAR(50), "
                + "PASSWORD VARCHAR(200), "
                + "NAME VARCHAR(50), "
                + "PHONE VARCHAR(50), "
                + "EMAIL VARCHAR(50), "
                + "ADDRESS VARCHAR(200), "
                + "CARDNUMBER VARCHAR(200), "
                + "CARDHOLDER VARCHAR(50))";
        
        if(tableExists("CUSTOMER")) {
            dropTableIfExists("CUSTOMER");
        }
        
        updateDB(createTbl);
    }
    
    public static HashMap<String, User> importData() {
        HashMap<String, User> customers = new HashMap<>();
        
        String query = "SELECT * FROM CUSTOMER";
        
        try {
            ResultSet rs = queryDB(query); // Query the Customer database for details.
            String loginID, password, name, phone, email, address, cardNumber, cardHolder;
            
            while(rs.next()) // While loop to pull data from resultString..
            {
                loginID = rs.getString("LOGINID");
                password = rs.getString("PASSWORD");
                name = rs.getString("NAME");
                phone = rs.getString("PHONE");
                email = rs.getString("EMAIL");
                address = rs.getString("ADDRESS");
                cardNumber = rs.getString("CARDNUMBER");
                cardHolder = rs.getString("CARDHOLDER");
                customers.put(loginID, new Customer(loginID, Utilities.decrypt(password), name, phone, email, address, Utilities.decrypt(cardNumber), cardHolder)); // Adds the customer to the hashmap.
            }
        } catch(Exception e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return customers;
    }
    
    public static boolean rowExists(String loginID) {
        String query = "SELECT * FROM CUSTOMER WHERE LOGINID = \'" + loginID + "\'";
        
        try {
            ResultSet rs = queryDB(query); // Query the Customer database for a specific Customer.
            
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
            if(!tableExists("CUSTOMER")) { // If table does not exist..
                createTable(); // Create table.
            }
            
            // Writes data from the passed in Hash Map onto the file specified.          
            for (Map.Entry<String, User> user : users.entrySet()) {
                if (user.getValue() instanceof Customer) { // Check if user is an instance of an Customer...
                    String[] data = user.getValue().toString().split(",");

                    if(!rowExists(data[0])) { // If the admin is non-existent in the table...
                        String insertValue = "INSERT INTO CUSTOMER VALUES (\'" 
                                + data[0] + "\', \'" // Login ID
                                + data[1] + "\', \'" // Encrypted Password
                                + data[2] + "\', \'" // Name
                                + data[3] + "\', \'" // Phone
                                + data[4] + "\', \'" // Email
                                + data[5].replaceAll(";", ",") + "\', \'" // Address
                                + data[6] + "\', \'" // Encrypted Card Number
                                + data[7] + "\')";   // Card Holder
                        
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

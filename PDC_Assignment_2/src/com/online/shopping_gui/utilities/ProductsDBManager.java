/*
 * The programs are designed for PDC paper
 */
package com.online.shopping_gui.utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class ProductsDBManager {

    private static final String USER_NAME = "pdc"; // DB username
    private static final String PASSWORD = "pdc"; // DB password
    private static final String URL = "jdbc:derby:ProductsEDB; create=true"; // Embedded database for Products.
    private static Connection connectionInstance;

    private ProductsDBManager() { }

    public static synchronized Connection getConnection() {
        if (connectionInstance == null) {
            try {
                connectionInstance = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " CONNECTED....");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
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
            }
        }
    }

    public static ResultSet queryDB(String sql) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultSet;
    }

    public static int updateDB(String sql) {
        Statement statement = null;

        try {
            statement = getConnection().createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override // Override the Object clone method to prevent cloning
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}

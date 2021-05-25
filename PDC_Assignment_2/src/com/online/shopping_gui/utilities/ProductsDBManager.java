package com.online.shopping_gui.utilities;

import com.online.shopping_gui.enumerations.Category;
import com.online.shopping_gui.model.Product;
import com.online.shopping_gui.model.ProductList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * A Database Manager Class for managing an embedded 
 * database to store details of Products.
 * Applies the singleton design pattern.
 * 
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 23/05/2021
 */
public final class ProductsDBManager {

    private static final String USER_NAME = "pdc"; // DB username
    private static final String PASSWORD = "pdc"; // DB password
    private static final String URL = "jdbc:derby:Products_EDB; create=true"; // Embedded database for Products.
    private static Connection connectionInstance;

    private ProductsDBManager() { }

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
        String createTbl = "CREATE TABLE PRODUCTS("
                + "PRODUCTNAME VARCHAR(100), "
                + "PRODUCTID INT(10), "
                + "PRICE DOUBLE(10,2), "
                + "CATEGORY VARCHAR(50), "
                + "STOCK INT(10))";
        
        if(tableExists("PRODUCTS")) {
            dropTableIfExists("PRODUCTS");
        }
        
        updateDB(createTbl);
    }
    
    public static ProductList importData() {
        ProductList pList = new ProductList();
        
        String query = "SELECT * FROM PRODUCTS";
        
        try {
            ResultSet rs = queryDB(query); // Query the Customer database for details.
            String productName, category;
            int productID, stock;
            double price;
            while(rs.next()) // While loop to pull data from resultString..
            {
                productName = rs.getString("PRODUCTNAME");
                productID = rs.getInt("PRODUCTID");
                price = rs.getDouble("PRICE");
                category = rs.getString("PHONE");
                stock = rs.getInt("STOCK");
                pList.addSingleProduct(new Product(productName, productID, price, Category.valueOf(category), stock));
            }
        } catch(Exception e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return pList;
    }
    
    public static boolean rowExists(String productName) {
        String query = "SELECT * FROM PRODUCTS WHERE PRODUCTNAME = \'" + productName + "\'";
        
        try {
            ResultSet rs = queryDB(query); // Query the admin database for a specific Customer.
            
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
    
    public static void exportData(ProductList products) {
        try {
            if(!tableExists("PRODUCTS")) { // If table does not exist..
                createTable(); // Create table.
            }
            
            ArrayList<Product> pList = products.getProductList();
            
            for (Product product : pList) {
                if(!rowExists(product.getProductName())) { // If the product is non-existent in the table...
                    String insertValue = "INSERT INTO PRODUCTS VALUES (\'" 
                            + product.getProductName() + "\', "         // Product name
                            + product.getProductID() + ", "             // Product ID
                            + product.getPrice() + ", \'"               // Price
                            + product.getCategory().toString() + "\', " // Category
                            + product.getStock() + ")";                 // Stock

                    updateDB(insertValue);
                } else {
                    System.err.println("Product " + product.getProductName() + " exists!");
                }
            }
        } catch(Exception e) {
            closeConnections();
            JOptionPane.showMessageDialog(null, e.getMessage(), ("Error: " + e.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    @Override // Override the Object clone method to prevent cloning
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}

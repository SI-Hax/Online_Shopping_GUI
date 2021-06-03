package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.ProductList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductsDBManagerTest {
    
    public ProductsDBManagerTest() {
    }

    /**
     * Test of getConnection method, of class ProductsDBManager.
     */
    @Test
    public void testGetConnection() {
        System.out.println("Test getConnection");
        assertNotNull(ProductsDBManager.getConnection());
    }
    
    /**
     * Test of importData method, of class ProductsDBManager.
     */
    @Test
    public void testImportData() {
        System.out.println("Test importData");
        assertNotNull(ProductsDBManager.importData()); // Returned data must not be null.
        System.out.println("importData");
    }
    
      
    /**
     * Test of importData method, of class ProductsDBManager.
     */
    @Test
    public void testImportDataSize() {
        System.out.println("Test importData returned values:");
        
        ProductList test = ProductsDBManager.importData();
        
        if(test.getProductList().size() < 12) {
            fail();
        }
    }

    /**
     * Test of rowExists method, of class ProductsDBManager.
     */
    @Test
    public void testRowExists() {
        System.out.println("rowExists (good input)");
        String productName = "AOC 27-inch";
        boolean expResult = true;
        boolean result = ProductsDBManager.rowExists(productName);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of rowExists method, of class ProductsDBManager.
     */
    @Test
    public void testRowExistsBadInput() {
        System.out.println("rowExists (bad input)");
        String productName = "Scissors";
        boolean expResult = false;
        boolean result = ProductsDBManager.rowExists(productName);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of tableExists method, of class CustomerDBManager.
     */
    @Test
    public void testTableExists() {
        System.out.println("Test if tableExists (good input) returns true");
        String tableName = "PRODUCTS";
        boolean expResult = true;
        boolean result = ProductsDBManager.tableExists(tableName);
        assertEquals(expResult, result); // Table must exist to pass.
    }
    
    /**
     * Test of tableExists method, of class CustomerDBManager.
     */
    @Test
    public void testTableExistsBadInput() {
        System.out.println("Test if tableExists with bad table name:");
        String tableName = "PRODUCT";
        boolean result = ProductsDBManager.tableExists(tableName);
        
        if(result == true) { // If tableExists returns true, that means we have an extra table.
            fail(); // Hence fail the test.
        }
    }
}

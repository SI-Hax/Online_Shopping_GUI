package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.User;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;


public class CustomerDBManagerTest {
    
    public CustomerDBManagerTest() {
    }

    /**
     * Test of getConnection method, of class CustomerDBManager.
     */
    @Test
    public void testGetConnection() {
        System.out.println("Test getConnection");       
        assertNotNull(CustomerDBManager.getConnection()); // Must return a connection instance.
    }

    /**
     * Test of tableExists method, of class CustomerDBManager.
     */
    @Test
    public void testTableExists() {
        System.out.println("Test if tableExists returns true");
        String tableName = "CUSTOMER";
        boolean expResult = true;
        boolean result = CustomerDBManager.tableExists(tableName);
        assertEquals(expResult, result); // Table must exist to pass.
    }
    
    /**
     * Test of tableExists method, of class CustomerDBManager.
     */
    @Test
    public void testTableExists2() {
        System.out.println("Test if tableExists does not return false:");
        String tableName = "CUSTOMER";
        boolean result = CustomerDBManager.tableExists(tableName);
        
        if(result == false) { // If tableExists returns false, that means we are missing the database for customer.
            fail(); // Hence fail the test.
        }
    }

    /**
     * Test of importData method, of class CustomerDBManager.
     */
    @Test
    public void testImportData() {
        System.out.println("Test importData");
        assertNotNull(CustomerDBManager.importData()); // Returned data must not be null.
    }
    
    /**
     * Test of importData method, of class CustomerDBManager.
     */
    @Test
    public void testImportDataSize() {
        System.out.println("Test importData returned values:");
        
        HashMap<String, User> testCustomer = CustomerDBManager.importData();
        
        if(testCustomer.size() < 3) {
            fail();
        }
    }
}

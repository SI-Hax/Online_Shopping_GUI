package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.User;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminDBManagerTest {
    
    public AdminDBManagerTest() {
    }

   /**
     * Test of getConnection method, of class AdminDBManager.
     */
    @Test
    public void testGetConnection() {
        System.out.println("Test getConnection");       
        assertNotNull(AdminDBManager.getConnection()); // Must return a connection instance.
    }

    /**
     * Test of tableExists method, of class AdminDBManager.
     */
    @Test
    public void testTableExists() {
        System.out.println("Test if tableExists returns true");
        String tableName = "ADMINISTRATOR";
        boolean expResult = true;
        boolean result = AdminDBManager.tableExists(tableName);
        assertEquals(expResult, result); // Table must exist to pass.
    }
    
    /**
     * Test of tableExists method, of class AdminDBManager.
     */
    @Test
    public void testTableExists2() {
        System.out.println("Test if tableExists does not return false:");
        String tableName = "ADMINISTRATOR";
        boolean result = AdminDBManager.tableExists(tableName);
        
        if(result == false) { // If tableExists returns false, that means we are missing the database for admin.
            fail(); // Hence fail the test.
        }
    }

    /**
     * Test of importData method, of class AdminDBManager.
     */
    @Test
    public void testImportData() {
        System.out.println("Test importData");
        assertNotNull(AdminDBManager.importData()); // Returned data must not be null.
    }
    
    /**
     * Test of importData method, of class AdminDBManager.
     */
    @Test
    public void testImportDataSize() {
        System.out.println("Test importData returned values:");
        
        HashMap<String, User> testAdmin = AdminDBManager.importData();
        
        if(testAdmin.size() < 3) {
            fail();
        }
    }
}

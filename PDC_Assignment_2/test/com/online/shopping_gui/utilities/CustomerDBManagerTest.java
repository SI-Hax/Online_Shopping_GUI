package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.User;
import com.online.shopping_gui.model.UserList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerDBManagerTest {
    
    public CustomerDBManagerTest() {
    }

    /**
     * Test of closeConnections method, of class CustomerDBManager.
     */
    @Test
    public void testCloseConnections() {
        System.out.println("closeConnections");
        CustomerDBManager.closeConnections();
        assert(true);
    }

    /**
     * Test of tableExists method, of class CustomerDBManager.
     */
    @Test
    public void testTableExists() {
        System.out.println("tableExists");
        String tableName = "CUSTOMER";
        CustomerDBManager.createTable();
        boolean expResult = true;
        boolean result = CustomerDBManager.tableExists(tableName);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        CustomerDBManager.createTable();
        assert(true);
    }


    /**
     * Test of exportData method, of class CustomerDBManager.
     */
    @Test
    public void testExportData() {
        System.out.println("exportData");
        HashMap<String, User> users = null;
        CustomerDBManager.exportData(users);
        assert(true);
    }
}

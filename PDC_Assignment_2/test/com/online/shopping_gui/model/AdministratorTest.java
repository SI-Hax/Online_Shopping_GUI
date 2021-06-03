package com.online.shopping_gui.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdministratorTest {
    
    public AdministratorTest() {
    }

    /**
     * Test of getAdminEmail method, of class Administrator.
     */
    @Test
    public void testGetAdminEmail() {
        System.out.println("getAdminEmail");
        Administrator instance = new Administrator("super123", "Woohoo1010101!");
        String expResult = "UNKNOWN";
        String result = instance.getAdminEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdminEmail method, of class Administrator.
     */
    @Test
    public void testSetAdminEmail() {
        System.out.println("setAdminEmail with good email format");
        String adminEmail = "test.email@aut.ac.nz";
        Administrator instance = new Administrator("super123", "Woohoo1010101!");
        boolean expResult = true;
        boolean result = instance.setAdminEmail(adminEmail);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setAdminEmail method, of class Administrator.
     */
    @Test
    public void testSetAdminBadEmail() {
        System.out.println("setAdminEmail with bad email format");
        String adminEmail = "test.ac.nz";
        Administrator instance = new Administrator("super123", "Woohoo1010101!");
        boolean expResult = false;
        boolean result = instance.setAdminEmail(adminEmail);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Administrator.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword with good input");
        String password = "Woohoo1010101@";
        Administrator instance = new Administrator("super123", "Woohoo1010101!");
        boolean expResult = true;
        boolean result = instance.setPassword(password);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setPassword method, of class Administrator.
     */
    @Test
    public void testSetBadPassword() {
        System.out.println("setPassword with bad input");
        String password = "sup123";
        Administrator instance = new Administrator("super123", "Woohoo1010101!");
        boolean expResult = false;
        boolean result = instance.setPassword(password);
        assertEquals(expResult, result);
    }
}

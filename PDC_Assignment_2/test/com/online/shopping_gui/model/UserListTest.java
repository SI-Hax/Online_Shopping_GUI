package com.online.shopping_gui.model;

import com.online.shopping_gui.utilities.UserFileIO;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserListTest {
    
    public UserListTest() {
    }

    /**
     * Test of removeUser method, of class UserList.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("remove existing User");
        UserList instance = new UserList(UserFileIO.importUserData());
        String loginID = "test123";
        boolean expResult = true;
        boolean result = instance.removeUser(loginID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removeUser method, of class UserList.
     */
    @Test
    public void testRemoveNonExistentUser() {
        System.out.println("remove non-existent User");
        UserList instance = new UserList(UserFileIO.importUserData());
        String loginID = "helloworld";
        boolean expResult = false;
        boolean result = instance.removeUser(loginID);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchUser method, of class UserList.
     */
    @Test
    public void testSearchUser() {
        System.out.println("search existent User");
        UserList instance = new UserList(UserFileIO.importUserData());
        String loginID = "test123";
        User result = instance.searchUser(loginID);
        assertNotNull(result);
    }
    
    /**
     * Test of searchUser method, of class UserList.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSearchNonExistentUser() {
        System.out.println("search non-existent User");
        UserList instance = new UserList(UserFileIO.importUserData());
        String loginID = "helloworld";
        instance.searchUser(loginID);
    }

    /**
     * Test of userExists method, of class UserList.
     */
    @Test
    public void testUserExists() {
        System.out.println("userExists - input is registered user");
        UserList instance = new UserList(UserFileIO.importUserData());
        String loginID = "test123";
        boolean expResult = true;
        boolean result = instance.userExists(loginID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of userExists method, of class UserList.
     */
    @Test
    public void testNonExistentUserExists() {
        System.out.println("userExists - input is non-existent user");
        UserList instance = new UserList(UserFileIO.importUserData());
        String loginID = "helloworld";
        boolean expResult = false;
        boolean result = instance.userExists(loginID);
        assertEquals(expResult, result);
    }

    /**
     * Test of logInUser method, of class UserList.
     */
    @Test
    public void testLogInUser() {
        System.out.println("logInUser");
        UserList instance = new UserList(UserFileIO.importUserData());
        
        String loginID = "test123"; // Registered user
        boolean expResult = true;
        boolean result = instance.logInUser(loginID);
        assertEquals(expResult, result);
        
        String loginID2 = "djorange77"; // Registered user
        boolean expResult2 = true;
        boolean result2 = instance.logInUser(loginID2);
        assertEquals(expResult2, result2);
        
        String loginID3 = "helloworld";// Not-registered user
        boolean expResult3 = false;
        boolean result3 = instance.logInUser(loginID3);
        assertEquals(expResult3, result3);
    }

    /**
     * Test of logOutUser method, of class UserList.
     */
    @Test
    public void testLogOutUser() {
        System.out.println("logOutUser");
        UserList instance = new UserList(UserFileIO.importUserData());
        String loginID = "test123"; // Registered user
        instance.logInUser(loginID);
        
        // There's a user logged in so should return true.
        boolean expResult = true;
        boolean result = instance.logOutUser();
        assertEquals(expResult, result);
        
        // No more user logged in so should return false.
        expResult = false; 
        result = instance.logOutUser();
        assertEquals(expResult, result);        
    }

    /**
     * Test of getCurrentUser method, of class UserList.
     */
    @Test
    public void testGetCurrentUser() {
        System.out.println("getCurrentUser");
        UserList instance = new UserList(UserFileIO.importUserData());
        String loginID = "test123"; // Registered user
        instance.logInUser(loginID);
        assertNotNull(instance.getCurrentUser());
        
        // See if expected and actual is matching.
        User expResult = instance.searchUser(loginID);
        User result = instance.getCurrentUser();
        assertEquals(expResult, result);
    }
    
}

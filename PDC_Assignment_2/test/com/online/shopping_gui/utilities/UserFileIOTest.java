package com.online.shopping_gui.utilities;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserFileIOTest {
    
    public UserFileIOTest() {
    }

    /**
     * Test of importUserData method, of class UserFileIO.
     */
    @Test
    public void testImportUserData() {
        System.out.println("importUserData");
        assertNotNull(UserFileIO.importUserData());
    }    
}

package com.online.shopping_gui.utilities;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilitiesTest {
    
    public UtilitiesTest() {
    }

    /**
     * Test of passIsSecure method, of class Utilities.
     */
    @Test
    public void testPassIsSecure() {
        System.out.println("passIsSecure (good input)");
        String password = "Woohoo101!";
        int minCharacters = 8;
        boolean expResult = true;
        boolean result = Utilities.passIsSecure(password, minCharacters);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of passIsSecure method, of class Utilities.
     */
    @Test
    public void testPassIsNotSecure() {
        System.out.println("passIsNotSecure (bad input)");
        String password = "amos";
        int minCharacters = 8;
        boolean expResult = false;
        boolean result = Utilities.passIsSecure(password, minCharacters);
        assertEquals(expResult, result);
    }

    /**
     * Test of emailIsValid method, of class Utilities.
     */
    @Test
    public void testEmailIsValid() {
        System.out.println("emailIsValid (good input)");
        String email = "test.email@aut.ac.nz";
        boolean expResult = true;
        boolean result = Utilities.emailIsValid(email);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of emailIsValid method, of class Utilities.
     */
    @Test
    public void testEmailIsNotValid() {
        System.out.println("emailIsNotValid (bad input)");
        String email = "test@aut";
        boolean expResult = false;
        boolean result = Utilities.emailIsValid(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of cardIsValid method, of class Utilities.
     */
    @Test
    public void testCardIsValid() {
        System.out.println("cardIsValid");
        String cardNumber = "5889510083564867";
        boolean expResult = true;
        boolean result = Utilities.cardIsValid(cardNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cardIsValid method, of class Utilities.
     */
    @Test
    public void testCardIsNotValid() {
        System.out.println("cardIsValid");
        String cardNumber = "12345678910";
        boolean expResult = false;
        boolean result = Utilities.cardIsValid(cardNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of encrypt method, of class Utilities.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt");
        String text = "some text here";
        assertNotNull(Utilities.encrypt(text));
    }

    /**
     * Test of decrypt method, of class Utilities.
     */
    @Test
    public void testDecrypt() {
        System.out.println("decrypt");
        String cipheredText = "g9E4KzoTdrbzzvVoJywIXg==";
        assertNotNull(Utilities.decrypt(cipheredText));
    }    
}

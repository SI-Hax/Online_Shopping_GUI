package com.online.shopping_gui.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerTest {
    
    public CustomerTest() {
    }

    /**
     * Test of getName method, of class Customer.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Customer instance = new Customer("cust123", "Woohoo101!");
        String expResult = "UNKNOWN";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setPhone method, of class Customer.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone with good input (numeric)");
        String phone = "02102277889";
        Customer instance = new Customer("cust123", "Woohoo101!");
        instance.setPhone(phone);
        String test = instance.getPhone();        
        assertEquals(test, phone);
    }
    
    /**
     * Test of setPhone method, of class Customer.
     */
    @Test
    public void testSetPhoneWithDash() {
        System.out.println("setPhone with good input (numeric with dashed & space separator)");
        String phone = "021-022 7788 9";
        Customer instance = new Customer("cust123", "Woohoo101!");
        instance.setPhone(phone);
        String expResult = "02102277889";        
        assertEquals(expResult, instance.getPhone());
    }
    
    /**
     * Test of setPhone method, of class Customer.
     */
    @Test(expected = NumberFormatException.class)
    public void testSetBadPhone() {
        System.out.println("setPhone with bad input (alphabets)");
        String phone = "not a phone number";
        Customer instance = new Customer("cust123", "Woohoo101!");
        instance.setPhone(phone);
    }
    
    

    /**
     * Test of setEmail method, of class Customer.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail with good email format");
        String email = "test@gmail.co.nz";
        Customer instance = new Customer("cust123", "Woohoo101!");
        boolean expResult = true;
        boolean result = instance.setEmail(email);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setEmail method, of class Customer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBadEmail() {
        System.out.println("setEmail with bad email format");
        String email = "not in email format";
        Customer instance = new Customer("cust123", "Woohoo101!");
        instance.setEmail(email);
    }

    /**
     * Test of setCardNumber method, of class Customer.
     */
    @Test
    public void testSetCardNumber() {
        System.out.println("setCardNumber with good input (no separators)");
        String cardNumber = "5889510083564867";
        Customer instance = new Customer("cust123", "Woohoo101!");
        boolean expResult = true;
        boolean result = instance.setCardNumber(cardNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setCardNumber method, of class Customer.
     */
    @Test
    public void testSetCardNumberSpaced() {
        System.out.println("setCardNumber with good input (with space separators)");
        String cardNumber = "5889 5100 8356 4867";
        Customer instance = new Customer("cust123", "Woohoo101!");
        boolean expResult = true;
        boolean result = instance.setCardNumber(cardNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setCardNumber method, of class Customer.
     */
    @Test
    public void testSetCardNumberDashed() {
        System.out.println("setCardNumber with good input (with dash separators)");
        String cardNumber = "5889-5100-8356-4867";
        Customer instance = new Customer("cust123", "Woohoo101!");
        boolean expResult = true;
        boolean result = instance.setCardNumber(cardNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setCardNumber method, of class Customer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBadCardNumber() {
        System.out.println("setCardNumber with bad input (invalid card number)");
        String cardNumber = "12345678910";
        Customer instance = new Customer("cust123", "Woohoo101!");
        instance.setCardNumber(cardNumber);
    }

    /**
     * Test of setPassword method, of class Customer.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword with good input");
        String password = "Wooohoo10101!!";
        Customer instance = new Customer("cust123", "Woohoo101!");
        boolean expResult = true;
        boolean result = instance.setPassword(password);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setPassword method, of class Customer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBadPassword() {
        System.out.println("setPassword with bad input (not meeting password requirements)");
        String password = "ooohoo10101!!";
        Customer instance = new Customer("cust123", "Woohoo101!");
        instance.setPassword(password);
    }
}

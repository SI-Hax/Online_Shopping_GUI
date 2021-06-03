package com.online.shopping_gui.model;

import com.online.shopping_gui.utilities.ProductFileIO;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShoppingCartTest {
    
    public ShoppingCartTest() {
    }

    /**
     * Test of setUser method, of class ShoppingCart.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = new Customer("guest", "Guest123!");
        ShoppingCart instance = new ShoppingCart();
        instance.setUser(user);
        assertEquals(user, instance.getUser());
    }

    /**
     * Test of size method, of class ShoppingCart.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        ProductList pList = ProductFileIO.importProductData();
        
        ShoppingCart instance = new ShoppingCart();
        instance.addToCart(pList.getProductList().get(1), 1);
        instance.addToCart(pList.getProductList().get(2), 2);
        instance.addToCart(pList.getProductList().get(3), 3);
        
        int expResult = 3;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class ShoppingCart.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ShoppingCart instance = new ShoppingCart();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGrandTotal method, of class ShoppingCart.
     */
    @Test
    public void testGetGrandTotal() {
        System.out.println("getGrandTotal");
        ShoppingCart instance = new ShoppingCart();
        ProductList pList = ProductFileIO.importProductData();
        instance.addToCart(pList.getProductList().get(7), 3);
        
        double expResult = 990.0;
        double result = instance.getGrandTotal();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of addToCart method, of class ShoppingCart.
     */
    @Test
    public void testAddToCart() {
        System.out.println("addToCart");
        ProductList pList = ProductFileIO.importProductData();
        Product product = pList.getProductList().get(1);
        int amount = 1;
        
        ShoppingCart instance = new ShoppingCart();
        instance.addToCart(product, amount);
        instance.addToCart(product, amount);
        instance.addToCart(product, amount);

        assertNotNull(instance);
    }

    /**
     * Test of removeFromCart method, of class ShoppingCart.
     */
    @Test
    public void testRemoveFromCart() {
        System.out.println("removeFromCart");
        ProductList pList = ProductFileIO.importProductData();
        
        ShoppingCart instance = new ShoppingCart();
        instance.addToCart(pList.getProductList().get(1), 1);
        instance.addToCart(pList.getProductList().get(2), 2);
        instance.addToCart(pList.getProductList().get(3), 3);
        
        int originalStockVal = 100;
        assertEquals(originalStockVal, instance.removeFromCart(2));
        assertEquals(originalStockVal, instance.removeFromCart(1));
        assertEquals(originalStockVal, instance.removeFromCart(0));
    }

    /**
     * Test of clearCart method, of class ShoppingCart.
     */
    @Test
    public void testClearCart() {
        System.out.println("clearCart");
        ProductList pList = ProductFileIO.importProductData();
        
        ShoppingCart instance = new ShoppingCart();
        instance.addToCart(pList.getProductList().get(1), 1);
        instance.addToCart(pList.getProductList().get(2), 2);
        instance.addToCart(pList.getProductList().get(3), 3);
        
        instance.clearCart();
        
        Assert.assertTrue(instance.isEmpty());
        assertEquals(0D, instance.getGrandTotal(), 0.01);
    }

    /**
     * Test of convertShoppingCart method, of class ShoppingCart.
     */
    @Test
    public void testConvertShoppingCart() {
        System.out.println("convertShoppingCart");
        ProductList pList = ProductFileIO.importProductData();
        
        ShoppingCart instance = new ShoppingCart();
        instance.addToCart(pList.getProductList().get(1), 1);
        instance.addToCart(pList.getProductList().get(2), 2);
        instance.addToCart(pList.getProductList().get(3), 3);
       
        assertNotNull(instance.convertShoppingCart());
    }
}

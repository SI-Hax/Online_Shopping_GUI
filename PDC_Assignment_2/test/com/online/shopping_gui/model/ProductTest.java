package com.online.shopping_gui.model;

import com.online.shopping_gui.utilities.ProductFileIO;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {
    
    public ProductTest() {
    }

    /**
     * Test of setProductID method, of class Product.
     */
    @Test
    public void testSetProductID() {
        System.out.println("setProductID with good input (number)");
        int productID = 2;
        Product instance = new Product();
        instance.setProductID(productID);
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        ProductList pList = ProductFileIO.importProductData();
        Product instance = pList.getProductList().get(0);
        double expResult = 378D;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.02);
    }

    /**
     * Test of setPrice method, of class Product.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 10D;
        Product instance = new Product();
        instance.setPrice(price);
        assertEquals(price, instance.getPrice(), 0.02);
    }

    /**
     * Test of getStock method, of class Product.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        ProductList pList = ProductFileIO.importProductData();
        Product instance = pList.getProductList().get(0);
        Integer expResult = 100;
        Integer result = instance.getStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStock method, of class Product.
     */
    @Test
    public void testSetStock() {
        System.out.println("setStock");
        Integer stock = 101;
        Product instance = new Product();
        instance.setStock(stock);
        assertEquals(stock, instance.getStock());
    }
}

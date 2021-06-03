package com.online.shopping_gui.model;

import com.online.shopping_gui.enumerations.Category;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductListTest {
    
    public ProductListTest() {
    }

    /**
     * Test of searchProduct method, of class ProductList.
     */
    @Test
    public void testSearchProduct() {
        System.out.println("searchProduct");
        String keyword = "AOC";
        ProductList instance = ProductFileIO.importProductData();
        ArrayList<Product> products = instance.getProductList();
        Product expResult = products.get(0);
        Product result = instance.searchProduct(keyword);
        assertEquals(expResult, result);
    }
}

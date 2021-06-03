package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.ProductList;
import org.junit.Assert;
import org.junit.Test;

public class ProductFileIOTest {
    
    public ProductFileIOTest() {
    }

    /**
     * Test of importProductData method, of class ProductFileIO.
     */
    @Test
    public void testImportProductData() {
        System.out.println("importProductData size > 10");
        ProductList result = ProductFileIO.importProductData();
        Assert.assertTrue(result.getProductList().size() > 10);
    }
}

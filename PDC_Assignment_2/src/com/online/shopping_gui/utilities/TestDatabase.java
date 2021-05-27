/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.User;
import java.util.HashMap;

/**
 *
 * @author Amos Foong <18044418>
 */
public class TestDatabase {
    public static void main(String[] args) {
        HashMap<String, User> users = UserFileIO.importUserData();
        
        AdminDBManager.createTable();
        AdminDBManager.exportData(users);
        AdminDBManager.closeConnections();
        
        CustomerDBManager.createTable();
        CustomerDBManager.exportData(users);
        CustomerDBManager.closeConnections();
        
        ProductList pList = ProductFileIO.importProductData();
        ProductsDBManager.createTable();
        ProductsDBManager.exportData(pList);
        ProductsDBManager.closeConnections();
    }
}

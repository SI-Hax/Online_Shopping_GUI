package com.online.shopping_gui.utilities;

import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.User;
import java.util.HashMap;

public class LoadToEDBFromCSV {
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

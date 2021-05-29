package com.online.shopping_gui.model;

import com.online.shopping_gui.utilities.AdminDBManager;
import com.online.shopping_gui.utilities.CustomerDBManager;
import com.online.shopping_gui.utilities.ProductsDBManager;
import com.online.shopping_gui.utilities.UserFileIO;
import com.online.shopping_gui.utilities.Utilities;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Observable;

/**
 * This class contains the Login GUI
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.1.0
 * @since 17/05/2021
 */
public class CardModel extends Observable {
    protected int mainMenuSelection;
    protected UserList users;
    protected ProductList productList;
    protected ShoppingCart shoppingCart;
    protected boolean custLoginFlag, adminLoginFlag, createAccFlag, cardFlag;
    
    public CardModel() {
        custLoginFlag = false;
        adminLoginFlag = false;
        createAccFlag = false;
        cardFlag = false;
//        users = new UserList(AdminDBManager.importData(), CustomerDBManager.importData()); 
//        productList = ProductsDBManager.importData();
        Runnable loadUsers = () -> {
            users = new UserList(AdminDBManager.importData(), CustomerDBManager.importData()); // Import data from database.
        };
        new Thread(loadUsers).start(); // Run in separate thread (background) to optimize UI responsiveness. 
        
        Runnable loadProducts = () -> {
            productList = ProductsDBManager.importData(); // Import data from database.
        };
        new Thread(loadProducts).start(); // Run in separate thread (background) to optimize UI responsiveness. 
    }
    
      public void setMainMenuSelection(int selection){
        this.mainMenuSelection = selection;
        cardFlag = true;
        custLoginFlag = false;
        adminLoginFlag = false;
        createAccFlag = false;
        setChanged();
        notifyObservers(this);
    }
             
    public boolean checkLogin(String loginID, String password) {
        System.out.println("Checking login for " + loginID + ", " + password);
        if(users.userExists(loginID)) { // If there's an existing user with passed-id login id...
            System.out.println("User exists");
            User user = users.searchUser(loginID);
            if(user.getPassword().equals(password)) { // Check if password match database stored password.
                System.out.println("Password match");
                users.logInUser(loginID); // Log in user (saves it as current user).
                if(user instanceof Customer) { // If user is an instance of Customer...
                    shoppingCart = new ShoppingCart(user);
                    System.out.println("Customer logged in.");
                    custLoginFlag = true; // Set custLogin flag to true to enable customer pop-up window.
                    cardFlag = false;
                    adminLoginFlag = false;
                    createAccFlag = false;
                    setChanged();
                    notifyObservers(this);
                    return true;
                } else if (user instanceof Administrator){ // Otherwise...
                    adminLoginFlag = true; // Set adminLogin flag to true to enable admin pop-up window.
                    cardFlag = false;
                    custLoginFlag = false;
                    createAccFlag = false;
                    setChanged();
                    notifyObservers(this);
                    return true;
                }
            } else { 
                throw new IllegalArgumentException("Incorrect password! Please try again.");
            }
        } else {
            throw new NoSuchElementException("User (" + loginID + ") does not exist! Please use a valid login ID or create a new account.");
        }
        return false;
    }
    
     public boolean addCustomer(String loginID, String password, String name, String phone, String email, String address, String cardNumber, String cardHolder) throws Exception { 
        Customer newCustomer = new Customer(loginID, password, name, phone, email, address, cardNumber, cardHolder); 
        boolean addSuccess = users.addUser(newCustomer);
        updateCustDB(loginID, password, name, phone, email, address, cardNumber, cardHolder);
        createAccFlag = true; // Set createAcc flag to true.
        custLoginFlag = false; 
        cardFlag = false;
        adminLoginFlag = false;
        setChanged();
        notifyObservers(this);
        return addSuccess;
    }
     
    public void updateCustDB(String loginID, String password, String name, String phone, String email, String address, String cardNumber, String cardHolder) {
        // Prepare statement to execute.
        String updateDB = "INSERT INTO CUSTOMER VALUES (\'" 
                            + loginID + "\', \'" // Login ID
                            + Utilities.encrypt(password) + "\', \'" // Encrypted Password
                            + name + "\', \'" // Name
                            + phone + "\', \'" // Phone
                            + email + "\', \'" // Email
                            + address + "\', \'" // Address
                            + Utilities.encrypt(cardNumber) + "\', \'" // Encrypted Card Number
                            + cardHolder + "\')";   // Card Holder
       
        if(!CustomerDBManager.rowExists(loginID)) { // If loginID is non-existant in database...
            Runnable updateCustDB = () -> {
                CustomerDBManager.updateDB(updateDB); // Insert row into EDB table.
                UserFileIO.exportUserData(users.getUserList()); // Write to csv.
            };
            new Thread(updateCustDB).start(); // Run in separate thread (background) to optimize UI responsiveness.  
        }
    }

    public int getMainMenuSelection() {
        return mainMenuSelection;
    }
    
    public UserList getUsers() {
        return users;
    }
    
    public ProductList getProductList() {
        return productList;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public boolean isCustLoginFlag() {
        return custLoginFlag;
    }

    public boolean isAdminLoginFlag() {
        return adminLoginFlag;
    }

    public boolean isCreateAccFlag() {
        return createAccFlag;
    }

    public boolean isCardFlag() {
        return cardFlag;
    }
}

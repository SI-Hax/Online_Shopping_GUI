package com.online.shopping_gui.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains method to add/remove/get data of users. Also has 
 * methods to save/remove a user when they log in/log out.
 * 
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.1.1
 * @since 25/05/2021
 */
public class UserList 
{
    private ConcurrentHashMap<String, User> userList;
    private User currentUser;
    
    /**
     * 0-parameter constructor to initialise userList HashMap
     * and currentUser to null.
     */
    public UserList() {
        userList = new ConcurrentHashMap<>();
        currentUser = null;
    }
    
    /**
     * Overloaded n-parameter constructor to receive n amount 
     * HashMaps of users and add them to the list
     * (java idioms' var args implementation).
     * 
     * @param loadedUsers : HashMap containing Users.
     */
    public UserList(HashMap<String, User>... loadedUsers) {
        userList = new ConcurrentHashMap<>(); // Prepare userList for use.
        
        for(HashMap<String, User> usersType : loadedUsers) {
            addMultipleUsers(usersType); // Adds preloaded users into the arraylist.
        }
        
        currentUser = null;
    }

    /**
     * Adds a User object into the entry list by LoginID (String)
     *
     * @param user : New user to be added into the collection.
     * @return T/F whether the user has been added successfully.
     */
    public boolean addUser(User user) {
        if(!userExists(user.getLoginID())) { // If passed in user is non-existant in the map...
            userList.put(user.getLoginID(), user); // Add new user to collection.
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Helper method to add collection users 
     * all at once into the userList Map.
     *
     * @param multipleUsers : Users to be added into the collection.
     */
    public void addMultipleUsers(HashMap<String, User> multipleUsers) {
        userList.putAll(multipleUsers);
    }
    
    /**
     * Removes a user from the user list by using a login id 
     * passed in (case-insensitive).
     * 
     * @param loginID : Login id to look out for.
     * @return T/F whether the removal process is successful.
     */
    public boolean removeUser(String loginID) {
        if(userExists(loginID)) { // If loginID is in the system...
            userList.remove(loginID.toLowerCase()); // Remove it from the map.
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Retrieves a specific user by their login ID and return 
     * the User object to caller.
     * 
     * @param loginID : ID to search for
     * @return User object if found
     */
    public User searchUser(String loginID) {
        if(userExists(loginID)) { // Check if there is a user within the system with specified loginID....
            return userList.get(loginID.toLowerCase()); // Return that user.
        } else {
            throw new IllegalArgumentException("User with login ID \"" + loginID + "\" not found!");
        }
    }
    
    /**
     * Helper method to check if a user exists in the user list(map).
     * 
     * @param loginID : ID to be checked.
     * @return T/F whether user exists in the map
     */
    public boolean userExists(String loginID) {
        return userList.containsKey(loginID.toLowerCase());
    }
    
    /**
     * Returns the HashMap of users. 
     * 
     * @return the map of users.
     */
    public ConcurrentHashMap<String, User> getUserList() {
        return userList;
    }
    
    /**
     * Method to search through the map of users (userList) using 
     * a login id, check if they exists, if they do, assign to 
     * current user and return true, otherwise, return false to 
     * signify that the login id is not found.
     * 
     * @param loginID : User's login id to spot.
     * @return T/F whether the log in process is successful.
     */
    public boolean logInUser(String loginID) {
        if(userExists(loginID)) { // If loginID is existent in the user List.
            currentUser = userList.get(loginID.toLowerCase()); // Logs in the current user.
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method to log a current user out. 
     * 
     * @return T/F whether the log out process was successful
     */
    public boolean logOutUser() {
        if(currentUser != null) { // If there is a user logged in...
            currentUser = null; // Log them out.
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the user that is currently logged in
     * and using the system.
     * 
     * @return Current user that is logged on.
     */
    public User getCurrentUser() {
        return currentUser;
    }
}

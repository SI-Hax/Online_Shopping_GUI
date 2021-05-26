package com.online.shopping_gui.model;

import java.util.*;

/**
 * Contains fields to collect data from user.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 25/05/2021
 */
public class UserList {

    private LinkedHashMap<String, ArrayList<User>> singleUserList;

    public UserList() {
        singleUserList = new LinkedHashMap<String, ArrayList<User>>();
    }

    /**
     * Adds a User object into the entry list by LoginID (String)
     *
     * @param newUser
     */
    public void addSingleCust(Customer customer) {
        ArrayList<User> tempList = new ArrayList<User>();
        if (singleUserList.containsKey(customer.getLoginID())) {
            tempList = singleUserList.get(customer.getLoginID());
        }

        tempList.add(customer);
        singleUserList.put(customer.getLoginID(), tempList);
    }

    public void addSingleAdmin(Administrator admin) {
        ArrayList<User> tempList = new ArrayList<User>();
        if (singleUserList.containsKey(admin.getLoginID())) {
            tempList = singleUserList.get(admin.getLoginID());
        }

        tempList.add(admin);
        singleUserList.put(admin.getLoginID(), tempList);
    }

    public LinkedHashMap<String, ArrayList<User>> getSingleUserList() {
        return singleUserList;
    }
}

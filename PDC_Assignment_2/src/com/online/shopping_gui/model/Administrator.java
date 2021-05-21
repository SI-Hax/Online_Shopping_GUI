package com.online.shopping_gui.model;

import com.online.shopping_gui.utilities.Utilities;
import com.online.shopping_gui.view.CardView;

import javax.swing.*;
import java.sql.*;

/**
 * This class holds information about an Administrator of the system. It is an
 * extension of User class. Behaviours include getters and setters that uses
 * externally-sourced validation methods (from Utilities class).
 *
 * <p>
 * Attributes:</p>
 * <ul>
 * <li>Administrator's Name</li>
 * <li>E-mail Address</li>
 * </ul>
 * Behaviours:
 * <ul>
 * <li>2-Parameter Constructor</li>
 * <li>4-Parameter Constructor</li>
 * <li>Getters and Setters</li>
 * <li>To String Method</li>
 * </ul>
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 1.02
 * @since 30/03/2021
 *
 */
public class Administrator extends User {

    private String adminName;
    private String adminEmail;
    private static Connection conn;

    /**
     * 2-parameter constructor for Administrator class. Admins must have at
     * least a loginID and a password. The rest will be computer-assigned.
     *
     * @param loginID : Login identifier for admins.
     * @param password : Password defined by the admin.
     *
     */
    public Administrator(String loginID, String password) {
        super(loginID);
        this.setPassword(password);
        this.setAdminName("Admin");
        this.setAdminEmail("");
    }

    /**
     * 4-parameter constructor for Administrator class. Admins can have a
     * loginID, password, name, and email.
     *
     * @param loginID : Login identifier for admins.
     * @param password : Password defined by the admin.
     * @param adminName : Name of the administrator.
     * @param adminEmail : Email address of the admin.
     *
     */
    public Administrator(String loginID, String password, String adminName, String adminEmail) throws IllegalArgumentException {
        super(loginID);
        this.setPassword(password);
        this.setAdminName(adminName);
        this.setAdminEmail(adminEmail);
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = (adminName.isEmpty() ? "UNKNOWN" : adminName);
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public boolean setAdminEmail(String adminEmail){
        boolean set = false;
        if (adminEmail.equalsIgnoreCase("UNKNOWN")) { // Checks if passed in data is "UNKNOWN"...
            this.adminEmail = "UNKNOWN";
            set = true;
        } else if (!(adminEmail.isEmpty() || adminEmail.equals(" "))) { // Checks if passed in data is not empty...
            if (Utilities.emailIsValid(adminEmail)) { // If passed in email passes check...
                this.adminEmail = adminEmail; // Assign passed in data to instance's attribute.
                set = true;
            } else {
                set = false; 
            }
        } else {
            this.adminEmail = "UNKNOWN";
            set = true;
        }
        
        return set;
    }

    // Temp method
    public static void  establishConnection() {
        try {
            conn = DriverManager.getConnection(DatabaseConn.CONNECTION_URL);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void createTable() {
        try {
            Statement statement = conn.createStatement();
            String newTableName = "Admin";
            String sqlCreate = "create table " + newTableName + " (Login_ID varchar(50), Password varchar(50), " +
                    "Name varchar(50), Email varchar(50), phoneNumber int, CardNumber int, CardHolder varchar(50))";
            statement.executeUpdate(sqlCreate);

            statement.close();
            System.out.println("Table created");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertData(CardView cardView) {
        try {
            Statement statement = conn.createStatement();
            String newTableName = "Admin";

            String url = "insert into " + newTableName + " values(?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(url);

            preparedStatement.setString(1, cardView.getCreatAdminAccountView().getLoginIDTxtField().getText());
            preparedStatement.setString(2, String.valueOf(cardView.getCreatAdminAccountView().getConfirmPassField().getPassword()));
            preparedStatement.setString(3, cardView.getCreatAdminAccountView().getNameTxtField().getText());
            preparedStatement.setString(4, cardView.getCreatAdminAccountView().getEmailTxtField().getText());
            preparedStatement.setString(5, cardView.getCreatAdminAccountView().getPhoneNoTxtField().getText());
            preparedStatement.setString(6, cardView.getCreatAdminAccountView().getCardNoTxtField().getText());
            preparedStatement.setString(7, cardView.getCreatAdminAccountView().getCardHolderTxtField().getText());

            int i = preparedStatement.executeUpdate();
            
            if (i > 0) {
                System.out.println("New Record Saved");
            }

            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean loginCheck(String loginID, String password) {
        try {
            Statement statement = conn.createStatement();

            String sql = "select * from Admin where LOGIN_ID='"+loginID+"' and PASSWORD='"+password+"'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Password or Login ID is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    // Temp method

    /**
     * Overridden method from superclass (User). Users that are Administrators
     * are required to have a password length of at least 14 characters.
     *
     * <p>
     * In addition, the password must also meet general password requirements
     * (At least: 1 Uppercase, 1 Lowercase, 1 Number, and 1 Symbol).</p>
     *
     * @param password : User defined password.
     * @throws IllegalArgumentException
     *
     */
    @Override
    public boolean setPassword(String password){
        boolean set = false;
        // Check if password length is at least 14 characters and if its secure...
        if (Utilities.passIsSecure(password, password.length())){
            this.password = password; // Saves user-defined password.
            set = true;
        } else {
            set = false;
        }
        
        return set;
    }

    /**
     * To String method to serialise object. String files Object's attributes,
     * which are separated by commas.
     *
     * @return A String containing User type, Admin's login credentials and
     * details, all separated by commas.
     *
     */
    @Override
    public String toString() {
        String comma = ",";
        String details = "";

        // Admins login credentials and details.
        details += this.getLoginID() + comma;
        details += Utilities.encrypt(this.getPassword()) + comma;
        details += this.getAdminName() + comma;
        details += this.getAdminEmail() + comma;
        details += "\n";

        return details;
    }
}

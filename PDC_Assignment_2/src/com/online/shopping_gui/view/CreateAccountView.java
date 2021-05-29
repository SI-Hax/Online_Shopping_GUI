package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

/**
 * Contains fields to collect data from user. 
 * 
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.2
 * @since 15/05/2021
 */
public class CreateAccountView extends JPanel 
{
    private final String ID_CHECK = "Existing ID detected, please select another.";
    private final String PASS_GUIDE = "Password must contain 8+ Characters, 1 Upper, 1 Lower, 1 Number, & 1 Symbol";
    private final String PASS_CHECK = "Weak Password. Please hover over field to see tip.";
    private final String PASS_NO_MATCH = "Passwords do not match.";
    private final int TXT_FIELD_WIDTH = 200;
    private final int TXT_FIELD_HEIGHT = 25;
    private final int FIELD_CHARS = 50;

    private JLabel loginIDLbl, loginIDCheckLbl, passwordLbl, passwordCheckLbl, confirmPassLbl, confirmPassCheckLbl;
    private JLabel nameLbl, phoneNoLbl, emailLbl, addressLbl, cardNoLbl, cardHolderLbl;
    private JTextField loginIDTxtField, nameTxtField, phoneNoTxtField, emailTxtField, addressTxtField, cardNoTxtField, cardHolderTxtField;
    private JPasswordField passwordPassField, confirmPassField;
    private JButton createAccountBtn, backBtn;
    
    public CreateAccountView() {
        FlatLightLaf.install();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(296, 406));
        this.setBackground(Color.WHITE);
        
        // Construct components.
        loginIDLbl = new JLabel("Login ID");
        loginIDTxtField = new JTextField(FIELD_CHARS);
        loginIDCheckLbl = new JLabel("");
        loginIDCheckLbl.setForeground(Color.RED);
        
        passwordLbl = new JLabel("Password");
        passwordPassField = new JPasswordField(FIELD_CHARS);
        passwordPassField.setToolTipText(PASS_GUIDE);
        passwordCheckLbl = new JLabel("");
        passwordCheckLbl.setForeground(Color.RED);

        confirmPassLbl = new JLabel("Confirm Password");
        confirmPassField = new JPasswordField(FIELD_CHARS);
        confirmPassCheckLbl = new JLabel("");
        confirmPassCheckLbl.setForeground(Color.RED);
        
        nameLbl = new JLabel("Name");
        nameTxtField = new JTextField(FIELD_CHARS);
        
        emailLbl = new JLabel("Email");
        emailTxtField = new JTextField(FIELD_CHARS);

        addressLbl = new JLabel("Address");
        addressTxtField = new JTextField(FIELD_CHARS);
        
        phoneNoLbl = new JLabel("Phone Number");
        phoneNoTxtField = new JTextField(FIELD_CHARS);
        
        cardNoLbl = new JLabel("Card Number");
        cardNoTxtField = new JTextField(FIELD_CHARS);
        
        cardHolderLbl = new JLabel("Card Holder");
        cardHolderTxtField = new JTextField(FIELD_CHARS);
        
        createAccountBtn = new JButton("Create Account");
        
        backBtn = new JButton("Back");

        // Add components to panel.
        add(loginIDLbl);
        add(loginIDTxtField);
        add(loginIDCheckLbl);
        add(passwordLbl);
        add(passwordPassField);
        add(passwordCheckLbl);
        add(confirmPassLbl);
        add(confirmPassField);
        add(confirmPassCheckLbl);
        add(nameLbl);
        add(nameTxtField);
        add(emailLbl);
        add(emailTxtField);
        add(addressLbl);
        add(addressTxtField);
        add(phoneNoLbl);
        add(phoneNoTxtField);
        add(cardNoLbl);
        add(cardNoTxtField);
        add(cardHolderLbl);
        add(cardHolderTxtField);
        add(createAccountBtn);
        add(backBtn);

        // Set component bounds (size and location)
        loginIDLbl.setBounds(70, 25, 50, 25);
        loginIDTxtField.setBounds(135, 25, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        loginIDCheckLbl.setBounds(135, 50, TXT_FIELD_WIDTH, 15);
        passwordLbl.setBounds(60, 65, 60, 25);
        passwordPassField.setBounds(135, 65, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        passwordCheckLbl.setBounds(135, 90, TXT_FIELD_WIDTH, 15);
        confirmPassLbl.setBounds(15, 105, 110, 25);
        confirmPassField.setBounds(135, 105, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        confirmPassCheckLbl.setBounds(135, 130, TXT_FIELD_WIDTH, 15);
        nameLbl.setBounds(85, 145, 35, 25);
        nameTxtField.setBounds(135, 145, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        emailLbl.setBounds(85, 185, 35, 25);
        emailTxtField.setBounds(135, 185, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        addressLbl.setBounds(75, 225, 50, 25);
        addressTxtField.setBounds(135, 225, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        phoneNoLbl.setBounds(35, 265, 85, 25);
        phoneNoTxtField.setBounds(135, 265, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        cardNoLbl.setBounds(45, 305, 80, 25);
        cardNoTxtField.setBounds(135, 305, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        cardHolderLbl.setBounds(50, 340, 70, 25);
        cardHolderTxtField.setBounds(135, 345, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        createAccountBtn.setBounds(135, 395, TXT_FIELD_WIDTH, 45);
        backBtn.setBounds(275, 500, 100, 25);
    }
    
    public void resetFields() {
        loginIDTxtField.setText("");
        passwordPassField.setText("");
        confirmPassField.setText("");
        nameTxtField.setText("");
        emailTxtField.setText("");
        addressTxtField.setText("");
        phoneNoTxtField.setText("");
        cardNoTxtField.setText("");
        cardHolderTxtField.setText("");
        createAccountBtn.setEnabled(false);
    }
    
    public void configCreateBtn(boolean value) {
        createAccountBtn.setEnabled(value);
    }

    public JLabel getLoginIDCheckLbl() {
        return loginIDCheckLbl;
    }
    
    public void warnLoginIDCheck() {
        loginIDCheckLbl.setText(ID_CHECK);
    }
    
    public void passLoginIDCheck() {
        loginIDCheckLbl.setText("");
    }
    
    public JLabel getPasswordCheckLbl() {
        return passwordCheckLbl;
    }
    
    public void warnPassCheck() {
        passwordCheckLbl.setText(PASS_CHECK);
    }
    
    public void passPassCheck() {
        passwordCheckLbl.setText("");
    }

    public JLabel getConfirmPassCheckLbl() {
        return confirmPassCheckLbl;
    }
    
    public void warnConfirmPassCheck() {
        passwordCheckLbl.setText(PASS_NO_MATCH);
    }
    
    public void passConfirmPassCheck() {
        passwordCheckLbl.setText("");
    }

    public JTextField getLoginIDTxtField() {
        return loginIDTxtField;
    }
    
    public JPasswordField getPasswordPassField() {
        return passwordPassField;
    }

    public JPasswordField getConfirmPassField() {
        return confirmPassField;
    }

    public JTextField getNameTxtField() {
        return nameTxtField;
    }

    public JTextField getPhoneNoTxtField() {
        return phoneNoTxtField;
    }

    public JTextField getEmailTxtField() {
        return emailTxtField;
    }

    public JTextField getAddressTxtField() {
        return addressTxtField;
    }

    public JTextField getCardNoTxtField() {
        return cardNoTxtField;
    }

    public JTextField getCardHolderTxtField() {
        return cardHolderTxtField;
    }

    public JButton getCreateAccountBtn() {
        return createAccountBtn;
    }
    
    public JButton getBackBtn() {
        return backBtn;
    }
}

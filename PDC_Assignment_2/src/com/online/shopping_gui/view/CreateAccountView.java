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
 * @version 2.0.1
 * @since 15/05/2021
 */
public class CreateAccountView extends JPanel 
{
    private final int TXT_FIELD_WIDTH = 200;
    private final int TXT_FIELD_HEIGHT = 25;
    private final int FIELD_CHARS = 50;

    private JLabel loginIDLbl, loginIDCheckLbl, passwordLbl, passwordCheckLbl, confirmPassLbl, confirmPassCheckLbl;
    private JLabel nameLbl, phoneNoLbl, emailLbl, cardNoLbl, cardHolderLbl;
    private JTextField loginIDTxtField, nameTxtField, phoneNoTxtField, emailTxtField, cardNoTxtField, cardHolderTxtField;
    private JPasswordField passwordPassField, confirmPassField;
    private JButton createAccountBtn, backBtn;
    
    public CreateAccountView() {
        FlatLightLaf.install();
        setLayout(null);
        setPreferredSize(new Dimension(296, 406));
        this.setBackground(Color.WHITE);
        
        // Construct components.
        loginIDLbl = new JLabel("Login ID");
        loginIDTxtField = new JTextField(FIELD_CHARS);
        loginIDCheckLbl = new JLabel("");
        
        passwordLbl = new JLabel("Password");
        passwordPassField = new JPasswordField(FIELD_CHARS);
        passwordPassField.setToolTipText("Password must contain 8+ Characters, 1 Upper, 1 Lower, 1 Number, & 1 Symbol");
        passwordCheckLbl = new JLabel("");

        confirmPassLbl = new JLabel("Confirm Password");
        confirmPassField = new JPasswordField(FIELD_CHARS);
        confirmPassCheckLbl = new JLabel("");
        
        nameLbl = new JLabel("Name");
        nameTxtField = new JTextField(FIELD_CHARS);
        
        emailLbl = new JLabel("Email");
        emailTxtField = new JTextField(FIELD_CHARS);
        
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
        phoneNoLbl.setBounds(35, 225, 85, 25);
        phoneNoTxtField.setBounds(135, 225, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        cardNoLbl.setBounds(45, 265, 80, 25);
        cardNoTxtField.setBounds(135, 265, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        cardHolderLbl.setBounds(50, 300, 70, 25);
        cardHolderTxtField.setBounds(135, 305, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        createAccountBtn.setBounds(135, 345, TXT_FIELD_WIDTH, 45);
        backBtn.setBounds(280, 500, 100, 25);
    }

    public JLabel getLoginIDCheckLbl() {
        return loginIDCheckLbl;
    }

    public JLabel getPasswordCheckLbl() {
        return passwordCheckLbl;
    }

    public JLabel getConfirmPassCheckLbl() {
        return confirmPassCheckLbl;
    }

    public JTextField getLoginIDTxtField() {
        return loginIDTxtField;
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

    public JTextField getCardNoTxtField() {
        return cardNoTxtField;
    }

    public JTextField getCardHolderTxtField() {
        return cardHolderTxtField;
    }

    public JPasswordField getPasswordPassField() {
        return passwordPassField;
    }

    public JPasswordField getConfirmPassField() {
        return confirmPassField;
    }

    public JButton getCreateAccountBtn() {
        return createAccountBtn;
    }
    
    
    
    public JButton getBackBtn() {
        return backBtn;
    }
}

package test.view;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 1.0
 * @since 15/05/2021
 */
public class CreateAccountView extends JPanel 
{
    public static final String NAME = "Create Account";
    private JLabel loginIDLbl, loginIDCheckLbl, passwordLbl, passwordCheckLbl, confirmPassLbl, confirmPassCheckLbl;
    private JLabel nameLbl, phoneNoLbl, emailLbl, cardNoLbl, cardHolderLbl;
    private JTextField loginIDTxtField, nameTxtField, phoneNoTxtField, emailTxtField, cardNoTxtField, cardHolderTxtField;
    private JPasswordField passwordPassField, confirmPassField;
    private JButton createAccountBtn;
    
    public CreateAccountView() {
        FlatLightLaf.install();
        setLayout(null);
        setPreferredSize(new Dimension(296, 406));
        
        // Construct components.
        loginIDLbl = new JLabel ("Login ID");
        loginIDTxtField = new JTextField (50);
        loginIDCheckLbl = new JLabel ("");
        
        passwordLbl = new JLabel("Password");
        passwordPassField = new JPasswordField(50);
        passwordPassField.setToolTipText("Password must contain 8+ Characters, 1 Upper, 1 Lower, 1 Number, & 1 Symbol");
        passwordCheckLbl = new JLabel("");

        confirmPassLbl = new JLabel("Confirm Password");
        confirmPassField = new JPasswordField(50);
        confirmPassCheckLbl = new JLabel("");
        
        nameLbl = new JLabel("Name");
        nameTxtField = new JTextField(50);
        
        emailLbl = new JLabel("Email");
        emailTxtField = new JTextField(50);
        
        phoneNoLbl = new JLabel("Phone Number");
        phoneNoTxtField = new JTextField(50);
        
        cardNoLbl = new JLabel("Card Number");
        cardNoTxtField = new JTextField(50);
        
        cardHolderLbl = new JLabel("Card Holder");
        cardHolderTxtField = new JTextField(50);
        
        createAccountBtn = new JButton("Create Account");

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

        // Set component bounds (size and location)
        loginIDLbl.setBounds(70, 25, 50, 25);
        loginIDTxtField.setBounds(135, 25, 145, 25);
        loginIDCheckLbl.setBounds(135, 50, 145, 15);
        passwordLbl.setBounds(60, 65, 60, 25);
        passwordPassField.setBounds(135, 65, 145, 25);
        passwordCheckLbl.setBounds(135, 90, 145, 15);
        confirmPassLbl.setBounds(15, 105, 110, 25);
        confirmPassField.setBounds(135, 105, 145, 25);
        confirmPassCheckLbl.setBounds(135, 130, 145, 15);
        nameLbl.setBounds(85, 145, 35, 25);
        nameTxtField.setBounds(135, 145, 145, 25);
        emailLbl.setBounds(85, 185, 35, 25);
        emailTxtField.setBounds(135, 185, 145, 25);
        phoneNoLbl.setBounds(35, 225, 85, 25);
        phoneNoTxtField.setBounds(135, 225, 145, 25);
        cardNoLbl.setBounds(45, 265, 80, 25);
        cardNoTxtField.setBounds(135, 265, 145, 25);
        cardHolderLbl.setBounds(50, 300, 70, 25);
        cardHolderTxtField.setBounds(135, 305, 145, 25);
        createAccountBtn.setBounds(135, 345, 145, 45);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setLocationRelativeTo(null);
        frame.add(new CreateAccountView());
        frame.pack();
        frame.setVisible(true);
    }
}

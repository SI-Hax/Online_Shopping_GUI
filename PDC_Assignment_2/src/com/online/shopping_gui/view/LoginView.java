package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;

import javax.swing.*;

/**
 * This class contains the Login GUI
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.2
 * @since 15/05/2021
 */
public class LoginView extends JPanel {

    private final int TXT_FIELD_WIDTH = 135;
    private final int TXT_FIELD_HEIGHT = 25;
    private final int FIELD_CHARS = 30;

    private JButton okay, resetBtn, backBtn;
    private JLabel logLbl, passLbl;
    private JPasswordField enterPass;
    private JTextField loginTxt;

    public LoginView() {
        FlatLightLaf.install();
        this.setLayout(null);
        this.setBackground(Color.WHITE);

        okay = new JButton("Login");
        okay.setEnabled(false);
        resetBtn = new JButton("Reset"); //TODO: Remove redundant Quit Button.
        logLbl = new JLabel("Login ID");
        passLbl = new JLabel("Password");
        enterPass = new JPasswordField(FIELD_CHARS);
        loginTxt = new JTextField(FIELD_CHARS);
        backBtn = new JButton("Back");

        add(okay);
        add(resetBtn); //TODO: Remove redundant Quit Button.
        add(logLbl);
        add(passLbl);
        add(enterPass);
        add(loginTxt);
        add(backBtn);

        okay.setBounds(75, 165, 100, 25);
        resetBtn.setBounds(180, 165, 100, 25); 
        logLbl.setBounds(80, 80, 100, 25);
        passLbl.setBounds(75, 120, 100, 25);
        enterPass.setBounds(145, 120, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        loginTxt.setBounds(145, 80, TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT);
        backBtn.setBounds(275, 500, 100, 25);
    }

    public JButton getOkay() {
        return okay;
    }

    public JButton getResetBtn() {
        return resetBtn;
    }

    public JPasswordField getEnterPass() {
        return enterPass;
    }

    public JTextField getLoginTxt() {
        return loginTxt;
    }
    
    public JButton getBackBtn() {
        return backBtn;
    }

    public void resetFields() {
        loginTxt.setText("");
        enterPass.setText("");
        okay.setEnabled(false);
    }
    
    public void configLoginBtn(boolean value) {
        okay.setEnabled(value);
    }
}

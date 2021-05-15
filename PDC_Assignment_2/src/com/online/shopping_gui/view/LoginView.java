package com.online.shopping_gui.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This class contains the State Enumeration which maintains constant variables
 * for the user account state
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 1.0
 * @since 15/03/2021
 *
 */
public class LoginView extends JFrame {

    private JButton okay;
    private JLabel logLabel, passLabel;
    private JPasswordField enterPass;
    private JTextField loginText;

    public LoginView() {
        okay = new JButton("Login");
        logLabel = new JLabel("Login ID");
        passLabel = new JLabel("Password");
        enterPass = new JPasswordField(30);
        loginText = new JTextField(30);

        setPreferredSize(new Dimension(394, 319));
        setLayout(null);

        add(okay);
        add(logLabel);
        add(passLabel);
        add(enterPass);
        add(loginText);

        okay.setBounds(145, 165, 100, 25);
        logLabel.setBounds(80, 80, 100, 25);
        passLabel.setBounds(75, 120, 100, 25);
        enterPass.setBounds(145, 120, 100, 25);
        loginText.setBounds(145, 80, 100, 25);
    }

    public static void main(String[] args) {
        LoginView login = new LoginView();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.pack();
        login.setVisible(true);
    }
}

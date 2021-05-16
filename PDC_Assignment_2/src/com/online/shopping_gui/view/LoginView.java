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

    private JButton okay, quit;
    private JLabel logLbl, passLbl;
    private JPasswordField enterPass;
    private JTextField loginTxt;

    public LoginView() {
        okay = new JButton("Login");
        quit = new JButton("Quit");
        logLbl = new JLabel("Login ID");
        passLbl = new JLabel("Password");
        enterPass = new JPasswordField(30);
        loginTxt = new JTextField(30);

        setPreferredSize(new Dimension(394, 319));
        setLayout(null);

        add(okay);
        add(quit);
        add(logLbl);
        add(passLbl);
        add(enterPass);
        add(loginTxt);

        okay.setBounds(75, 165, 100, 25);
        quit.setBounds(180, 165, 100, 25);
        logLbl.setBounds(80, 80, 100, 25);
        passLbl.setBounds(75, 120, 100, 25);
        enterPass.setBounds(145, 120, 100, 25);
        loginTxt.setBounds(145, 80, 100, 25);
    }

    //testing
    public static void main(String[] args) {
        LoginView login = new LoginView();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.pack();
        login.setVisible(true);
    }
}

package com.online.shopping_gui.view;

import java.awt.*;
import javax.swing.*;

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
public class WelcomeView extends JFrame {

    private JButton custLogin, adminLogin, createAccount;

    public WelcomeView() {
        setLayout(null);
        
        ImageShopAndRun rightPanel = new ImageShopAndRun();
        rightPanel.setBounds(20, 140, 400, 116);
        this.add(rightPanel);
        

        custLogin = new JButton("Login as Customer");
        adminLogin = new JButton("Login as Admin");
        createAccount = new JButton("Create an Account");

        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.white);
        this.add(custLogin);
        this.add(adminLogin);
        this.add(createAccount);

        custLogin.setBounds (450, 110, 150, 55);
        adminLogin.setBounds (450, 185, 150, 55);
        createAccount.setBounds (450, 260, 150, 55);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");

        WelcomeView hi = new WelcomeView();
        hi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hi.pack();
        hi.setVisible(true);
    }
}

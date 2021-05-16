/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.shopping_gui.view;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author roxyc
 */
public class MainMenuView extends JPanel {
    private JButton custLogin, adminLogin, createAccount, quit;

    public MainMenuView(){
        setPreferredSize(new Dimension(400, 560));
        setLayout(null);
        
        custLogin = new JButton("Login as Customer");
        adminLogin = new JButton("Login as Admin");
        createAccount = new JButton("Create an Account");
        quit = new JButton("Quit");

        add(custLogin);
        add(adminLogin);
        add(createAccount);
        add(quit);

        custLogin.setBounds(125, 110, 150, 55);
        adminLogin.setBounds(125, 185, 150, 55);
        createAccount.setBounds(125, 260, 150, 55);
        quit.setBounds(125, 335, 150, 55);
    }
    
}

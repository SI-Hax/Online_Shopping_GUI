package com.online.shopping_gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the View (Buttons) for the Main Menu.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 15/05/2021
 */
public class MainMenuView extends JPanel {
    protected JButton custLogin, adminLogin, createAccount, quit;

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

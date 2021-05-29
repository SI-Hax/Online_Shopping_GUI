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
    protected JButton loginBtn, createAccount, quit;

    public MainMenuView(){
        this.setPreferredSize(new Dimension(395, 550));
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        
//        custLogin = new JButton("Login as Customer");
        loginBtn = new JButton("Login");
        createAccount = new JButton("Create an Account");
        quit = new JButton("Quit");

//        add(custLogin);
        add(loginBtn);
        add(createAccount);
        add(quit);

//        custLogin.setBounds(125, 110, 150, 55);
        loginBtn.setBounds(125, 165, 150, 55);
        createAccount.setBounds(125, 240, 150, 55);
        quit.setBounds(125, 315, 150, 55);
    }

//    public JButton getCustLogin() {
//        return custLogin;
//    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JButton getCreateAccount() {
        return createAccount;
    }

    public JButton getQuit() {
        return quit;
    }    
}
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

    private MainMenuView mainMenu;

    public WelcomeView() {
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.white);

        ImageShopAndRun rightPanel = new ImageShopAndRun();
        rightPanel.setBounds(50, 200, 400, 116);
        this.add(rightPanel);

        mainMenu = new MainMenuView();
        mainMenu.setBounds(475, 50, 400, 560);
        this.add(mainMenu);
    }

    public static void main(String[] args) {
        WelcomeView hi = new WelcomeView();
        hi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hi.pack();
        hi.setVisible(true);
    }
}

package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the Login GUI
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 15/05/2021
 */
public class LoginView extends JPanel {

    private JButton okay, quit;
    private JLabel logLbl, passLbl;
    private JPasswordField enterPass;
    private JTextField loginTxt;

    public LoginView() {
        FlatLightLaf.install();
        this.setLayout(null);

        okay = new JButton("Login");
        quit = new JButton("Quit");
        logLbl = new JLabel("Login ID");
        passLbl = new JLabel("Password");
        enterPass = new JPasswordField(30);
        loginTxt = new JTextField(30);

        //get the size of the screen
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        this.setPreferredSize(new Dimension(400, 320));

        //Calculate the frame location
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;

        this.setLocation(x, y);

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
}

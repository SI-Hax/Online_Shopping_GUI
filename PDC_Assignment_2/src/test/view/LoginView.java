package test.view;

import com.formdev.flatlaf.FlatLightLaf;

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

    private JButton okay, resetBtn, backBtn;
    private JLabel logLbl, passLbl;
    private JPasswordField enterPass;
    private JTextField loginTxt;

    public LoginView() {
        FlatLightLaf.install();
        this.setLayout(null);

        okay = new JButton("Login");
        resetBtn = new JButton("Reset");
        logLbl = new JLabel("Login ID");
        passLbl = new JLabel("Password");
        enterPass = new JPasswordField(30);
        loginTxt = new JTextField(30);
        backBtn = new JButton("Back");

        add(okay);
        add(resetBtn);
        add(logLbl);
        add(passLbl);
        add(enterPass);
        add(loginTxt);
        add(backBtn);

        okay.setBounds(75, 165, 100, 25);
        resetBtn.setBounds(180, 165, 100, 25);
        logLbl.setBounds(80, 80, 100, 25);
        passLbl.setBounds(75, 120, 100, 25);
        enterPass.setBounds(145, 120, 135, 25);
        loginTxt.setBounds(145, 80, 135, 25);
        backBtn.setBounds(280, 500, 100, 25);
    }
    
    public JButton getBackBtn() {
        return backBtn;
    }
}

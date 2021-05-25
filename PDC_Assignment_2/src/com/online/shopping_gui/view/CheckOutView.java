package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Contains fields to collect data from user. 
 * 
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 25/05/2021
 */

public class CheckOutView extends JPanel{

    private JLabel total;
    private JTextField totalTxt;
    private JLabel shipTo;
    private JTextArea shipToTxt;
    private JLabel visa;
    private JLabel ccv;
    private JPasswordField visaTxt;
    private JPasswordField ccvTxt;
    private JButton confirm;
    private JButton cancel;

    public CheckOutView() {
        FlatLightLaf.install();

        total = new JLabel("Total:");
        totalTxt = new JTextField(20);
        shipTo = new JLabel("Ship To:");
        shipToTxt = new JTextArea(100, 100);
        visa = new JLabel("Visa:");
        ccv = new JLabel("CCV:");
        visaTxt = new JPasswordField(20);
        ccvTxt = new JPasswordField(5);
        confirm = new JButton("Confirm");
        cancel = new JButton("Cancel");

        setPreferredSize(new Dimension(284, 382));
        setLayout(null);

        add(total);
        add(totalTxt);
        add(shipTo);
        add(shipToTxt);
        add(visa);
        add(ccv);
        add(visaTxt);
        add(ccvTxt);
        add(confirm);
        add(cancel);

        total.setBounds(25, 40, 100, 25);
        totalTxt.setBounds(95, 40, 165, 25);
        shipTo.setBounds(25, 70, 100, 25);
        shipToTxt.setBounds(95, 75, 165, 150);
        visa.setBounds(25, 235, 100, 25);
        ccv.setBounds(25, 265, 100, 25);
        visaTxt.setBounds(95, 235, 165, 25);
        ccvTxt.setBounds(95, 265, 165, 25);
        confirm.setBounds(30, 315, 100, 25);
        cancel.setBounds(145, 315, 100, 25);
    }
}

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
public class ReceiptView extends JPanel {

    private JLabel rec;
    private JTextArea receipt;
    private JLabel thank;

    public ReceiptView() {
        FlatLightLaf.install();
        rec = new JLabel("Receipt");
        receipt = new JTextArea(20, 20);
        thank = new JLabel("Thank you for shopping with us!");

        setPreferredSize(new Dimension(284, 382));
        setLayout(null);

        add(rec);
        add(receipt);
        add(thank);

        rec.setBounds(120, 25, 100, 25);
        receipt.setBounds(45, 50, 205, 250);
        thank.setBounds(55, 315, 185, 30);
    }
}

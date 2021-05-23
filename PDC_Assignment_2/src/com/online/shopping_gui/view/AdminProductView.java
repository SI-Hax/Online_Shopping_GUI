package com.online.shopping_gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Miguel Emmara - 18022146
 */
public class AdminProductView extends JPanel {
    private JLabel addProductLabel;

    public AdminProductView() {
        setPreferredSize(new Dimension(400, 560));
        setLayout(null);

        addProductLabel = new JLabel("Add Product Panel");

        add(addProductLabel);

        addProductLabel.setBounds(20, 20, 100, 50);
    }
}

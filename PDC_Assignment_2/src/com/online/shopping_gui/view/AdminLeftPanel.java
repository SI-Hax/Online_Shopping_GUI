package com.online.shopping_gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Miguel Emmara - 18022146
 */
public class AdminLeftPanel extends JPanel {
    protected JButton productsBtn, addProductBtn;

    public AdminLeftPanel(){
        setPreferredSize(new Dimension(400, 560));
        setLayout(null);

        productsBtn = new JButton("Product");
        addProductBtn = new JButton("Add Product");

        add(productsBtn);
        add(addProductBtn);

        productsBtn.setFocusable(false);
        addProductBtn.setFocusable(false);

        productsBtn.setBounds(20, 100, 100, 50);
        addProductBtn.setBounds(20, 200, 100, 50);
    }

    public JButton getProductsBtn() {
        return productsBtn;
    }

    public JButton getAddProductBtn() {
        return addProductBtn;
    }
}
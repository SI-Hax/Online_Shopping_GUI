package com.online.shopping_gui.view;

/**
 * This class contains the Right Panel for WelcomeView which encompasses several
 * different views (panels). It also contains a method to set which panel to
 * display.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 22/05/2021
 */
import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;

public class AddProductsView extends JPanel {

    private JButton add;
    private JLabel productID, productName, price, cat, stock;
    private JTextField prodIDTxt, prodNameTxt, priceTxt, catTxt, stockTxt;

    public AddProductsView() {
        FlatLightLaf.install();

        add = new JButton("Add");
        productID = new JLabel("Product ID");
        prodIDTxt = new JTextField(10);
        productName = new JLabel("Product Name");
        prodNameTxt = new JTextField(40);
        price = new JLabel("Price");
        priceTxt = new JTextField(15);
        cat = new JLabel("Category");
        catTxt = new JTextField(10);
        stockTxt = new JTextField(5);
        stock = new JLabel("Stock");

        setPreferredSize(new Dimension(460, 340));
        setLayout(null);

        add(add);
        add(productID);
        add(prodIDTxt);
        add(productName);
        add(prodNameTxt);
        add(price);
        add(priceTxt);
        add(cat);
        add(catTxt);
        add(stockTxt);
        add(stock);

        add.setBounds(190, 250, 110, 25);
        productID.setBounds(75, 40, 100, 25);
        prodIDTxt.setBounds(190, 40, 110, 25);
        productName.setBounds(75, 80, 100, 25);
        prodNameTxt.setBounds(190, 80, 110, 25);
        price.setBounds(75, 120, 100, 25);
        priceTxt.setBounds(190, 120, 110, 25);
        cat.setBounds(75, 160, 100, 25);
        catTxt.setBounds(190, 160, 110, 25);
        stockTxt.setBounds(190, 200, 110, 25);
        stock.setBounds(75, 200, 100, 25);
    }
}

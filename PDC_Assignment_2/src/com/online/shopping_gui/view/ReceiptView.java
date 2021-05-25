package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.model.User;
import com.online.shopping_gui.utilities.ProductFileIO;
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
    private ShoppingCart cart;

    public ReceiptView(ShoppingCart cart, User currentUser) {
        FlatLightLaf.install();
        
        this.cart = cart;
        receipt = new JTextArea(20, 20);
        receipt.setEditable(false);
        receipt.setText(cart.generateInvoice(currentUser)); 
        thank = new JLabel("Thank you for shopping with us!");

        setPreferredSize(new Dimension(284, 382));
        setLayout(null);

        add(receipt);
        add(thank);

        receipt.setBounds(37, 50, 205, 250);
        thank.setBounds(55, 300, 185, 30);
    }

    public static void main(String[] args) {
	User currentUser = new Customer("test1234", "Woohoo10101!");
	ShoppingCart scart = new ShoppingCart(currentUser);
        ProductList plist = ProductFileIO.importProductData();
        scart.addToCart(plist.searchProduct("Apple"), 3);
        scart.addToCart(plist.searchProduct("AOC"), 3);
        JFrame frame = new JFrame("MyPanel");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ReceiptView(scart, currentUser));
        frame.pack();
        frame.setVisible(true);
    }
}
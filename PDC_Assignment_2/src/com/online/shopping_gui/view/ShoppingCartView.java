/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.model.Table;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Amos Foong <18044418>
 */
public class ShoppingCartView extends JPanel
{
    private final String[] COLUMN_HEADERS = new String[]{"Product", "Quantity", "Price"};
    private ShoppingCart cart;
    private Table scTableModel;
    private JTable cartTable;
    private JScrollPane scrollPane;
    private JPanel tablePanel, lblPanel, btnPanel;
    private JLabel itemSelectedLbl;
    private JButton rmvFromCartBtn, continueShopBtn, proceedToChkOutBtn;
    
    public ShoppingCartView(ShoppingCart cart) {
        FlatLightLaf.install();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(900, 500));
        
        this.cart = cart;
        
        // Init table panel.
        tablePanel = new JPanel();
        boolean sorter = true;
        this.scTableModel = new Table(cart.convertShoppingCart(), COLUMN_HEADERS);
        cartTable = new JTable(scTableModel);
        cartTable.setPreferredScrollableViewportSize(new Dimension(700, 400));
        cartTable.setFillsViewportHeight(true);
        cartTable.setAutoCreateRowSorter(sorter);
        scrollPane = new JScrollPane(cartTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tablePanel.add(scrollPane);
        
        // Init label panel to display item selected.
        lblPanel = new JPanel();
        itemSelectedLbl = new JLabel("Item Selected: ");
        lblPanel.add(itemSelectedLbl);
        
        // Init bottom panel to allow user to add an item with quantity specified to cart.
        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setPreferredSize(new Dimension(700, 40));
        rmvFromCartBtn = new JButton("Remove From Cart");
        continueShopBtn = new JButton("Continue Shopping");
        proceedToChkOutBtn = new JButton("Proceed to Checkout");
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(rmvFromCartBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(continueShopBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(proceedToChkOutBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        // Add all panels to the parent container.
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(tablePanel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(lblPanel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(btnPanel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Table getScTableModel() {
        return scTableModel;
    }

    public JTable getCartTable() {
        return cartTable;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JLabel getItemSelectedLbl() {
        return itemSelectedLbl;
    }

    public JButton getRmvFromCartBtn() {
        return rmvFromCartBtn;
    }

    public JButton getContinueShopBtn() {
        return continueShopBtn;
    }

    public JButton getProceedToChkOutBtn() {
        return proceedToChkOutBtn;
    }
    
    public static void main(String[] args) {
        ProductList list = ProductFileIO.importProductData();
        ShoppingCart cart = new ShoppingCart(new Customer("test1234", "Woohoo10101!"));
        cart.addToCart(list.searchProduct("Apple"), 3);
        cart.addToCart(list.searchProduct("AOC"), 3);
        
        JFrame frame = new JFrame("Test Shopping Cart Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ShoppingCartView cartView = new ShoppingCartView(cart); 
        frame.add(cartView);
        frame.pack();
        frame.setMinimumSize(cartView.getPreferredSize()); // Specifies the min size so table's info wont be obscured.
        frame.setVisible(true);
    }
}

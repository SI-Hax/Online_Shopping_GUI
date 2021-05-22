/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.shopping_gui.view;

import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.model.Table;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.awt.Dimension;
import javax.swing.JFrame;
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
    private Table scTableModel;
    private JTable cartTable;
    private ShoppingCart cart;
    private JScrollPane scrollPane;
    
    public ShoppingCartView(ShoppingCart cart) {
        this.cart = cart;
        boolean sorter = true;
        this.scTableModel = new Table(cart.convertShoppingCart(), COLUMN_HEADERS);
        
        cartTable = new JTable(scTableModel);
        cartTable.setPreferredScrollableViewportSize(new Dimension(400, 400));
        cartTable.setFillsViewportHeight(true);
        cartTable.setAutoCreateRowSorter(sorter);

        scrollPane = new JScrollPane(cartTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        this.setPreferredSize(new Dimension(500, 500));
    }
    
    public static void main(String[] args) {
        ProductList list = ProductFileIO.importProductData();
        ShoppingCart cart = new ShoppingCart(new Customer("test1234", "Woohoo10101!"));
        cart.addToCart(list.searchProduct("Apple"), 3);
        cart.addToCart(list.searchProduct("AOC"), 3);
        
        JFrame frame = new JFrame("Test Shopping Cart Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ShoppingCartView(cart));
        frame.pack();
        frame.setVisible(true);
    }
}

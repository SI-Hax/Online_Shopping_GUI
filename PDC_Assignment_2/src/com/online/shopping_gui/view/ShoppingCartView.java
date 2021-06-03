package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.model.Table;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class contains the view to simulate a Shopping Cart.
 * The shopping cart table displays the name of the product,
 * quantity ordered, and its respective cost (cost of product
 * multiplied by the quantity).
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.3
 * @since 22/05/2021
 */
public class ShoppingCartView extends JPanel
{
    private final String[] COLUMN_HEADERS = new String[]{"Product", "Quantity", "Total Cost (NZD $)"};
    public final int PANEL_WIDTH = 900;
    public final int PANEL_HEIGHT = 500;
    private ShoppingCart cart;
    private Table scTableModel;
    private JTable cartTable;
    private JScrollPane scrollPane;
    private JPanel tablePanel, lblPanel, btnPanel;
    private JLabel itemSelectedLbl;
    private JButton rmvFromCartBtn, viewTotalBtn, proceedToChkOutBtn;
    
    public ShoppingCartView(ShoppingCart cart) {
        FlatLightLaf.install();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.WHITE);
        
        this.setCart(cart);
   
        // Init table panel.
        tablePanel = new JPanel();
        tablePanel.setBackground(Color.WHITE);
        
        this.scTableModel = new Table(cart.convertShoppingCart(), COLUMN_HEADERS);
        cartTable = new JTable(scTableModel);
        cartTable.setPreferredScrollableViewportSize(new Dimension(700, 350));
        cartTable.setFillsViewportHeight(true);
        cartTable.setAutoCreateRowSorter(false); // Does not allow sort function of table columns.

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // Prep text alignment renderer.
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Define centre alignment.
        for(int i = 0; i < cartTable.getColumnCount(); i++) { // For each column in the productTable.
            cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); // Set text alignment to centre.
        }
        
        scrollPane = new JScrollPane(cartTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tablePanel.add(scrollPane);
        
        // Init label panel to display item selected.
        lblPanel = new JPanel();
        lblPanel.setBackground(Color.WHITE);
        itemSelectedLbl = new JLabel("Item Selected: ");
        itemSelectedLbl.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
        lblPanel.add(itemSelectedLbl);
        
        // Init bottom panel to allow user to add an item with quantity specified to cart.
        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setPreferredSize(new Dimension(700, 40));
        btnPanel.setBackground(Color.WHITE);
        rmvFromCartBtn = new JButton("Remove From Cart");
        rmvFromCartBtn.setEnabled(false);
        viewTotalBtn = new JButton("View Grand Total");
        proceedToChkOutBtn = new JButton("Proceed to Checkout");
        proceedToChkOutBtn.setEnabled(false);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(rmvFromCartBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(viewTotalBtn);
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

    public String[] getCOLUMN_HEADERS() {
        return COLUMN_HEADERS;
    }
    
    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        if(cart == null) {
            this.cart = new ShoppingCart(null);
        }
        this.cart = cart;
    }
    
    public Table getScTableModel() {
        return scTableModel;
    }

    public void setScTableModel(Table scTableModel) {
        this.scTableModel = scTableModel;
    }

    public JTable getCartTable() {
        return cartTable;
    }

    public void setCartTable(JTable cartTable) {
        this.cartTable = cartTable;
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

    public JButton getViewTotalBtn() {
        return viewTotalBtn;
    }

    public JButton getProceedToChkOutBtn() {
        return proceedToChkOutBtn;
    }

    public void configRmvBtn(boolean value) {
        rmvFromCartBtn.setEnabled(value);
    }
    
    public void configChkOutBtn(boolean value) {
        proceedToChkOutBtn.setEnabled(value);
    }
}

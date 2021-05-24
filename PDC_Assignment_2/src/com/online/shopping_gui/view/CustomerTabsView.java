package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * This class contains the the Tabs Panel for Customer's View.
 * It encompasses several different views (panels).
 * <ol>
 * <li>View Products: Allows a Customer to view all products and 
 *                  its details and add them to a shopping cart.</li>
 * <li>View Cart: Products and quantity the Customer has Ordered</li>
 * </ol>
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 24/05/2021
 */
public class CustomerTabsView extends JPanel implements Observer {
    public final int PANEL_WIDTH = 920;
    public final int PANEL_HEIGHT = 540;
    // TODO: Add a main frame to this Panel.
    private JTabbedPane customerTabs;
    private ProductsView productsView;
    private ShoppingCartView cartView;
    
    public CustomerTabsView(ProductList list, ShoppingCart cart) {
        FlatLightLaf.install();
        this.setLayout(new FlowLayout());
	this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.WHITE);
        
        // Initialise components.
        customerTabs = new JTabbedPane();
        
        productsView = new ProductsView(list);
        customerTabs.add("View Products", productsView);
        
        cartView = new ShoppingCartView(cart);
        customerTabs.add("View Cart", cartView);
        this.add(customerTabs);
    }

    public JTabbedPane getCustomerTabs() {
        return customerTabs;
    }

    public ProductsView getProductsView() {
        return productsView;
    }

    public ShoppingCartView getCartView() {
        return cartView;
    }
    
    public void addController() {
        // TODO: Add controllers to respective views.
    }
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        ProductList plist = ProductFileIO.importProductData();
        ShoppingCart scart = new ShoppingCart(new Customer("test1234", "Woohoo10101!"));
        scart.addToCart(plist.searchProduct("Apple"), 3);
        scart.addToCart(plist.searchProduct("AOC"), 3);
        CustomerTabsView test = new CustomerTabsView(plist, scart);
        
        JFrame frame = new JFrame("Test Customer Tabs");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(test);
        frame.pack();
        frame.setMinimumSize(new Dimension(test.PANEL_WIDTH+10, test.PANEL_HEIGHT+40)); // Specifies the min size so table's info wont be obscured.
        frame.setVisible(true);
    }
}

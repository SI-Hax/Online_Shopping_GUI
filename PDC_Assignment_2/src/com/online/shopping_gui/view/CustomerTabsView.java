package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.controller.CardController;
import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
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
 * @version 2.2.0
 * @since 24/05/2021
 */
public class CustomerTabsView extends JFrame implements Observer {
    public final int PANEL_WIDTH = 920;
    public final int PANEL_HEIGHT = 580;
    private JTabbedPane customerTabs;
    private ProductsView productsView;
    private ShoppingCartView cartView;
    
    public CustomerTabsView(ProductList list, ShoppingCart cart) {
        super("Si-Hax Store");
        FlatLightLaf.install();
        this.setLayout(new FlowLayout());
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(new Point((d.width / 2) - (this.getWidth() / 2), (d.height / 2) - (this.getHeight() / 2)));
        
        // Initialise components.
        customerTabs = new JTabbedPane();
        
        productsView = new ProductsView();
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
    
    public void addController(CardController controller) {
        // TODO: Add controllers to respective views.
        // CustomerTabsView -> View Products
        this.getProductsView().getProductsTableView().getProductTable().getSelectionModel().addListSelectionListener(controller);    
        this.getProductsView().getQtyTxtField().getDocument().addDocumentListener(controller);
        this.getProductsView().getQtyTxtField().addKeyListener(controller);
        this.getProductsView().getAddToCartBtn().addActionListener(controller);
        
        // CustomerTabsView -> View Cart
        this.getCartView().getCartTable().getSelectionModel().addListSelectionListener(controller);
        this.getCartView().getRmvFromCartBtn().addActionListener(controller);
        this.getCartView().getViewTotalBtn().addActionListener(controller);
        this.getCartView().getViewTotalBtn().addKeyListener(controller);
        this.getCartView().getProceedToChkOutBtn().addActionListener(controller);
        this.getCartView().getProceedToChkOutBtn().addKeyListener(controller);
    }
    
    @Override
    public void update(Observable o, Object selection) {
        CardModel cm = (CardModel) selection;
        
        if(cm.isCustLoginFlag()) { // If its customer loggin in....
            this.updateTables(cm.getShoppingCart(), cm.getProductList()); // Refresh tables' ui.
            this.showTabs(cm.getShoppingCart(), cm.getProductList());
        } else if(cm.isAddToCartFlag()) { // If add to cart modifications made...
            this.updateTables(cm.getShoppingCart(), cm.getProductList());
        } else if(cm.isRmvFromCartFlag()) { // If remove from cart modifications made...
            this.updateTables(cm.getShoppingCart(), cm.getProductList());
        }
    }
   
    /**
     * Helper method to launch Window for Customer's shopping.
     * 
     * @param sc : Shopping Cart object.
     * @param pList : Product List.
     */
    public void showTabs(ShoppingCart sc, ProductList pList) {
        productsView.getProductsTableView().getTable().update(pList.convertProductList());
        productsView.getProductsTableView().getProductTable().updateUI();
        productsView.getProductsTableView().updateUI();
        this.setMinimumSize(new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT)); // Specifies the min size so table's info wont be obscured.
        this.setVisible(true); 
        this.setResizable(true);
//        productsView.getProductsTableView().requestFocusInWindow();
    }
    
    /**
     * Helper method to update the item selected label in View Products tab.
     * 
     * @param index : Index of the selected item.
     */
    public void updateSelectedLabelProdV(int index) {
        if(index >= 0) { // If its greater or equal to 0...
            getProductsView().getItemSelectedLbl().setText("Item Selected: " + getProductsView().getProductsTableView().getProductTable().getValueAt(index , 1)); // Set label.   
        } else {
            getProductsView().getItemSelectedLbl().setText("Item Selected:");
        }
    }
    
    /**
     * Helper method to update the item selected label in View Cart tab.
     * 
     * @param index : Index of the selected item.
     */
    public void updateSelectedLabelCartV(int index) {
        if(!getCartView().getCart().isEmpty() && index >= 0) { // If its greater or equal to 0...
            getCartView().getItemSelectedLbl().setText("Item Selected: " + getCartView().getScTableModel().getValueAt(index , 0)); // Set label.   
        } else {
            getCartView().getItemSelectedLbl().setText("Item Selected:");
        }
    }
    
    /**
     * Helper method to notify user of the item that is added to the cart in View Products tab.
     * 
     * @param index : Index of the selected item.
     */
    public void updateAddedToCartLabel(int index) {
        if(index >= 0) { // If its greater or equal to 0...
            getProductsView().getItemSelectedLbl().setText(getProductsView().getQtyTxtField().getText() + " * " + getProductsView().getProductsTableView().getProductTable().getValueAt(index , 1) + " Added To Cart!"); // Notify user item & qty has been added to cart..   
        } else {
            getProductsView().getItemSelectedLbl().setText("Item Selected:");
        }
    }
    
    /**
     * Helper method to notify user of the item that is added to the cart in View Cart tab.
     * 
     * @param index : Index of the selected item.
     */
    public void updateRemoveFromCartLabel(int index) {
        if(!getCartView().getCart().isEmpty() && index >= 0) { // If its greater or equal to 0...
            getCartView().getItemSelectedLbl().setText(getCartView().getScTableModel().getValueAt(index , 0) + " Removed From Cart!"); // Notify user item & qty has been removed from cart..   
        } else {
            getCartView().getItemSelectedLbl().setText("Item Selected:");
        }
    }
    
    /**
     * Helper method to refresh and update the tables' data so user
     * can view information in real-time.
     * @param sc : ShoppingCart object.
     * @param pList : Product List.
     */
    public void updateTables(ShoppingCart sc, ProductList pList) {
        productsView.getProductsTableView().getTable().update(pList.convertProductList());
        productsView.getProductsTableView().getProductTable().updateUI();
        productsView.getProductsTableView().updateUI();
        
        cartView.setCart(sc);
        cartView.getScTableModel().update(sc.convertShoppingCart());
        cartView.getCartTable().updateUI();
        cartView.getScrollPane().updateUI();
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

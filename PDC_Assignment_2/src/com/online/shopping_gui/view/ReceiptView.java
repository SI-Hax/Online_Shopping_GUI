package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.controller.CardController;
import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.model.User;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * Contains fields to collect data from user.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.1.0
 * @since 25/05/2021
 */
public class ReceiptView extends JFrame implements Observer {
    private JPanel mainPanel;
    private JTextArea receipt;
    private JLabel thank;
    private ShoppingCart cart;
    private User currentUser;

    public ReceiptView() {
        super("Receipt");
        FlatLightLaf.install();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initView();
        
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(284, 382));
        mainPanel.setLayout(null);
        
        receipt = new JTextArea(55, 50);
        receipt.setEditable(false);
//        receipt.setText(cart.generateInvoice(currentUser)); 
        thank = new JLabel("Thank you for shopping with us!");

        mainPanel.add(receipt);
        mainPanel.add(thank);

        receipt.setBounds(30, 50, 212, 280);
        thank.setBounds(55, 340, 185, 30);
        
        this.add(mainPanel);
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public void initView() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.WHITE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(new Point((d.width / 2) - (this.getWidth() / 2), (d.height / 2) - (this.getHeight() / 2)));
    }
    
    public void showReceipt() {
        receipt.setText(cart.generateInvoice(currentUser)); 
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
	User currentUser = new Customer("test1234", "Woohoo10101!");
	ShoppingCart scart = new ShoppingCart(currentUser);
        
        ProductList plist = ProductFileIO.importProductData();
        scart.addToCart(plist.searchProduct("Apple"), 3);
        scart.addToCart(plist.searchProduct("AOC"), 3);
        JFrame frame = new JFrame("MyPanel");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(new ReceiptView(scart, currentUser));
        frame.pack();
        frame.setVisible(true);
    }
    
    public void addController(CardController controller) {
        // TODO: Add controllers to respective views.
        
    }

    @Override
    public void update(Observable o, Object selection) {
        CardModel cm = (CardModel) selection;
        
        if(cm.isReceiptFlag()) { // If its customer confirms their check out....
            this.setCart(cm.getShoppingCart()); // Init shopping cart.
            this.setCurrentUser(cm.getUsers().getCurrentUser()); // Init current user.
            this.showReceipt(); // Show frame.
        } 
    }
}
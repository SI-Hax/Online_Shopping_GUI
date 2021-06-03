package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.controller.CardController;
import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.model.User;
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
 * @version 2.1.1
 * @since 25/05/2021
 */
public class ReceiptView extends JFrame implements Observer {
    public final int PANEL_WIDTH = 284;
    public final int PANEL_HEIGHT = 422;
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
        mainPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        mainPanel.setLayout(null);
        
        receipt = new JTextArea(55, 50);
        receipt.setEditable(false);
        thank = new JLabel("Thank you for shopping with us!");
        
        mainPanel.add(receipt);
        mainPanel.add(thank);

        receipt.setBounds(30, 20, 212, 370);
        thank.setBounds(55, 380, 185, 30);
        
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
        this.setLocation(new Point((d.width /4) , (d.height /4)));
        this.setMinimumSize(new Dimension(PANEL_WIDTH+40, this.PANEL_HEIGHT+40));
    }
    
    public void showReceipt() {
        receipt.setText(cart.generateInvoice(currentUser)); 
        this.pack();
        this.setVisible(true);
    }
    
    public void addController(CardController controller) {
        // No controllers to add.        
    }

    @Override
    public void update(Observable o, Object selection) {
        CardModel cm = (CardModel) selection;
        
        if(cm.isReceiptFlag()) { // If its customer confirms their check out....
            this.setCart(cm.getShoppingCart()); // Init shopping cart.
            this.setCurrentUser(cm.getUsers().getCurrentUser()); // Init current user.
            this.showReceipt(); // Show frame.
        } else if(cm.isCustLoginFlag()) { // If a different customer is logged in...
            this.dispose();
            this.setCart(cm.getShoppingCart()); // Re-init shopping cart.
            this.setCurrentUser(cm.getUsers().getCurrentUser()); // Re-init current user.
        }
    }
}
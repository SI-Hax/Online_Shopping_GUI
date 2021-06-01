package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.controller.CardController;
import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.model.User;
import com.online.shopping_gui.model.UserList;
import com.online.shopping_gui.utilities.AdminDBManager;
import com.online.shopping_gui.utilities.CustomerDBManager;
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
public class CheckOutView extends JFrame implements Observer {
    public final String INVALID_CARD = "Please enter a valid card number.";
    private JPanel mainPanel;
    private JLabel total, visa, shipTo, ccv, visaCheckLbl;
    private JTextField totalTxt;
    private JTextArea shipToTxt;
    private JPasswordField visaTxt, ccvTxt;
    private JButton confirm, cancel;
    private ShoppingCart cart;
    private User currentUser;
//    
//    UserList userList = new UserList(CustomerDBManager.importData());
//    protected User currentUser = userList.searchUser("djorange77");

    public CheckOutView() {
        super("Check Out");
        FlatLightLaf.install();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initView();
        
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(284, 382));

        total = new JLabel("Total:");
        totalTxt = new JTextField(20);
        totalTxt.setEditable(false);
//        totalTxt.setText(String.valueOf(String.format("$%.2f", cart.getGrandTotal())));
        shipTo = new JLabel("Ship To:");
        shipToTxt = new JTextArea(100, 100);
//        setShipToTxt(user);
        visa = new JLabel("Card No:");
        visaTxt = new JPasswordField(20);
        visaTxt.setToolTipText("Masked card number.");
        visaCheckLbl = new JLabel("");
        visaCheckLbl.setForeground(Color.RED);
//        setVisaTxt(user);
        ccv = new JLabel("CCV:");
        ccvTxt = new JPasswordField(5);
        confirm = new JButton("Confirm");
        cancel = new JButton("Cancel");

        mainPanel.add(total);
        mainPanel.add(totalTxt);
        mainPanel.add(shipTo);
        mainPanel.add(shipToTxt);
        mainPanel.add(visa);
        mainPanel.add(visaTxt);
        mainPanel.add(visaCheckLbl);
        mainPanel.add(ccv);
        mainPanel.add(ccvTxt);
        mainPanel.add(visaTxt);
        mainPanel.add(confirm);
        mainPanel.add(cancel);

        total.setBounds(25, 40, 100, 25);
        totalTxt.setBounds(95, 40, 165, 25);
        shipTo.setBounds(25, 70, 100, 25);
        shipToTxt.setBounds(95, 75, 165, 150);
        visa.setBounds(25, 235, 100, 25);
        visaTxt.setBounds(95, 235, 165, 25);
        visaCheckLbl.setBounds(95, 260, 165, 15);
        ccv.setBounds(25, 280, 100, 25);
        ccvTxt.setBounds(95, 280, 165, 25);
        confirm.setBounds(30, 315, 100, 25);
        cancel.setBounds(145, 315, 100, 25);
        
        this.add(mainPanel);
    }

    public JTextArea getShipToTxt() {
        return shipToTxt;
    }

    public JPasswordField getVisaTxt() {
        return visaTxt;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public JButton getCancel() {
        return cancel;
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
    
    public void setShipToTxt(User currentUser) {
        if (!((Customer) currentUser).getName().equals("UNKNOWN") && !((Customer) currentUser).getAddress().equals("UNKNOWN")) {
            shipToTxt.setEditable(false);
            shipToTxt.setText(((Customer) currentUser).getName()
                    + "\n" + ((Customer) currentUser).getAddress());
        }
    }

    public void setVisaTxt(User currentUser) {
        if (!((Customer) currentUser).getCardNumber().equals("UNKNOWN")) {
            visaTxt.setEditable(false);
            visaTxt.setText(((Customer) currentUser).getCardNumber());
        }
    }
    
    public void initView() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.WHITE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(new Point((d.width / 2) - (this.getWidth() / 2), (d.height / 2) - (this.getHeight() / 2)));
    }
    
    public void showCheckOut() {
        totalTxt.setText(String.valueOf(String.format("$%.2f", this.cart.getGrandTotal())));
        setShipToTxt(this.currentUser);
        setVisaTxt(this.currentUser);
        this.pack();
        this.setVisible(true);
    }
    
    public void warnVisaCheck() {
        visaCheckLbl.setText(INVALID_CARD);
    }
    
    public void passVisaCheck() {
        visaCheckLbl.setText("");
    }

    public static void main(String[] args) {
        UserList users = new UserList(CustomerDBManager.importData(), AdminDBManager.importData());
        User currentUser = users.searchUser("djorange77");
        ShoppingCart scart = new ShoppingCart(currentUser);
        ProductList plist = ProductFileIO.importProductData();
        scart.addToCart(plist.searchProduct("Apple"), 3);
        scart.addToCart(plist.searchProduct("AOC"), 3);
        JFrame frame = new CheckOutView();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void addController(CardController controller) {
        // TODO: Add controllers to respective views.
        shipToTxt.getDocument().addDocumentListener(controller);
        visaTxt.getDocument().addDocumentListener(controller);
        confirm.addActionListener(controller);
        cancel.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object selection) {
        CardModel cm = (CardModel) selection;
        
        if(cm.isCheckOutFlag()) { // If its customer check out....
            this.setCart(cm.getShoppingCart()); // Init shopping cart.
            this.setCurrentUser(cm.getUsers().getCurrentUser()); // Init current user.
            this.showCheckOut(); // Show frame.
        } else if(cm.isReceiptFlag()) { // If receipt flag is triggered...
            this.dispose(); // Close this window.
        }
    }
}

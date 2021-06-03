package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.controller.CardController;
import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.model.Customer;
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
public class CheckOutView extends JFrame implements Observer {
    public final String INVALID_CARD = "Please enter a valid card no.";
    public final int PANEL_WIDTH = 284;
    public final int PANEL_HEIGHT = 382;
    private JPanel mainPanel;
    private JLabel total, visa, name, address, ccv, visaCheckLbl;
    private JTextField totalTxt, nameTxt;
    private JTextArea addressTxt;
    private JScrollPane scrollPane;
    private JTextField visaTxt;
    private JPasswordField ccvTxt;
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
        mainPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        total = new JLabel("Total:");
        totalTxt = new JTextField(20);
        totalTxt.setEditable(false);
        name = new JLabel("Name:");
        nameTxt = new JTextField(20);
        nameTxt.setEditable(false);
        address = new JLabel("Address:");
        addressTxt = new JTextArea(28, 20);
        scrollPane = new JScrollPane(addressTxt, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        visa = new JLabel("Card No:");
        visaTxt = new JTextField(20);
        visaTxt.setToolTipText("Masked card number.");
        visaCheckLbl = new JLabel("");
        visaCheckLbl.setForeground(Color.RED);
        ccv = new JLabel("CCV:");
        ccvTxt = new JPasswordField(5);
        confirm = new JButton("Confirm");
        confirm.setEnabled(false);
        cancel = new JButton("Cancel");
    
        mainPanel.add(total);
        mainPanel.add(totalTxt);
        mainPanel.add(name);
        mainPanel.add(nameTxt);
        mainPanel.add(address);
        mainPanel.add(scrollPane);
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
        name.setBounds(25, 75, 100, 25);
        nameTxt.setBounds(95, 75, 165, 25);
        address.setBounds(25, 110, 100, 25);
        scrollPane.setBounds(95, 110, 165, 115);
        visa.setBounds(25, 235, 100, 25);
        visaTxt.setBounds(95, 235, 165, 25);
        visaCheckLbl.setBounds(95, 260, 165, 15);
        ccv.setBounds(25, 280, 100, 25);
        ccvTxt.setBounds(95, 280, 165, 25);
        confirm.setBounds(30, 315, 100, 25);
        cancel.setBounds(145, 315, 100, 25);
        
        this.add(mainPanel);
    }

    public JTextField getNameTxt() {
        return nameTxt;
    }

    public JTextArea getAddressTxt() {
        return addressTxt;
    }

    public JTextField getVisaTxt() {
        return visaTxt;
    }

    public JPasswordField getCcvTxt() {
        return ccvTxt;
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
        nameTxt.setText(((Customer) currentUser).getName());
        addressTxt.setText(((Customer) currentUser).getAddress()); // Set the text area.

        if (!((Customer) currentUser).getName().equals("UNKNOWN")) {
            nameTxt.setEditable(false); // If its not unknown, dont allow user to edit.
        } else { // Otherwise...
            nameTxt.setText(""); // Clear field
            nameTxt.setEditable(true); // Let user enter details.
        }
        
        if(!((Customer) currentUser).getAddress().equals("UNKNOWN")) {
            addressTxt.setEditable(false); // If its not unknown, dont allow user to edit.
        } else { // Otherwise...
            addressTxt.setText(""); // Clear field
            addressTxt.setEditable(true); // Let user enter details.
        }
        
    }

    public void setVisaTxt(User currentUser) {
        visaTxt.setText(((Customer) currentUser).getCardNumber().replaceAll("\\w(?=\\w{4})", "*")); // Mask everything except the last 4 digits of the card number.
        if (!((Customer) currentUser).getCardNumber().equals("UNKNOWN")) {
            visaTxt.setEditable(false); // If its not unknown, dont allow user to edit.
        } else {
            visaTxt.setText(""); // Clear field
            visaTxt.setEditable(true); // Let user enter details.
        }
    }
    
    public void initView() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.WHITE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(new Point((d.width / 3) - (this.getWidth() / 2), (d.height / 3) - (this.getHeight() / 2)));
    }
    
    public void showCheckOut() {
        System.out.println("Current User: " + this.currentUser.toString());
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
    
    public void configConfirmBtn(boolean value) {
        confirm.setEnabled(value);
    }
    
    public void addController(CardController controller) {
        // TODO: Add controllers to respective views.
        nameTxt.getDocument().addDocumentListener(controller);
        addressTxt.getDocument().addDocumentListener(controller);
        visaTxt.getDocument().addDocumentListener(controller);
        ccvTxt.getDocument().addDocumentListener(controller);
        ccvTxt.addKeyListener(controller);
        confirm.addActionListener(controller);
        cancel.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object selection) {
        CardModel cm = (CardModel) selection;
        
        if(cm.isCheckOutFlag()) { // If its customer check out....
            this.setCart(cm.getShoppingCart()); // Init shopping cart.
            this.setCurrentUser(cm.getShoppingCart().getUser()); // Init current user.
            this.showCheckOut(); // Show frame.
        } else if(cm.isReceiptFlag()) { // If receipt flag is triggered...
            this.dispose(); // Close this window.
        } else if(cm.isCustLoginFlag()) { // If a different customer is logged in...
            this.dispose();
            this.setCart(cm.getShoppingCart()); // Re-init shopping cart.
            this.setCurrentUser(cm.getShoppingCart().getUser()); // Init current user.
            setShipToTxt(this.currentUser);
            scrollPane.updateUI();
            setVisaTxt(this.currentUser);
        }
    }
}

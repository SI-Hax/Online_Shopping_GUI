package com.online.shopping_gui.controller;

import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.utilities.Utilities;
import com.online.shopping_gui.view.CardView;
import com.online.shopping_gui.view.CustomerTabsView;
import com.online.shopping_gui.view.WelcomeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This class contains the controllers for associated Panels in CardView.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.2.1
 * @since 17/05/2021
 */
public class CardController implements ActionListener, DocumentListener, KeyListener, MouseListener {
    protected WelcomeView welcomeView;
    protected CardView cardView;
    protected CardModel cardModel;
    protected CustomerTabsView customerTabsView;
    
    public CardController() {
        
    }
    
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
       if(source == cardView.getMainMenuView().getLoginBtn()) { // Main Menu Panel -> Login btn.
            cardModel.setMainMenuSelection(1);
        } else if(source == cardView.getMainMenuView().getCreateAccount()) { // Main Menu Panel -> Create account btn
            cardModel.setMainMenuSelection(2);
        } else if(source == cardView.getMainMenuView().getQuit()) { // Main Menu Panel-> Quit btn
            System.exit(0); // TODO: Temporary solution.
        } else if(source == cardView.getLoginView().getOkay()) { // Login -> Login btn
            showCustomerTabs(); // Check login & password, if checks out, show Customer Shopping Tabs.
        } else if(source == cardView.getLoginView().getResetBtn()) { // Login -> Reset btn
            cardView.getLoginView().resetFields(); // Reset fields.
        } else if(source == cardView.getLoginView().getBackBtn()) { // Login -> Back btn
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getCreateAccountView().getCreateAccountBtn()) { // Create Account Panel -> Create Account btn
            createAccount();
        } else if(source == cardView.getCreateAccountView().getBackBtn()) { // Create Account Panel -> Back btn
            cardModel.setMainMenuSelection(0);
        }
    }
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    //DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD 
    @Override
    public void insertUpdate(DocumentEvent e) {
        Object source = e.getDocument();
        
        if(source == cardView.getLoginView().getEnterPass().getDocument()) { // Login -> Password field
            checkLoginCredentialUpdates(); // Enable/disable login button depending on field's content.
        } else if(source == cardView.getCreateAccountView().getLoginIDTxtField().getDocument()) { // Create Account -> Login ID Text field
            checkCreateCredentialUpdates();
            toggleLoginLabel();
        } else if(source == cardView.getCreateAccountView().getPasswordPassField().getDocument()) { // Create Account -> Password field
            checkCreateCredentialUpdates();
            togglePassLabel();
        } else if(source == cardView.getCreateAccountView().getConfirmPassField().getDocument()) { // Create Account -> Confirm Password field
            checkCreateCredentialUpdates();
            toggleConfirmPassLabel();
        }
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
        Object source = e.getDocument();
        
        if(source == cardView.getLoginView().getEnterPass().getDocument()) { // Login -> Password field
            checkLoginCredentialUpdates(); // Enable/disable login button depending on field's content.
        } else if(source == cardView.getCreateAccountView().getLoginIDTxtField().getDocument()) { // Create Account -> Login ID Text field
            checkCreateCredentialUpdates();
            toggleLoginLabel();
        } else if(source == cardView.getCreateAccountView().getPasswordPassField().getDocument()) { // Create Account -> Password field
            checkCreateCredentialUpdates();
            togglePassLabel();
        } else if(source == cardView.getCreateAccountView().getConfirmPassField().getDocument()) { // Create Account -> Confirm Password field
            checkCreateCredentialUpdates();
            toggleConfirmPassLabel();
        }  
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        Object source = e.getDocument();
        
        if(source == cardView.getLoginView().getEnterPass().getDocument()) { // Login -> Password field
            checkLoginCredentialUpdates(); // Enable/disable login button depending on field's content.
        } else if(source == cardView.getCreateAccountView().getLoginIDTxtField().getDocument()) { // Create Account -> Login ID Text field
            checkCreateCredentialUpdates();
            toggleLoginLabel();
        } else if(source == cardView.getCreateAccountView().getPasswordPassField().getDocument()) { // Create Account -> Password field
            checkCreateCredentialUpdates();
            togglePassLabel();
        } else if(source == cardView.getCreateAccountView().getConfirmPassField().getDocument()) { // Create Account -> Confirm Password field
            checkCreateCredentialUpdates();
            toggleConfirmPassLabel();
        }
    }
    //DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
    //KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object source = e.getComponent();
        
        if(source == cardView.getLoginView().getEnterPass()) { // Login View -> Password Field
            if(e.getKeyCode() == KeyEvent.VK_ENTER) { // If enter is pressed in password field...
                showCustomerTabs();  // Check login & password, if checks out, show Customer Shopping Tabs.
            }
        } else if(source == cardView.getCreateAccountView().getConfirmPassField()) { // Create Account View -> Confirm Password Field
            if(e.getKeyCode() == KeyEvent.VK_ENTER) { // If enter is pressed in confirm password field...
                createAccount();  // Create a new Customer account.
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
    //MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getComponent());
        Object source = e.getComponent();
        int index = customerTabsView.getProductsView().getProductsTableView().getProductTable().getSelectedRow();
        System.out.println(index);
        customerTabsView.getProductsView().updateSelectedLabel(index);
 
        
        if(source == customerTabsView.getProductsView().getProductsTableView().getProductTable()) {
            System.out.println("Clicked");
//            int index = customerTabsView.getProductsView().getProductsTableView().getProductTable().getSelectedRow();
            if(index >= 0) {
                customerTabsView.getProductsView().updateSelectedLabel(index);
                String selectedCellValue = customerTabsView.getProductsView().getProductsTableView().getProductTable().getValueAt(customerTabsView.getProductsView().getProductsTableView().getProductTable().getSelectedRow() , 1).toString();
                System.out.println(selectedCellValue);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getComponent());
        Object source = e.getComponent();
        int index = customerTabsView.getProductsView().getProductsTableView().getProductTable().getSelectedRow();
        System.out.println(index);
        customerTabsView.getProductsView().updateSelectedLabel(index);

//        if(source == customerTabsView.getProductsView().getProductsTableView().getProductTable()) {
//            System.out.println("Pressed");
//            int index = customerTabsView.getProductsView().getProductsTableView().getProductTable().getSelectedRow();
//            if(index >= 0) {
//                customerTabsView.getProductsView().updateSelectedLabel(index);
//                String selectedCellValue = customerTabsView.getProductsView().getProductsTableView().getProductTable().getValueAt(customerTabsView.getProductsView().getProductsTableView().getProductTable().getSelectedRow() , 1).toString();
//                System.out.println(selectedCellValue);
//            }
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM

    /**
     * Helper method (for Action Listener/Key Listener) for checking login ID and password
     * in Login View and showing Customer Shopping Window if credentials checks out.
     */
    public void showCustomerTabs() {
        boolean loginSuccess;
        try{
            loginSuccess = cardModel.checkLogin(cardView.getLoginView().getLoginTxt().getText(), new String(cardView.getLoginView().getEnterPass().getPassword()));
            if(loginSuccess) { // If loginID and password match database records...
                customerTabsView.update(cardModel, cardModel); // Pop-up customer shopping window.
            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(welcomeView, ex.getMessage(), ("Error: " + ex.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Helper method (for Action Listener/Key Listener) for creating a Customer Account in 
     * Create Account View and showing appropriate message if credentials checks out or not.
     */
    public void createAccount() {
        String loginID, password, confirmPass, name, phone, email, address, cardNumber, cardHolder;
        boolean createSuccess = false;
        
        loginID = cardView.getCreateAccountView().getLoginIDTxtField().getText().trim();
        password = new String(cardView.getCreateAccountView().getPasswordPassField().getPassword());
        confirmPass = new String(cardView.getCreateAccountView().getConfirmPassField().getPassword());
        name = cardView.getCreateAccountView().getNameTxtField().getText();
        phone = cardView.getCreateAccountView().getPhoneNoTxtField().getText();
        email = cardView.getCreateAccountView().getEmailTxtField().getText();
        address = cardView.getCreateAccountView().getAddressTxtField().getText();
        cardNumber = cardView.getCreateAccountView().getCardNoTxtField().getText();
        cardHolder = cardView.getCreateAccountView().getCardHolderTxtField().getText();
        
        if(password.equals(confirmPass)) { // If password matches confirmed password....
            try {
                createSuccess = cardModel.addCustomer(loginID, password, name, phone, email, address, cardNumber, cardHolder);    
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(welcomeView, ex.getMessage(), ("Error: " + ex.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
            }
        }
       
        if(createSuccess) { // If account has been created...
            // Output welcome message.
            String welcomeMsg = "Welcome " + name + " ! Please log in to begin shopping!";
            JOptionPane.showMessageDialog(welcomeView, welcomeMsg, "Haere Mai", JOptionPane.PLAIN_MESSAGE);
            cardView.getCreateAccountView().resetFields(); // Clear fields.
        } else { // Otherwise...
            // Output error message.
            String errMsg = "Much apologies, system encounted an error during the creation of your account. Please double-check fields and ensure they are valid then try again. Thank you.";
            JOptionPane.showMessageDialog(welcomeView, errMsg, "Sorry", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Helper method (for Document Listener) to check if both fields in
     * Login View are filled so that the login button can be enabled/disabled.
     */
    public void checkLoginCredentialUpdates() {
        boolean value;
        value = (!cardView.getLoginView().getLoginTxt().getText().trim().isEmpty()) &&  // Check if login id is filled in...
                (cardView.getLoginView().getEnterPass().getPassword().length > 0); // Check if password field is filled in...
        cardView.getLoginView().configLoginBtn(value); // Enable/disable login button depending on conditions. 
    }
    
    /**
     * Helper method (for Document Listener) to check if essential fields in
     * Create Account View are filled so that the create button can be enabled/disabled.
     */
    public void checkCreateCredentialUpdates() {
        boolean value;
        value = (!cardView.getCreateAccountView().getLoginIDTxtField().getText().trim().isEmpty()) &&
                (cardView.getCreateAccountView().getPasswordPassField().getPassword().length > 0) &&
                (cardView.getCreateAccountView().getConfirmPassField().getPassword().length > 0); // Check if essential fields are filled in...
        cardView.getCreateAccountView().configCreateBtn(value); // Set login button.
    }
    
    /**
     * Helper method (for Document Listener) to validate 
     * essential fields in Create Account View.
     */
    public void toggleLoginLabel() {
        String loginID = cardView.getCreateAccountView().getLoginIDTxtField().getText().trim();
        
        if(cardModel.getUsers().userExists(loginID)) { // If user-selected login id is existing...
            cardView.getCreateAccountView().warnLoginIDCheck(); // Warn user.
        } else { // Otherwise...
            cardView.getCreateAccountView().passLoginIDCheck(); // Clear label.
        }
    }
    
    /**
     * Helper method (for Document Listener) to validate 
     * essential fields in Create Account View.
     */
    public void togglePassLabel() {
        String password = new String(cardView.getCreateAccountView().getPasswordPassField().getPassword());
        
        if(!Utilities.passIsSecure(password, 8)) { // If user-selected password do not meet requirements...
            cardView.getCreateAccountView().warnPassCheck(); // Warn user.
        } else { // Otherwise...
            cardView.getCreateAccountView().passPassCheck(); // Clear label.
        }
    }
    
    /**
     * Helper method (for Document Listener) to validate 
     * essential fields in Create Account View.
     */
    public void toggleConfirmPassLabel() {
        String password = new String(cardView.getCreateAccountView().getPasswordPassField().getPassword());
        String confirmPass = new String(cardView.getCreateAccountView().getConfirmPassField().getPassword());
        
        if(!password.equals(confirmPass)) { // If passwords do not match...
            cardView.getCreateAccountView().warnConfirmPassCheck(); // Warn user.
        } else { // Otherwise...
            cardView.getCreateAccountView().passConfirmPassCheck(); // Clear label.
        }
    }
    
    public void addView(WelcomeView wv, CardView cv, CustomerTabsView ctv) {
        this.welcomeView = wv;
        this.cardView = cv;
        this.customerTabsView = ctv;
    }
    
    public void addModel(CardModel cm) {
        this.cardModel = cm;
    }
    
    public void initModel(int x) {
        this.cardModel.setMainMenuSelection(x);
    }
}

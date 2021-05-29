package com.online.shopping_gui.controller;

import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.view.CardView;
import com.online.shopping_gui.view.CustomerTabsView;
import com.online.shopping_gui.view.WelcomeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This class contains the controllers for associated Panels in CardView.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.1.1
 * @since 17/05/2021
 */
public class CardController implements ActionListener, DocumentListener, KeyListener {
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
        } else if(source == cardView.getLoginView().getOkay()) { // Login as Customer/Admin -> Login btn
            showCustomerTabs(); // Check login & password, if checks out, show Customer Shopping Tabs.
        } else if(source == cardView.getLoginView().getResetBtn()) { // Login as Customer/Admin -> Reset btn
            cardView.getLoginView().resetFields(); // Reset fields.
        } else if(source == cardView.getLoginView().getBackBtn()) { // Login Panel -> Back btn
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getCreateAccountView().getCreateAccountBtn()) { // Create Account Panel -> Create Account btn
            createAccount();
        } else if(source == cardView.getCreateAccountView().getBackBtn()) { // Create Account Panel -> Back btn
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getCreateAccountView().getCreateAccountBtn()) { // Create Account Panel -> Create Account Btn
            System.out.println("Create Btn Clicked!");
        }
    }
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    //DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD 
    @Override
    public void insertUpdate(DocumentEvent e) {
        Object source = e.getDocument();
        if(source == cardView.getLoginView().getEnterPass().getDocument()) { // Check if login credentials are entered.
            checkLoginCredentialUpdates(); // Enable/disable login button depending on field's content.
        } else if(source == cardView.getCreateAccountView().getConfirmPassField()) {
            
        }
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
        Object source = e.getDocument();
        if(source == cardView.getLoginView().getEnterPass().getDocument()) { // Check if login credentials are entered.
            checkLoginCredentialUpdates(); // Enable/disable login button depending on field's content.
        }   
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        Object source = e.getDocument();
        if(source == cardView.getLoginView().getEnterPass().getDocument()) { // Check if login credentials are entered.
            checkLoginCredentialUpdates(); // Enable/disable login button depending on field's content.
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
        if(source == cardView.getLoginView().getEnterPass()) { // If source is from password field...
            if(e.getKeyCode() == KeyEvent.VK_ENTER) { // If enter is pressed in password field...
                showCustomerTabs();  // Check login & password, if checks out, show Customer Shopping Tabs.
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
    
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
    
    public void createAccount() {
        String loginID, password, name, phone, email, address, cardNumber, cardHolder;
        boolean createSuccess = false;
        
        loginID = cardView.getCreateAccountView().getLoginIDTxtField().getText().trim();
        password = new String(cardView.getCreateAccountView().getConfirmPassField().getPassword());
        name = cardView.getCreateAccountView().getNameTxtField().getText();
        phone = cardView.getCreateAccountView().getPhoneNoTxtField().getText();
        email = cardView.getCreateAccountView().getEmailTxtField().getText();
        address = cardView.getCreateAccountView().getAddressTxtField().getText();
        cardNumber = cardView.getCreateAccountView().getCardNoTxtField().getText();
        cardHolder = cardView.getCreateAccountView().getCardHolderTxtField().getText();
        
        try {
            createSuccess = cardModel.addCustomer(loginID, password, name, phone, email, address, cardNumber, cardHolder);    
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(welcomeView, ex.getMessage(), ("Error: " + ex.getClass().getSimpleName()), JOptionPane.ERROR_MESSAGE);
        }
       
        if(createSuccess) { // If account has been created...
            // Output welcome message.
            String welcomeMsg = "Welcome " + name + " ! Please log in to begin shopping!";
            JOptionPane.showMessageDialog(welcomeView, welcomeMsg, "Haere Mai", JOptionPane.PLAIN_MESSAGE);
        } else {
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
        if(!cardView.getLoginView().getLoginTxt().getText().trim().isEmpty()) { // Check if login id is filled in...
            value = cardView.getLoginView().getEnterPass().getPassword().length > 0; // Check if password field is filled in...
            cardView.getLoginView().configLoginBtn(value); // Enable login button.
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

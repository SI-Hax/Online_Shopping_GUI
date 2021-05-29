package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.controller.CardController;
import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.model.ShoppingCart;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * This class contains the Right Panel for WelcomeView
 * which encompasses several different views (panels). It  
 * also contains a method to set which panel to display.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.1.0
 * @since 17/05/2021
 */
public class CardView extends JPanel implements Observer {
    protected MainMenuView mainMenuView;
    protected LoginView loginView;
    protected CreateAccountView createAccountView;
    protected CustomerTabsView customerTabsView;
    
    public CardView() {
        FlatLightLaf.install();
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(395, 550));
        this.setBackground(Color.WHITE);
        
        this.mainMenuView = new MainMenuView();
        add(mainMenuView, "Main Menu");
        
        this.loginView = new LoginView();
        add(loginView, "Login Menu");
        
        this.createAccountView = new CreateAccountView();
        add(createAccountView, "Create Account");
        
        customerTabsView = new CustomerTabsView(ProductFileIO.importProductData(), new ShoppingCart());
    }
    
    /**
     * Change the active panel according to user's selection.
     * 
     * @param selection : User's selection.
     */
    public void setActivePanel(int selection) {
        CardLayout cl = (CardLayout)(this.getLayout());
        
        switch(selection) {
            case 0:
                cl.show(this, "Main Menu");
                break;
            case 1:
                cl.show(this, "Login Menu");
                break;
            case 2:
                cl.show(this, "Create Account");
                break; 
        }
    }

    public void addController(CardController controller) {
        // Main Menu View
        mainMenuView.getLoginBtn().addActionListener(controller);
        mainMenuView.getCreateAccount().addActionListener(controller);
        mainMenuView.getQuit().addActionListener(controller);
        // Login View.
        loginView.getOkay().addActionListener(controller);
        loginView.getBackBtn().addActionListener(controller);
        loginView.getResetBtn().addActionListener(controller);
        loginView.getEnterPass().getDocument().addDocumentListener(controller);
        loginView.getEnterPass().addKeyListener(controller);
        
        // Create Account View
        createAccountView.getBackBtn().addActionListener(controller);
        createAccountView.getCreateAccountBtn().addActionListener(controller);
        
//        createAccountView.getLoginIDTxtField().getDocument().addDocumentListener(controller);
        createAccountView.getPasswordPassField().getDocument().addDocumentListener(controller);
        createAccountView.getConfirmPassField().getDocument().addDocumentListener(controller);
        createAccountView.getConfirmPassField().addKeyListener(controller);
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public CreateAccountView getCreateAccountView() {
        return createAccountView;
    }
    
    @Override
    public void update(Observable o, Object selection) {
        CardModel cm = (CardModel) selection;
        if(cm.isCardFlag()) {
            setActivePanel(cm.getMainMenuSelection());
        } else if (cm.isCustLoginFlag()) {
            loginView.resetFields();
        }
    }
}

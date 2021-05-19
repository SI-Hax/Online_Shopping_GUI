package com.online.shopping_gui.view;

import com.online.shopping_gui.controller.CardController;
import java.awt.CardLayout;
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
 * @version 2.0.1
 * @since 17/05/2021
 */
public class CardView extends JPanel implements Observer {
    protected MainMenuView mainMenuView;
    protected LoginView loginView;
    protected CreateAccountView createAccountView;
    
    public CardView() {
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(400, 560));
        
        this.mainMenuView = new MainMenuView();
        add(mainMenuView, "Main Menu");
        
        this.loginView = new LoginView();
        add(loginView, "Login Menu");
        
        this.createAccountView = new CreateAccountView();
        add(createAccountView, "Create Account");
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
        //need a controller before adding it as a listener 
        mainMenuView.getCustLogin().addActionListener(controller);
        mainMenuView.getAdminLogin().addActionListener(controller);
        mainMenuView.getCreateAccount().addActionListener(controller);
        mainMenuView.getQuit().addActionListener(controller);
        loginView.getBackBtn().addActionListener(controller);
        createAccountView.getBackBtn().addActionListener(controller);
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
    public void update(Observable cardModel, Object selection) {
        setActivePanel((int) selection);
    }
}

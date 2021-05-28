package com.online.shopping_gui.controller;

import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.view.CardView;
import com.online.shopping_gui.view.CreateAccountView;
import com.online.shopping_gui.view.WelcomeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains the controllers for associated Panels in CardView.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.2
 * @since 17/05/2021
 */
public class CardController implements ActionListener {
    protected WelcomeView welcomeView;
    protected CardView cardView;
    protected CardModel cardModel;
    
    public CardController() {
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == cardView.getMainMenuView().getCustLogin()) { // Main Menu Panel -> Customer login btn.
            cardModel.setMainMenuSelection(1); 
        } else if(source == cardView.getMainMenuView().getAdminLogin()) { // Main Menu Panel -> Admin login btn.
            cardModel.setMainMenuSelection(1);
        } else if(source == cardView.getMainMenuView().getCreateAccount()) { // Main Menu Panel -> Create account btn
            cardModel.setMainMenuSelection(2);
        } else if(source == cardView.getMainMenuView().getQuit()) { // Main Menu Panel-> Quit btn
            // TODO: Temporary solution.
            System.exit(0);
        } else if(source == cardView.getLoginView().getBackBtn()) { // Login Panel -> Back btn
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getLoginView().getResetBtn()) { // Login Panel -> Reset Btn
            cardView.getLoginView().reset();
        } else if(source == cardView.getCreateAccountView().getBackBtn()) { // Create Account Panel -> Back btn
            cardModel.setMainMenuSelection(0);
        }
    }
    
    public void addView(WelcomeView wv, CardView cv) {
        this.welcomeView = wv;
        this.cardView = cv;
    }
    
    public void addModel(CardModel cm) {
        this.cardModel = cm;
    }
    
    public void initModel(int x) {
        this.cardModel.setMainMenuSelection(x);
    }
}

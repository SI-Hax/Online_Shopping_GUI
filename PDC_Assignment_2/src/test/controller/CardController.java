package test.controller;

import test.model.Administrator;
import test.view.CardModel;
import test.view.CardView;
import test.view.WelcomeView;
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
        
        if(source == cardView.getMainMenuView().getCustLogin()) {
            // Go to Login menu
            cardModel.setMainMenuSelection(1); 
        } else if(source == cardView.getMainMenuView().getAdminLogin()) {
            // Go to Login menu
            cardModel.setMainMenuSelection(1);
        } else if(source == cardView.getMainMenuView().getCreateAccount()) {
            cardModel.setMainMenuSelection(2);
        } else if(source == cardView.getCreateAccountChoiceView().getCreateCustomerAccount()) {
            // Go to Create Customer Panel
            cardModel.setMainMenuSelection(3);
        } else if(source == cardView.getCreateAccountChoiceView().getCreateAdminAccount()) {
            // Go to Create Admin Panel
            cardModel.setMainMenuSelection(4);
        } else if(source == cardView.getCreateAccountChoiceView().getBackBtn()) {
            // Go to Main Menu
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getMainMenuView().getQuit()) {
            // TODO: Temporary solution.
            System.exit(0);
        } else if(source == cardView.getLoginView().getBackBtn()) {
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getCreatCustomerAccountView().getCreateAccountBtn()) {
            System.out.println("TODO");
            // TODO Customer Database
        } else if(source == cardView.getCreatCustomerAccountView().getResetBtn()) {
            // Reset All Fields
            cardView.getCreatCustomerAccountView().reset();
        } else if(source == cardView.getCreatCustomerAccountView().getBackBtn()) {
            // Go to Create Account Choice Menu
            cardModel.setMainMenuSelection(2);
        } else if(source == cardView.getCreatAdminAccountView().getCreateAccountBtn()) {
            // Temporary Method
            Administrator.establishConnection();
            Administrator.insertData(cardView);
        } else if(source == cardView.getCreatAdminAccountView().getResetBtn()) {
            // Reset All Fields
            cardView.getCreatAdminAccountView().reset();
        } else if(source == cardView.getCreatAdminAccountView().getBackBtn()) {
            // Go to Create Account Choice Menu
            cardModel.setMainMenuSelection(2);
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

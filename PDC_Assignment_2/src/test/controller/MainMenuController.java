package test.controller;

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
public class MainMenuController implements ActionListener {
    protected WelcomeView welcomeView;
    protected CardView cardView;
    protected CardModel cardModel;
    
    public MainMenuController() {
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == cardView.getMainMenuView().getCustLogin()) {
            cardModel.setMainMenuSelection(1);
        } else if(source == cardView.getMainMenuView().getAdminLogin()) {
            cardModel.setMainMenuSelection(1);
        } else if(source == cardView.getMainMenuView().getCreateAccount()) {
            cardModel.setMainMenuSelection(2);
        } else if(source == cardView.getMainMenuView().getQuit()) {
            // TODO: Temporary solution.
            System.exit(0);
        } else if(source == cardView.getLoginView().getBackBtn()) {
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getCreateAccountView().getBackBtn()) {
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

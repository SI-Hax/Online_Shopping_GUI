package test.view;

import test.controller.CardController;

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
    protected LoginAdminView loginAdminView;
    protected LoginCustomerView loginCustomerView;
    protected CreatAccountChoiceView createAccountChoiceView;
    protected CreatCustomerAccountView creatCustomerAccountView;
    protected CreatAdminAccountView creatAdminAccountView;


    public CardView() {
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(400, 560));
        
        this.mainMenuView = new MainMenuView();
        add(mainMenuView, "Main Menu");

        this.loginCustomerView = new LoginCustomerView();
        add(loginCustomerView, "Login Customer Menu");

        this.loginAdminView = new LoginAdminView();
        add(loginAdminView, "Login Admin Menu");

        this.createAccountChoiceView = new CreatAccountChoiceView();
        add(createAccountChoiceView, "Create Account Choice Menu");

        this.creatCustomerAccountView = new CreatCustomerAccountView();
        add(creatCustomerAccountView, "Create Customer Account");

        this.creatAdminAccountView = new CreatAdminAccountView();
        add(creatAdminAccountView, "Create Admin Account");
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
                cl.show(this, "Login Admin Menu");
                break;
            case 2:
                cl.show(this, "Login Customer Menu");
                break;
            case 3:
                cl.show(this,"Create Account Choice Menu");
                break;
            case 4:
                cl.show(this,"Create Customer Account");
                break;
            case 5:
                cl.show(this,"Create Admin Account");
                break;
        }
    }

    public void addController(CardController controller) {
        // Need a controller before adding it as a listener

        // Main Menu View
        mainMenuView.getCustLogin().addActionListener(controller);
        mainMenuView.getAdminLogin().addActionListener(controller);
        mainMenuView.getCreateAccount().addActionListener(controller);
        mainMenuView.getQuit().addActionListener(controller);

        // Login Customer View
        loginCustomerView.getLogin().addActionListener(controller);
        loginCustomerView.getResetBtn().addActionListener(controller);
        loginCustomerView.getBackBtn().addActionListener(controller);
        loginCustomerView.getLoginTxt().addActionListener(controller);
        loginCustomerView.getEnterPass().addActionListener(controller);

        // Login Admin View
        loginAdminView.getLogin().addActionListener(controller);
        loginAdminView.getResetBtn().addActionListener(controller);
        loginAdminView.getBackBtn().addActionListener(controller);
        loginAdminView.getLoginTxt().addActionListener(controller);
        loginAdminView.getEnterPass().addActionListener(controller);

        // Create Account Choice View
        createAccountChoiceView.getCreateCustomerAccount().addActionListener(controller);
        createAccountChoiceView.getCreateAdminAccount().addActionListener(controller);
        createAccountChoiceView.getBackBtn().addActionListener(controller);

        // Create Customer Account View
        creatCustomerAccountView.getCreateAccountBtn().addActionListener(controller);
        creatCustomerAccountView.getResetBtn().addActionListener(controller);
        creatCustomerAccountView.getBackBtn().addActionListener(controller);

        // Create Admin Account View
        creatAdminAccountView.getCreateAccountBtn().addActionListener(controller);
        creatAdminAccountView.getResetBtn().addActionListener(controller);
        creatAdminAccountView.getBackBtn().addActionListener(controller);
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    public LoginCustomerView getLoginCustomerView() {
        return loginCustomerView;
    }

    public LoginAdminView getLoginAdminView() {
        return loginAdminView;
    }

    public CreatAccountChoiceView getCreateAccountChoiceView() {
        return createAccountChoiceView;
    }

    public CreatCustomerAccountView getCreatCustomerAccountView() {
        return creatCustomerAccountView;
    }

    public CreatAdminAccountView getCreatAdminAccountView() {
        return creatAdminAccountView;
    }

    @Override
    public void update(Observable cardModel, Object selection) {
        setActivePanel((int) selection);
    }
}

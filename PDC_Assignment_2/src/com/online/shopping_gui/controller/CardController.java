package com.online.shopping_gui.controller;

import com.online.shopping_gui.model.Administrator;
import com.online.shopping_gui.model.Customer;
import com.online.shopping_gui.view.CardModel;
import com.online.shopping_gui.view.CardView;
import com.online.shopping_gui.view.WelcomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
            // Go to Login Customer menu
            cardModel.setMainMenuSelection(2);
        } else if(source == cardView.getMainMenuView().getAdminLogin()) {
            // Go to Login Admin menu
            cardModel.setMainMenuSelection(1);
        } else if(source == cardView.getMainMenuView().getCreateAccount()) {
            // Go to Create Account Choice Menu
            cardModel.setMainMenuSelection(3);
        } else if(source == cardView.getCreateAccountChoiceView().getCreateCustomerAccount()) {
            // Go to Create Customer Panel
            cardModel.setMainMenuSelection(4);
        } else if(source == cardView.getCreateAccountChoiceView().getCreateAdminAccount()) {
            // Go to Create Admin Panel
            cardModel.setMainMenuSelection(5);
        } else if(source == cardView.getCreateAccountChoiceView().getBackBtn()) {
            // Go to Main Menu
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getMainMenuView().getQuit()) {
            // TODO: Temporary solution.
            System.exit(0);
        } else if(source == cardView.getLoginAdminView().getBackBtn()) {
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getLoginAdminView().getLogin()) {
            if (cardView.getLoginAdminView().getEnterPass().getPassword().length != 0 && !cardView.getLoginAdminView().getLoginTxt().getText().isEmpty()) {
                // Temp Method
                String loginID = cardView.getLoginAdminView().getLoginTxt().getText();
                String password = String.valueOf(cardView.getLoginAdminView().getEnterPass().getPassword());
                try {
                    if (Administrator.isTableExist()) {
                        Administrator.createTable();
                    }
                    if (Administrator.loginCheck(loginID, password))
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                    else
                        JOptionPane.showMessageDialog(null, "Password or Login ID is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else
                JOptionPane.showMessageDialog(null, "Please fill both fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(source == cardView.getLoginAdminView().getResetBtn()) {
            // Reset All Fields
            cardView.getLoginAdminView().reset();
        } else if(source == cardView.getLoginCustomerView().getBackBtn()) {
            cardModel.setMainMenuSelection(0);
        } else if(source == cardView.getLoginCustomerView().getLogin()) {
            if (cardView.getLoginCustomerView().getEnterPass().getPassword().length != 0 && !cardView.getLoginCustomerView().getLoginTxt().getText().isEmpty()) {
                // Temp Method
                String loginID = cardView.getLoginCustomerView().getLoginTxt().getText();
                String password = String.valueOf(cardView.getLoginCustomerView().getEnterPass().getPassword());
                try {
                    if (Customer.isTableExist()) {
                        Customer.createTable();
                    }
                    if (Customer.loginCheck(loginID, password))
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                    else
                        JOptionPane.showMessageDialog(null, "Password or Login ID is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else
                JOptionPane.showMessageDialog(null, "Please fill both fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(source == cardView.getLoginCustomerView().getResetBtn()) {
            // Reset All Fields
            cardView.getLoginCustomerView().reset();
        } else if(source == cardView.getCreatCustomerAccountView().getCreateAccountBtn()) {
            // Temporary Method
            try {
                if (Customer.isTableExist()) {
                    Customer.createTable();
                }
                Customer.insertData(cardView);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if(source == cardView.getCreatCustomerAccountView().getResetBtn()) {
            // Reset All Fields
            cardView.getCreatCustomerAccountView().reset();
        } else if(source == cardView.getCreatCustomerAccountView().getBackBtn()) {
            // Go to Create Account Choice Menu
            cardModel.setMainMenuSelection(3);
        } else if(source == cardView.getCreatAdminAccountView().getCreateAccountBtn()) {
            // Temporary Method
            try {
                if (Administrator.isTableExist()) {
                    Administrator.createTable();
                }
                Administrator.insertData(cardView);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if(source == cardView.getCreatAdminAccountView().getResetBtn()) {
            // Reset All Fields
            cardView.getCreatAdminAccountView().reset();
        } else if(source == cardView.getCreatAdminAccountView().getBackBtn()) {
            // Go to Create Account Choice Menu
            cardModel.setMainMenuSelection(3);
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

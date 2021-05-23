package com.online.shopping_gui.controller;

import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.view.*;

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
public class AdminDashboardLeftPanelCardController implements ActionListener {
    protected AdminDashboardView adminDashboardView;
    protected AdminLeftPanelCardView adminCardView;
    protected CardModel cardModel;

    public AdminDashboardLeftPanelCardController() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == adminCardView.getAdminLeftPanel().getProductsBtn()) {
            cardModel.setMainMenuSelection(0);
            System.out.println("Clicked 1!");
        } else if (source == adminCardView.getAdminLeftPanel().getAddProductBtn()) {
            System.out.println("Clicked 2!");
        }
    }

    public void addView(AdminDashboardView adminDashboardView, AdminLeftPanelCardView adminCardView) {
        this.adminDashboardView = adminDashboardView;
        this.adminCardView = adminCardView;
    }

    public void addModel(CardModel cm) {
        this.cardModel = cm;
    }

    public void initModel(int x) {
        this.cardModel.setMainMenuSelection(x);
    }
}

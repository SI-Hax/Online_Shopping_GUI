package com.online.shopping_gui.controller;

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
public class AdminDashboardRightPanelCardController implements ActionListener {
    protected AdminDashboardView adminDashboardView;
    protected AdminRightPanelCardView adminRightPanelCardView;
    protected CardModel cardModel;

    public AdminDashboardRightPanelCardController() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        /*if (source == adminCardView.getAdminProductView().getTest()) {
            System.out.println("Clicked!");
        }*/
    }

    public void addView(AdminDashboardView adminDashboardView, AdminRightPanelCardView adminRightPanelCardView) {
        this.adminDashboardView = adminDashboardView;
        this.adminRightPanelCardView = adminRightPanelCardView;
    }

    public void addModel(CardModel cm) {
        this.cardModel = cm;
    }

    public void initModel(int x) {
        this.cardModel.setMainMenuSelection(x);
    }
}

package com.online.shopping_gui.view;

import com.online.shopping_gui.controller.AdminDashboardRightPanelCardController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Miguel Emmara - 18022146
 */
public class AdminRightPanelCardView extends JPanel implements Observer {
    protected AdminProductView adminProductView;

    public AdminRightPanelCardView() {
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(400, 560));

        this.adminProductView = new AdminProductView();
        add(adminProductView, "Product");
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
                cl.show(this, "Product");
                break;
            case 1:
                cl.show(this, "Test");
                break;
        }
    }

    public void addController(AdminDashboardRightPanelCardController adminDashboardController) {
    }


    public AdminProductView getAdminProductView() {
        return adminProductView;
    }

    @Override
    public void update(Observable cardModel, Object selection) {
        setActivePanel((int) selection);
    }


}

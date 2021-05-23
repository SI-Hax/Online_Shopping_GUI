package com.online.shopping_gui.view;

import com.online.shopping_gui.controller.AdminDashboardLeftPanelCardController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Miguel Emmara - 18022146
 */
public class AdminLeftPanelCardView extends JPanel implements Observer {
    protected AdminLeftPanel adminLeftPanel;
    protected AdminProductView adminProductView = new AdminProductView();

    public AdminLeftPanelCardView() {
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(400, 560));

        this.adminLeftPanel = new AdminLeftPanel();
        add(adminLeftPanel, "Product");
        add(adminLeftPanel,"Add Product");
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
                cl.show(this, "Add Product");
                break;
        }
    }

    public void addController(AdminDashboardLeftPanelCardController adminDashboardController) {
        adminLeftPanel.getProductsBtn().addActionListener(adminDashboardController);
        adminLeftPanel.getAddProductBtn().addActionListener(adminDashboardController);
    }

    public AdminLeftPanel getAdminLeftPanel() {
        return adminLeftPanel;
    }

    @Override
    public void update(Observable cardModel, Object selection) {
        setActivePanel((int) selection);
    }


}

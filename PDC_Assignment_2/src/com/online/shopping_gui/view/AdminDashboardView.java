package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.controller.AdminDashboardLeftPanelCardController;
import com.online.shopping_gui.controller.AdminDashboardRightPanelCardController;
import com.online.shopping_gui.model.CardModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Miguel Emmara - 18022146
 */
public class AdminDashboardView extends JFrame {
    private JPanel leftPanel, rightPanel;

    public AdminDashboardView() {
        FlatLightLaf.install();
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);

        initLeftPanel();
        initRightPanel();

        // Get the size of the screen
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        this.setSize(900, 600);

        // Calculate the frame location
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;

        this.setLocation(x,y);
        this.setTitle("Admin Dashboard");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }

    public void initLeftPanel() {
        CardModel cardModel = new CardModel();
        AdminLeftPanelCardView adminCardView = new AdminLeftPanelCardView();
        cardModel.addObserver(adminCardView);

        AdminDashboardLeftPanelCardController adminDashboardController = new AdminDashboardLeftPanelCardController();
        // Pass the reference of model and view to the controller
        adminDashboardController.addModel(cardModel);
        adminDashboardController.addView(this, adminCardView);
        adminDashboardController.initModel(0);
        adminCardView.addController(adminDashboardController);

        leftPanel = adminCardView;
        leftPanel.setBounds(0, 0, 150, 600);
        this.add(leftPanel);
    }

    public void initRightPanel() {
        CardModel cardModel = new CardModel();
        AdminRightPanelCardView adminRightPanelCardView = new AdminRightPanelCardView();
        cardModel.addObserver(adminRightPanelCardView);

        AdminDashboardRightPanelCardController adminDashboardRightPanelCardController = new AdminDashboardRightPanelCardController();
        // Pass the reference of model and view to the controller
        adminDashboardRightPanelCardController.addModel(cardModel);
        adminDashboardRightPanelCardController.addView(this, adminRightPanelCardView);
        adminDashboardRightPanelCardController.initModel(0);
        adminRightPanelCardView.addController(adminDashboardRightPanelCardController);

        rightPanel = adminRightPanelCardView;
        rightPanel.setBounds(400, 0, 500, 560);
        this.add(rightPanel);
    }

    public static void main(String[] args) {
        new AdminDashboardView();
    }
}

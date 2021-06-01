package com.online.shopping_gui.view;

import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.controller.CardController;
import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.model.ShoppingCart;

import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Main driver class for the Online Shopping System.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.5
 * @since 15/05/2021
 */
public class WelcomeView extends JFrame {

    private JPanel leftPanel, rightPanel;
    
    public WelcomeView() {
        FlatLightLaf.install();
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        
        initLeftPanel();
        initRightPanel();
    }
    
    public void initLeftPanel() {
        leftPanel = new ImageShopAndRun();
        this.add(leftPanel);
    }
    
    /**
     * Initialises the right side panel. It links a Model, View, and Controller
     * together.
     */
    public void initRightPanel() {
        CardModel cardModel = new CardModel();
        CardView cardView = new CardView();
        CustomerTabsView custTabsView = new CustomerTabsView(cardModel.getProductList(), new ShoppingCart());
        CheckOutView checkOutView = new CheckOutView();
        ReceiptView receiptView = new ReceiptView();
        
        cardModel.addObserver(cardView);
        cardModel.addObserver(custTabsView);
        cardModel.addObserver(checkOutView);
        cardModel.addObserver(receiptView);
        
        CardController cardController = new CardController();
        //pass the reference of model and view to the controller
        cardController.addModel(cardModel);
        cardController.addView(this, cardView, custTabsView, checkOutView, receiptView);
        cardController.initModel(0);
        cardView.addController(cardController);
        custTabsView.addController(cardController);
        checkOutView.addController(cardController);
        receiptView.addController(cardController);
        
        rightPanel = cardView;
//        rightPanel.setBounds(475, 0, 400, 560);
        this.add(rightPanel);
    }
    
    /**
     * Entry point of the program.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeView hi = new WelcomeView();
            // Get the size of the screen
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            hi.setSize(900, 600);
            // Calculate the frame location
            int x1 = (screenSize.width - hi.getWidth()) / 2;
            int y1 = (screenSize.height - hi.getHeight()) / 2;
            hi.setLocation(x1, y1);
            hi.setTitle("Si Hax Store");
            hi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            hi.pack();
            hi.setVisible(true);
            hi.setResizable(false);
        });
//        WelcomeView hi = new WelcomeView();
//        // Get the size of the screen
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = kit.getScreenSize();
//
//        hi.setSize(900, 600);
//
//        // Calculate the frame location
//        int x = (screenSize.width - hi.getWidth()) / 2;
//        int y = (screenSize.height - hi.getHeight()) / 2;
//
//        hi.setLocation(x,y);
//        hi.setTitle("Si Hax Store");
//        hi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        hi.pack();
//        hi.setVisible(true);
//        hi.setResizable(false);
    }
}

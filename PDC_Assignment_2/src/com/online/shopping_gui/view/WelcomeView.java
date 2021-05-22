package com.online.shopping_gui.view;

import com.online.shopping_gui.model.CardModel;
import com.online.shopping_gui.controller.CardController;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

/**
 * Main driver class for the Online Shopping System.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.4
 * @since 15/05/2021
 */
public class WelcomeView extends JFrame {

    private JPanel leftPanel, rightPanel;

    public WelcomeView() {
        FlatLightLaf.install();
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        
        initLeftPanel();

        initRightPanel();
    }
    
    public void initLeftPanel() {
        leftPanel = new ImageShopAndRun();
        leftPanel.setAlignmentX(SwingConstants.CENTER);
        leftPanel.setAlignmentY(SwingConstants.CENTER);
        leftPanel.setBounds(50, 200, 400, 116);
        this.add(leftPanel);
    }
    
    /**
     * Initialises the right side panel. It links a Model, View, and Controller
     * together.
     */
    public void initRightPanel() {
        CardModel cardModel = new CardModel();
        CardView cardView = new CardView();
        cardModel.addObserver(cardView);
        
        CardController mainMenuController = new CardController();
        //pass the reference of model and view to the controllor
        mainMenuController.addModel(cardModel);
        mainMenuController.addView(this, cardView);
        mainMenuController.initModel(0);
        cardView.addController(mainMenuController);
        
        rightPanel = cardView;
        rightPanel.setBounds(475, 0, 400, 560);
        this.add(rightPanel);
    }

    /**
     * Entry point of the program.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        WelcomeView hi = new WelcomeView();
        // Get the size of the screen
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        hi.setSize(900, 600);

        // Calculate the frame location
        int x = (screenSize.width - hi.getWidth()) / 2;
        int y = (screenSize.height - hi.getHeight()) / 2;

        hi.setLocation(x,y);
        hi.setTitle("Si Hax Store");
        hi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hi.pack();
        hi.setVisible(true);
        hi.setResizable(false);
    }
}

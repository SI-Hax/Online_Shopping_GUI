package com.online.shopping_gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the icon for the GUI
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.2
 * @since 15/05/2021
 */
public class ImageShopAndRun extends JPanel {
    public final int PANEL_WIDTH = 460;
    public final int PANEL_HEIGHT = 530;
    public final String imagePath = "./Resources/images/shop-and-run-v-w.png";
    private DrawingPanel drawPanel;
    public ImageIcon icon;
    
    public ImageShopAndRun(){
        icon = new ImageIcon(imagePath);
        drawPanel = new DrawingPanel();
        add(drawPanel);
    }
    
    private class DrawingPanel extends JPanel
    {
        public DrawingPanel() {
            setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBackground(Color.WHITE);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Image i = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            icon.setImage(i);
            icon.paintIcon(this, g, 0, 0);
        }
    }
}

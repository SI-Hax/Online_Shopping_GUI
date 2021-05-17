package com.online.shopping_gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the image for the GUI
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 1.0
 * @since 15/03/2021
 *
 */

public class ImageShopAndRun extends JPanel {
    public Image image;
    
    public ImageShopAndRun(){
        this.image = new ImageIcon("./Resources/images/shop-and-run.jpg").getImage();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }
}

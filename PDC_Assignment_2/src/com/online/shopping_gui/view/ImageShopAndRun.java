package com.online.shopping_gui.view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageShopAndRun extends JPanel {
    public Image image;
    
    public ImageShopAndRun(){
        this.image = new ImageIcon("./resources/shop-and-run.jpg").getImage();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }
}

package test.view;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the image for the GUI
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 15/05/2021
 */
public class ImageShopAndRun extends JPanel {
    public Image image;

    public ImageShopAndRun(){
        this.image = new ImageIcon("./PDC_Assignment_2/Resources/images/shop-and-run.jpg").getImage();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }
}

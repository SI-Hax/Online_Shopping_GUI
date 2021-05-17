package com.online.shopping_gui.view;

import java.util.Observable;

/**
 * This class contains the Login GUI
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 17/05/2021
 */
public class CardModel extends Observable {
    protected int mainMenuSelection;
    
    public CardModel() {
        
    }
    
    public void setMainMenuSelection(int selection){
        this.mainMenuSelection = selection;
        setChanged();
        notifyObservers(mainMenuSelection);
    }
}

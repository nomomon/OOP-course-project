package src.code.model.data;

import java.io.Serializable;

/**
 * Stores the data that needs to be saved with the serializable
 * interface
 * 
 * @author Roos Spierings (s)
 */
public class DataStorage implements Serializable {

    // Player data
    public int playerWorldX;
    public int playerWorldY;
    public int playerLife;
    public boolean hasAxe;
    public boolean hasCarrot;
    public boolean hasBowl;
    public boolean hasMeat;
    public boolean isEnglishSelected;
}

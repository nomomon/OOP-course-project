package src.code.model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import src.code.view.GamePanel;

/**
 * Implements saving and loading of the game
 * 
 * @author Roos Spierings (s)
 */
public class SaveLoad {
    GamePanel gp;
    public File saveFile;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        saveFile = new File("save.dat");
    }

    public void saveGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));

            DataStorage ds = new DataStorage();

            ds.playerWorldX = gp.player.worldX;
            ds.playerWorldY = gp.player.worldY;
            ds.playerLife = gp.player.life;
            ds.hasAxe = gp.player.hasAxe;
            ds.hasCarrot = gp.player.hasCarrot;
            ds.hasBowl = gp.player.hasBowl;
            ds.hasMeat = gp.player.hasMeat;
            ds.isEnglishSelected = (gp.languageSelected == "english");

            // Write the object to the file
            oos.writeObject(ds);

            oos.close();
        } catch (Exception e) {
            System.out.println("Error saving game");
            e.printStackTrace();
        }
    }

    public void loadGame() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));

            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.worldX = ds.playerWorldX;
            gp.player.worldY = ds.playerWorldY;
            gp.player.life = ds.playerLife;
            gp.player.hasAxe = ds.hasAxe;
            gp.player.hasCarrot = ds.hasCarrot;
            gp.player.hasBowl = ds.hasBowl;
            gp.player.hasMeat = ds.hasMeat;
            gp.languageSelected = ds.isEnglishSelected ? "english" : "russian";

            ois.close();

        } catch (Exception e) {
            System.out.println("Error loading game");
            e.printStackTrace();
        }
    }
}

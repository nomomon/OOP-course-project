package src.code.model.object;

import javax.imageio.ImageIO;

/**
 * @author Roos Spierings (s)
 */
public class OBJ_Chest extends SuperObject {

    public OBJ_Chest() {
        name = "Chest";

        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/src/res/object/chest.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image: " + name);
            e.printStackTrace();
        }

        collision = true;
    }
}
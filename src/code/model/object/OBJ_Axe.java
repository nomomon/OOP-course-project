package src.code.model.object;

import javax.imageio.ImageIO;

/**
 * @author Roos Spierings (s)
 */
public class OBJ_Axe extends SuperObject {

    public OBJ_Axe() {
        name = "Axe";

        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/src/res/object/axe.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image: " + name);
            e.printStackTrace();
        }
    }
}
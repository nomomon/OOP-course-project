package src.code.model.object;

import javax.imageio.ImageIO;

/**
 * @author Roos Spierings (s)
 */
public class OBJ_Meat extends SuperObject {

    public OBJ_Meat() {
        name = "Meat";

        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/src/res/object/meat.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image: " + name);
            e.printStackTrace();
        }
    }
}
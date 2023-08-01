package src.code.model.object;

import javax.imageio.ImageIO;

/**
 * @author Roos Spierings (s)
 */
public class OBJ_Carrot extends SuperObject {

    public OBJ_Carrot() {
        name = "Carrot";

        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/src/res/object/carrot.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image: " + name);
            e.printStackTrace();
        }
    }
}
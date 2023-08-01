package src.code.model.object;

import javax.imageio.ImageIO;

/**
 * @author Roos Spierings (s)
 */
public class OBJ_Heart extends SuperObject {

    public OBJ_Heart() {
        name = "Heart";

        try {
            image3 = ImageIO.read(getClass().getResourceAsStream("/src/res/object/heart_blank.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/src/res/object/heart_half.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("/src/res/object/heart_full.png"));

        } catch (Exception e) {
            System.out.println("Failed to load image: " + name);
            e.printStackTrace();
        }
    }

}

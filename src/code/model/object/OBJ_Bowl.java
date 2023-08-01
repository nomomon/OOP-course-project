package src.code.model.object;

import javax.imageio.ImageIO;

/**
 * @author Roos Spierings (s)
 */
public class OBJ_Bowl extends SuperObject {

    public OBJ_Bowl() {
        name = "Bowl";

        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/src/res/object/bowl.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image: " + name);
            e.printStackTrace();
        }
    }
}
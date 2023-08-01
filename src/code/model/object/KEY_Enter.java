package src.code.model.object;

import javax.imageio.ImageIO;

/**
 * @author Mansur Nurmukhambetov (s4774841)
 */
public class KEY_Enter extends SuperObject {

    public KEY_Enter() {
        name = "Enter";

        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/src/res/keyboard/enter.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image: " + name);
            e.printStackTrace();
        }
    }
}
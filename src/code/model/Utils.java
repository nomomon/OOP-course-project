package src.code.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Mansur Nurmukhambetov (s4774841)
 */
public class Utils {

    public BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

}

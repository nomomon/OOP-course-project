package src.code.model.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import src.code.view.GamePanel;

/**
 * @author Roos Spierings (s)
 */
public class SuperObject {
    public BufferedImage image1, image2, image3;
    public String name;
    public boolean collision;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (screenX > -gp.tileSize && screenY > -gp.tileSize &&
                screenX < gp.screenWidth && screenY < gp.screenHeight) {
            g2.drawImage(
                    image1,
                    screenX,
                    screenY,
                    gp.tileSize,
                    gp.tileSize,
                    null);
        }
    }
}
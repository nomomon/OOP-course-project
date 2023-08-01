package src.code.model.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import src.code.model.Utils;
import src.code.view.GamePanel;

/**
 * Class to manage the world tiles
 * 
 * @author Mansur Nurmukhambetov (s4774841)
 */
public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[100];

        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/src/res/map/map02.txt");

        getTileImage();
    }

    public void getTileImage() {
        createTile(0, "empty", false);

        createTile(1, "brick_floor", false);
        createTile(2, "brick_wall", true);
        createTile(3, "plate_floor", false);
        createTile(4, "bush", false);
        createTile(5, "particle", false);

        createTile(6, "edge_b", true);
        createTile(7, "edge_bl", true);
        createTile(8, "edge_br", true);
        createTile(9, "edge_lr", true);
        createTile(10, "edge_t", true);
        createTile(11, "edge_tl", true);
        createTile(12, "edge_tr", true);
    }

    public void createTile(int i, String imageName, boolean collision) {
        Utils util = new Utils();

        try {
            tile[i] = new Tile();
            tile[i].image = ImageIO.read(getClass().getResourceAsStream("/src/res/tile/" + imageName + ".png"));
            tile[i].image = util.scaleImage(tile[i].image, gp.tileSize, gp.tileSize);
            tile[i].collision = collision;
        } catch (IOException e) {
            System.out.println("Failed to load image: " + imageName);
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {

            InputStream ls = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(ls));

            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                }
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Failed to load map: " + filePath);
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Draw tile if it is on the screen
            if (screenX > -gp.tileSize && screenY > -gp.tileSize &&
                    screenX < gp.screenWidth && screenY < gp.screenHeight) {
                g2.drawImage(
                        tile[tileNum].image,
                        screenX, screenY,
                        null);
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

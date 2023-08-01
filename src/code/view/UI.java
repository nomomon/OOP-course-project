package src.code.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.code.model.object.KEY_Enter;
import src.code.model.object.OBJ_Axe;
import src.code.model.object.OBJ_Bowl;
import src.code.model.object.OBJ_Carrot;
import src.code.model.object.OBJ_Heart;
import src.code.model.object.OBJ_Meat;

/**
 * @author Mansur Nurmukhambetov (s4774841)
 *         - title window and instructions
 * @author Laura M Quirós (s4776380)
 *         - dialogue window and pause window
 */
public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font;

    public int titleScreenState = 0;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    BufferedImage axeImage;
    BufferedImage bowlImage;
    BufferedImage carrotImage;
    BufferedImage meatImage;
    BufferedImage fullHeart, halfHeart, blankHeart;
    BufferedImage enterKey;

    public UI(GamePanel gp) {
        this.gp = gp;
        font = new Font("Arial", Font.PLAIN, 16);
        axeImage = new OBJ_Axe().image1;
        bowlImage = new OBJ_Bowl().image1;
        carrotImage = new OBJ_Carrot().image1;
        meatImage = new OBJ_Meat().image1;
        blankHeart = new OBJ_Heart().image3;
        halfHeart = new OBJ_Heart().image2;
        fullHeart = new OBJ_Heart().image1;
        enterKey = new KEY_Enter().image1;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            // Inventory
            g2.setColor(Color.WHITE);
            g2.drawRect(10, 10, gp.tileSize + 10, gp.tileSize * 4);
            g2.setColor(Color.BLACK);
            g2.fillRect(10, 10, gp.tileSize + 10, gp.tileSize * 4);

            if (gp.player.hasMeat) {
                g2.drawImage(meatImage, 15, 10 + gp.tileSize * 0, gp.tileSize, gp.tileSize, null);
            }
            if (gp.player.hasAxe) {
                g2.drawImage(axeImage, 15, 10 + gp.tileSize * 1, gp.tileSize, gp.tileSize, null);
            }
            if (gp.player.hasBowl) {
                g2.drawImage(bowlImage, 15, 10 + gp.tileSize * 2, gp.tileSize, gp.tileSize, null);
            }
            if (gp.player.hasCarrot) {
                g2.drawImage(carrotImage, 15, 10 + gp.tileSize * 3, gp.tileSize, gp.tileSize, null);
            }

            // Player's life
            drawPlayerLife();

            // Draw player's keys
            drawPlayerKeys();
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    public void drawTitleScreen() {
        String text;
        int x, y;
        BufferedImage russianFlag, englishFlag;
        // loading the pictures
        try {
            russianFlag = ImageIO.read(getClass().getResourceAsStream("/src/res/object/russianFlag.png"));
            englishFlag = ImageIO.read(getClass().getResourceAsStream("/src/res/object/englishFlag.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            russianFlag = null;
            englishFlag = null;
        }

        // begining screen
        if (titleScreenState == 0) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
            text = "Axe Soup";

            // shadow
            g2.setColor(Color.GRAY);
            x = getXforCenteredText(text);
            y = gp.screenHeight / 2;
            g2.drawString(text, x + 5, y + 5);

            // title
            g2.setColor(Color.WHITE);
            x = getXforCenteredText(text);
            y = gp.screenHeight / 2;
            for (String line : text.split("\n")) {
                g2.drawString(line, x, y);
                y += g2.getFontMetrics().getHeight();
            }

            // axe image
            g2.drawImage(axeImage,
                    gp.screenWidth / 2 - gp.tileSize / 2,
                    gp.screenHeight / 2 + 30, gp.tileSize,
                    gp.tileSize, null);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));

            // load game
            if (gp.saveLoad.saveFile.exists() && !gp.saveLoad.saveFile.isDirectory()) {
                text = "Press L to load game";
                x = getXforCenteredText(text);
                y = gp.screenHeight / 2 + 110;
                g2.drawString(text, x, y);
            }

            // new game
            text = "Press N to start new game";
            x = getXforCenteredText(text);
            y = gp.screenHeight / 2 + 150;
            g2.drawString(text, x, y);

            // quit
            text = "Press Q to quit";
            x = getXforCenteredText(text);
            y = gp.screenHeight / 2 + 190;
            g2.drawString(text, x, y);
        }
        // choose language of new language
        else if (titleScreenState == 1) {

            // title
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
            text = "How to play";
            x = getXforCenteredText(text);
            y = gp.screenHeight / 4;
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F));

            // controls
            text = "Use WASD to move, ENTER to attack or interact with an npc, and P to pause the game.";
            x = getXforCenteredText(text);
            y = gp.screenHeight / 4 + 100;
            g2.drawString(text, x, y);

            // Press R to choose Russian
            text = "Press R to choose Russian";
            x = getXforCenteredText(text) - 100;
            y = gp.screenHeight * 3 / 4;
            g2.drawImage(russianFlag, x + 50, y - 100, gp.tileSize, gp.tileSize, null);
            g2.drawString(text, x, y);

            // Press E to choose English
            text = "Press E to choose English";
            x = getXforCenteredText(text) + 100;
            y = gp.screenHeight * 3 / 4;
            g2.drawImage(englishFlag, x + 50, y - 100, gp.tileSize, gp.tileSize, null);
            g2.drawString(text, x, y);
        }
    }

    public void drawPlayerKeys() {
        // draw in bottom right corner
        int x = gp.screenWidth - gp.tileSize * 5 / 2;
        int y = gp.screenHeight - gp.tileSize * 5 / 2;

        if (gp.collisionChecker.checkEntity(gp.player, gp.npc) != 999 ||
                gp.collisionChecker.checkEntity(gp.player, gp.monster) != 999) {
            // draw enter key
            g2.drawImage(enterKey, x, y + gp.tileSize, gp.tileSize * 2, gp.tileSize, null);
        }
    }

    public void drawPlayerLife() {
        int x = gp.screenWidth - gp.tileSize * 3 / 2; // Start from the rightmost position
        int y = gp.tileSize / 2; // Keep the y position as it is
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            if (i < gp.player.life / 2) {
                g2.drawImage(fullHeart, x - i * gp.tileSize, y, gp.tileSize, gp.tileSize, null);
            } else if (i == gp.player.life / 2 && gp.player.life % 2 == 1) {
                g2.drawImage(halfHeart, x - i * gp.tileSize, y, gp.tileSize, gp.tileSize, null);
            } else {
                g2.drawImage(blankHeart, x - i * gp.tileSize, y, gp.tileSize, gp.tileSize, null);
            }
            i++;
        }

    }

    public void drawGameOverScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        // shadow
        g2.setColor(Color.GRAY);
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x + 5, y + 5);

        // text
        g2.setColor(Color.WHITE);
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        // shadow
        g2.setColor(Color.GRAY);
        text = "Press Alt F4 to quit";
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2 + 40;
        g2.drawString(text, x, y);

        // text
        g2.setColor(Color.WHITE);
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2 + 40;
        g2.drawString(text, x + 2, y + 2);
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        // shadow
        g2.setColor(Color.GRAY);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x + 5, y + 5);

        // text
        g2.setColor(Color.WHITE);
        text = "PAUSED";
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        // window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - gp.tileSize * 4;
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += g2.getFontMetrics().getHeight();
        }
        // g2.drawString(currentDialogue, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(152, 255, 152, 240);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int textWidth = (int) g2.getFontMetrics().stringWidth(text);
        return (gp.screenWidth - textWidth) / 2;
    }

}

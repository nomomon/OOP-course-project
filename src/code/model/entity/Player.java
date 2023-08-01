package src.code.model.entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import src.code.view.GamePanel;
import src.code.controller.KeyHandler;
import src.code.model.Utils;

/**
 * Player class
 * 
 * @author Laura M Quirós (s4776380):
 *         - Attack images
 *         - Attack method
 *         - Hitbox
 *         - Collision with npcs and monsters
 *         - Invincibility after being hit
 * 
 * @author Mansur Nurmukhambetov (s4774841):
 *         - Player class
 *         - Pick up items method
 *         - Update method
 *         - Draw method
 *         - Player images
 *         - Collision with objects and tiles
 */
public class Player extends Entity {
    KeyHandler kH;

    public final int screenX;
    public final int screenY;

    public boolean hasAxe = false;
    public boolean hasCarrot = false;
    public boolean hasBowl = false;
    public boolean hasMeat = false;

    public boolean attacking = false;

    public Player(GamePanel gp, KeyHandler kH) {
        super(gp);
        this.kH = kH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(
                gp.tileSize / 6,
                gp.tileSize / 3,
                gp.tileSize / 3 * 2,
                gp.tileSize / 3 * 2);

        hitbox.width = 36; // range of attack
        hitbox.height = 36;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues(super.gp);
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(GamePanel gp) {
        worldX = super.gp.tileSize * 18;
        worldY = super.gp.tileSize * 15;
        speed = 3;
        direction = "down";
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = getPlayerFrame("player_up_1");
        up2 = getPlayerFrame("player_up_2");
        up3 = getPlayerFrame("player_up_3");
        down1 = getPlayerFrame("player_down_1");
        down2 = getPlayerFrame("player_down_2");
        down3 = getPlayerFrame("player_down_3");
        right1 = getPlayerFrame("player_right_1");
        right2 = getPlayerFrame("player_right_2");
        right3 = getPlayerFrame("player_right_3");
        left1 = getPlayerFrame("player_left_1");
        left2 = getPlayerFrame("player_left_2");
        left3 = getPlayerFrame("player_left_3");

    }

    public void getPlayerAttackImage() {
        attackDown1 = getPlayerFrame("attack/player_down_1");
        attackDown2 = getPlayerFrame("attack/player_down_2");
        attackDown3 = getPlayerFrame("attack/player_down_3");
        attackUp1 = getPlayerFrame("attack/player_up_1");
        attackUp2 = getPlayerFrame("attack/player_up_2");
        attackUp3 = getPlayerFrame("attack/player_up_3");
        attackRight1 = getPlayerFrame("attack/player_right_1");
        attackRight2 = getPlayerFrame("attack/player_right_2");
        attackRight3 = getPlayerFrame("attack/player_right_3");
        attackLeft1 = getPlayerFrame("attack/player_left_1");
        attackLeft2 = getPlayerFrame("attack/player_left_2");
        attackLeft3 = getPlayerFrame("attack/player_left_3");
    }

    /**
     * Returns the image to the description
     * 
     * @param imageName name of the image to be drawn
     * @return BufferedImage image of the player
     */
    public BufferedImage getPlayerFrame(String imageName) {

        Utils util = new Utils();
        BufferedImage image;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/src/res/player/" + imageName + ".png"));
            image = util.scaleImage(image, image.getWidth() * super.gp.scale, image.getHeight() * super.gp.scale);
        } catch (Exception e) {
            System.out.println("Failed to load image: " + imageName);
            e.printStackTrace();
            image = null;
        }

        return image;
    }

    public void update() {

        if (attacking) {
            attacking();
        }

        if (!kH.upPressed && !kH.downPressed && !kH.leftPressed && !kH.rightPressed && !kH.enterPressed)
            return;

        if (kH.upPressed) {
            direction = "up";
        }
        if (kH.downPressed) {
            direction = "down";
        }
        if (kH.leftPressed) {
            direction = "left";
        }
        if (kH.rightPressed) {
            direction = "right";
        }

        // Check tile collision
        collisionOn = false;
        super.gp.collisionChecker.checkTile(this);

        // Check object collision
        int objIndex = super.gp.collisionChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // Check NPC collision
        int npcIndex = super.gp.collisionChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        // Check monster collision
        int monsterIndex = super.gp.collisionChecker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum++;
            if (spriteNum > 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
        }
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        } else if (spriteCounter <= 25) {
            spriteNum = 2;

            // SAVE TO current
            int currentWorldX = (int) worldX;
            int currentWorldY = (int) worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // adjustfor the attack area
            switch (direction) {
                case "up":
                    worldY -= hitbox.height;
                    break;
                case "down":
                    worldY += hitbox.height;
                    break;
                case "left":
                    worldX -= hitbox.width;
                    break;
                case "right":
                    worldX += hitbox.width;
                    break;
            }

            solidArea.width = hitbox.width;
            solidArea.height = hitbox.height;
            int monsterIndex = super.gp.collisionChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // restore the original values after collision check
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        } else if (spriteCounter > 25) {
            spriteNum = 3;
            spriteCounter = 0;
            attacking = false;
        }
    }

    /**
     * Compute the damage to the monster
     * 
     * @param i
     */
    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monster[i].invincible) {
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                }
            }
        }
    }

    /**
     * Pick up an object
     * 
     * @param i
     */
    public void pickUpObject(int i) {
        // TODO: replace 999 with a constant
        if (i != 999) {
            String objName = super.gp.obj[i].name;

            // Add the interaction with items here
            switch (objName) {
                case "Axe":
                    hasAxe = true;
                    super.gp.obj[i] = null;
                    super.gp.playSoundEffect(2);
                    break;
                case "Carrot":
                    hasCarrot = true;
                    super.gp.obj[i] = null;
                    super.gp.playSoundEffect(3);
                    break;
                case "Chest":
                    hasBowl = true;
                    super.gp.obj[i] = null;
                    super.gp.playSoundEffect(2);
                    break;
                case "Meat":
                    hasMeat = true;
                    super.gp.obj[i] = null;
                    // super.gp.playSoundEffect(3);
                    break;
                case "Heart":
                    life++;
                    super.gp.obj[i] = null;
                    // super.gp.playSoundEffect(3);
                    break;
            }

            if (gp.languageSelected == "english") {
                gp.ui.currentDialogue = "You picked up the " + objName
                        + ".\n(Game state saved) \n\n(Enter)";
            } else if (gp.languageSelected == "russian") {
                gp.ui.currentDialogue = "Вы подобрали " + objName
                        + ".\n(Игра сохранена) \n\n(Enter)";
            }

            gp.gameState = gp.dialogueState;
            gp.saveLoad.saveGame();
        }
    }

    /**
     * Interact with an NPC
     * 
     * @param i
     */
    public void interactNPC(int i) {

        if (gp.keyHandler.enterPressed) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else {
                attacking = true;
            }
        }
        gp.keyHandler.enterPressed = false;
    }

    /**
     * Check if the player is in contact with a monster
     * 
     * @param i
     */
    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                life--;
                invincible = true;
            }
        }
    }

    /**
     * Set the correct sprite to draw and
     * readjust the screen position to account for the sprite size
     * 
     * @param i
     */
    public void draw(Graphics2D g2) {

        BufferedImage playerImage = null;
        Integer tempScreenX = screenX;
        Integer tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking) {
                    tempScreenY -= super.gp.tileSize;
                    if (spriteNum == 1) {
                        playerImage = attackUp1;
                    } else if (spriteNum == 2) {
                        playerImage = attackUp2;
                    } else if (spriteNum == 3) {
                        playerImage = attackUp3;
                    }
                } else {
                    if (spriteNum == 1) {
                        playerImage = up1;
                    } else if (spriteNum == 2) {
                        playerImage = up2;
                    } else if (spriteNum == 3) {
                        playerImage = up3;
                    }
                }
                break;
            case "down":
                if (attacking) {
                    if (spriteNum == 1) {
                        playerImage = attackDown1;
                    } else if (spriteNum == 2) {
                        playerImage = attackDown2;
                    } else if (spriteNum == 3) {
                        playerImage = attackDown3;
                    }
                } else {
                    if (spriteNum == 1) {
                        playerImage = down1;
                    } else if (spriteNum == 2) {
                        playerImage = down2;
                    } else if (spriteNum == 3) {
                        playerImage = down3;
                    }
                }
                break;
            case "right":
                if (attacking) {
                    if (spriteNum == 1) {
                        playerImage = attackRight1;
                    } else if (spriteNum == 2) {
                        playerImage = attackRight2;
                    } else if (spriteNum == 3) {
                        playerImage = attackRight3;
                    }
                } else {
                    if (spriteNum == 1) {
                        playerImage = right1;
                    } else if (spriteNum == 2) {
                        playerImage = right2;
                    } else if (spriteNum == 3) {
                        playerImage = right3;
                    }
                }
                break;
            case "left":
                if (attacking) {
                    tempScreenX -= super.gp.tileSize;
                    if (spriteNum == 1) {
                        playerImage = attackLeft1;
                    } else if (spriteNum == 2) {
                        playerImage = attackLeft2;
                    } else if (spriteNum == 3) {
                        playerImage = attackLeft3;
                    }
                } else {
                    if (spriteNum == 1) {
                        playerImage = left1;
                    } else if (spriteNum == 2) {
                        playerImage = left2;
                    } else if (spriteNum == 3) {
                        playerImage = left3;
                    }
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2.drawImage(playerImage, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}

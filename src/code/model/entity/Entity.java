package src.code.model.entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import src.code.view.GamePanel;
import src.code.model.object.SuperObject;

/**
 * Entity class
 * 
 * @author Laura M Quirós (s4776380)
 */
public class Entity {
    protected GamePanel gp;

    public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackDown1, attackDown2, attackDown3, attackRight1,
            attackRight2, attackRight3, attackLeft1, attackLeft2, attackLeft3;
    public Rectangle hitbox = new Rectangle(0, 0, 0, 0);
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    String dialogues[] = new String[20];

    // State
    public String direction;
    public int worldX, worldY;
    public int spriteNum = 1;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;

    // character attributes
    public int maxLife, life, speed;
    public String name;
    public int type; // 1 = player, 2 = npc, 3 = monster
    public boolean alive = true;
    public boolean dying = false;

    // Counters
    public int spriteCounter = 0;
    public int actionLockCounter = 0, invincibleCounter = 0;

    public Entity(GamePanel gamepanel) {
        this.gp = gamepanel;
    }

    public void setAction() {
    }

    public void speak() {
    }

    public void checkDrop() {
    }

    // Drop the item where the entity is standing
    public void dropItem(SuperObject droppedItem) {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] == null) {
                gp.obj[i] = droppedItem;
                gp.obj[i].worldX = this.worldX;
                gp.obj[i].worldY = this.worldY;
                break;
            }
        }
    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.npc);
        gp.collisionChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        // if just hit by player, take damage and invincibility activates
        if (this.type == 3 && contactPlayer) {
            if (!gp.player.invincible) {
                gp.player.life--;
                gp.player.invincible = true;
            }
        }

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
    }

    public void updatedialogueIndex() {
        if (gp.player.hasAxe != true) {
            dialogueIndex = 1;
        } else if (gp.player.hasCarrot != true) {
            dialogueIndex = 2;
        } else if (gp.player.hasMeat != true) {
            dialogueIndex = 3;
        } else if (gp.player.hasBowl != true) {
            dialogueIndex = 4;
        } else {
            dialogueIndex = 5;
        }
    }

    /**
     * Draw the entity
     * 
     * @param g2
     */
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    } else if (spriteNum == 2) {
                        image = up2;
                    } else if (spriteNum == 3) {
                        image = up3;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    } else if (spriteNum == 2) {
                        image = down2;
                    } else if (spriteNum == 3) {
                        image = down3;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    } else if (spriteNum == 2) {
                        image = right2;
                    } else if (spriteNum == 3) {
                        image = right3;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    } else if (spriteNum == 2) {
                        image = left2;
                    } else if (spriteNum == 3) {
                        image = left3;
                    }
                    break;
                case "still":
                    image = down2;
                    break;
            }
            if (invincible) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}

package src.code.model.entity;

import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import src.code.view.GamePanel;
import src.code.model.object.OBJ_Heart;
import src.code.model.object.OBJ_Meat;

/**
 * MON_GreenSlime class
 * 
 * @author Laura M Quirós (s4776380)
 */
public class MON_GreenSlime extends Entity {

    private int actionLockCounter = 0;

    public MON_GreenSlime(GamePanel gamepanel) {
        super(gamepanel);

        name = "Green Slime";
        type = 3;
        direction = "up";
        speed = 1;
        maxLife = 5;
        life = maxLife;
        alive = true;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/src/res/monster/slimeMonster_down_1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // if this is the first monster, drop meat, else drop heart
    public void checkDrop() {
        if (gp.monster[0] == this) {
            dropItem(new OBJ_Meat());
        } else {
            dropItem(new OBJ_Heart());
        }

    }

    @Override
    public void setAction() {
        actionLockCounter++;

        // every 2 seconds, select a new move at random
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // 1-100

            if (i <= 25) {
                direction = "up";
            } // between 25 and 50
            else if (i <= 50) {
                direction = "down";
            } // between 50 and 75
            else if (i <= 75) {
                direction = "left";
            } // between 75 and 100
            else if (i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

}

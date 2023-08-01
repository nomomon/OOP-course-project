package src.code.model;

import src.code.model.entity.MON_GreenSlime;
import src.code.model.entity.NPC_Traveler;
import src.code.model.object.OBJ_Axe;
import src.code.model.object.OBJ_Carrot;
import src.code.model.object.OBJ_Chest;
import src.code.view.GamePanel;

/**
 * @author Mansur Nurmukhambetov (s4774841):
 *         - setting objects
 * @author Laura M Quirós (s4776380):
 *         - setting NPCs, monsters
 */
public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        if (!gp.player.hasAxe) {
            gp.obj[0] = new OBJ_Axe();
            gp.obj[0].worldX = gp.tileSize * 23;
            gp.obj[0].worldY = gp.tileSize * 7;
        }

        if (!gp.player.hasCarrot) {
            gp.obj[1] = new OBJ_Carrot();
            gp.obj[1].worldX = gp.tileSize * 7;
            gp.obj[1].worldY = gp.tileSize * 23;
        }
        if (!gp.player.hasBowl) {
            gp.obj[2] = new OBJ_Chest();
            gp.obj[2].worldX = gp.tileSize * 17;
            gp.obj[2].worldY = gp.tileSize * 12;
        }
    }

    public void setNPC() {
        gp.npc[0] = new NPC_Traveler(gp);
        gp.npc[0].worldX = gp.tileSize * 20;
        gp.npc[0].worldY = gp.tileSize * 15;

    }

    public void setMonster() {
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize * 30;
        gp.monster[0].worldY = gp.tileSize * 15;

        // make another monster
        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize * 30;
        gp.monster[1].worldY = gp.tileSize * 17;
    }
}

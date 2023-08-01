package src.code.model.entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.view.GamePanel;

/**
 * NPC class
 * 
 * @author Laura M Quiros (s4776380)
 */

public class NPC_Traveler extends Entity {

    public NPC_Traveler(GamePanel gamepanel) {
        super(gamepanel);
        direction = "still";
        speed = 1;
        getAxeManImage();
        setDialogue();
    }

    public void getAxeManImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_down_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_right_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/src/res/npc/axeman_left_3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {
        if (gp.languageSelected == "english") {
            dialogues[0] = "I'm a tired traveler.\nI can make you an Axe Soup just with few ingredients. \nI will need an axe first.";
            dialogues[1] = "The soup is not ready.\nWithout an axe, I can't make the axe soup. \nCould you bring me an axe?";
            dialogues[2] = "The soup is not ready.\nWithout a carrot, I can't make the axe soup.\nCould you bring me a carrot?";
            dialogues[3] = "The soup is not ready.\nWithout some meat, I can't make the axe soup. \nCould you bring me some meat? Killing one of the monsters should do it.";
            dialogues[4] = "The axe soup is ready.\nBut you don't have a bowl to pour it in. \nGet your bowl and you'll get your soup";
            dialogues[5] = "There you go.\n Enjoy your axe soup! ^^ \n(The end)";
        }
        if (gp.languageSelected == "russian") {
            dialogues[0] = "Я усталый путешественник. Я могу приготовить \nдля вас суп из топора всего из нескольких \nингредиентов. Мне сначала понадобится топор.";
            dialogues[1] = "Суп не готов.\nБез топора я не смогу приготовить суп из топора. \nВы можете принести мне топор?";
            dialogues[2] = "Суп не готов.\nБез моркови я не смогу приготовить суп из топора. \nВы можете принести мне морковь?";
            dialogues[3] = "Суп не готов.\nБез мяса я не смогу приготовить суп из топора. \nВы можете принести мне немного мяса?";
            dialogues[4] = "Суп из топора готов.\nНо у вас нет миски, чтобы налить его. \nВозьмите свою миску, и вы получите свой суп";
            dialogues[5] = "Вот, пожалуйста.\nНаслаждайтесь своим супом из топора! ^^ \\n" + //
                    "(Конец.)";
        }
    }

    public void setAction() {
        direction = "still";
    }

    @Override
    public void update() {
        setAction();
    }

    public void speak() {
        if (dialogueIndex != 0) {
            updatedialogueIndex();
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex] + "\n\n(Enter)";
        if (dialogueIndex == 0) {
            dialogueIndex++;
        } else {
            updatedialogueIndex();
        }
    }

}

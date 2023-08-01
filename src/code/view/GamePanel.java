package src.code.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import src.code.controller.KeyHandler;
import src.code.model.AssetSetter;
import src.code.model.CollisionChecker;
import src.code.model.Sound;
import src.code.model.data.SaveLoad;
import src.code.model.entity.Entity;
import src.code.model.entity.Player;
import src.code.model.object.SuperObject;
import src.code.model.tile.TileManager;

/**
 * GamePanel
 * 
 * @author Mansur Nurmukhambetov (s4774841):
 *         - world parameters,
 *         - game loop,
 *         - paint component,
 *         - update method
 * 
 * @author Roos Spierings (s):
 *         - sound
 */
public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16x16 tile
    public final int scale = 3; // 3x scale

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD PARAMETERS
    public final int maxWorldCol = 44;
    public final int maxWorldRow = 50;

    // FPS
    final int FPS = 60;

    // System
    public TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    AssetSetter assetSetter = new AssetSetter(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound backgroundSound = new Sound();
    Sound soundEffect = new Sound();
    Thread gameThread;
    public SaveLoad saveLoad = new SaveLoad(this);
    public UI ui = new UI(this);

    // Player default position
    public Player player = new Player(this, keyHandler);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[10];

    // Game state
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int gameOverState = 4;
    public int gameState = titleState;

    public String languageSelected = "english"; // default language

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setUpGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Game loop
    @Override
    public void run() {

        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            // update all npc
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            // update all monster
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].dying) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    } else {
                        monster[i].update();
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // Pause game
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Title screen
        if (gameState == titleState) {
            ui.draw(g2);
        } else {

            // Draw tiles
            tileManager.draw(g2);

            // Draw objects
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            // Draw NPCs
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {

                    npc[i].draw(g2);
                }
            }

            // Draw monsters
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].draw(g2);
                }
            }

            // Draw player
            player.draw(g2);

            // Draw ui
            ui.draw(g2);

        }
        g2.dispose();
    }

    public void playBackgroundSound(int i) {
        backgroundSound.setFile(i);
        backgroundSound.play();
        backgroundSound.loop();
    }

    public void stopBackgroundSound() {
        backgroundSound.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }

}

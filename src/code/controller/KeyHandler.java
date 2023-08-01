package src.code.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.code.view.GamePanel;

/**
 * @author Mansur Nurmukhambetov (s4774841):
 *         - WASD keys
 *         - title state controls
 * @author Laura M Quirós (s4776380):
 *         - enter key
 *         - dialogue state and pause state
 */
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // title state
        if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0) {
                if (keyCode == KeyEvent.VK_N) { // new game
                    // choose language
                    gp.ui.titleScreenState = 1;
                }
                if (keyCode == KeyEvent.VK_L) { // load game
                    gp.gameState = gp.playState;
                    gp.saveLoad.loadGame();
                    gp.playBackgroundSound(0);
                    gp.setUpGame();
                }
                if (keyCode == KeyEvent.VK_Q) { // quit game
                    System.exit(0);
                }
            } else if (gp.ui.titleScreenState == 1) {
                if (keyCode == KeyEvent.VK_R) { // new game in russian
                    gp.gameState = gp.playState;
                    gp.languageSelected = "russian";
                    gp.playBackgroundSound(0);
                    gp.setUpGame();
                }
                if (keyCode == KeyEvent.VK_E) { // new game in english
                    gp.gameState = gp.playState;
                    gp.languageSelected = "english";
                    gp.playBackgroundSound(0);
                    gp.setUpGame();
                }
            }
        }

        // play state
        if (gp.gameState == gp.playState) {
            if (keyCode == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (keyCode == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (keyCode == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (keyCode == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (keyCode == KeyEvent.VK_P) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                }
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }
        // dialogue state and pause state
        else if (gp.gameState == gp.pauseState) {
            if (keyCode == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        } else if (gp.gameState == gp.dialogueState) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

}

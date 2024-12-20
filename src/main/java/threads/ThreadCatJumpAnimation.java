package threads;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import screens.CatchTheCatScreen;
import minigames.SpriteCatPositionsEnum;

/**
 *
 * @author Brian Ramirez
 */
public class ThreadCatJumpAnimation extends Thread {
    private boolean isRunning;
    private boolean isPaused;
    private SpriteCatPositionsEnum position;
    private int targetX, targetY;
    private int currentX, currentY; // Current position of the character
    private CatchTheCatScreen screen;


    public ThreadCatJumpAnimation( int targetX, int targetY, CatchTheCatScreen screen, int currentX, int currentY) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.screen = screen;
        this.currentX = currentX;
        this.currentY = currentY;
        selectPosition();
        this.isRunning = true;
        this.isPaused = false;
    }

    @Override
    public void run() {
    
        while (isRunning) {
            screen.getSettings().setEnableCatMovement(false);
            try {
                screen.getCharacterImage().changeSprite(position);
                screen.getCharacterLabel().revalidate();
                screen.getCharacterLabel().repaint();
                sleep(800);
                 if(position==SpriteCatPositionsEnum.FRONT||position==SpriteCatPositionsEnum.RIGHT)
                    screen.getCharacterImage().changeSprite(SpriteCatPositionsEnum.JUMP_RIGHT);
                 else
                    screen.getCharacterImage().changeSprite(SpriteCatPositionsEnum.JUMP_LEFT);
                screen.putCharacter(targetX, targetY);
                screen.getCharacterLabel().revalidate();
                screen.getCharacterLabel().repaint();
                sleep(800);
                screen.getCharacterImage().changeSprite(SpriteCatPositionsEnum.FRONT);
                screen.getCharacterLabel().repaint();
                 sleep(800);
                 if(targetX==0 || targetX==screen.getROWS()-1 || targetY==0 ||targetY==screen.getCOLS()-1 ){
                     screen.getGameState().setText("You lost!");
                     screen.getGameState().setBackground(Color.red);
                     sleep(1500);
                     screen.getSettings().endGame();
                 
                 }
                 screen.getSettings().setEnableCatMovement(true);
                 break;
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadCatJumpAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
            while (isPaused
                    ) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadCatJumpAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
    
    private void selectPosition() {
        if (currentX > targetX && currentY == targetY) {
            this.position = SpriteCatPositionsEnum.LEFT;
        } else if (currentX < targetX && currentY == targetY) {
            this.position = SpriteCatPositionsEnum.RIGHT;
        } else if (currentY > targetY && currentX == targetX) {
            this.position = SpriteCatPositionsEnum.BACK;
        } else if (currentY < targetY && currentX == targetX) {
            this.position = SpriteCatPositionsEnum.FRONT;
        } else if (currentX > targetX && currentY > targetY) {
            this.position = SpriteCatPositionsEnum.LEFT;
        } else if (currentX > targetX && currentY < targetY) {
            this.position = SpriteCatPositionsEnum.LEFT;
        } else if (currentX < targetX && currentY > targetY) {
            this.position = SpriteCatPositionsEnum.RIGHT;
        } else if (currentX < targetX && currentY < targetY) {
            this.position = SpriteCatPositionsEnum.RIGHT;
        }
    }
}
    
 
    
    


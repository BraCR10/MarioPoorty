package threads;


import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import screens.BomberMarioScreen;


/**
 *
 * @author Brian Ramirez
 */
public class ThreadExplotionAnimation extends Thread{
   private  boolean isRunning = true;
    private  boolean isPaused = false;
    private BomberMarioScreen screen;
    private int sourceY,sourceX;
    private int explotionType;
    private  Point[] chestPositions;
    
    public ThreadExplotionAnimation(BomberMarioScreen screen,int sourceX,int sourceY,Point[] chestPositions,int explotionType) {
        this.screen=screen;
        this.sourceX=sourceX;
        this.sourceY=sourceY;
        this.explotionType=explotionType;
        this.chestPositions=chestPositions;
        
    }

    @Override
    public void run() {
        while (isRunning) {       
            screen.setEnableExplosionFlag(false);
            switch (explotionType) {
                case 1:
                    simpleExplotion();
                    break;
                case 2:
                    doubleExplotion();
                    break;
                case 3:
                    crossExplotion();
                    break;
                case 4:
                    lineExplotion();
                    break;
            }
           
            if(screen.getSettings().getChestFoundPartCounter()>4){
                screen.getLabelCounter().setText("You won!!");
                screen.Win();
                screen.getLabelCounter().setBackground(Color.GREEN);
                screen.getLabelCounter().repaint();
               try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
               screen.getSettings().endGame();
            }
            if(screen.getSettings().getBombCounter()==0){
                 screen.getLabelCounter().setText("You lost!!");
                 screen.getLabelCounter().setBackground(Color.BLACK);
                 screen.getLabelCounter().repaint();
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
                 screen.getSettings().endGame();
            }
            screen.setEnableExplosionFlag(true);
            break;
        }
    }
    
    

    private void simpleExplotion() {
        if (screen.getMatrizButtons()[sourceX][sourceY].getIcon() == screen.getMisteryBox()) {
            screen.getMatrizButtons()[sourceX][sourceY].setIcon(screen.getExplosion());

            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }

            screen.getMatrizButtons()[sourceX][sourceY].setIcon(checkChestPosition(sourceX, sourceY));
        }
    }

    private void doubleExplotion() {
        if (screen.getMatrizButtons()[sourceX][sourceY].getIcon() == screen.getMisteryBox()) {
            screen.getMatrizButtons()[sourceX][sourceY].setIcon(screen.getExplosion());

            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 1; j++) {
                    int posX = sourceX + i;
                    int posY = sourceY + j;

                    if (posX >= 0 && posX < screen.getMatrizButtons().length &&
                        posY >= 0 && posY < screen.getMatrizButtons()[0].length &&
                        screen.getMatrizButtons()[posX][posY].getIcon() == screen.getMisteryBox()) {
                        screen.getMatrizButtons()[posX][posY].setIcon(screen.getExplosion());
                    }
                }
            }

            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 1; j++) {
                    int posX = sourceX + i;
                    int posY = sourceY + j;

                    if (posX >= 0 && posX < screen.getMatrizButtons().length &&
                        posY >= 0 && posY < screen.getMatrizButtons()[0].length &&
                        screen.getMatrizButtons()[posX][posY].getIcon() == screen.getExplosion()) {
                        screen.getMatrizButtons()[posX][posY].setIcon(checkChestPosition(posX, posY));
                    }
                }
            }
        }
    }

    private void crossExplotion() {
        if (screen.getMatrizButtons()[sourceX][sourceY].getIcon() == screen.getMisteryBox()) {
            screen.getMatrizButtons()[sourceX][sourceY].setIcon(screen.getExplosion());

            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }

            int[][] crossPositions = {
                {sourceX - 1, sourceY}, {sourceX + 1, sourceY},
                {sourceX, sourceY - 1}, {sourceX, sourceY + 1}
            };

            for (int[] pos : crossPositions) {
                int posX = pos[0];
                int posY = pos[1];

                if (posX >= 0 && posX < screen.getMatrizButtons().length &&
                    posY >= 0 && posY < screen.getMatrizButtons()[0].length &&
                    screen.getMatrizButtons()[posX][posY].getIcon() == screen.getMisteryBox()) {
                    screen.getMatrizButtons()[posX][posY].setIcon(screen.getExplosion());
                }
            }

            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int[] pos : crossPositions) {
                int posX = pos[0];
                int posY = pos[1];

                if (posX >= 0 && posX < screen.getMatrizButtons().length &&
                    posY >= 0 && posY < screen.getMatrizButtons()[0].length &&
                    screen.getMatrizButtons()[posX][posY].getIcon() == screen.getExplosion()) {
                    screen.getMatrizButtons()[posX][posY].setIcon(checkChestPosition(posX, posY));
                }
            }

            screen.getMatrizButtons()[sourceX][sourceY].setIcon(checkChestPosition(sourceX, sourceY));
        }
    }

    private void lineExplotion() {
        boolean isVertical = (new Random()).nextBoolean();

        try {
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isVertical) {
            for (int i = -1; i <= 2; i++) {
                int posX = sourceX + i;
                int posY = sourceY;

                if (posX >= 0 && posX < screen.getMatrizButtons().length &&
                    screen.getMatrizButtons()[posX][posY].getIcon() == screen.getMisteryBox()) {
                    screen.getMatrizButtons()[posX][posY].setIcon(screen.getExplosion());
                }
            }
        } else {
            for (int j = -1; j <= 2; j++) {
                int posX = sourceX;
                int posY = sourceY + j;

                if (posY >= 0 && posY < screen.getMatrizButtons()[0].length &&
                    screen.getMatrizButtons()[posX][posY].getIcon() == screen.getMisteryBox()) {
                    screen.getMatrizButtons()[posX][posY].setIcon(screen.getExplosion());
                }
            }
        }

        try {
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadExplotionAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isVertical) {
            for (int i = -1; i <= 2; i++) {
                int posX = sourceX + i;
                int posY = sourceY;

                if (posX >= 0 && posX < screen.getMatrizButtons().length &&
                    screen.getMatrizButtons()[posX][posY].getIcon() == screen.getExplosion()) {
                    screen.getMatrizButtons()[posX][posY].setIcon(checkChestPosition(posX, posY));
                }
            }
        } else {
            for (int j = -1; j <= 2; j++) {
                int posX = sourceX;
                int posY = sourceY + j;

                if (posY >= 0 && posY < screen.getMatrizButtons()[0].length &&
                    screen.getMatrizButtons()[posX][posY].getIcon() == screen.getExplosion()) {
                    screen.getMatrizButtons()[posX][posY].setIcon(checkChestPosition(posX, posY));
                }
            }
        }
    }
    
    
    private Icon checkChestPosition( int cellX,int cellY) {

        for (int i = 0; i < chestPositions.length; i++) {
            int chestX = (int)chestPositions[i].getX();
            int chestY = (int)chestPositions[i].getY();
            if (chestX == cellX && chestY == cellY) {
                
                switch (screen.getSettings().getChestFoundPartCounter()) {
                    case 1:
                        screen.getSettings().setChestFoundPartCounter(2);
                        return screen.getChest_bottomleft(); 
                    case 2:
                        screen.getSettings().setChestFoundPartCounter(3);
                        return screen.getChest_topright(); 
                    case 3:
                        screen.getSettings().setChestFoundPartCounter(4);
                        return screen.getChest_topleft(); 
                    case 4:
                         screen.getSettings().setChestFoundPartCounter(5);
                        return screen.getChest_bottomright(); 

                }
                
            }
        }

        return screen.getMisteryBoxEmpty(); 
    }
}
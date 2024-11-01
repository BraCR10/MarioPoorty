/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.threads;

import javax.swing.JLabel;
import poo.mariopoorty.minigames.MemoryPath;
import poo.mariopoorty.screens.MemoryPathScreen;


/**
 *
 * @author Brian Ramirez
 */

public class ThreadCharacterMemoryPathMovement extends Thread {
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private  MemoryPathScreen screen;
    private final int xLimit;
    private final int yLimit;
    private int x, y;;

    public ThreadCharacterMemoryPathMovement(MemoryPathScreen screen, int xLimit, int yLimit ) {
        this.isRunning = true;
        this.isPaused = false;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.x = screen.getCharacter().getX();
        this.y = screen.getCharacter().getY();
        this.screen=screen;
    }

    @Override
   public void run() {
    while (isRunning) {
        try {
           if (!isPaused) {
            screen.getCharacter().setLocation(x, y);

            if (x < xLimit - 5) x += 6;
            else if (x > xLimit + 5) x -= 6;

            if (y < yLimit - 5) y += 6;
            else if (y > yLimit + 5) y -= 6;

            if (Math.abs(x - xLimit) <= 5 && Math.abs(y - yLimit) <= 5) {
                screen.setIsArrivedFlag(true);
                isRunning = false;
            }

            screen.getCharacter().repaint();
}
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


}
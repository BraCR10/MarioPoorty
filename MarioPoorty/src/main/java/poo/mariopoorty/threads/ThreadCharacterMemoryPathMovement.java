/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.threads;

import javax.swing.JLabel;
import poo.mariopoorty.minigames.MemoryPath;


/**
 *
 * @author Brian Ramirez
 */

public class ThreadCharacterMemoryPathMovement extends Thread {
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private final JLabel character;
    private final int xLimit;
    private final int yLimit;
    private int x, y;;

    public ThreadCharacterMemoryPathMovement(JLabel character, int xLimit, int yLimit) {
        this.character = character;
        this.isRunning = true;
        this.isPaused = false;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.x = character.getX();
        this.y = character.getY();
    }

    @Override
   public void run() {
    while (isRunning) {
        try {
           if (!isPaused) {
            character.setLocation(x, y);

            if (x < xLimit - 5) x += 10;
            else if (x > xLimit + 5) x -= 10;

            if (y < yLimit - 5) y += 10;
            else if (y > yLimit + 5) y -= 10;

            if (Math.abs(x - xLimit) <= 5 && Math.abs(y - yLimit) <= 5) {
                MemoryPath.setArrivedFlag(true);
                isRunning = false;
            }

            character.repaint();
}
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


}
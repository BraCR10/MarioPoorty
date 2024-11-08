/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.threads;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.JFrame;
import poo.mariopoorty.screens.CollectCoinsScreen;

/**
 *
 * @author Brian Ramirez
 */
public class ThreadTimerCollectCoins extends Thread{
    boolean isRunning ;
    boolean isPaused ;
    CollectCoinsScreen  screen;
    static Random randomNumber = new Random(); 
    static int[] seconds={45,30,60};
    public ThreadTimerCollectCoins(JFrame  screen) {
        isPaused=false;
        isRunning=true;
        this.screen=(CollectCoinsScreen)screen;

    }

    @Override
    public void run() {
        int totalSeconds = seconds[randomNumber.nextInt(seconds.length)]; 
        

        while (isRunning && screen!=null) {
            try {
                sleep(1000);
                totalSeconds--; 

                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
                screen.getJtTimer().setText(String.format("Time: %02d mins %02d segs", minutes, seconds));

                if (totalSeconds <= 0) {
                    if(screen.getSettings().getScore()>0)
                    {
                    screen.getJtTimer().setText("You won!!!");
                    screen.getJtTimer().setBackground(Color.GREEN);
                    }else{
                     screen.getJtTimer().setText("You lost!!!");
                     screen.getJtTimer().setForeground(Color.white);
                    screen.getJtTimer().setBackground(Color.black);                  
                    
                    }
                    sleep(2000);
                    screen.getSettings().endGame();
                    isRunning = false;
                }

            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }

            while (isPaused) {
                try {
                    sleep(1000); 
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

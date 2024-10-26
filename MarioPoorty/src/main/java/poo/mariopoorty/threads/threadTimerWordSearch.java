/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.threads;

import poo.mariopoorty.screens.WordSearchScreen;
import java.awt.Color;
import poo.mariopoorty.minigames.WordSearch;

/**
 *
 * @author Brian
 */
public class threadTimerWordSearch extends Thread{
    boolean isRunning ;
    boolean isPaused ;
    WordSearch  wordSearch;
    WordSearchScreen  screen;
    public threadTimerWordSearch(WordSearch  wordSearch,WordSearchScreen  screen) {
        isPaused=false;
        isRunning=true;
        this.wordSearch=wordSearch;
        this.screen=screen;
    }

    @Override
    public void run() {
        while (isRunning) {
            
            try {
                screen.setTimer(screen.getTimer()-0.01);
                sleep(1000);
                
                if(screen.getTimer()<=0 ){
                    screen.getTimerLabel().setText(" You lost!!!");
                    screen.getTimerLabel().setBackground(Color.BLACK);
                    sleep(2000);
                    wordSearch.endGame();
                    isRunning=false;
                }
                if(WordSearch.getCounterCollectedWords()>=4 ){
                    screen.getTimerLabel().setText(" You won!!!");
                    screen.getTimerLabel().setBackground(Color.GREEN);
                    sleep(2000);
                    wordSearch.endGame();
                    isRunning=false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace(); // Log the exception if it occurs
            }

            // Pausing the thread
            while (isPaused) {
                try {
                    sleep(1000); // Sleep while paused, checking every second
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
}

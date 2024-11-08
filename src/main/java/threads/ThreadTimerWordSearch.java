package threads;


import java.awt.Color;
import minigames.WordSearch;
import screens.WordSearchScreen;


/**
 *
 * @author Brian
 */
public class ThreadTimerWordSearch extends Thread{
    boolean isRunning ;
    boolean isPaused ;
    WordSearch  wordSearch;
    WordSearchScreen  screen;
    public ThreadTimerWordSearch(WordSearch  wordSearch,WordSearchScreen  screen) {
        isPaused=false;
        isRunning=true;
        this.wordSearch=wordSearch;
        this.screen=screen;
    }

    @Override
    public void run() {
        int totalSeconds = 120; 
        

        while (isRunning && screen!=null) {
            try {
                sleep(1000);
                totalSeconds--; 

                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
                screen.getTimerLabel().setText(String.format("Time: %02d mins %02d segs", minutes, seconds));

                if (totalSeconds <= 0) {
                    screen.getTimerLabel().setText("You lost!!!");
                    screen.getTimerLabel().setBackground(Color.BLACK);
                    sleep(2000);
                    wordSearch.endGame();
                    isRunning = false;
                }

                if (WordSearch.getCounterCollectedWords() >= 4) {
                    screen.getTimerLabel().setText("You won!!!");
                    wordSearch.players.get(0).Condition = "Roll";
                    screen.getTimerLabel().setBackground(Color.GREEN);
                    sleep(2000);
                    wordSearch.endGame();
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

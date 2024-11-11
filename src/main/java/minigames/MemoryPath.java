package minigames;

import com.mycompany.proyect2.Board;
import com.mycompany.proyect2.Player;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import screens.MemoryPathScreen;

/**
 *
 * @author Brian
 */
public class MemoryPath extends MiniGames {
    private static final int ROWS = 6;
    private static final int COLS = 3;
    private static final Random randomNumber = new Random();
    private final boolean[][] matrizPathSelected = new boolean[ROWS][COLS];
    private int attempts;

    public MemoryPath(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
        this.attempts = 3;
    }

    private void configurePathMatrix() {
        for (int i = 0; i < ROWS; i++) {
             matrizPathSelected[i][randomNumber.nextInt(COLS)] = true;
        }
        //to show solution
        System.out.println("Memory path solution");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
              System.out.println("|"+matrizPathSelected[i][j]+"|");  
            }
            System.out.println(" ");     
        }
    }


    @Override
    public void startGame() {
        this.board.setVisible(false);
        this.gamePanel = new MemoryPathScreen(this);
        configurePathMatrix();
        this.gamePanel.setVisible(true);
    }

    @Override
    public void endGame() {
        this.board.setVisible(true);
        this.gamePanel.dispose();
    }

    @Override
    public void playTurn() {
        for (Player player : players) {
            if (player.isMyTurn) {
                player.isMyTurn = false;
                player.miniGame.startGame();
            }
        }
    }


    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public boolean[][] getMatrizPathSelected() {
        return matrizPathSelected;
    }
    
}
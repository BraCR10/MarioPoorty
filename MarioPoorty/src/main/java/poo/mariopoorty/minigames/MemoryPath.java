/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import poo.mariopoorty.Board;
import poo.mariopoorty.Player;
import poo.mariopoorty.screens.MemoryPathScreen;

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
            matrizPathSelected[i][randomNumber.nextInt(3)] = true;
        }
    }


    @Override
    public void startGame() {
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
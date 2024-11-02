/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.util.ArrayList;
import javax.swing.JFrame;
import poo.mariopoorty.Board;
import poo.mariopoorty.Player;
import poo.mariopoorty.screens.CatchTheCatScreen;

/**
 *
 * @author Brian
 */
public class CatchTheCat extends MiniGames{
    
    public CatchTheCat(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
    }

    @Override
    public void startGame() {
        //this.board.setVisible(false);
        this.gamePanel=new CatchTheCatScreen();
        this.gamePanel.setVisible(true);
    }

    @Override
    public void endGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void playTurn() {
       for (Player player : players) {
            if (player.isMyTurn) {
                player.isMyTurn=false;
                player.miniGame.startGame();
            }
        }
    }
    
    
    
}

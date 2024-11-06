/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import poo.mariopoorty.Board;
import poo.mariopoorty.Player;
import poo.mariopoorty.screens.CollectCoinsScreen;

/**
 *
 * @author Brian
 */
public class CollectCoins extends MiniGames{

    public CollectCoins(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
    }

    @Override
    public void startGame() {
        
        gamePanel=new CollectCoinsScreen(this);
        gamePanel.setVisible(true);
    }

    @Override
    public void endGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void playTurn() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void coinClicked(java.awt.event.MouseEvent e){
        ((JLabel)e.getSource()).setIcon(null);
    
    }
}

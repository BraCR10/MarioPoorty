/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import poo.mariopoorty.Player;


/**
 *
 * @author Brian
 */
public abstract class MiniGames {
    public ArrayList<Player> players;
    public String type;
    public String description;
    public int currentPlayers;
    public JFrame gamePanel;

    public MiniGames( String type, String description, int currentPlayers, JFrame gamePanel) {
        this.type = type;
        this.description = description;
        this.currentPlayers = currentPlayers;
        this.gamePanel = gamePanel;
    }

    public MiniGames(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel) {
        this.players = players;
        this.type = type;
        this.description = description;
        this.currentPlayers = currentPlayers;
        this.gamePanel = gamePanel;
    }
    
    
    public abstract void  startGame();
    public abstract  void  endGame();
    public abstract void playTurn(Player player);
    
            
}

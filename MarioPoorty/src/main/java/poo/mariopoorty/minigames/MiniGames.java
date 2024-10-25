/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.util.ArrayList;
import javax.swing.JPanel;
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
    public JPanel gamePanel;
    
    
    abstract void  startGame();
    abstract  void  endGame();
    abstract void playTurn(Player player);
    
            
}

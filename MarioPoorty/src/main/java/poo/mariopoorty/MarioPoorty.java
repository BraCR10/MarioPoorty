/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.mariopoorty;

import poo.mariopoorty.screens.WordSearchScreen;
import java.util.ArrayList;
import javax.swing.JFrame;
import poo.mariopoorty.minigames.MiniGames;
import poo.mariopoorty.minigames.WordSearch;

/**
 *
 * @author Brian
 */
public class MarioPoorty {

    public static void main(String[] args) {
        JFrame wordSearchScreen = new WordSearchScreen();
        ArrayList<Player> players = new ArrayList();
        
        MiniGames miniGame = new WordSearch(players,"Search Word","Play alone",1,wordSearchScreen);
        
        players.add(new Player(0));
        miniGame.startGame();
        miniGame.playTurn(players.get(0));
        
    
        
        
        
    }
}

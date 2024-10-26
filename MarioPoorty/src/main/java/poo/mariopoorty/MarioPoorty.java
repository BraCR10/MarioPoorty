/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.mariopoorty;

import Screens.wordSearchScreen;
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
        JFrame wordSearchScreen = new wordSearchScreen();
        ArrayList<Player> players = new ArrayList();
        MiniGames miniGame;
        
        miniGame = new WordSearch(players,"","",4,wordSearchScreen);
        wordSearchScreen.setVisible(true);
       miniGame.startGame();
        
    
        
        
        
    }
}

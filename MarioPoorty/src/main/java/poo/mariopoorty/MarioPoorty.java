/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.mariopoorty;

import java.util.ArrayList;
import javax.swing.JFrame;
import poo.mariopoorty.minigames.CatchTheCat;
import poo.mariopoorty.minigames.MemoryPath;
import poo.mariopoorty.minigames.MiniGames;
import poo.mariopoorty.minigames.WordSearch;


/**
 *
 * @author Brian
 */
public class MarioPoorty {

    public static void main(String[] args) {
        /*
        new GameServer();
        */
        ArrayList<Player> p = new ArrayList<>();
        Board b=null;
        JFrame g=null;
        MiniGames c = new CatchTheCat(p,"", "", 0, g, b);
       c.startGame();
        
    
        
        
        
    }
}

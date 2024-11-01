/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.mariopoorty;

import java.util.ArrayList;
import javax.swing.JFrame;
import poo.mariopoorty.minigames.MemoryPath;
import poo.mariopoorty.screens.MemoryPathScreen;


/**
 *
 * @author Brian
 */
public class MarioPoorty {

    public static void main(String[] args) {
        
        //new GameServer();
        ArrayList<Player> a = new ArrayList();
        JFrame g =null;
        Board b = null;
        MemoryPath m = new MemoryPath(a, "", "", 0, g, b);
        m.startGame();
        
    
        
        
        
    }
}

package minigames;

import com.mycompany.proyect2.Board;
import com.mycompany.proyect2.Player;
import java.util.ArrayList;
import javax.swing.JFrame;


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
    public Board board;

    public MiniGames(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        this.players = players;
        this.type = type;
        this.description = description;
        this.currentPlayers = currentPlayers;
        this.gamePanel = gamePanel;
        this.board = board;
    }
    
    public abstract void  startGame();
    public abstract  void  endGame();
    public abstract void playTurn();
    
            
}

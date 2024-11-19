package minigames;

import BoardPawnsDice.Board;
import MainGame.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import screens.CardsScreen;
import screens.TicTacToeScreen;


public class Cards extends MiniGames{

    public Cards(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
        startGame();
    }

    @Override
    public void startGame() {
        this.board.setVisible(false);
        this.gamePanel = new CardsScreen(getPlayer(),this);
        this.gamePanel.setVisible(true);
        while(gamePanel.isDisplayable()){
        }
        
    }

    private boolean CompareCards(CardsScreen screen, int num, String sym){
        return screen.CompareCards(num, sym);
    }
    
    @Override
    public void endGame() {
        
        try {
            String name = getPlayer().in.readUTF();
            
            int num = getPlayer().in.readInt();
            String symbol = getPlayer().in.readUTF();
            
            boolean condition = CompareCards((CardsScreen) gamePanel, num, symbol);
            
            if (condition && name.equals(getPlayer().name)){
                getPlayer().Condition = "Roll";
            }
            
            this.board.setVisible(true);
            this.gamePanel.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Cards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Player getPlayer(){
        return players.get(0);
    }
    
    @Override
    public void playTurn() { }

    
}

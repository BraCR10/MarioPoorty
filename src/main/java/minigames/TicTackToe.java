package minigames;

import BoardPawnsDice.Board;
import MainGame.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import screens.TicTacToeScreen;


public class TicTackToe extends MiniGames{

    public ArrayList<Integer> MyList = new ArrayList<>();

    public boolean MyTurn;
    public String MySimbol;
    public boolean finished = false;
    
    public int[][] wins = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

    
    public TicTackToe(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board                board) throws IOException {
        
        
        super(players, type, description, currentPlayers, gamePanel, board);
        MySimbol = this.players.get(0).in.readUTF();
        this.startGame();
    }

    @Override
    public void startGame() {
        try {
            this.board.setVisible(false);
            this.gamePanel = new TicTacToeScreen(this);
            this.gamePanel.setVisible(true);
            GameLoop((TicTacToeScreen) gamePanel);
            
            
        } catch (IOException ex) {
            Logger.getLogger(TicTackToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void endGame() {
        this.board.setVisible(true);
        this.gamePanel.dispose();
    }
    
    
    
    private void GameLoop(TicTacToeScreen gamePanel) throws IOException{
        String name = "";
        String str = "";
        
        while (!finished){
            for (Player player : players) {            
                this.MyTurn = player.in.readBoolean();
                
                gamePanel.ReadInfo();
                
                gamePanel.FinishTurn();

                name = player.in.readUTF();
                str = player.in.readUTF();
                
                if ("Win".equals(str)){finished = true;}
                if ("Draw".equals(str)){finished = true;}
                
            }

        }
        
        if (name.equals(this.board.boardOwner.name) && "Win".equals(str)){this.board.boardOwner.Condition = "Roll";}
        endGame();
    }
    
    
    public Player getPlayer(){
        return players.get(0);
    }
    
    

    @Override
    public void playTurn() {

    }
    
}

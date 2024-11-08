package minigames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.mycompany.proyect2.Board;
import com.mycompany.proyect2.Player;
import screens.CollectCoinsScreen;
import threads.ThreadTimerCollectCoins;


/**
 *
 * @author Brian
 */
public class CollectCoins extends MiniGames{
    private  int MATRIZSIZE=25;
    ArrayList<Coin> coins; //false for bad coins and true for good coins
    int score;
    static Random randomNumber = new Random(); 
    private static final String RESOURCEPATH = "/CollectCoins/";
    public CollectCoins(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
        coins=new ArrayList<>();
        score=0;
    }

    @Override
    public void startGame() {
        this.board.setVisible(false);
        this.generateBoard();
        gamePanel=new CollectCoinsScreen(this);
        gamePanel.setVisible(true);
        Thread t = new ThreadTimerCollectCoins(gamePanel);
        t.start();
    }

    @Override
    public void endGame() {
        this.board.setVisible(true);
        this.gamePanel.dispose();
    }

    @Override
    public void playTurn() {
        for (Player player : players) {
            if (player.isMyTurn) {
                player.isMyTurn=false;
                player.miniGame.startGame();
            }
        }
    }
    
    public void coinClicked(java.awt.event.MouseEvent e){
        CollectCoinsScreen screen = (CollectCoinsScreen)gamePanel;
        for (int i = 0; i < screen.getCoinsLabel().size(); i++) {
            if(((JLabel)e.getSource()).equals(screen.getCoinsLabel().get(i))){
                ((JLabel)e.getSource()).setIcon(null);
                if(coins.get(i).type){
                    int num=randomNumber.nextInt(1,11);
                    score+=num;
                    screen.getJtPoints().setText("Score: "+score);
                    screen.getJtValueCollected().setText("Last collected: "+num);
                    
                }else{
                    int num=randomNumber.nextInt(-10,0); 
                    score+=num;
                    screen.getJtPoints().setText("Score: "+score);
                    screen.getJtValueCollected().setText("Last collected: "+num);
                }
                    
            
            }
                
        }

        
    
    }
    
    public void generateBoard(){
       int labelWidth = 23; 
       int labelHeight = 23; 
       ImageIcon image1=LoadImage.loadImageAdjusted(RESOURCEPATH+"coin.png", labelWidth, labelHeight);
       ImageIcon image2=LoadImage.loadImageAdjusted(RESOURCEPATH+"bad_coin.png", labelWidth, labelHeight);
         
        int halfCells = MATRIZSIZE * MATRIZSIZE / 2+1;
        for (int i = 0; i < halfCells; i++) {

            coins.add(new Coin(image1,true));
            coins.add(new Coin(image2,false));
        }
        Collections.shuffle(coins);
        
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public static String getRESOURCEPATH() {
        return RESOURCEPATH;
    }

    public int getScore() {
        return score;
    }
    
    
}



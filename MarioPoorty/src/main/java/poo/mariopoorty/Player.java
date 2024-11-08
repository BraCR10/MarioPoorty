package poo.mariopoorty;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import poo.mariopoorty.minigames.BomberMario;
import poo.mariopoorty.minigames.CatchTheCat;
import poo.mariopoorty.minigames.CollectCoins;
import poo.mariopoorty.minigames.GuessWho;
import poo.mariopoorty.minigames.MemoryPath;
import poo.mariopoorty.minigames.MiniGames;
import poo.mariopoorty.minigames.WordSearch;


public class Player implements Runnable{
    public Socket socket;
    
    public DataOutputStream out;
    public DataInputStream in;
    public Board board;
    
    
    public int Position;
    public String Condition;
    public boolean isMyTurn = false;
    
    public String name;
    private Thread thread;
    
    public JFrame miniGameScreen=null;
    public MiniGames miniGame ;
   
    //TODO:DELETE
    private static ArrayList<Player> players = new ArrayList<>();

    
    
    
    
    public Player() {
        try {
            this.socket = new Socket("localhost", 12);
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());
            
            this.board = new Board(this, ReadGameBoard());   
            
            this.thread = new Thread(this, "Game");
            this.thread.start();
            
            
            //TODO
            players.add(this);
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String[] ReadGameBoard(){
        System.out.println("Reading Board....\n");

        try {
           
            String serializedArray = in.readUTF();
            String[] receivedArray = serializedArray.split(",");
            System.out.println("Read it...");
            return receivedArray;
            
        } catch (Exception e) { 
            String[] receivedArray = new String[28];
            return receivedArray;
        }
    }
    public static void main(String[] args) {
        new Player();
    }
 
    
    @Override
    public void run() {
        
        try{
            while (true) {
                System.out.println("Begginig theard...\n");
                String serverMessage = in.readUTF();
                System.out.println(serverMessage);

                if (serverMessage.equals("Your Turn")) {
                    isMyTurn=true;
                    initSearchWord();
                    isMyTurn=true;
                    initMemoryPath();
                    isMyTurn=true;
                    initCatchTheCat();
                    isMyTurn=true;
                    initBomberMario();
                    isMyTurn=true;
                    initGuessWho();
                    isMyTurn=true;
                    initCollectCoins();
                }

                this.board.updateBoard();
            }
        } catch (IOException e){
            System.out.println("error...\n");
        }
    }
    
    void initSearchWord(){
        //Test
        String description ="The player will have 2 minutes to find the 4 " +
        "words and win. If the player of the turn wins, he can roll the dice in his next turn, "
        + "otherwise he plays the same box again.";
        miniGame = new WordSearch(players,"Search Word",description,1,miniGameScreen,board);
        miniGame.playTurn();
    }
    
    void initMemoryPath(){
        //Test
        String description="The player must complete the path in 3 attempts without making a mistake. "
                + "If you reach the end of the road, you win.If in one of the attempts you do not get to the goal, "
                + "you must return to the beginning of the game. If the player in the turn wins, he can roll the dice on his next turn, "
                + "otherwise he plays the same square again.";
        miniGame = new MemoryPath(players, "Memory Path",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initCatchTheCat(){
        //Test
        String description="The game involves locking in the cat, preventing that this can move." +
        "You win if you lock the cat, you lose if the cat escapes by the edges.";
        miniGame = new CatchTheCat(players, "Catch The Cat",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initBomberMario(){
        //Test
        String description=" A treasure will be hidden that will occupy 4 boxes. The player has 7 bombs to find the treasure.";
        miniGame = new BomberMario(players, "Bomber Mario",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initGuessWho(){
        //Test
        String description="The game will give the player between 4 and 8 random cells to discover the image. "
                + "After discovering all the cells you have choice, you must choose which is the hidden character. "
                + "If you hit it wins.";
        miniGame = new GuessWho(players, "Guess who",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initCollectCoins(){
        //Test
        String description="The coins, both good and bad, each have a value between +-1 and +-10 (random). "
                + "The good coins have a positive value, the bad coins a negative one."
                + " The player will have 30 seconds, 45 seconds or 60 seconds (random) to select as many squares as he wants and can. Each cell will have a coin, "
                + "at the end all coins of the collection are added up and if the value is positive, the game wins.";
        miniGame = new CollectCoins(players, "Collect Coins",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
        

}

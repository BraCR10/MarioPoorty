package com.mycompany.proyect2;

import BoardANDPawns.DiceRoller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import minigames.BomberMario;
import minigames.CatchTheCat;
import minigames.CollectCoins;
import minigames.GuessWho;
import minigames.MemoryPath;
import minigames.MiniGames;
import minigames.WordSearch;

public class Player implements Runnable{
    public Socket socket;
    public int NumPlayer;
    
    public DataOutputStream out;
    public DataInputStream in;
    public Board board;
    
     public JFrame miniGameScreen=null;
    public MiniGames miniGame ;
    private static ArrayList<Player> players = new ArrayList<>();
    

    public String Condition = "Roll";
    public boolean isMyTurn = false;
    
    public String name = "Shy Guy";
    public JLabel Pawn; 
    private Thread thread;
    public Scanner sc = new Scanner(System.in);
   
    
    public Player(Socket socket, DataInputStream in, DataOutputStream out){
        this.socket = socket;
        this.in = in;
        this.out = out;
    }
    
    
    
    public Player() throws InterruptedException {
        try {
            // Set Sokect, input & output
            this.socket = new Socket("localhost", 122);
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());
            players.add(this);
            
            // Set name & the turn
            SetName();
            setPosition();
            
            ReciveGeneralInfo();

   
            
            this.thread = new Thread(this, "Game");
            this.thread.start();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String[] ReadGameBoard(){
        try {
            String serializedArray = this.in.readUTF();
            String[] receivedArray = serializedArray.split(",");
            System.out.println("[Board] : "+serializedArray);
            return receivedArray;
            
        } catch (Exception e) { 
            System.out.println("Something went wrong...\n");
            String[] receivedArray = new String[28];
            return receivedArray;
        }
    }
    // -------------------------------------------------------------------------
    private void SetName() throws IOException{
        

        System.out.print("Enter a your Name: ");  
        String str= sc.nextLine(); 
        
        if ("".equals(str)) {str = name;}
        
        this.out.writeUTF(str);
        
        boolean accepted = in.readBoolean();
        while (!accepted){
            System.out.print("Enter a your Name: ");  
            str= sc.nextLine(); 
            this.out.writeUTF(str);
            accepted = in.readBoolean();
        }

        
        System.out.print("Name Accepted ["+str+"]\n");  
        this.name = str;
    }

    private void setPosition() throws IOException{

        System.out.print("\nEnter a number [1-100]: ");
        int randomNumber = sc.nextInt();
        this.out.writeInt(randomNumber);
        sc.close();

    }
    
    private void ReciveGeneralInfo() throws IOException{
        this.NumPlayer = this.in.readInt(); 
        System.out.println("\n[Num Players] : "+this.NumPlayer);
  
        String serializedArrayBOARD = this.in.readUTF();
        System.out.println("[Board] : "+serializedArrayBOARD);
        String[] receivedBoard = serializedArrayBOARD.split(",");
        
        String serializedArrayCHARACTERS = this.in.readUTF();
        System.out.println("[Characters] : "+serializedArrayCHARACTERS);
        String[] receivedCharacters = serializedArrayCHARACTERS.split(",");
        
        this.board = new Board(this, NumPlayer, receivedBoard, receivedCharacters);
        
      
    }
    // -------------------------------------------------------------------------
 
    
    @Override
    public void run() {
         initSearchWord();
        try{
            
            while (true) {
                isMyTurn = in.readBoolean();
                

                if (isMyTurn) {
                    this.out.writeUTF(Condition);    
                    if ("Roll".equals(this.Condition)){ RollDice();}
                } 
                
                
                String pawnToMove = this.in.readUTF();
                this.board.MovePawn(pawnToMove, this.in.readInt());
                
                if (pawnToMove.equals(this.name)){
                    initSearchWord();
                    System.out.println("Playing...");
                    SwitchPlay();
                }

                
            }
        } catch (IOException e){
            System.out.println("[ERROR] : Player -> run()");
        }  catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private void SwitchPlay(){
        System.out.println(this.Condition);
        switch (this.Condition) {
            // ----------------------------
            case "Letters":
                initSearchWord();
                break;
            case "MemoryPath":
                initMemoryPath();
                break;
            case "CatchCat":
                initCatchTheCat();
                break;
            case "Bomber":
                initBomberMario();
                break;
            case "GuessWho":
                initGuessWho();
                break;
            case "Coins":
                initCollectCoins();
                break;
            // ----------------------------
            case "Pipe":
                
                this.Condition = "Roll";
                break;

            case "Jail":
                
                this.Condition = "Roll";
                break;
                
            case "Star":
                
                this.Condition = "Roll";
                break;
            case "FireFlower":
                
                this.Condition = "Roll";
                break;
            case "IceFlower":
                
                this.Condition = "Roll";
                break;
            case "Tanuki":
                
                this.Condition = "Roll";
                break;
            default:
                this.Condition = "Roll";
                break;
        }
    }
    
    private void RollDice() throws IOException{
        this.out.writeUTF(this.name);        
        new DiceRoller(this.out);
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
    public static void main(String[] args) throws InterruptedException {
        new Player();
    }
}

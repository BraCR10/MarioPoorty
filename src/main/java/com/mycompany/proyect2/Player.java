package com.mycompany.proyect2;

import BoardANDPawns.DiceRoller;
import BoardANDPawns.firstScreenPlayers;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import threads.ThreadPlayerChat;

public class Player implements Runnable{
    public Socket socket;
    public int NumPlayer;
    
    public DataOutputStream out;
    public ObjectInputStream inObj;
    public DataInputStream in;
    public Board board;
    
    private ArrayList<String> characterNames ;
    
     public JFrame miniGameScreen=null;
    public MiniGames miniGame ;
    private static ArrayList<Player> players = new ArrayList<>();
    public firstScreenPlayers firstScreen;

    public String Condition = "Roll";
    public boolean isMyTurn = false;
    
    public String name = "Shy Guy";
    public JLabel Pawn; 
    private Thread thread;
    public Scanner sc = new Scanner(System.in);
    public int LoseTurn = 0;
    
        //For chat services
    Socket socketPlayerChat;
    private final int CHAT_PORT = 3006;
    public ObjectOutputStream output;
    public DataOutputStream outputData;
    
    public Player(Socket socket, DataInputStream in, DataOutputStream out){
        this.socket = socket;
        this.in = in;
        this.out = out;
        
        
        //for chat
        try {
            connect();
        } catch (IOException ex) {
            
        }
    }
    
    
    
    public Player() throws InterruptedException {
        try {

            // Set Sokect, input & output
            this.socket = new Socket("localhost", 122);
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());
            this.inObj= new ObjectInputStream(socket.getInputStream());
            players.add(this);
            
            // Set name & the turn
            readListCaracters();
            firstScreen=new firstScreenPlayers(characterNames,this);
            SetName();
            //setPosition();
            
            ReciveGeneralInfo();

   
            
            this.thread = new Thread(this, "Game");
            this.thread.start();
        
            
             //For chat
            connect();

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

        boolean accepted = in.readBoolean();
        while (!accepted){
            readListCaracters();
            firstScreen.setListCharacters(characterNames);
            accepted = in.readBoolean();
        }
    }
    
    private void  readListCaracters(){
        try {
            try {
                characterNames=(ArrayList<String>)inObj.readObject();
                
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*In the first screen
    private void setPosition() throws IOException{
    }*/
    
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

    //For chat services
    public void connect() throws IOException{
        socketPlayerChat = new Socket("localhost", CHAT_PORT);
        output=new ObjectOutputStream(socketPlayerChat.getOutputStream());
        outputData=new DataOutputStream(socketPlayerChat.getOutputStream());
        
        ThreadPlayerChat t = new ThreadPlayerChat(socketPlayerChat, this);
        t.start();

    }
    // -------------------------------------------------------------------------
    @Override
    public void run() {
        try{
            
            while (true) {
                isMyTurn = in.readBoolean();
           
       

                if (isMyTurn) {
                    this.out.writeUTF(Condition);    
                    if ("Roll".equals(this.Condition)){ RollDice();}
                } 
                
                String pawnToMove;
                pawnToMove = this.in.readUTF();
                this.board.MovePawn(pawnToMove, this.in.readInt());

                
                if (pawnToMove.equals(this.name)){
                    SwitchPlay();
                }
               
                
                

                
            }
        } catch (IOException e){
            System.out.println("[ERROR] : Player -> run()");
        }  catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private void Change() throws IOException{
        String Changes = this.in.readUTF();
        System.out.println(Changes+"----------------------");
        if (!"Nothing".equals(Changes)){
            
            String Character = this.in.readUTF();
            if ("Move".equals(Changes)){
                int newPos = this.in.readInt();
                this.board.getPawn(Character).initialPosition = newPos;
                this.board.getPawn(Character).finalPosition = newPos;
                this.board.getPawn(Character).SetPosition(newPos);
            }
            if ("Freeze".equals(Changes)){
                if (Character.equals(this.name)){this.LoseTurn = -2;}
            }
        } else {System.out.println(Changes);}
    }
    
    private void SwitchPlay() throws IOException{
        
        switch (this.Condition) {
            // ----------------------------
            case "Letters":
                this.out.writeUTF("No");
                
                Change();
                initSearchWord();
                
                break;
            case "MemoryPath":
                this.out.writeUTF("No");
                Change();
                initMemoryPath();
                break;
            case "CatchCat":
                this.out.writeUTF("No");
                Change();
                initCatchTheCat();
                break;
            case "Bomber":
                this.out.writeUTF("No");
                Change();
                initBomberMario();
                break;
            case "GuessWho":
                this.out.writeUTF("No");
                Change();
                initGuessWho();
                break;
            case "Coins":
                this.out.writeUTF("No");
                Change();
                initCollectCoins();
                break;
            // ----------------------------
            case "Pipe":
                this.out.writeUTF("Move");
                this.out.writeUTF(this.name);
                
                int pos = this.board.getPawn(this.name).initialPosition;
                
                if (pos == 7){pos = 14;}
                if (pos == 14){pos = 21;}
                if (pos == 21){pos = 7;}
                
                this.out.writeInt(pos);
                
                this.Condition = "Roll";
                Change();
                try {
                    this.out.writeUTF("Done");
                } catch (IOException ex) {
                    Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "Jail":
                if (this.LoseTurn >= 0){this.out.writeUTF(this.name);}
                else{this.out.writeUTF("No");}

                
  
                this.Condition = "Roll";
                Change();
                try {
                    this.out.writeUTF("Done");
                } catch (IOException ex) {
                    Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case "Star":            
                this.out.writeUTF("Move");
                this.out.writeUTF(this.name);
                this.out.writeInt(3);
                
                this.Condition = "Roll";
                Change();
                try {
                    this.out.writeUTF("Done");
                } catch (IOException ex) {
                    Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case "FireFlower":
                this.out.writeUTF("Move");
                SelectACharacter();
                this.out.writeInt(0);
                
                this.Condition = "Roll";
                Change();
                try {
                    this.out.writeUTF("Done");
                } catch (IOException ex) {
                    Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                break;
            case "IceFlower":
                this.out.writeUTF("Freeze");
                SelectACharacter();
                
                this.Condition = "Roll";
                Change();
                try {
                    this.out.writeUTF("Done");
                } catch (IOException ex) {
                    Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case "Tanuki":
                this.out.writeUTF("Move");
                this.out.writeUTF(this.name);
                this.out.writeInt(3);
                
                this.Condition = "Roll";
                Change();
                try {
                    this.out.writeUTF("Done");
                } catch (IOException ex) {
                    Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case "Finish":
               
                try {
                    this.out.writeUTF("Win");
                } catch (IOException ex) {
                    Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            default:
                this.Condition = "Roll";
                this.out.writeUTF("No");
                Change();
                try {
                    this.out.writeUTF("Done");
                } catch (IOException ex) {
                    Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
    
    private void RollDice() throws IOException{
        this.out.writeUTF(this.name);
        if (this.LoseTurn >= 0){new DiceRoller(this.out);}
        else {System.out.println("Lost your turn");
            this.out.writeInt(0);
            this.LoseTurn++;
        }
        
    }
    
    private void SelectACharacter() throws IOException{
        this.out.writeUTF(this.Condition);  
        //new SelectCharacter(out, this.board.character);
    
    }
    
    
   void initSearchWord(){
        String description ="The player will have 2 minutes to find the 4 " +
                "words and win. If the player of the turn wins, he can roll the dice in his next turn, "
                + "otherwise he plays the same box again.";
        miniGame = new WordSearch(players,"Search Word",description,1,miniGameScreen,board);
        miniGame.playTurn();
    }

    void initMemoryPath(){
        String description="The player must complete the path in 3 attempts without making a mistake. "
                + "If you reach the end of the road, you win.If in one of the attempts you do not get to the goal, "
                + "you must return to the beginning of the game. If the player in the turn wins, he can roll the dice on his next turn, "
                + "otherwise he plays the same square again.";
        miniGame = new MemoryPath(players, "Memory Path",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initCatchTheCat(){
        String description="The game involves locking in the cat, preventing that this can move." +
                "You win if you lock the cat, you lose if the cat escapes by the edges.";
        miniGame = new CatchTheCat(players, "Catch The Cat",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initBomberMario(){
        String description=" A treasure will be hidden that will occupy 4 boxes. The player has 7 bombs to find the treasure.";
        miniGame = new BomberMario(players, "Bomber Mario",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initGuessWho(){
        String description="The game will give the player between 4 and 8 random cells to discover the image. "
                + "After discovering all the cells you have choice, you must choose which is the hidden character. "
                + "If you hit it wins.";
        miniGame = new GuessWho(players, "Guess who",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initCollectCoins(){
        String description="The coins, both good and bad, each have a value between +-1 and +-10 (random). "
                + "The good coins have a positive value, the bad coins a negative one."
                + " The player will have 30 seconds, 45 seconds or 60 seconds (random) to select as many squares as he wants and can. Each cell will have a coin, "
                + "at the end all coins of the collection are added up and if the value is positive, the game wins.";
        miniGame = new CollectCoins(players, "Collect Coins",description,1, miniGameScreen, board);
        miniGame.playTurn();
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static void main(String[] args) throws InterruptedException {
        new Player();
       
    }
}

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
        miniGame = new WordSearch(players,"Search Word","Play alone",1,miniGameScreen,board);
        miniGame.playTurn();
    }
    void initMemoryPath(){
        //Test
        miniGame = new MemoryPath(players, "Search Word","Play alone",1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initCatchTheCat(){
        //Test
        miniGame = new CatchTheCat(players, "Search Word","Play alone",1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initBomberMario(){
        //Test
        miniGame = new BomberMario(players, "Search Word","Play alone",1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initGuessWho(){
        //Test
        miniGame = new GuessWho(players, "Search Word","Play alone",1, miniGameScreen, board);
        miniGame.playTurn();
    }
    void initCollectCoins(){
        //Test
        miniGame = new CollectCoins(players, "Search Word","Play alone",1, miniGameScreen, board);
        miniGame.playTurn();
    }
    
    public static void main(String[] args) throws InterruptedException {
        new Player();
    }
}

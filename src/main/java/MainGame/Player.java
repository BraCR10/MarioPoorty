package MainGame;

import BoardPawnsDice.Board;
import BoardPawnsDice.DiceRoller;
import BoardPawnsDice.MoveTanuki;
import BoardPawnsDice.Pawn;
import BoardPawnsDice.SelectCharacterInGame;
import BoardPawnsDice.SelectCharacterScreen;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import minigames.BomberMario;
import minigames.Cards;
import minigames.CatchTheCat;
import minigames.CollectCoins;
import minigames.GuessWho;
import minigames.MemoryPath;
import minigames.MiniGames;
import minigames.SuperBrosMemory;
import minigames.TicTackToe;
import minigames.WordSearch;
import threads.ThreadPlayerChat;

public class Player implements Runnable{
    public Socket socket;
    public int NumPlayer;
    
    public DataOutputStream out;
    public DataInputStream in;
    public Board board;
    
    public JFrame miniGameScreen = null;
    public MiniGames miniGame ;
    private static ArrayList<Player> players = new ArrayList<>();
    
    public SelectCharacterScreen selectCharacterScreen;
    public String name = "Shy Guy";

    public String Condition = "Roll";
    public boolean isMyTurn = false;
    
    
    public JLabel Pawn; 
    private Thread thread;
    public Scanner sc = new Scanner(System.in);
    public int LoseTurn = 0;
    private boolean CanPlayThisTurn = true;
    
    Socket socketPlayerChat;
    private final int CHAT_PORT = 3006;
    public ObjectOutputStream output;
    public DataOutputStream outputData;
    
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
            
            if(in.readBoolean()){//to confirm acceptance
                selectCharacterScreen = new SelectCharacterScreen(this);
                SetName();
                
                ReciveGeneralInfo();
                this.selectCharacterScreen.dispose();//to delete the first sreen and start the game
                
                //start the game
                this.thread = new Thread(this, "Game");
                this.thread.start();


                 //For chat
                connect();
            }
        
        } catch (IOException e) {
            System.out.println("Sorry the server is down!!!");
        }
    
    }
    
    public void connect() throws IOException{
        socketPlayerChat = new Socket("localhost", CHAT_PORT);
        output=new ObjectOutputStream(socketPlayerChat.getOutputStream());
        outputData=new DataOutputStream(socketPlayerChat.getOutputStream());

        ThreadPlayerChat t = new ThreadPlayerChat(socketPlayerChat, this);
        t.start();
    }
    

    // -------------------------------------------------------------------------
    
    private void SetName() throws IOException{
        boolean accepted = in.readBoolean();
        while (!accepted){
            this.selectCharacterScreen.NameCaseRejected();
            accepted = in.readBoolean();
        }
        
    }
    
    public void setName(String name) {
        this.name = name;
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
            while (board.isDisplayable()) {
                isMyTurn = in.readBoolean();
                System.out.println("\n\n[NAME]: "+name+" - [STATUS]: "+Condition+" - [LoseTurn]: "+LoseTurn);
                
                System.out.println("Is my turn: "+this.isMyTurn);
                
                WriteInfoDice_A();
                ReadAfterDice_B();
                WriteCondition_C();
                ReadCondition_D();
                
                isMyTurn = false;
            }
        } catch (IOException e){
            System.out.println("[ERROR] : Player -> run()");
        }  catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    private void WriteInfoDice_A() throws IOException{
        if (isMyTurn){
            RollDice(); 
            System.out.println("[A] --------------------");
        }
    }
    
    private void ReadAfterDice_B() throws IOException, InterruptedException{
        
        String pawnToMove = this.in.readUTF();
        int steps = this.in.readInt();

        System.out.println("Character to Move : "+pawnToMove+" - "+steps);
        
        if (steps != 0){
            this.board.MovePawn(pawnToMove, steps);
        }
        
        System.out.println("[B] --------------------");
    }

    private void WriteCondition_C() throws IOException{
        if (isMyTurn){
        System.out.println("[NAME]: "+name+" - [STATUS]: "+Condition+" - [LoseTurn]: "+LoseTurn);}
        
        if(this.isMyTurn && CanPlayThisTurn){

            this.out.writeUTF(Condition);
            this.out.writeBoolean(true);
            
            System.out.println("Can Move : [TRUE] ---> "+Condition);
            
            switch (this.Condition) {
                case "Letters" -> initSearchWord();

                case "MemoryPath" -> initMemoryPath();

                case "CatchCat" -> initCatchTheCat();

                case "Bomber" -> initBomberMario();

                case "GuessWho" -> initGuessWho();

                case "Coins" -> initCollectCoins();

                case "TicTackToe" -> {
                    this.out.writeUTF("TicTackToe");
                    FinishAction();
                
                }
                
                case "Cards" -> {
                    this.out.writeUTF("Cards");
                    FinishAction();
                
                }

                case "SuperBrosMemory" -> {
                    this.out.writeUTF("SuperBrosMemory");
                    FinishAction();
                
                }                
                case "Pipe" -> {
                    this.out.writeUTF("MovePawn");
                    this.out.writeUTF(this.name);

                    int pos = this.board.getPawn(this.name).initialPosition;

                    switch (pos) {
                        case 7 -> pos = 14;
                        case 14 -> pos = 21;
                        case 21 -> pos = 7;
                        default -> {}
                    }

                    this.out.writeInt(pos);

                    this.Condition = "Roll";
                    FinishAction();
                    }

                case "Jail" -> {
                    this.out.writeUTF("FreezePawn");
                    this.out.writeUTF(this.name);
                    this.Condition = "Roll";
                    FinishAction();
                    }

                case "Star" -> {            
                    this.out.writeUTF("Reroll");
                    this.out.writeUTF(this.name);

                    this.Condition = "Roll";
                    FinishAction();
                    }

                case "FireFlower" -> {
                    this.out.writeUTF("MovePawn");
                    SelectACharacter();
                    this.out.writeInt(0);

                    this.Condition = "Roll";
                    FinishAction();
                    }

                case "IceFlower" -> {
                    this.out.writeUTF("FreezePawn");

                    SelectACharacter();

                    this.Condition = "Roll";
                    FinishAction();
                    }

                case "Tanuki" -> {
                    this.out.writeUTF("MovePawnSetAmount");
                    this.out.writeUTF(this.name);

                    MoveASetMount();

                    this.Condition = "Roll";
                    FinishAction();
                    }

                case "Finish" -> {
                        try {
                            this.out.writeUTF("Finish");
                            this.out.writeUTF(this.name);
                            this.out.writeUTF("Win");
                        } catch (IOException ex) {
                            Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                default -> {
                    this.Condition = "Roll";
                    FinishAction();
                    }
                }

            System.out.println("[C] --------------------");
            
        } else if (this.isMyTurn && !CanPlayThisTurn){
            this.out.writeUTF("Lost Turn");
            this.out.writeBoolean(false);
            FinishAction();
            System.out.println("Can Move : [FALSE] ---> "+Condition);
            System.out.println("[C] -------------------- Pass");    
        }
    } 
    
    private void ReadCondition_D() throws IOException, InterruptedException{
       
        String readName = this.in.readUTF();
        String conditionToApply = this.in.readUTF();

        System.out.println("Character to Change.: "+readName+" -[Condition]-> "+conditionToApply);
        
        switch (conditionToApply) {
            case "MovePawn" -> {
                Pawn pawn = this.board.getPawn(readName);
                int newPosition = this.in.readInt();
                pawn.Teleport(newPosition);
            }
            case "FreezePawn" -> {
                if (readName.equals(this.name)){
                    System.out.println("You've lost 2 turns");
                    LoseTurn = -2;
                }
            }
            case "MovePawnSetAmount" -> {
                Pawn pawn = this.board.getPawn(readName);
                int newPosition = this.in.readInt();
                pawn.Teleport(newPosition);
                
                if (!readName.equals(this.name)){
                    LoseTurn = -1;
                } else {
                    this.board.MovePawn(name, 0);
                }  
            }
            
            case "Cards" -> {
                initCards();
            }
            
            case "TicTackToe" -> {
                if ("TicTackToe".equals(Condition) || readName.equals(this.name)){
                    initTicTackToe();
                }
            }
            
            case "SuperBrosMemory" -> {
                if ("SuperBrosMemory".equals(Condition) || readName.equals(this.name)){
                    initSuperBrosMemory();
                }
            }
            
            case "Reroll" -> {
                if (!readName.equals(this.name)){
                    System.out.println("You've lost 1 turn");
                    LoseTurn = -1;
                }    
            }
            case "Finish" -> {
                System.out.println(readName + "WON !!!!!!!!");
                this.board.dispose();
            }
            default -> { }
        }
        
        System.out.println("[D] --------------------");
    }
    
    private void RollDice() throws IOException{
        
        this.out.writeUTF(this.name);
        
        
        CanPlayThisTurn = (this.LoseTurn >= 0);
        
        if (this.LoseTurn >= 0 && "Roll".equals(Condition)){
            new DiceRoller(this);
            System.out.println("Roll the Dice...");
        } else {
            System.out.println("Lost your turn...");
            this.LoseTurn++;
            this.out.writeInt(0);            
        }  
    }
    
    
    
    private void initTicTackToe() throws IOException{
        miniGame = new TicTackToe(players, "TicTackToe", "-", 1, miniGameScreen, board);
  
    }
    
    private void initCards() throws IOException{
        miniGame = new Cards(players, "Cards", "-", 1, miniGameScreen, board);
    }
    
    private void initSuperBrosMemory(){
        
        SuperBrosMemory window =  new SuperBrosMemory(this);
        while(window.isDisplayable()){
        }
    }
    
    private void SelectACharacter() throws IOException{
        SelectCharacterInGame window = new SelectCharacterInGame(this, this.board.charactes, this);
        while(window.isDisplayable()){
        }
    }
    
    private void MoveASetMount() throws IOException{
        MoveTanuki window = new MoveTanuki(this);
        while(window.isDisplayable()){
        }
    }
    
    private void FinishAction(){
        try {
            this.out.writeUTF("Done");
        } catch (IOException ex) {
            Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public static void main(String[] args) throws InterruptedException {
        new Player();
    }
}

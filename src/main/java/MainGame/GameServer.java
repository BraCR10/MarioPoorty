package MainGame;

import BoardPawnsDice.ServerConsoleScreen;
import com.mycompany.proyect2.Message;
import com.mycompany.proyect2.ServerPlayers;
import java.io.DataInputStream;
import java.io.IOException;


import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

import threads.ConnectionThreadChat;
import threads.ThreadCheckAllPlayersReadyServer;
import threads.ThreadReceivePlayerFirstInfoServer;
import threads.ThreadServerChat;

public class GameServer {
    private ServerSocket serverSocket;
    public ServerConsoleScreen console= new ServerConsoleScreen();
    private ArrayList<ServerPlayers> Players = new ArrayList<>();
    public int NumPlayers = 0;
    public int secondPlayerIndex;
    
    private String[] types = new String[28];
    private boolean [] fixedPositions = new boolean[28];
    
    int numeroRandom ;
    public ArrayList< ThreadReceivePlayerFirstInfoServer> threadsFirstMenuPlayers;
        
    String CharacterSelected = "Mario";
    int pos = 0;
    String Condition = "Roll";
    boolean CanReact = true;

    private final int CHAT_PORT = 3006;
    public ServerSocket serverSocketChat;
    public ArrayList<ThreadServerChat> playersChat;
    ConnectionThreadChat threadConnectionsListener;
    
    public GameServer() {
        
        this.TypesList();
        
        //------------------------------------------------------------
       //To chat service
       try {
            serverSocketChat = new ServerSocket(CHAT_PORT);//1025-65535
            playersChat=new ArrayList<>();
            threadConnectionsListener=new ConnectionThreadChat (this);
            threadConnectionsListener.start();
            console.write("Ready to chat");
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {
            
            Random random = new Random();
            numeroRandom = random.nextInt(100); 
            console.write("Random Number: " + numeroRandom);
            
            serverSocket = new ServerSocket(122); 
            threadsFirstMenuPlayers=new ArrayList<>();
            
            //to recive info of each player in parallel
            SearchPlayers();
            ThreadCheckAllPlayersReadyServer startGame=new ThreadCheckAllPlayersReadyServer(this);
            startGame.start();//when all players are ready to start, this thread start the game loop and general funtions

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // [Star the GAME] -------------------------------------------------------------------------
    
    //BroadCoast funtion for chat
     public void broadCoast(Message msj){
        
        for (ThreadServerChat player : playersChat) {
            try {
                player.output.writeObject(msj);
            } catch (IOException ex) {
              this.console.write("Unable to send a broadcoast message");
            }
        }
    
    }
       
    private void setNumPlayers(){
        while (!(1 <= NumPlayers && NumPlayers <= 6)) {
            console.write("Enter the number of players: ");
            NumPlayers = console.readInt();
        }
      
    }

    public void setOrder(){//this is used in threadStartGame to define the order to play 
       Collections.sort(Players);
    }
       
    private void SearchPlayers() {
        setNumPlayers();
        try {
             console.write("Waiting for players to connect...");
            for (int i = 0; i < NumPlayers; i++) {
               
                ServerPlayers newPlayer = new ServerPlayers(serverSocket.accept());
                Players.add(newPlayer);
                
                newPlayer.playerOut.writeBoolean(true);//to confirm acceptance
                ThreadReceivePlayerFirstInfoServer playerThread = new ThreadReceivePlayerFirstInfoServer(newPlayer,i, this);
                playerThread.start();//each thread will recive the name selected and the number
                threadsFirstMenuPlayers.add(playerThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    public ArrayList<ServerPlayers> getPlayers() {
        return Players;
    }

    public int getNumeroRandom() {
        return numeroRandom;
    }
    
     public ServerSocket getServerSocket() {
        return serverSocket;
    }
    private boolean NameExists(String newName, int MaximunPosition){
        for (int i = 0; i < MaximunPosition; i++){
            if (Players.get(i).name == null ? newName == null : Players.get(i).name.equals(newName)){return true;}
        }
        return false;
    }
       
    public void SentGeneralInfo(){
        try {
            String boardPlan = WriteGameBoard();
            
           for (int index = 0; index < NumPlayers; index++){
               Players.get(index).playerOut.writeInt(NumPlayers);
               Players.get(index).playerOut.writeUTF(boardPlan);
               Players.get(index).playerOut.writeUTF(WriteCharacters());
           } 
        } catch (Exception e) {
        }
    }
    
    public static void shuffle(String[] array, boolean[] fixedPositions) {
        Random rand = new Random();
        
        for (int i = array.length - 1; i > 0; i--) {
            if (fixedPositions[i]) {
                continue;
            }
            

            int j = rand.nextInt(i + 1);
            while (fixedPositions[j]) {
                j = rand.nextInt(i + 1);
            }

            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    
    private void  TypesList(){
        
        boolean[] fixedPositionsAux = {
            
            true, // [0]
        
            false,
            false,
            false,
            false,
            false,
            false,
            
            true, // [7]
            
            false,
            false,
            false,
            false,
            false,
            false,
            
            true, // [14]
            
            false,
            false,
            false,
            false,
            false,
            false,
            
            true, // [21]
            
            false,
            false,
            false,
            false,
            false,
            false,
        };
            
        String[] typesAux = {
            
            "Finish", // [0]
        
            "TicTackToe",
            "Letters",
            "MemoryPath",
            "SuperBrosMemory",
            "CatchCat",
            "Bomber",
            
            "Pipe", // [7]
            
            "GuessWho",
            "Coins",
            "Cards",
            "TicTackToe",
            "Letters",
            "MemoryPath",
            
            "Pipe", // [14]
            
            "SuperBrosMemory",
            "CatchCat",
            "Bomber",
            "GuessWho",
            "Coins",
            "Cards",
            
            "Pipe", // [21]
            
            "Jail",
            "Jail",
            "Star",
            "FireFlower",
            "IceFlower",
            "Tanuki",
        };    
            
        this.types = typesAux;
        this.fixedPositions = fixedPositionsAux;
        this.shuffle(types, fixedPositions);
    
    }
    
    private String WriteGameBoard(){

        StringJoiner joiner = new StringJoiner(",");
        for (String s : types) {
            joiner.add(s);
        }
        String serializedArray = joiner.toString();
        return serializedArray; 
    }

    private String WriteCharacters(){

        StringJoiner joiner = new StringJoiner(",");
       for (int index = 0; index < NumPlayers; index++){
            joiner.add(Players.get(index).name);
        }
        String serializedArray = joiner.toString();
        return serializedArray; 
    }    

    public void GameLoop() {
        boolean finished = false;
        
        
        try {
        while (!finished) {
            for (int index = 0; index < NumPlayers; index++){
                
                Players.get(index).playerOut.writeBoolean(true);
                
                
                ReadInfoDice_A(index);

                WriteAfterDice_B(index);

                ReadCondition_C(index);

                WriteCondition_D(index);

            }
        }
        this.serverSocket.close();
    
        } catch(IOException e){
        console.write("Error reading player coordinates: " + e.getMessage());
        }
    }

    
    
    public static void main(String[] args) {
        new GameServer();
    }
    
    private void ReadInfoDice_A(int index) throws IOException{
        System.out.println(Players.get(index).name+" : [A-B] --------------------");
        
        CharacterSelected = Players.get(index).playerIn.readUTF();
        System.out.print("Character Selected.: "+CharacterSelected+"  ");
        
        pos =  Players.get(index).playerIn.readInt();
        System.out.println("Steps.: "+pos);
        
        System.out.println("----------------------------\n");
    }
    
    private void WriteAfterDice_B(int index) throws IOException{
        for (int i = 0; i < NumPlayers; i++){ 
            if (i != index){Players.get(i).playerOut.writeBoolean(false);}
            Players.get(i).playerOut.writeUTF(CharacterSelected);
            Players.get(i).playerOut.writeInt(pos);
        }

    }
    
    private void ReadCondition_C(int index) throws IOException{
        System.out.println(Players.get(index).name+" : [C-D] --------------------");
        
        Condition = Players.get(index).playerIn.readUTF();
        System.out.print("Condition.: "+Condition+"  ");
        
        CanReact = Players.get(index).playerIn.readBoolean();
        System.out.println("Can React.: ["+CanReact+"]  ");

        if (CanReact){
                if ("Pipe".equals(Condition) ||"FireFlower".equals(Condition)){
                    Condition = Players.get(index).playerIn.readUTF();
                    CharacterSelected = Players.get(index).playerIn.readUTF();
                    pos = Players.get(index).playerIn.readInt();
                    
                    System.out.println("Condition to apply.: ["+Condition+"]  ");
                    System.out.println("Character Selected.: ["+CharacterSelected+"]  ");
                    System.out.println("Position to move char.: ["+pos+"]  ");
                }

                if ("Tanuki".equals(Condition)){
                    Condition = Players.get(index).playerIn.readUTF();
                    CharacterSelected = Players.get(index).playerIn.readUTF();
                    pos = Players.get(index).playerIn.readInt();
                    
                  
                    System.out.println("Condition to apply.: ["+Condition+"]  ");
                    System.out.println("Character Selected.: ["+CharacterSelected+"]  ");
                    System.out.println("Position to move char.: ["+pos+"]  ");
                }   

                if ("TicTackToe".equals(Condition)){
                    Condition = Players.get(index).playerIn.readUTF();
                    secondPlayerIndex = getRandomExcluding(index, Players.size());
                    CharacterSelected = Players.get(secondPlayerIndex).name;
                }
                
                if ("Cards".equals(Condition)){
                    Condition = Players.get(index).playerIn.readUTF();
                }
                
                if ("SuperBrosMemory".equals(Condition)){
                    Condition = Players.get(index).playerIn.readUTF();
                    secondPlayerIndex = getRandomExcluding(index, Players.size());
                    CharacterSelected = Players.get(secondPlayerIndex).name;
                }
                
                if ("Star".equals(Condition)){
                    Condition = Players.get(index).playerIn.readUTF();
                    CharacterSelected = Players.get(index).playerIn.readUTF();
                    
                    System.out.println("Condition to apply.: ["+Condition+"]  ");
                    System.out.println("Character Selected.: ["+CharacterSelected+"]  ");
                }   

                if("Jail".equals(Condition)||"IceFlower".equals(Condition)){
                    Condition = Players.get(index).playerIn.readUTF();
                    CharacterSelected = Players.get(index).playerIn.readUTF();
                    
                    System.out.println("Condition to apply.: ["+Condition+"]  ");
                    System.out.println("Character Selected.: ["+CharacterSelected+"]  ");
                }
                
                if ("Finish".equals(Condition)){
                    Condition = Players.get(index).playerIn.readUTF();
                    CharacterSelected = Players.get(index).playerIn.readUTF();
                
                }
        }
        
        String ConditionWinnig = Players.get(index).playerIn.readUTF();
        System.out.println("ACTIONS FINISHED (with statues).: "+ConditionWinnig);
        
        
        if (ConditionWinnig.equals("Win")){
            System.out.println("-------------[WIN]------------");
            console.write(CharacterSelected + " WON !!!!!!!!!!!!!!!!!!!!!!!!");
        }
        
        System.out.println("----------------------------\n");

    }
    
    private void WriteCondition_D(int index) throws IOException{

        for (int i = 0; i < NumPlayers; i++){
            Players.get(i).playerOut.writeUTF(CharacterSelected);
            Players.get(i).playerOut.writeUTF(Condition);

            if ("MovePawn".equals(Condition)){Players.get(i).playerOut.writeInt(pos); }
            if ("MovePawnSetAmount".equals(Condition)){Players.get(i).playerOut.writeInt(pos); }
            
        }
        
        if ("TicTackToe".equals(Condition) && CanReact){TicTackToeLoop(index, secondPlayerIndex);}
        if ("SuperBrosMemory".equals(Condition) && CanReact){SuperBrosMemoryLoop(index,secondPlayerIndex);}
        if ("Cards".equals(Condition) && CanReact){CardsLoop(index);}
        
    } 

    
    
    private void TicTackToeLoop(int indexPlayer1, int indexPlayer2) throws IOException{
        boolean finished = false;
        
        ServerPlayers[] players = new ServerPlayers[2];
        
        players[0] = Players.get(indexPlayer1);
        players[0].playerOut.writeUTF("X");
        
        players[1] = Players.get(indexPlayer2); 
        players[1].playerOut.writeUTF("O");
        
        while (!finished){
            
            for (int index = 0; (index < 2 && !finished); index++){
                players[index].playerOut.writeBoolean(true);
                int newpos = players[index].playerIn.readInt();
                String Simbol =  players[index].playerIn.readUTF();
            
                for (int i = 0; i < 2; i++){
                    if (i != index){players[i].playerOut.writeBoolean(false);}
                    
                    players[i].playerOut.writeInt(newpos);
                    players[i].playerOut.writeUTF(Simbol);
                }
                
                String str = players[index].playerIn.readUTF();
                String codition = players[index].playerIn.readUTF();
                
                switch (codition) {
                    case "Win" -> {codition = "Win"; finished = true; }
                    case "Draw" -> {codition = "Draw"; finished = true;}
                    case "Continue" -> {codition = "Continue";}
                }
                
                
                for (int i = 0; i < 2; i++){ 
                    players[i].playerOut.writeUTF(str);
                    players[i].playerOut.writeUTF(codition);
                }
            }
        }
        System.out.println("[TicTackToe ----> [FINISHED]");
    }

    private void SuperBrosMemoryLoop (int indexPlayer1, int indexPlayer2) throws IOException{
        boolean win = false;
        
        ServerPlayers[] players = new ServerPlayers[2];
        
        players[0] = Players.get(indexPlayer1);
        players[1] = Players.get(indexPlayer2); 

        int Player1Score = players[0].playerIn.readInt();
        int Player2Score = players[1].playerIn.readInt();

        if (Player1Score > Player2Score){
            win = true;
        } else if (Player1Score == Player2Score){
            win = false;
        }else {
           win = false; 
        }
        
        players[0].playerOut.writeUTF(players[0].name);
        players[0].playerOut.writeBoolean(win);
        
        players[1].playerOut.writeUTF(players[0].name);
        players[1].playerOut.writeBoolean(win);
        
       System.out.println("SuperBrosMemory ----> [FINISHED]");
    }
    
    int CardNumber = 2;
    String CardSimbol = "diamonds";
    private final String[] cards = {"hearts", "clubs", "spades", "diamonds"};
    
    private int getCardGrade(String str){
        for (int i = 0; i < cards.length; i++){
            if (cards[i].equals(str)){return i;}
        }
        return -1;
    }
    
    private void CardsLoop (int index) throws IOException{
        boolean win = false;
               

        for (ServerPlayers p : Players){
            int newInt = p.playerIn.readInt();
            String newString = p.playerIn.readUTF(); 

            compareCards(newInt, newString);
        }
        
        for (ServerPlayers p : Players){
            p.playerOut.writeUTF(Players.get(index).name);
            p.playerOut.writeInt(CardNumber);
            p.playerOut.writeUTF(CardSimbol);
            
        }
       
       System.out.println("Cards ----> [FINISHED]");
    }
    
    private void compareCards(int num, String smb){
        if (num > CardNumber){
            CardNumber = num;
            CardSimbol = smb;
        } else if (num == CardNumber){
            if (getCardGrade(smb) < getCardGrade(CardSimbol)){
                CardNumber = num;
                CardSimbol = smb;
            }
        }

    }
    
    
    public int getRandomExcluding(int excludedNumber, int max) {
        int min = 0;
        
        Random rand = new Random();
        int randomNum;
        
        do {
            randomNum = rand.nextInt((max - min - 1) + 1) + min;
        } while (randomNum == excludedNumber);
        
        return randomNum;
        
    }
}


package com.mycompany.proyect2;

import threads.ThreadCheckAllPlayersReadyServer;
import threads.ThreadReceivePlayerFirstInfoServer;
import BoardANDPawns.ServerConsoleScreen;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ConnectionThreadChat;
import threads.ThreadServerChat;

public class GameServer {
    //console
     public ServerConsoleScreen console= new ServerConsoleScreen();
        
    //Game server settings
    private ServerSocket serverSocket;
    private ArrayList<ServerPlayers> Players = new ArrayList<>();
    private String[] types = new String[28];
    public int NumPlayers = 0;
    private boolean [] fixedPositions = new boolean[28];
    
    //Wait for each player to estar the game settings
    int numeroRandom ;
    public ArrayList< ThreadReceivePlayerFirstInfoServer> threadsFirstMenuPlayers;//a thread for each player, when the player are ready to start, tje thread will stop
    
    //Chat server settings
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
       //-------------------------------------------------------
       //start the game
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
    //--------------------------------------------------------------------------
    //functions to manage the entry of each player’s name and number to get started
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
    //end of name and number settings
    //--------------------------------------------------------------------------

   
    
    
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
                console.write("Turn :"+Players.get(index).name);
                
                String condition = Players.get(index).playerIn.readUTF();

                ReadCondition(condition, index);
                ReadChangesInBoard(index);
                
                if("Win".equals(Players.get(index).playerIn.readUTF())){
                    finished = true;
                    break;
                } 
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

    
    
    public void ReadCondition(String condition, int index){
        try{
        String name;
        switch (condition) {
            case "Roll":
                name = Players.get(index).playerIn.readUTF();
                int position =  Players.get(index).playerIn.readInt();
                
                for (int i = 0; i < NumPlayers; i++){
                    if (i != index){Players.get(i).playerOut.writeBoolean(false);}
                    Players.get(i).playerOut.writeUTF(name);
                    Players.get(i).playerOut.writeInt(position);
                }
                break;
 
            default:
                for (int i = 0; i < NumPlayers; i++){
                    if (i != index){Players.get(i).playerOut.writeBoolean(false);}
                    Players.get(i).playerOut.writeUTF(Players.get(i).name);
                    Players.get(i).playerOut.writeInt(-1);
                    ReadChangesInBoard(index);
                }
                break;

        }
        }catch(IOException e){}

    }
    
    public void ReadChangesInBoard(int index) throws IOException{
        String Changes = Players.get(index).playerIn.readUTF();
        if (!"No".equals(Changes)){
            if ("Move".equals(Changes)){MoveCharacter(index);}
            if ("Freeze".equals(Changes)){FreezeCharacter(index);}
        } else {
            for (int i = 0; i < NumPlayers; i++){
                Players.get(i).playerOut.writeUTF("Nothing");
            }
        }
    }
    
    public void MoveCharacter(int index) throws IOException{
        String cha = Players.get(index).playerIn.readUTF();
        int position = Players.get(index).playerIn.readInt();
        
        for (int i = 0; i < NumPlayers; i++){
            Players.get(i).playerOut.writeUTF("Move");
            Players.get(i).playerOut.writeUTF(cha);
            Players.get(i).playerOut.writeInt(position);
        }
    }
    
    public void FreezeCharacter(int index) throws IOException{
    String cha = Players.get(index).playerIn.readUTF();
        
        for (int i = 0; i < NumPlayers; i++){
            Players.get(i).playerOut.writeUTF("Freeze");
            Players.get(i).playerOut.writeUTF(cha);
        }
    }
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


    
}


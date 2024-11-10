package com.mycompany.proyect2;

import BoardANDPawns.ServerConsoleScreen;
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
import threads.ThreadServerChat;

public class GameServer {
    private ServerSocket serverSocket;
    public ServerConsoleScreen console= new ServerConsoleScreen();
    private ArrayList<ServerPlayers> Players = new ArrayList<>();
    int NumPlayers = 0;

    private String[] types = new String[28];
    private ArrayList<String> characterNames = new ArrayList<>();
    private boolean [] fixedPositions = new boolean[28];
    
    //Chat variables
    private final int CHAT_PORT = 3006;
    public ServerSocket serverSocketChat;
    public ArrayList<ThreadServerChat> players;
    ConnectionThreadChat threadConnectionsListener;
    
    public GameServer() {
        
        this.TypesList();
          //To chat service
       try {
            serverSocketChat = new ServerSocket(CHAT_PORT);//1025-65535
            players=new ArrayList<>();
            threadConnectionsListener=new ConnectionThreadChat (this);
            threadConnectionsListener.start();
            console.write("Ready to chat");
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
       //-------------------------------------------------------
        try {
            loadCharacterNames();
            
            serverSocket = new ServerSocket(122); 
            
            SearchPlayers();
            setOrder();
            
            for (int i = 0; i < NumPlayers; i++){
                console.write("["+Players.get(i).Index+"] "+Players.get(i).name);
            }
            
            SentGeneralInfo();
            
            GameLoop();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // [Star the GAME] -------------------------------------------------------------------------

    private void setNumPlayers(){
        
        
        while (!(1 <= NumPlayers && NumPlayers <= 6)) {
            console.write("Enter the number of players: ");
            NumPlayers = console.readInt();
        }
      
    }

    public void setOrder() throws IOException{
        Random random = new Random();
        
        int numeroRandom = random.nextInt(100); 
         console.write("Random Number: " + numeroRandom);
        

        for (int i = 0; i < NumPlayers;i++){
            Players.get(i).NumberStart = numeroRandom;
            Players.get(i).Index = Players.get(i).playerIn.readInt();
            
        }
        
       Collections.sort(Players);

    }
    private void SearchPlayers() {
        setNumPlayers();
        try {
            for (int i = 0; i < NumPlayers; i++) {
                console.write("Waiting for players to connect...");
                ServerPlayers newPlayer = new ServerPlayers(serverSocket.accept());
                Players.add(newPlayer);

                
                ThreadCatchNameServer playerThread = new ThreadCatchNameServer(newPlayer, this);
                playerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private boolean NameExists(String newName, int MaximunPosition){
        for (int i = 0; i < MaximunPosition; i++){
            console.write(Players.get(i).name);
            if (Players.get(i).name == null ? newName == null : Players.get(i).name.equals(newName)){return true;}
        }
        return false;
    }
    
    private void loadCharacterNames(){
        this.characterNames.add("Mario");
        this.characterNames.add("Luigi");
        this.characterNames.add("Bowser");
        this.characterNames.add("Kamek");
        this.characterNames.add("Dry Bones");
        this.characterNames.add("Donkey Kong");
        this.characterNames.add("Peach");
        this.characterNames.add("Shy Guy");
        this.characterNames.add("Toad");
        this.characterNames.add("Yoshi");
    }
    private void SentGeneralInfo(){
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

    private void GameLoop() {
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
        
        for (ThreadServerChat player : players) {
            try {
                player.output.writeObject(msj);
            } catch (IOException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }

    public ArrayList<String> getCharacterNames() {
        return characterNames;
    }

    public ArrayList<ServerPlayers> getPlayers() {
        return Players;
    }

    public void setCharacterNames(ArrayList<String> characterNames) {
        this.characterNames = characterNames;
    }
    
}


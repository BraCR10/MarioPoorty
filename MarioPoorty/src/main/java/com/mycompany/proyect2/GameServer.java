package com.mycompany.proyect2;

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

public class GameServer {
    private ServerSocket serverSocket;
    
    private ArrayList<ServerPlayers> Players = new ArrayList<>();
    int NumPlayers = 0;

    private String[] types = new String[28];
    private boolean [] fixedPositions = new boolean[28];
    
    public GameServer() {
        
        this.TypesList();
        
        try {
            serverSocket = new ServerSocket(122); 
            
            
            SearchPlayers();
            setOrder();
            
            for (int i = 0; i < NumPlayers; i++){
                System.out.println("["+Players.get(i).Index+"] "+Players.get(i).name);
            }
            
            SentGeneralInfo();
            
            GameLoop();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // [Star the GAME] -------------------------------------------------------------------------

    private void setNumPlayers(){
        
        Scanner scanner = new Scanner(System.in);
        
        while (!(1 <= NumPlayers && NumPlayers <= 6)) {
            System.out.print("Enter the number of players: ");
            NumPlayers = scanner.nextInt();
        }
        scanner.close();
    }

    public void setOrder() throws IOException{
        Random random = new Random();
        
        int numeroRandom = random.nextInt(100); 
         System.out.println("Random Number: " + numeroRandom);
        

        for (int i = 0; i < NumPlayers;i++){
            Players.get(i).NumberStart = numeroRandom;
            Players.get(i).Index = Players.get(i).playerIn.readInt();
            
        }
        
       Collections.sort(Players);

    }
       
    private void SearchPlayers(){
        setNumPlayers();
        try {
            
            for (int i = 0; i < NumPlayers; i++){
                
                System.out.println("Waiting for players to connect...");
                Players.add(new ServerPlayers(serverSocket.accept()));

                String newName = Players.get(i).playerIn.readUTF();

                while (NameExists(newName,i)){
                    Players.get(i).playerOut.writeBoolean(false);
                    newName = Players.get(i).playerIn.readUTF();
                }
                Players.get(i).playerOut.writeBoolean(true);
                Players.get(i).name = newName;
                System.out.println("New player add : "+ newName);
                
            }

        } catch (IOException e) {e.printStackTrace();}
        
    }
    
    private boolean NameExists(String newName, int MaximunPosition){
        for (int i = 0; i < MaximunPosition; i++){
            System.out.println(Players.get(i).name);
            if (Players.get(i).name == null ? newName == null : Players.get(i).name.equals(newName)){return true;}
        }
        return false;
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
        
        try {
        while (true) {
            for (int index = 0; index < NumPlayers; index++){
                Players.get(index).playerOut.writeBoolean(true);
                String condition = Players.get(index).playerIn.readUTF();
                
                ReadCondition(condition, index);

            }
        }
    } catch(IOException e){
        System.out.println("Error reading player coordinates: " + e.getMessage());
        }
    }

    
    public static void main(String[] args) {
        new GameServer();
    }

    public void ReadCondition(String condition, int index){
        try{
        switch (condition) {
            //case "Roll":
                default:
                String name = Players.get(index).playerIn.readUTF();
                int position =  Players.get(index).playerIn.readInt();
                
                for (int i = 0; i < NumPlayers; i++){
                    if (i != index){Players.get(i).playerOut.writeBoolean(false);}
                    Players.get(i).playerOut.writeUTF(name);
                    Players.get(i).playerOut.writeInt(position);
                }
                break;

        }
        }catch(IOException e){}

    }
    
    
}


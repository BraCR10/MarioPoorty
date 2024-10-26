package poo.mariopoorty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;


public class GameServer {
    private ServerSocket serverSocket;
    
    private Socket[] players = new Socket[6];
    private DataOutputStream[] playerOut = new DataOutputStream[6];
    private DataInputStream[] playerIn = new DataInputStream[6];
    
    int NumPlayers = 0;

    private String[] types = new String[28];
    private boolean [] fixedPositions = new boolean[28];
    
    public GameServer() {
        
        this.TypesList();
        
        try {
            serverSocket = new ServerSocket(123); 
            Scanner scanner = new Scanner(System.in);
            
            while (!(2 <= NumPlayers && NumPlayers <= 6)) {
                
                System.out.print("Enter the number of players: ");
                NumPlayers = scanner.nextInt();
                
            }
            scanner.close();
            
                       
            for (int i = 0; i < NumPlayers; i++){
                SearchPlayers(i);
            }
            
            gameLoop();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SearchPlayers(int i){
        try {
            System.out.println("Waiting for players to connect...");
            players[i] = serverSocket.accept();

            System.out.println("Player "+ (i+1) +" connected.");

            playerOut[i] = new DataOutputStream(players[i].getOutputStream());
            playerIn[i] = new DataInputStream(players[i].getInputStream());

            WriteGameBoard(i);
        } catch (IOException e) {
            e.printStackTrace();
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
    
    private void WriteGameBoard(int playerIndex){
            try {
                StringJoiner joiner = new StringJoiner(",");
            for (String s : types) {
                joiner.add(s);
            }
            String serializedArray = joiner.toString();
            
            playerOut[playerIndex].writeUTF(serializedArray);

            } catch (Exception e) {
            
            }
        
    }
    public static void main(String[] args) {
        new GameServer();
    }
    
    private void gameLoop() {
        System.out.println("Beginnig Loop...\n");
        
        try {
        while (true) {
            for (int index = 0; index < NumPlayers; index++){

                    System.out.println("Waiting for player " + (index + 1) + "'s move...\n");
                    playerOut[index].writeUTF("Your Turn");
                    System.out.println("...\n");
                    int posX = playerIn[index].readInt();
                    int posY = playerIn[index].readInt();
                    System.out.println(posX+", " + posY+"\n");
                    
                    
            }
        }
    } catch(IOException e){
        System.out.println("Error reading player coordinates: " + e.getMessage());
    }
    }

}

package poo.mariopoorty;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
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
                    
                    
                }

                this.board.updateBoard();
            }
        } catch (IOException e){
            System.out.println("error...\n");
        }
    }
    
    void initSearchWord(){
        //Test
        miniGame = new WordSearch(players,"Search Word","Play alone",1,miniGameScreen,board);
        miniGame.playTurn();
    }

}

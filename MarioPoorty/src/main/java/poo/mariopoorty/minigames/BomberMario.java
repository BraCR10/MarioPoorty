/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import poo.mariopoorty.screens.WordSearchScreen;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import poo.mariopoorty.Board;
import poo.mariopoorty.Player;
import poo.mariopoorty.screens.BomberMarioScreen;
//import poo.mariopoorty.threads.ThreadTimerWordSearch;

/**
 *
 * @author Brian
 */
public class BomberMario extends MiniGames{
    ArrayList<String> wordsList;
    ArrayList<String> selectedWordsList;
    ArrayList<Point> randomChestPositions;
    int boardDisplaySize;
    static int[] enableSizes={10,15,20};
    static Random randomNumber = new Random(); 
    int bombsCounter;

    public BomberMario( ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
        
        this.wordsList = new ArrayList<>();
        
        
        this.selectedWordsList = new ArrayList<>();
        //chooseRandomWordsPosition();
        
        this.boardDisplaySize=randomNumber.nextInt(enableSizes.length);
        this.boardDisplaySize=enableSizes[boardDisplaySize];
        
        randomChestPositions=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            randomChestPositions.add(new Point(randomNumber.nextInt(boardDisplaySize),randomNumber.nextInt(boardDisplaySize)));
        }
        
        bombsCounter=3;
       
    }

    private void generateBoard(){
        
        
        /*
        for (int i = 0; i < boardDisplaySize; i++) {
            for (int j = 0; j < boardDisplaySize; j++) {
                matrizChars[i][j] = ' '; 
            }
        }
        
        String word;
        for (String i : selectedWordsList) {
            word=i;   
        }
        
        for (int i = 0; i < boardDisplaySize; i++) {
            for (int j = 0; j < boardDisplaySize; j++) {
                 if (matrizChars[i][j] == ' ') 
                    matrizChars[i][j] = (char) ('A'+randomNumber.nextInt(26) ); //A - Z
            }
        }*/
    
    
    }         
    
   
    private  void chooseRandomWordsPosition(){
        //Get 4 random words 
        while (this.selectedWordsList.size()<4) {
             int number =randomNumber.nextInt(100);
             if(!selectedWordsList.contains(wordsList.get(number)))//to avoid the same word
                selectedWordsList.add(wordsList.get(number));
        }
    }
    
  
    
    private void startBoard(){
       // try {
            BomberMarioScreen screen = (BomberMarioScreen) gamePanel;
            screen.setBoardSize(boardDisplaySize);
            //screen.setBoardChars(matrizChars);
           // screen.setWord1(selectedWordsList.get(0));
            //screen.setWord2(selectedWordsList.get(1));
            //screen.setWord3(selectedWordsList.get(2));
            //screen.setWord4(selectedWordsList.get(3));
           screen.drawScreen();
            //Thread timer = new ThreadTimerWordSearch(this,screen);
            //timer.start();
            
        //} catch (Exception e) {
          //  throw new UnsupportedOperationException("Must be a proper screen."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //}  
    }
    
    @Override
    public void startGame() {
//        this.board.setVisible(false);
        this.gamePanel=new BomberMarioScreen(this);
        //generateBoard();
//        startBoard();
         startBoard();
        this.gamePanel.setVisible(true);
    }

    @Override
    public void endGame() {
        //Cleanning static vars
        BomberMario.correctButtons.clear();
        BomberMario.pressedButtons.clear();
        BomberMario.bufferWordsSeleted="";
        BomberMario.counterCollectedWords=0;
        this.board.setVisible(true);
        //Deleting the screen
        this.gamePanel.dispose();
    }

    @Override
    public void playTurn() {
        for (Player player : players) {
            if (player.isMyTurn) {
                player.isMyTurn=false;
                player.miniGame.startGame();
            }
        }
    }

    
    //Methods to check words in the screen
    private static String bufferWordsSeleted="";
    private static final ArrayList<JButton> pressedButtons = new ArrayList<>();
    private static final ArrayList<JButton> correctButtons = new ArrayList<>();
    private static int counterCollectedWords; //True for win and False for lose
    public static void wordsChecker(JButton sourceButton){
        if (!pressedButtons.contains(sourceButton)) {
            bufferWordsSeleted += sourceButton.getText();
            pressedButtons.add(sourceButton);
        }
    }
    public static void checkSeletedWord(String w1,String w2,String w3,String w4 ){
         
         if(bufferWordsSeleted.equals(w4)||
            bufferWordsSeleted.equals(w3)||
            bufferWordsSeleted.equals(w2)||
            bufferWordsSeleted.equals(w1)){
             for (JButton pressedButton : pressedButtons) {
                 correctButtons.add(pressedButton);
                 
             }
             counterCollectedWords++;
        }
        for (JButton correctButton : correctButtons) {
             correctButton.setBackground(Color.YELLOW);
         }
        pressedButtons.clear();
        bufferWordsSeleted="";
     }
    public static int getCounterCollectedWords() {
        return counterCollectedWords;
    }

    public int getBombCounter() {
        return bombsCounter;
    }

  

    
 
}


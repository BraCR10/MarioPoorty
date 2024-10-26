/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import Screens.wordSearchScreen;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import poo.mariopoorty.Player;

/**
 *
 * @author Brian
 */
public class WordSearch extends MiniGames{
    ArrayList<String> wordsList;
    ArrayList<String> selectedWordsList;
    char[][] board;
    static Random randomNumber; 
    Set<Integer> randomWordsPositions ;
    int boardDisplaySize;
    static int[] enableSize={10,15,20};
    

  

    public WordSearch(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel) {
        super(players, type, description, currentPlayers,gamePanel);
        
        randomNumber = new Random();
        this.randomWordsPositions= new HashSet<>();
        chooseRandomWordsPosition();
        
        this.wordsList = new ArrayList<>();
        loadWords();
        
        this.selectedWordsList = new ArrayList<>();
        this.boardDisplaySize=randomNumber.nextInt(0,2);
        this.boardDisplaySize=enableSize[boardDisplaySize];  
        this.board = new char[boardDisplaySize][boardDisplaySize];
        
    }
   
    private void generateBoard(){
        for (int i = 0; i < boardDisplaySize; i++) {
            for (int j = 0; j < boardDisplaySize; j++) {
                board[i][j] = ' '; 
            }
        }
        
        String word;
        
        for (int i : randomWordsPositions) {
            word=this.wordsList.get(i);
            this.selectedWordsList.add(word);
            placeWord(word);
        }
        
        for (int i = 0; i < boardDisplaySize; i++) {
            for (int j = 0; j < boardDisplaySize; j++) {
                 if (board[i][j] == ' ') 
                    board[i][j] = (char) ('A'+randomNumber.nextInt(26) ); //A - Z
            }
        }
    
    
    }         
    private void placeWord(String word) {
        int length = word.length(); // Get the length of the word
        boolean placed = false; // Flag to check if the word is placed

        while (!placed) {
            // Select a random direction (0 = horizontal, 1 = vertical, 2 = diagonal)
            int direction = randomNumber.nextInt(3);

            // Select a random starting position for the word
            int row = randomNumber.nextInt(boardDisplaySize);
            int col = randomNumber.nextInt(boardDisplaySize);
            boolean canPlace ; // Flag to check if the word can be placed
            switch (direction) {
                case 0: // Horizontal
                    // Check if the word fits in the horizontal direction
                    if (col + length <= boardDisplaySize) {
                        canPlace = true; // Flag to check if the word can be placed
                        for (int j = 0; j < length; j++) {
                            // Check if each position is empty
                            if (board[row][col + j] != ' ') {
                                canPlace = false; // Cannot place if any position is occupied
                                break;
                            }
                        }

                        // Place the word if there's enough space
                        if (canPlace) {
                            for (int j = 0; j < length; j++) {
                                board[row][col + j] = word.charAt(j); // Place the letter in the matrix
                            }
                            placed = true; // Set placed flag to true
                        }
                    }
                    break;

                case 1: // Vertical
                    // Check if the word fits in the vertical direction
                    if (row + length <= boardDisplaySize) {
                         canPlace = true; // Flag to check if the word can be placed
                        for (int j = 0; j < length; j++) {
                            // Check if each position is empty
                            if (board[row + j][col] != ' ') {
                                canPlace = false; // Cannot place if any position is occupied
                                break;
                            }
                        }

                        // Place the word if there's enough space
                        if (canPlace) {
                            for (int j = 0; j < length; j++) {
                                board[row + j][col] = word.charAt(j); // Place the letter in the matrix
                            }
                            placed = true; // Set placed flag to true
                        }
                    }
                    break;

                default: // Diagonal
                    // Check if the word fits in the diagonal direction
                    if (row + length <= boardDisplaySize && col + length <= boardDisplaySize) {
                        canPlace = true; // Flag to check if the word can be placed
                        for (int j = 0; j < length; j++) {
                            // Check if each diagonal position is empty
                            if (board[row + j][col + j] != ' ') {
                                canPlace = false; // Cannot place if any position is occupied
                                break;
                            }
                        }

                        // Place the word if there's enough space
                        if (canPlace) {
                            for (int j = 0; j < length; j++) {
                                board[row + j][col + j] = word.charAt(j); // Place the letter in the matrix
                            }
                            placed = true; // Set placed flag to true
                        }
                    }
                    break;
            }
        }
    }
   
    private  void chooseRandomWordsPosition(){
        //Get 4 random words 
        while (this.randomWordsPositions.size()<4) {
             this.randomWordsPositions.add(randomNumber.nextInt(100));
        }
    }
    
    private  void loadWords(){
        ReadFiles.readWords("words.txt", this.wordsList);
    }
    
    private void startBoard(){
        try {
            wordSearchScreen screen = (wordSearchScreen) gamePanel;
            screen.setBoardSize(boardDisplaySize);
            screen.setBoardChars(board);
            screen.setWord1(selectedWordsList.get(0));
            screen.setWord2(selectedWordsList.get(1));
            screen.setWord3(selectedWordsList.get(2));
            screen.setWord4(selectedWordsList.get(3));
            screen.drawScreen();
            
        } catch (Exception e) {
            throw new UnsupportedOperationException("Must be a proper screen."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        
        
    }
    
    @Override
    public void startGame() {
        generateBoard();
        startBoard();
    }

    @Override
    public void endGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void playTurn(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public char[][] getBoard() {
        return board;
    }
    
    
    private static String bufferWordsSeleted="";
    private static ArrayList<javax.swing.JButton> pressedButtons = new ArrayList<>();
    private static ArrayList<javax.swing.JButton> correctButtons = new ArrayList<>();
    public static void wordsChecker(javax.swing.JButton sourceButton){
        // Check if the button has already been pressed
        if (!pressedButtons.contains(sourceButton)) {
            // Append the button's text to the buffer
            bufferWordsSeleted += sourceButton.getText();
            // Add the button to the pressed list
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

            
        }
        for (JButton correctButton : correctButtons) {
             correctButton.setBackground(Color.YELLOW);
         }
        pressedButtons.clear();
        bufferWordsSeleted="";
     }
 
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JFrame;
import poo.mariopoorty.Player;

/**
 *
 * @author Brian
 */
public class WordSearch {
    static ArrayList<String> wordsList;
    static char[][] board;
    static Random randomNumber; 
    static Set<Integer> randomWordsPositions ;
    static int boardDisplaySize;
    static int[] enableSize={10,15,20};

    public WordSearch() {
        //super(players, type, description, currentPlayers, gamePanel);
        
        randomNumber = new Random();
        randomWordsPositions= new HashSet<>();
        
        wordsList = new ArrayList<>();
        loadWords();
        
        // randomWordsPositions
        chooseRandomWordsPosition();
        
        //Get a random size 
        boardDisplaySize=randomNumber.nextInt(0,2);
        boardDisplaySize=enableSize[boardDisplaySize];  
        board = new char[boardDisplaySize][boardDisplaySize];
        
        generateBoard();
        
        for (int i = 0; i < boardDisplaySize; i++) {
            for (int j = 0; j < boardDisplaySize; j++) {
                System.out.println(board[i][j]);
            }
        }
    }
   
    private void generateBoard(){
        for (int i = 0; i < boardDisplaySize; i++) {
            for (int j = 0; j < boardDisplaySize; j++) {
                board[i][j] = ' '; 
            }
        }
        
        String word;
        for (int i : randomWordsPositions) {
            word=wordsList.get(i);
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
   
    /*
    @Override
    void startGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    void endGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    void playTurn(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
    
    private static void chooseRandomWordsPosition(){
        //Get 4 random words 
        while (randomWordsPositions.size()<4) {
             randomWordsPositions.add(randomNumber.nextInt(100));
        }
    }
    
    private static void loadWords(){
        ReadFiles.readWords("words.txt", wordsList);
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import poo.mariopoorty.Board;
import poo.mariopoorty.Player;
import poo.mariopoorty.screens.MemoryPathScreen;
import poo.mariopoorty.threads.ThreadCellVerifierMemoryPath;
import poo.mariopoorty.threads.ThreadCharacterMemoryPathMovement;

/**
 *
 * @author Brian
 */
public class MemoryPath extends MiniGames{
    static Random randomNumber = new Random(); 
    static boolean [][] matrizPathSeleted = new boolean[6][3];//Everybody false
    int attempts;
    
    
    public MemoryPath(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
        attempts=3;
        
    }
    
    private void generateBoard(){
        for (int i = 0; i < 6; i++) {
            matrizPathSeleted[i][randomNumber.nextInt(3)]=true;
        }
    }

    private void startBoard(){
        try {
            MemoryPathScreen screen = (MemoryPathScreen) gamePanel;
            screen.setCellsSeleted(matrizPathSeleted);
            screen.setAttempts(attempts);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Must be a proper screen."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        
        
    }
    @Override
    public void startGame() {
        this.gamePanel=new MemoryPathScreen();
        generateBoard();
        startBoard();
        this.gamePanel.setVisible(true);
    }

    @Override
    public void endGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void playTurn() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    static boolean arrivedFlag=false;
    public static void moveCharacter(JLabel cell, JLabel character) {
        int cellWidth = cell.getWidth();
        int cellHeight = cell.getHeight();

        int iconWidth = character.getPreferredSize().width; 
        int iconHeight = character.getPreferredSize().height;

        int centerX = cell.getX() + (cellWidth - iconWidth) / 2;
        int centerY = cell.getY() + (cellHeight - iconHeight) / 2;

        Thread thread = new ThreadCharacterMemoryPathMovement(character, centerX,centerY);
        thread.start();
        
    }
    

    public static boolean isArrivedFlag() {
        return arrivedFlag;
    }

    public static void setArrivedFlag(boolean arrivedFlag) {
        MemoryPath.arrivedFlag = arrivedFlag;
    }
    
    
}

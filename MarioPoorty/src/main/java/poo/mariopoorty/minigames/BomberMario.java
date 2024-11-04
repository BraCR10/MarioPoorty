/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JFrame;
import poo.mariopoorty.Board;
import poo.mariopoorty.Player;
import poo.mariopoorty.screens.BomberMarioScreen;
import poo.mariopoorty.threads.ThreadExplotionAnimation;
//import poo.mariopoorty.threads.ThreadTimerWordSearch;

/**
 *
 * @author Brian
 */
public class BomberMario extends MiniGames{

    Point[] randomChestPositions;
    int boardDisplaySize;
    static int[] enableSizes={10,15,20};
    static Random randomNumber = new Random(); 
    int bombsCounter, chestFoundPartCounter;

    public BomberMario( ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);

        
        this.boardDisplaySize=randomNumber.nextInt(enableSizes.length);
        this.boardDisplaySize=enableSizes[boardDisplaySize];
        
        randomChestPositions=new Point[4];
        for (int i = 0; i < 4; i++) {
            randomChestPositions[i]=(new Point(randomNumber.nextInt(boardDisplaySize),randomNumber.nextInt(boardDisplaySize)));
            System.out.println(randomChestPositions[i]);
        }
        
        bombsCounter=7;
        chestFoundPartCounter=0;
    }
        
    

    
  
    
    private void startBoard(){
       try {
            BomberMarioScreen screen = (BomberMarioScreen) gamePanel;
            screen.setBoardSize(boardDisplaySize);
            
           screen.drawScreen();
            
        } catch (Exception e) {
            throw new UnsupportedOperationException("Must be a proper screen."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }  
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
        this.board.setVisible(true);
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
    
    
    
    public void produceExplotion(int sourceX,int sourceY,Icon image){
        BomberMarioScreen screen = (BomberMarioScreen)gamePanel;
        if(screen.getBombLabels()[0].getText().endsWith("(*)"))
            explotion(1,sourceX,sourceY);
        if(screen.getBombLabels()[1].getText().endsWith("(*)"))
            explotion(2,sourceX,sourceY);
        if(screen.getBombLabels()[2].getText().endsWith("(*)"))
            explotion(3,sourceX,sourceY);
        if(screen.getBombLabels()[3].getText().endsWith("(*)"))
            explotion(4,sourceX,sourceY);
        
        
        
        
    }
   
    private void explotion(int explotionType,int sourceX,int sourceY){
        BomberMarioScreen screen = (BomberMarioScreen)gamePanel;
        if(screen.getMatrizButtons()[sourceX][sourceY]!=null && 
           screen.getMatrizButtons()[sourceX][sourceY].getIcon()==screen.getMisteryBox()){

            Thread t = new ThreadExplotionAnimation(screen,sourceX,sourceY,randomChestPositions,explotionType);
            t.start();
            
            this.bombsCounter--;
            screen.getLabelCounter().setText("Enable bombs: "+this.bombsCounter);
        }
    }
    
   
    public int getBombCounter() {
        return bombsCounter;
    }

    public void setBombsCounter(int bombsCounter) {
        this.bombsCounter = bombsCounter;
    }

    public int getChestFoundPartCounter() {
        return chestFoundPartCounter;
    }

    public void setChestFoundPartCounter(int chestFoundPartCounter) {
        this.chestFoundPartCounter = chestFoundPartCounter;
    }

  

    
 
}


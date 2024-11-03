/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import poo.mariopoorty.Board;
import poo.mariopoorty.Player;
import poo.mariopoorty.screens.CatchTheCatScreen;
import poo.mariopoorty.threads.ThreadCatJumpAnimation;

/**
 *
 * @author Brian
 */
public class CatchTheCat extends MiniGames{
    private  final int ROWS = 11;
    private  final int COLS = 11;
    private boolean enableCatMovement;
    public CatchTheCat(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
        enableCatMovement=true;
    }

    @Override
    public void startGame() {
        this.board.setVisible(false);
        this.gamePanel=new CatchTheCatScreen(this);
        this.gamePanel.setVisible(true);
    }

    @Override
    public void endGame() {
        this.board.setVisible(false);
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
    private boolean characterIsInSpace(int row,int col){
        CatchTheCatScreen screen = (CatchTheCatScreen) gamePanel;
        boolean isInTheSpace= screen.getCharacterLabel().getY()+1==screen.getMatrizSpacesLabels()[row][col].getY()-screen.getCharacterCentreY()&&
                      screen.getCharacterLabel().getX()+1==screen.getMatrizSpacesLabels()[row][col].getX()+screen.getCharacterCentreX()  ;
        return  isInTheSpace;
    
    
    }
    
   private int characterX(){//Returns the character position in the matriz
        CatchTheCatScreen screen = (CatchTheCatScreen) gamePanel;
        return  (screen.getCharacterLabel().getX()-screen.getCharacterCentreX())/screen.getxOFFSET();
    }
    private int characterY(){//Returns the character position in the matriz
        CatchTheCatScreen screen = (CatchTheCatScreen) gamePanel;
        return  (screen.getCharacterLabel().getY()+screen.getCharacterCentreY())/screen.getyOFFSET();
    }
    public void moveCharacter(int row,int col ) throws InterruptedException{
        CatchTheCatScreen screen = (CatchTheCatScreen) gamePanel;
        boolean isInTheSpace= characterIsInSpace(row, col);

        if(isInTheSpace){
        }else{
            screen.getMatrizSpacesLabels()[row][col].setIcon(screen.getSpaceImageDimmed() );

            ArrayList<Point> path=searchExit();
            if(path!=null){
                Thread t = new ThreadCatJumpAnimation( (int)path.get(0).getX(), (int)path.get(0).getY(), screen,characterX(),characterY());
                t.start();
            }
            else{
                screen.getGameSatate().setText("You win!");
                screen.getGameSatate().setBackground(Color.GREEN);
                Thread t = new Thread(() -> {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(() -> endGame());
                });
                t.start();
                
            }
            
        }
    
    }
    


   private ArrayList<Point> searchExit() {
        CatchTheCatScreen screen = (CatchTheCatScreen) gamePanel;

        // Lists to hold exit points
        ArrayList<Point> top = new ArrayList<>();
        ArrayList<Point> bottom = new ArrayList<>();
        ArrayList<Point> left = new ArrayList<>();
        ArrayList<Point> right = new ArrayList<>();
        ArrayList<Point> topLeft = new ArrayList<>();
        ArrayList<Point> topRight = new ArrayList<>();
        ArrayList<Point> bottomLeft = new ArrayList<>();
        ArrayList<Point> bottomRight = new ArrayList<>();

        // General list of exit lists
        ArrayList<ArrayList<Point>> general = new ArrayList<>();
        general.add(top);
        general.add(bottom);
        general.add(left);
        general.add(right);
        general.add(topLeft);
        general.add(topRight);
        general.add(bottomLeft);
        general.add(bottomRight);

        // Character position
        int characterX = characterX();
        int characterY = characterY();


        // Check for exits in the top direction
        for (int i = characterY - 1; i > -1; i--) {
            if (!screen.getMatrizSpacesLabels()[characterX][i].getIcon().equals(screen.getSpaceImageDimmed())) {
                top.add(new Point(characterX, i)); // Store as Point
            } else {
                top.clear();
                break;
            }
        }

        // Check for exits in the bottom direction
        for (int i = characterY + 1; i < ROWS; i++) {
            if (!screen.getMatrizSpacesLabels()[characterX][i].getIcon().equals(screen.getSpaceImageDimmed())) {
                bottom.add(new Point(characterX, i)); // Store as Point
            } else {
                bottom.clear();
                break;
            }
        }

        // Check for exits in the left direction
        for (int i = characterX - 1; i > -1; i--) {
            if (!screen.getMatrizSpacesLabels()[i][characterY].getIcon().equals(screen.getSpaceImageDimmed())) {
                left.add(new Point(i, characterY)); // Store as Point
            } else {
                left.clear();
                break;
            }
        }

        // Check for exits in the right direction
        for (int i = characterX + 1; i < ROWS; i++) {
            if (!screen.getMatrizSpacesLabels()[i][characterY].getIcon().equals(screen.getSpaceImageDimmed())) {
                right.add(new Point(i, characterY)); // Store as Point
            } else {
                right.clear();
                break;
            }
        }
        //if the character is in the near the edge, the cat cannot jump to the follow directions
        boolean topLimit =characterY()!=1 ;
        boolean bottomLimit =characterY()!=ROWS-2 ;
        boolean lateralLimits =(characterX()!=1 && characterX()!=COLS-2);
        
        if(topLimit&&lateralLimits ){
            // Check for exits in the top-left direction
            for (int i = 1; characterX - i > -1 && characterY - i > -1; i++) {
                if (!screen.getMatrizSpacesLabels()[characterX - i][characterY - i].getIcon().equals(screen.getSpaceImageDimmed())) {
                    topLeft.add(new Point(characterX - i, characterY - i)); // Store as Point
                } else {
                    topLeft.clear();
                    break;
                }
            }
        
        
            // Check for exits in the top-right direction
            for (int i = 1; characterX + i < ROWS && characterY - i > -1; i++) {
                if (!screen.getMatrizSpacesLabels()[characterX + i][characterY - i].getIcon().equals(screen.getSpaceImageDimmed())) {
                    topRight.add(new Point(characterX + i, characterY - i)); // Store as Point
                } else {
                    topRight.clear();
                    break;
                }
            }
        }
        if(bottomLimit&&lateralLimits){
            // Check for exits in the bottom-left direction
            for (int i = 1; characterX - i > -1 && characterY + i < ROWS; i++) {
                if (!screen.getMatrizSpacesLabels()[characterX - i][characterY + i].getIcon().equals(screen.getSpaceImageDimmed())) {
                    bottomLeft.add(new Point(characterX - i, characterY + i)); // Store as Point
                } else {
                    bottomLeft.clear();
                    break;
                }
            }
   
            // Check for exits in the bottom-right direction
            for (int i = 1; characterX + i < ROWS && characterY + i < ROWS; i++) {
                if (!screen.getMatrizSpacesLabels()[characterX + i][characterY + i].getIcon().equals(screen.getSpaceImageDimmed())) {
                    bottomRight.add(new Point(characterX + i, characterY + i)); // Store as Point
                } else {
                    bottomRight.clear();
                    break;
                }
            }
        }
        
        // Find the minimum size exit list
        ArrayList<Point> minList = null;
        for (ArrayList<Point> list : general) {
    
            if (!list.isEmpty() && (minList == null || list.size() < minList.size())) {
                minList = list;
            }
        }
        if(minList==null){
            minList = new ArrayList<>();
            if(! screen.getMatrizSpacesLabels()[characterX-1][characterY].getIcon().equals(screen.getSpaceImageDimmed()))
                minList.add(new Point(characterX-1,characterY));
            else if(! screen.getMatrizSpacesLabels()[characterX+1][characterY].getIcon().equals(screen.getSpaceImageDimmed()))
                minList.add(new Point(characterX+1,characterY));
            else if(! screen.getMatrizSpacesLabels()[characterX][characterY-1].getIcon().equals(screen.getSpaceImageDimmed()))
                minList.add(new Point(characterX,characterY-1));
            else if(! screen.getMatrizSpacesLabels()[characterX][characterY+1].getIcon().equals(screen.getSpaceImageDimmed()))
                minList.add(new Point(characterX,characterY+1));
            else
                minList=null;
        }


        return minList; // Return the minimum exit list
    }

    public  int getROWS() {
        return ROWS;
    }

    public  int getCOLS() {
        return COLS;
    }

    public boolean isEnableCatMovement() {
        return enableCatMovement;
    }

    public void setEnableCatMovement(boolean enableCatMovement) {
        this.enableCatMovement = enableCatMovement;
    }
    
}
    


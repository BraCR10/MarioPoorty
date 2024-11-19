package minigames;


import BoardPawnsDice.Board;
import MainGame.Player;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFrame;
import screens.BomberMarioScreen;
import threads.ThreadExplotionAnimation;


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
        chooseRandomPosition();
        bombsCounter=7;
        chestFoundPartCounter=1;
    }
    private void chooseRandomPosition(){
        for (int i = 0; i < 4; i++) {
            randomChestPositions[i]=(new Point(randomNumber.nextInt(boardDisplaySize),randomNumber.nextInt(boardDisplaySize)));
        }
        //to show solution
        System.out.println("\n Bomber Mario chest position");
        for (int i = 0; i < 4; i++) {
            System.out.println((i+1)+" x= "+randomChestPositions[i].y+" , y="+randomChestPositions[i].x);
        }
    }
    
    private void configurateScreen(){
        
        BomberMarioScreen screen = (BomberMarioScreen) gamePanel;
        screen.setBoardSize(boardDisplaySize);
        screen.drawScreen();
        

    }
    
    @Override
    public void startGame() {
        this.board.setVisible(false);
        this.gamePanel=new BomberMarioScreen(this);
        configurateScreen();
        this.gamePanel.setVisible(true);
    }

    @Override
    public void endGame() {
        this.board.setVisible(true);
        this.gamePanel.dispose();
	try {
            this.players.get(0).out.writeUTF("Done");
        } catch (IOException ex) {
            Logger.getLogger(BomberMario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String validation="<<<";
        if(screen.getBombLabels()[0].getText().endsWith(validation))
            explotion(1,sourceX,sourceY);
        if(screen.getBombLabels()[1].getText().endsWith(validation))
            explotion(2,sourceX,sourceY);
        if(screen.getBombLabels()[2].getText().endsWith(validation))
            explotion(3,sourceX,sourceY);
        if(screen.getBombLabels()[3].getText().endsWith(validation))
            explotion(4,sourceX,sourceY);
        
        
        
        
    }
   
    private void explotion(int explotionType,int sourceX,int sourceY){
        BomberMarioScreen screen = (BomberMarioScreen)gamePanel;
        if(screen.getMatrizButtons()[sourceX][sourceY]!=null && 
           screen.getMatrizButtons()[sourceX][sourceY].getIcon()==screen.getMisteryBox()){
            this.bombsCounter--;
            screen.getLabelCounter().setText("Enable bombs: "+this.bombsCounter);
            Thread t = new ThreadExplotionAnimation(screen,sourceX,sourceY,randomChestPositions,explotionType);
            t.start();
            

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


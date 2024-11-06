/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import poo.mariopoorty.Board;
import poo.mariopoorty.Player;
import poo.mariopoorty.screens.GuessWhoScreen;

/**
 *
 * @author Brian
 */
public class GuessWho extends MiniGames{
    int MATRIZSIZE=10;
    static Random randomNumber = new Random(); 
    ArrayList<String> imagesPath=new ArrayList<>();
    int enableBlock;
    int imageSelectedPosition;
    public int counterMatrizPosition;

    public GuessWho(ArrayList<Player> players, String type, String description, int currentPlayers, JFrame gamePanel, Board board) {
        super(players, type, description, currentPlayers, gamePanel, board);
        
         imagesPath.add("mario.png");
        imagesPath.add("luigi.png");
        imagesPath.add("peach.png");
        imagesPath.add("bowser.png");
        imagesPath.add("toad.png");
        imagesPath.add("daisy.png");
        imagesPath.add("donkey.png");
        imagesPath.add("goomba.png");
        imagesPath.add("koopa.png");
        imagesPath.add("bobonmb.png");
        imagesPath.add("bullet.png");
        imagesPath.add("chainchop.png");
        imagesPath.add("shyguy.png");
        imagesPath.add("wario.png");
        imagesPath.add("waluigi.png");
        
        enableBlock=randomNumber.nextInt(4, 9);
    }

    @Override
    public void startGame() { 
        this.board.setVisible(false);
        gamePanel=new GuessWhoScreen(this);
        ((GuessWhoScreen)gamePanel).initPlayGround();
        ((GuessWhoScreen)gamePanel).getJtAttempts().setText("Blocks to be deleted: "+enableBlock);
        gamePanel.setVisible(true);
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
    
    
    public ImageIcon selectRandomImage(){
        GuessWhoScreen screen = (GuessWhoScreen)gamePanel;

        Random random = new Random();
        this.imageSelectedPosition=random.nextInt(screen.getImages().size());
        return screen.getImages().get(imageSelectedPosition);
        
    }
        
        
    
    public ArrayList<ImageIcon> suffleImage(){
        ImageIcon icon= selectRandomImage();
       
        
        Image resizeImage = icon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImage);
        
        
        int labelWidth = 50; 
        int labelHeight = 50; 

        BufferedImage bufferedImage = new BufferedImage(
            icon.getIconWidth(),
            icon.getIconHeight(),
            BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        Image scaledImage = bufferedImage.getScaledInstance(labelWidth * 10, labelHeight * 10, Image.SCALE_SMOOTH);
        BufferedImage scaledBufferedImage = new BufferedImage(labelWidth * 10, labelHeight * 10, BufferedImage.TYPE_INT_ARGB);
        g2d = scaledBufferedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        ArrayList<ImageIcon> imageSegmentsList = new ArrayList<>();
        for (int row = 0; row < MATRIZSIZE; row++) {
            for (int col = 0; col < 10; col++) {
                BufferedImage segment = scaledBufferedImage.getSubimage(col * labelWidth, row * labelHeight, labelWidth, labelHeight);
                imageSegmentsList.add(new ImageIcon(segment));
            }
        }
        return imageSegmentsList;
    
    }
    
    
    public void misteryBoxClicked(java.awt.event.MouseEvent e){
        GuessWhoScreen screen = (GuessWhoScreen)gamePanel;
       if (enableBlock!=0) {
            for (int i = 0; i < MATRIZSIZE; i++) {
                for (int j = 0; j < MATRIZSIZE; j++) {
                    if(screen.getMisteryBoxMatriz()[i][j].equals(((JLabel)e.getSource())))
                    ((JLabel)e.getSource()).setIcon(screen.getImageSegments()[i][j]);
                }
            }
        
            this.enableBlock--;
            screen.getJtAttempts().setText("Blocks to be deleted: "+enableBlock);
       }
    }
    
    public void checkSelectedImage(java.awt.event.MouseEvent e){
        GuessWhoScreen screen = (GuessWhoScreen)gamePanel;

        if (screen.getImages().get(imageSelectedPosition).equals(screen.getImages().get(counterMatrizPosition))) {
             screen.getJtAttempts().setBackground(Color.GREEN);
            screen.getJtAttempts().setText("You win!!!"); 
        }else{
            screen.getJtAttempts().setBackground(Color.RED);
            screen.getJtAttempts().setText("You lost!!!");   
        }
        Thread t = new Thread(() -> {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(GuessWho.class.getName()).log(Level.SEVERE, null, ex);}
        SwingUtilities.invokeLater(() -> endGame());
        });
        t.start();
    }

    public ArrayList<String> getImagesPath() {
        return imagesPath;
    }

    public int getMATRIZSIZE() {
        return MATRIZSIZE;
    }
    
}

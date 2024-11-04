package poo.mariopoorty.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import poo.mariopoorty.minigames.BomberMario;
import poo.mariopoorty.minigames.LoadImage;
import poo.mariopoorty.minigames.WordSearch;

public class BomberMarioScreen extends JFrame {
    private int boardSize ;
    private JLabel[][] matrizButtons;
    private JPanel jpBoard;
    private JPanel screenPanel;
    private String word1,word2,word3,word4;
    private ImageIcon misteryBox,misteryBoxEmpty,bomb_simple,bomb_double,bomb_cross,bomb_line,chest,chest_topleft,chest_topright,chest_bottomleft,chest_bottomright,explotion;
    private static final String RESOURCEPATH = "/BomberMario/";
    private BomberMario settings;

    //TIMMER
    JTextField timerLabel;
    
    public BomberMarioScreen(BomberMario settings) {
         this.settings=settings;
    }

    private void initComponents() {

        setTitle("Bomber Mario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenPanel = new JPanel();
        jpBoard= new JPanel();
        screenPanel.add(jpBoard);
        matrizButtons = new JLabel[boardSize][boardSize];
        jpBoard.setLayout(new GridLayout(boardSize, boardSize)); 
        
        int bombSize=90;
        misteryBox = LoadImage.loadImageAdjusted(RESOURCEPATH+"misteryBox.jpg",boardSize+10,boardSize+10);
        misteryBoxEmpty = LoadImage.loadImageAdjusted(RESOURCEPATH+"misteryBoxEmpty.jpg",boardSize+10,boardSize+10);
        bomb_simple = LoadImage.loadImageAdjusted(RESOURCEPATH+"simple_bomb.png",bombSize,bombSize);
        bomb_double = LoadImage.loadImageAdjusted(RESOURCEPATH+"double_bomb.png",bombSize,bombSize);
        bomb_cross = LoadImage.loadImageAdjusted(RESOURCEPATH+"cross_bomb.PNG",bombSize,bombSize);
        bomb_line = LoadImage.loadImageAdjusted(RESOURCEPATH+"line_bomb.PNG",bombSize,bombSize);
        chest = LoadImage.loadImageAdjusted(RESOURCEPATH+"chest.png",bombSize,bombSize);
        chest_topleft = LoadImage.loadImageAdjusted(RESOURCEPATH+"chest_top_left.png",bombSize,bombSize);
        chest_topright = LoadImage.loadImageAdjusted(RESOURCEPATH+"chest_top_right.png",bombSize,bombSize);
        chest_bottomleft = LoadImage.loadImageAdjusted(RESOURCEPATH+"chest_bottom_left.png",bombSize,bombSize);
        chest_bottomright = LoadImage.loadImageAdjusted(RESOURCEPATH+"chest_bottom_right.png",bombSize,bombSize);
        explotion = LoadImage.loadImageAdjusted(RESOURCEPATH+"explotion.png",bombSize,bombSize);
        
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                matrizButtons[i][j] = new JLabel(misteryBox);
              //  matrizButtons[i][j].setText(String.valueOf(boardChars[i][j]));
               // matrizButtons[i][j].addActionListener(evt -> charButtonActionPerformed(evt));
                matrizButtons[i][j].setBackground(Color.WHITE);
                matrizButtons[i][j].setPreferredSize(new Dimension(10, 10)); 
                jpBoard.add(matrizButtons[i][j]);
            }
        }


        add(jpBoard, BorderLayout.CENTER);

        // Left panel 
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); 
        leftPanel.setBackground(new Color(128, 0, 0,128)); 
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        
        JLabel label = new JLabel("Enable bombs: "+ settings.getBombCounter());
        label.setForeground(Color.WHITE); 
        leftPanel.add(label);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space
        
        JLabel simpleBombLabel = new JLabel(bomb_simple);
        JLabel doubleBombLabel = new JLabel(bomb_double);
        JLabel crossBombLabel = new JLabel(bomb_cross);
        JLabel lineBombLabel = new JLabel(bomb_line);
        
       
        JLabel bombLabel1 = new JLabel("Simple bomb : ");
        JLabel bombLabel2 = new JLabel("Double bomb: ");
        JLabel word3Label = new JLabel("Cross bomb: ");
        JLabel word4Label = new JLabel("Line bomb: ");

        
        bombLabel1.setForeground(Color.WHITE);
        bombLabel2.setForeground(Color.WHITE);
        word3Label.setForeground(Color.WHITE);
        word4Label.setForeground(Color.WHITE);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add space
        leftPanel.add(bombLabel1);
        leftPanel.add(simpleBombLabel);
        
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add space
        leftPanel.add(bombLabel2);
        leftPanel.add(doubleBombLabel);
        
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add space
        leftPanel.add(word3Label);
        leftPanel.add(crossBombLabel);
        
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add space
        leftPanel.add(word4Label);
        leftPanel.add(lineBombLabel);
        

        add(leftPanel, BorderLayout.WEST);

        pack(); 
        setSize(1250,650); 
        setLocationRelativeTo(null); 
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    public void drawScreen() {
        initComponents(); 
    }
   public void labelsEvents(JLabel j) {
        j.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bombEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bombExited(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                bombActionPerformed(e);
            }
        });
    }

    public void bombActionPerformed(MouseEvent e) {
        JLabel sourceLabel = (JLabel) e.getSource();
        
    }
    public void bombEntered(MouseEvent e) {
        JLabel sourceLabel = (JLabel) e.getSource();
        
    }
    public void bombExited(MouseEvent e) {
        JLabel sourceLabel = (JLabel) e.getSource();
        
    }
    
    
 
    
    private void checkWordAction() {
    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
            matrizButtons[i][j].setBackground(Color.WHITE);
        }
    }
    WordSearch.checkSeletedWord(word1, word2, word3, word4);   
    
}

    public int getBoardSize() {
        return boardSize;
    }


    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public void setWord3(String word3) {
        this.word3 = word3;
    }

    public void setWord4(String word4) {
        this.word4 = word4;
    }

    public JTextField getTimerLabel() {
        return timerLabel;
    }



}
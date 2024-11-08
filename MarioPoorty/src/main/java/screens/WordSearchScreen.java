package poo.mariopoorty.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import poo.mariopoorty.minigames.WordSearch;

public class WordSearchScreen extends JFrame implements IScreenMethods{
    private int boardSize ;
    private JButton[][] matrizButtons;
    private JPanel jpPlayGround;
    private JPanel screenPanel;
    private char[][] boardChars;
    private String word1,word2,word3,word4;
    //TIMMER
    JTextField timerLabel;
    
    public WordSearchScreen() {
         
    }

    @Override
    public void initPlayGround() {
        screenPanel = new JPanel();
        jpPlayGround= new JPanel();
        screenPanel.add(jpPlayGround);
        matrizButtons = new JButton[boardSize][boardSize];
        jpPlayGround.setLayout(new GridLayout(boardSize, boardSize)); 


        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                matrizButtons[i][j] = new JButton();
                matrizButtons[i][j].setText(String.valueOf(boardChars[i][j]));
                matrizButtons[i][j].addActionListener(evt -> charButtonActionPerformed(evt));
                matrizButtons[i][j].setBackground(Color.WHITE);
                matrizButtons[i][j].setPreferredSize(new Dimension(10, 10)); 
                jpPlayGround.add(matrizButtons[i][j]);
            }
        }


        add(jpPlayGround, BorderLayout.CENTER);
    }
    
    private void initComponents() {

        setTitle("Word Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        // Left panel 
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); 
        leftPanel.setBackground(new Color(128, 0, 0,128)); 
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        
        JLabel label = new JLabel("Words to Find:");
        label.setForeground(Color.WHITE); 
        leftPanel.add(label);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Add space
        
         JLabel word1Label = new JLabel("1. "+word1);
         JLabel word2Label = new JLabel("2. "+word2);
         JLabel word3Label = new JLabel("3. "+word3);
         JLabel word4Label = new JLabel("4. "+word4);

        
        word1Label.setForeground(Color.WHITE);
        word2Label.setForeground(Color.WHITE);
        word3Label.setForeground(Color.WHITE);
        word4Label.setForeground(Color.WHITE);

        
        leftPanel.add(word1Label);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space
        leftPanel.add(word2Label);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space
        leftPanel.add(word3Label);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space
        leftPanel.add(word4Label);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 150))); // Add space

        JButton checkWordButton = new JButton("Check Word");
        leftPanel.add(checkWordButton); 
        checkWordButton.addActionListener(evt -> checkWordAction()); // Add action listener
        leftPanel.add(Box.createRigidArea(new Dimension(0, 150))); // Add space

     
        timerLabel = new JTextField("Time: 2 mins");
        timerLabel.setEditable(false); 
        timerLabel.setForeground(Color.WHITE); 
        timerLabel.setBackground(new Color(128, 0, 0)); 
        timerLabel.setMaximumSize(new Dimension(400, 40)); 
        leftPanel.add(timerLabel,RIGHT_ALIGNMENT);
        add(leftPanel, BorderLayout.WEST);

        pack(); 
        setSize(1250,650); 
        setLocationRelativeTo(null); 
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    public void drawScreen() {
        initPlayGround();
        initComponents(); 
    }

    private void charButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JButton sourceButton = (JButton) evt.getSource();
        sourceButton.setBackground(Color.GREEN);
        WordSearch.wordsChecker(sourceButton);
    }
    
    private void checkWordAction() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                matrizButtons[i][j].setBackground(Color.WHITE);
            }
        }
        WordSearch.checkSeletedWord(word1, word2, word3, word4);   
    
    }

    @Override
    public void loadImages() {
        throw new UnsupportedOperationException("Not images in this game."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardChars(char[][] boardChars) {
        this.boardChars = boardChars;
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
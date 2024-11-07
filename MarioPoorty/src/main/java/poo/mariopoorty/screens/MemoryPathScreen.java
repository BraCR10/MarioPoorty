/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.mariopoorty.screens;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import poo.mariopoorty.minigames.LoadImage;
import poo.mariopoorty.minigames.MemoryPath;
import poo.mariopoorty.threads.ThreadCellVerifierMemoryPath;
import poo.mariopoorty.threads.ThreadCharacterMemoryPathMovement;



/**
 *
 * @author Brian Ramirez
 */
public class MemoryPathScreen extends JFrame implements IScreenMethods{

    private static final int ROWS = 6;
    private static final int COLS = 3;


    private JLabel[][] cellsLabels = new JLabel[ROWS][COLS];
    private final ImageIcon misteryBox, misteryBoxDimmed, misteryBoxNotAllowed, misteryBoxIncorrect, targetImage, characterImage;
    private static final String RESOURCEPATH = "/MemoryPathGame/";

    private JLabel character;
    private int currentRow = -1;
    private final MemoryPath settings;
    private  boolean  isArrivedFlag;
    
    /**
     * Creates new form MemoryPathScreen
     * @param memoryPathSettings
     */
    public MemoryPathScreen(MemoryPath memoryPathSettings) {
        initComponents();
        setTitle("Memory Path");
        this.setSize(1250, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLayout(new BorderLayout());
        
        this.jpExit.setPreferredSize(new Dimension(100, this.getHeight())); 
        this.jpTarget.setPreferredSize(new Dimension(100, this.getHeight())); 
        
        misteryBox = LoadImage.loadImageAdjusted(RESOURCEPATH+"misteryBox.jpg",150,150);
        misteryBoxDimmed = LoadImage.loadImageAdjusted(RESOURCEPATH+"misteryBoxDimmed.jpg",150,150);
        misteryBoxIncorrect = LoadImage.loadImageAdjusted(RESOURCEPATH+"misteryBoxNotAllowed.jpg",150,150);
        misteryBoxNotAllowed = LoadImage.loadImageAdjusted(RESOURCEPATH+"misteryBoxIncorrect.jpg",150,150);
        targetImage = LoadImage.loadImageAdjusted(RESOURCEPATH+"target.jpg",jpTarget.getWidth(),jpTarget.getHeight());
        characterImage=LoadImage.loadImageAdjusted(RESOURCEPATH+"character.png",60,60);
        character = new JLabel(characterImage);
        this.isArrivedFlag=false;
        this.settings=memoryPathSettings;
        
        initPlayGround();
        putTarget();
        defaultSettings();
        
        this.add(this.jpExit, BorderLayout.WEST); 
        this.add(this.jpPlayGround, BorderLayout.CENTER); 
        this.add(this.jpTarget, BorderLayout.EAST); 
        
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPlayGround = new javax.swing.JPanel();
        jpExit = new javax.swing.JPanel();
        jtAttemps = new javax.swing.JTextField();
        jpTarget = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpPlayGround.setBackground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jpPlayGroundLayout = new javax.swing.GroupLayout(jpPlayGround);
        jpPlayGround.setLayout(jpPlayGroundLayout);
        jpPlayGroundLayout.setHorizontalGroup(
            jpPlayGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );
        jpPlayGroundLayout.setVerticalGroup(
            jpPlayGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1960, Short.MAX_VALUE)
        );

        jpExit.setBackground(new java.awt.Color(204, 0, 102));
        jpExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 51)));

        jtAttemps.setBackground(new java.awt.Color(0, 0, 0));
        jtAttemps.setForeground(new java.awt.Color(255, 255, 255));
        jtAttemps.setText("  Attemps: 3");
        jtAttemps.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtAttemps.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jtAttemps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtAttempsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpExitLayout = new javax.swing.GroupLayout(jpExit);
        jpExit.setLayout(jpExitLayout);
        jpExitLayout.setHorizontalGroup(
            jpExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtAttemps, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jpExitLayout.setVerticalGroup(
            jpExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExitLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jtAttemps, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtAttemps.setEditable(false);

        javax.swing.GroupLayout jpTargetLayout = new javax.swing.GroupLayout(jpTarget);
        jpTarget.setLayout(jpTargetLayout);
        jpTargetLayout.setHorizontalGroup(
            jpTargetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );
        jpTargetLayout.setVerticalGroup(
            jpTargetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jpPlayGround, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpPlayGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtAttempsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtAttempsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtAttempsActionPerformed


    @Override
    public void initPlayGround() {
        this.jpPlayGround.setLayout(new GridLayout(COLS, ROWS)); 

        Border leftRightBorder = BorderFactory.createMatteBorder(0, 5, 0, 1, Color.red);

        for (int j = 0; j < COLS; j++) { 
            for (int i = 0; i < ROWS; i++) {
                this.cellsLabels[i][j] = new JLabel(misteryBox);
                this.cellsLabels[i][j].setVisible(true);
                this.cellsLabels[i][j].setBorder(leftRightBorder);

                this.cellsLabels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        cellMouseClicked(evt);
                    }

                    @Override
                    public void mouseEntered(MouseEvent evt) {
                        cellMouseEntered(evt);
                    }

                    @Override
                    public void mouseExited(MouseEvent evt) {
                        cellMouseExited(evt);
                    }
                });
                this.currentRow=-1;
                this.jpPlayGround.add(cellsLabels[i][j]);
            }
        }

        jpPlayGround.revalidate(); 
        jpPlayGround.repaint(); 
    }

    private void putTarget() {
        this.jpTarget.setLayout(null); 
        JLabel targetLabel = new JLabel(this.targetImage);
        targetLabel.setBounds(0, 0, jpTarget.getWidth(), jpTarget.getHeight()); 
        jpTarget.add(targetLabel); 
        this.jpTarget.revalidate(); 
        this.jpTarget.repaint(); 
    }

    public void defaultSettings(){
        putCharacter(jpExit, (this.jpExit.getWidth() - character.getPreferredSize().width) / 2,(this.jpExit.getHeight()) / 3);

        //To put images again
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                this.getCellsLabels()[i][j].setIcon(this.getMisteryBox());
            }
        }

        this.setCurrentRow(-1);
    }

    public void putCharacter(JPanel jp, int x, int y){
        jp.setLayout(null);
        jp.add(character);
        this.character.setBounds(x, y, character.getPreferredSize().width, character.getPreferredSize().height);
        jp.revalidate();
        jp.repaint();

    }

    private void cellMouseClicked(MouseEvent evt) { 
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (evt.getSource().equals(cellsLabels[i][j])
                    && currentRow+1==i) {

                    jpPlayGround.setLayout(null);
                    jpExit.setLayout(null);

                   if (character.getParent() == jpExit)jpExit.remove(character);
                   jpPlayGround.add(character);

                   moveCharacter(cellsLabels[i][j],settings.getMatrizPathSelected()[i][j]);
                   jpPlayGround.revalidate();
                   jpPlayGround.repaint();
                   jpExit.revalidate();
                   jpExit.repaint();
                   jpPlayGround.setComponentZOrder(character, 0);
                   currentRow++;

                }
            }
        }
    }

    private void cellMouseEntered(java.awt.event.MouseEvent evt) { 
            if( ((JLabel) evt.getSource()).getIcon()!=null ){
                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLS; j++){
                        if(cellsLabels[i][j].equals(((JLabel) evt.getSource()))){
                            if(i-1==currentRow)
                                ((JLabel) evt.getSource()).setIcon(this.misteryBoxDimmed);
                            else
                                ((JLabel) evt.getSource()).setIcon(this.misteryBoxNotAllowed); 
                        }
                    }
                }

            }
        } 

    private void cellMouseExited(java.awt.event.MouseEvent evt) { 
            if( ((JLabel) evt.getSource()).getIcon()!=null){

                ((JLabel) evt.getSource()).setIcon(this.misteryBox);
            }
        } 






    public JLabel[][] getCellsLabels() {
        return cellsLabels;
    }

    public void setCellsLabels(JLabel[][] cellsLabels) {
        this.cellsLabels = cellsLabels;
    }

    public JLabel getCharacter() {
        return character;
    }

    public void setCharacter(JLabel character) {
        this.character = character;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public JPanel getJpExit() {
        return jpExit;
    }

    public void setJpExit(JPanel jpExit) {
        this.jpExit = jpExit;
    }

    public JPanel getJpPlayGround() {
        return jpPlayGround;
    }

    public void setJpPlayGround(JPanel jpPlayGround) {
        this.jpPlayGround = jpPlayGround;
    }

    public JPanel getJpTarget() {
        return jpTarget;
    }

    public void setJpTarget(JPanel jpTarget) {
        this.jpTarget = jpTarget;
    }

    public ImageIcon getMisteryBox() {
        return misteryBox;
    }

    public JTextField getJtAttemps() {
        return jtAttemps;
    }

    public ImageIcon getMisteryBoxNotAllowed() {
        return misteryBoxNotAllowed;
    }

    public ImageIcon getMisteryBoxIncorrect() {
        return misteryBoxIncorrect;
    }

    public void setIsArrivedFlag(boolean isArrivedFlag) {
        this.isArrivedFlag = isArrivedFlag;
    }

    public boolean isIsArrivedFlag() {
        return isArrivedFlag;
    }


    public  void moveCharacter(JLabel cell, boolean cellSeletedState) {
        int centerX = cell.getX() + (cell.getWidth() - character.getPreferredSize().width) / 2;
        int centerY = cell.getY() + (cell.getHeight() - character.getPreferredSize().height) / 2;

        Thread thread = new ThreadCharacterMemoryPathMovement(this, centerX, centerY);
        thread.start();
        thread = new ThreadCellVerifierMemoryPath(cellSeletedState,this,cell,settings);
        thread.start();
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpExit;
    private javax.swing.JPanel jpPlayGround;
    private javax.swing.JPanel jpTarget;
    private javax.swing.JTextField jtAttemps;
    // End of variables declaration//GEN-END:variables
}

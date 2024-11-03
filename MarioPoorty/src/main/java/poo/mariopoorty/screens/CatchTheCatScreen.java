/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.mariopoorty.screens;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import poo.mariopoorty.minigames.LoadImage;

/**
 *
 * @author Brian Ramirez
 */
public class CatchTheCatScreen extends javax.swing.JFrame {
     private static final int ROWS=11;
     private static final int COLS=11;
     private static final String RESOURCEPATH = "/CatchTheCatGame/";
     private final JLabel[][] matrizSpacesLabels;
     private final ImageIcon spaceImage;
     private JLabel characterLabel;
    /**
     * Creates new form CatchTheCatScreen
     */
    public CatchTheCatScreen() {
        initComponents();
        setTitle("Catch the cat");
        this.setSize(1250, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(false);
        
        spaceImage=LoadImage.loadImageAdjusted(RESOURCEPATH+"base.png",ROWS*8,COLS*5);
        //spaceImage=new SpriteSelector(RESOURCEPATH+"cat.png",SpriteCatPositionsEnum.LEFT);
        matrizSpacesLabels=new JLabel[ROWS][COLS];
         //initCharacter();
        putSpaces();
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpPlayGround = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jpPlayGround.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jpPlayGroundLayout = new javax.swing.GroupLayout(jpPlayGround);
        jpPlayGround.setLayout(jpPlayGroundLayout);
        jpPlayGroundLayout.setHorizontalGroup(
            jpPlayGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        jpPlayGroundLayout.setVerticalGroup(
            jpPlayGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPlayGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPlayGround, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 


    private void putSpaces() {
        jpPlayGround.setLayout(new GridLayout(ROWS, COLS));
        jpPlayGround.setPreferredSize(new Dimension(COLS * 53, ROWS * 50)); // Adjusted for better sizing

        for (int j = 0; j < ROWS; j++) {
            for (int i = 0; i < COLS; i++) {
                matrizSpacesLabels[i][j] = new JLabel(spaceImage);
                matrizSpacesLabels[i][j].setVisible(true);
                jpPlayGround.add(matrizSpacesLabels[i][j]);

                matrizSpacesLabels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        spaceMouseClicked(evt);
                    }

                    @Override
                    public void mouseEntered(MouseEvent evt) {
                        spaceMouseEntered(evt);
                    }

                    @Override
                    public void mouseExited(MouseEvent evt) {
                        spaceMouseExited(evt);
                    }
                });
            }
        }

        jpPlayGround.revalidate();
        jpPlayGround.repaint();
    }

    private void initCharacter() {
        characterLabel = new JLabel(new SpriteSelector(RESOURCEPATH + "cat.png").getSpriteLabel());
        characterLabel.setSize(70, 80);
        int cellSize = 100;
        int middleX = (5 * cellSize) + (cellSize - 70) / 2;
        int middleY = (5 * cellSize) + (cellSize - 80) / 2;
       
        //characterLabel.setVisible(true);
        jpPlayGround.add(characterLabel); // Ensure this is added last for layering
        jpPlayGround.setComponentZOrder(characterLabel, 0);
        //jpPlayGround.revalidate();
        //jpPlayGround.repaint();
    }
    private void spaceMouseClicked(MouseEvent evt) {
    // Implement logic for when the label is clicked
    System.out.println("Label clicked!");
}

private void spaceMouseEntered(MouseEvent evt) {
    // Change the label appearance when the mouse enters, e.g., set background color
    JLabel label = (JLabel) evt.getSource();
    label.setBackground(Color.LIGHT_GRAY); // Example highlight color
}

private void spaceMouseExited(MouseEvent evt) {
    // Revert the label appearance when the mouse exits
    JLabel label = (JLabel) evt.getSource();
    label.setIcon(LoadImage.loadImageAdjusted(RESOURCEPATH+"baseDimmed.png",ROWS*8,COLS*5)); // Resets to default color
}

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpPlayGround;
    // End of variables declaration//GEN-END:variables
}

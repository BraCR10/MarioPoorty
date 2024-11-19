package BoardPawnsDice;

import BoardPawnsDice.Pawn;
import com.mycompany.proyect2.Message;
import com.mycompany.proyect2.Message;
import MainGame.Player;
import MainGame.Player;
import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;


public class Board extends javax.swing.JFrame {

    public Player boardOwner;
    
    private Space[] spaces = new Space[28];
    private String[] types = new String[28];
    
    public String[] charactes;
    private ArrayList<Pawn> Pawns = new ArrayList<>();
    
    private int AmountCharacters;
   
    

    public Board(Player boardOwner, int AmountCharacters, String[] types, String[] charactes) {
        

        this.boardOwner = boardOwner;
        this.AmountCharacters = AmountCharacters;
        
        this.types = types;
        this.charactes = charactes;


        initComponents();

        this.setTitle(this.boardOwner.name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


     
        SetPawns();
        SetRandomSpaces();  
        
        this.setVisible(true);
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        MiniGamePanels = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaChatSpace = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaTextChat = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        tiitleChat = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1250, 695));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 204, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 650));

        MiniGamePanels.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MiniGamePanelsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MiniGamePanelsLayout = new javax.swing.GroupLayout(MiniGamePanels);
        MiniGamePanels.setLayout(MiniGamePanelsLayout);
        MiniGamePanelsLayout.setHorizontalGroup(
            MiniGamePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        MiniGamePanelsLayout.setVerticalGroup(
            MiniGamePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(MiniGamePanels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(MiniGamePanels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(375, 650));
        jPanel2.setMinimumSize(new java.awt.Dimension(375, 650));
        jPanel2.setPreferredSize(new java.awt.Dimension(375, 650));

        jtaChatSpace.setEditable(false);
        jtaChatSpace.setBackground(new java.awt.Color(255, 255, 255));
        jtaChatSpace.setColumns(20);
        jtaChatSpace.setFont(new java.awt.Font("Franklin Gothic Demi", 2, 11)); // NOI18N
        jtaChatSpace.setRows(5);
        jtaChatSpace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jScrollPane1.setViewportView(jtaChatSpace);

        jtaTextChat.setColumns(20);
        jtaTextChat.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jtaTextChat.setRows(5);
        jtaTextChat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jScrollPane2.setViewportView(jtaTextChat);

        sendButton.setText("SEND");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        tiitleChat.setEditable(false);
        tiitleChat.setBackground(new java.awt.Color(255, 255, 255));
        tiitleChat.setFont(new java.awt.Font("Eras Medium ITC", 1, 12)); // NOI18N
        tiitleChat.setText("CHAT");
        tiitleChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(tiitleChat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tiitleChat, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(109, 109, 109))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MiniGamePanelsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MiniGamePanelsMouseClicked

    }//GEN-LAST:event_MiniGamePanelsMouseClicked

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        try {
            // TODO add your handling code here:
            if (!"".equals(jtaTextChat.getText())) 
                boardOwner.output.writeObject(new Message(boardOwner.name,jtaTextChat.getText()));
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        jtaTextChat.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

  
    private void SetRandomSpaces() {
        for (int i = 0; i < 28; i++) {
            spaces[i] = new Space(i, types[i]);
            this.jPanel1.add(spaces[i].panel, Integer.valueOf(0)); 
        }   
    }
    
    private void SetPawns(){
        for (int i = 0; i < AmountCharacters; i++) {
            Pawn newPawn = new Pawn(charactes[i]);
            Pawns.add(newPawn);
            this.jPanel1.add(newPawn, Integer.valueOf(1));
        }
    }
    
    public JTextArea getJtaChatSpace() {
        return jtaChatSpace;
    }

    
    public void MovePawn(String name, int position) throws InterruptedException{
        Pawn pawn = getPawn(name);
        pawn.finalPosition = this.setNewPosition(pawn, position);
        
        while (pawn.initialPosition != pawn.finalPosition){
            pawn.initialPosition++;
            
            if (pawn.initialPosition >= 28){
                pawn.initialPosition = 0;
            }
            
            
            pawn.SetPosition(pawn.initialPosition);
            
            
            
            Thread.sleep(500);
            
        }
        setCondition(pawn);
    }

    public Pawn getPawn(String name){
        for (int i = 0; i < Pawns.size(); i++){
            if (Pawns.get(i).name.equals(name)){
                return Pawns.get(i);
            }
        }
        return null;
    }
    
    private void setCondition(Pawn pawn){

        if (pawn.name == null ? this.boardOwner.name == null : pawn.name.equals(this.boardOwner.name)){
            this.boardOwner.Condition = types[pawn.initialPosition];
        }
    }

    
    public int setNewPosition(Pawn pawn, int getDiceNumber){
        if (pawn.finalPosition + getDiceNumber >= 28){
            return (pawn.finalPosition + getDiceNumber) - 28; 
        }
        
        if (pawn.finalPosition + getDiceNumber < 0){
            return 28 - getDiceNumber; 
        }
        
        return pawn.finalPosition + getDiceNumber; 
    
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel MiniGamePanels;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jtaChatSpace;
    private javax.swing.JTextArea jtaTextChat;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField tiitleChat;
    // End of variables declaration//GEN-END:variables

  

}

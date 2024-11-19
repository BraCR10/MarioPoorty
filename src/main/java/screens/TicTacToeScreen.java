package screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import minigames.TicTackToe;


public class TicTacToeScreen extends JFrame implements IScreenMethods{
    TicTackToe tictactoe;
    
    ImageIcon X;
    ImageIcon O; 

    
    
    public TicTacToeScreen(TicTackToe tictactoe) {
        this.tictactoe = tictactoe; 
        loadImages();
        initComponents();
        setTitle("TicTackToe ["+this.tictactoe.getPlayer().name+"]");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pos2 = new javax.swing.JLabel();
        Pos1 = new javax.swing.JLabel();
        Pos3 = new javax.swing.JLabel();
        Pos5 = new javax.swing.JLabel();
        Pos4 = new javax.swing.JLabel();
        Pos6 = new javax.swing.JLabel();
        Pos8 = new javax.swing.JLabel();
        Pos7 = new javax.swing.JLabel();
        Pos9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTackToe");
        setBackground(new java.awt.Color(0, 0, 0));

        Pos2.setBackground(new java.awt.Color(255, 255, 255));
        Pos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos2.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos2.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos2.setOpaque(true);
        Pos2.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos2MouseClicked(evt);
            }
        });

        Pos1.setBackground(new java.awt.Color(255, 255, 255));
        Pos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos1.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos1.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos1.setOpaque(true);
        Pos1.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos1MouseClicked(evt);
            }
        });

        Pos3.setBackground(new java.awt.Color(255, 255, 255));
        Pos3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos3.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos3.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos3.setOpaque(true);
        Pos3.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos3MouseClicked(evt);
            }
        });

        Pos5.setBackground(new java.awt.Color(255, 255, 255));
        Pos5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos5.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos5.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos5.setOpaque(true);
        Pos5.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos5MouseClicked(evt);
            }
        });

        Pos4.setBackground(new java.awt.Color(255, 255, 255));
        Pos4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos4.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos4.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos4.setOpaque(true);
        Pos4.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos4MouseClicked(evt);
            }
        });

        Pos6.setBackground(new java.awt.Color(255, 255, 255));
        Pos6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos6.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos6.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos6.setOpaque(true);
        Pos6.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos6MouseClicked(evt);
            }
        });

        Pos8.setBackground(new java.awt.Color(255, 255, 255));
        Pos8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos8.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos8.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos8.setOpaque(true);
        Pos8.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos8MouseClicked(evt);
            }
        });

        Pos7.setBackground(new java.awt.Color(255, 255, 255));
        Pos7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos7.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos7.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos7.setOpaque(true);
        Pos7.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos7MouseClicked(evt);
            }
        });

        Pos9.setBackground(new java.awt.Color(255, 255, 255));
        Pos9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pos9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Pos9.setMaximumSize(new java.awt.Dimension(160, 160));
        Pos9.setMinimumSize(new java.awt.Dimension(160, 160));
        Pos9.setOpaque(true);
        Pos9.setPreferredSize(new java.awt.Dimension(160, 160));
        Pos9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pos9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Pos7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Pos8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Pos9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Pos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Pos5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Pos6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Pos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Pos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Pos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pos5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pos6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pos8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pos9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pos7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Pos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos1MouseClicked
        if (tictactoe.MyTurn && SpaceEmpty(Pos1)){
            try {
                tictactoe.getPlayer().out.writeInt(1);
                tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
                tictactoe.MyList.add(1);
                

            } catch (IOException ex) {
                Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }//GEN-LAST:event_Pos1MouseClicked

    private void Pos2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos2MouseClicked
        if (tictactoe.MyTurn && SpaceEmpty(Pos2)){
            try {
                tictactoe.getPlayer().out.writeInt(2);
                tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
                tictactoe.MyList.add(2);
                

            } catch (IOException ex) {
                Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }//GEN-LAST:event_Pos2MouseClicked

    private void Pos3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos3MouseClicked
        if (tictactoe.MyTurn && SpaceEmpty(Pos3)){
            try {
                tictactoe.getPlayer().out.writeInt(3);
                tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
                tictactoe.MyList.add(3);
                

            } catch (IOException ex) {
                Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }//GEN-LAST:event_Pos3MouseClicked

    private void Pos4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos4MouseClicked
    if (tictactoe.MyTurn && SpaceEmpty(Pos4)){
        try {
            tictactoe.getPlayer().out.writeInt(4);
            tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
            tictactoe.MyList.add(4);


        } catch (IOException ex) {
            Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }//GEN-LAST:event_Pos4MouseClicked

    private void Pos5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos5MouseClicked
    if (tictactoe.MyTurn && SpaceEmpty(Pos5)){
        try {
            tictactoe.getPlayer().out.writeInt(5);
            tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
            tictactoe.MyList.add(5);


        } catch (IOException ex) {
            Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }//GEN-LAST:event_Pos5MouseClicked

    private void Pos6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos6MouseClicked
    if (tictactoe.MyTurn && SpaceEmpty(Pos6)){
        try {
            tictactoe.getPlayer().out.writeInt(6);
            tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
            tictactoe.MyList.add(6);


        } catch (IOException ex) {
            Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }//GEN-LAST:event_Pos6MouseClicked

    private void Pos7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos7MouseClicked
    if (tictactoe.MyTurn && SpaceEmpty(Pos7)){
        try {
            tictactoe.getPlayer().out.writeInt(7);
            tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
            tictactoe.MyList.add(7);


        } catch (IOException ex) {
            Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }     
    }//GEN-LAST:event_Pos7MouseClicked

    private void Pos8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos8MouseClicked
    if (tictactoe.MyTurn && SpaceEmpty(Pos8)){
        try {
            tictactoe.getPlayer().out.writeInt(8);
            tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
            tictactoe.MyList.add(8);


        } catch (IOException ex) {
            Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }      
    }//GEN-LAST:event_Pos8MouseClicked

    private void Pos9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pos9MouseClicked
    if (tictactoe.MyTurn && SpaceEmpty(Pos9)){
        try {
            tictactoe.getPlayer().out.writeInt(9);
            tictactoe.getPlayer().out.writeUTF(tictactoe.MySimbol);
            tictactoe.MyList.add(9);


        } catch (IOException ex) {
            Logger.getLogger(TicTacToeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }     
    }//GEN-LAST:event_Pos9MouseClicked

    private boolean SpaceEmpty(JLabel label){
        return label.getIcon() == null;
    }
    
    public boolean checkWin() {
        int[][] win = this.tictactoe.wins;
        ArrayList<Integer> myList = this.tictactoe.MyList;
        
        for (int[] winSet : win) {
            ArrayList<Integer> winList = new ArrayList<>();
            for (int num : winSet) {
                winList.add(num+1);
            }

            if (myList.containsAll(winList)) {
                return true;
            }
        }
        return false;
    }
    
    public void ReadInfo() throws IOException{
        int pos = tictactoe.getPlayer().in.readInt();
        String Simbol = tictactoe.getPlayer().in.readUTF();
        
        switch (pos) {
            case 1 -> {Pos1.setIcon(getImageIcon(Simbol));}
            case 2 -> {Pos2.setIcon(getImageIcon(Simbol));}
            case 3 -> {Pos3.setIcon(getImageIcon(Simbol));}
            case 4 -> {Pos4.setIcon(getImageIcon(Simbol));}
            case 5 -> {Pos5.setIcon(getImageIcon(Simbol));}
            case 6 -> {Pos6.setIcon(getImageIcon(Simbol));}
            case 7 -> {Pos7.setIcon(getImageIcon(Simbol));}
            case 8 -> {Pos8.setIcon(getImageIcon(Simbol));}
            case 9 -> {Pos9.setIcon(getImageIcon(Simbol));}
        }
              
    
    }
    
    public void FinishTurn() throws IOException{
        if (tictactoe.MyTurn){
            
            tictactoe.getPlayer().out.writeUTF(tictactoe.getPlayer().name);
            
            
            if (checkWin()){tictactoe.getPlayer().out.writeUTF("Win");}
            else if (!checkWin() && CanContinue()){tictactoe.getPlayer().out.writeUTF("Continue");}
            else {tictactoe.getPlayer().out.writeUTF("Draw");}
           
            tictactoe.MyTurn = false;
        }
    }
    
    @Override
    public void initPlayGround() {

    }
    
    private boolean CanContinue(){
    
        return SpaceEmpty(Pos1) || SpaceEmpty(Pos2) || SpaceEmpty(Pos3) 
            || SpaceEmpty(Pos4) || SpaceEmpty(Pos5) || SpaceEmpty(Pos6) 
            || SpaceEmpty(Pos7) || SpaceEmpty(Pos8) || SpaceEmpty(Pos9); 
    
    
    }
    
    
    private ImageIcon getImageIcon(String str){
        switch (str) {
            case "X" -> {return X;}
            case "O" -> {return O;}
        }
        return null;
        
    }

    @Override
    public void loadImages() {
        X = new ImageIcon(getClass().getResource("/TicTackToe/X.png"));
        O = new ImageIcon(getClass().getResource("/TicTackToe/O.png"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Pos1;
    private javax.swing.JLabel Pos2;
    private javax.swing.JLabel Pos3;
    private javax.swing.JLabel Pos4;
    private javax.swing.JLabel Pos5;
    private javax.swing.JLabel Pos6;
    private javax.swing.JLabel Pos7;
    private javax.swing.JLabel Pos8;
    private javax.swing.JLabel Pos9;
    // End of variables declaration//GEN-END:variables
}

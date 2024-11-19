package BoardPawnsDice;

import MainGame.Player;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveTanuki extends javax.swing.JFrame  {

    private int MaxRange = 3;
    private Player player;


    
    public MoveTanuki(Player player) {
        
        this.player = player;

        initComponents();
        this.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        Inc = new javax.swing.JButton();
        Dec = new javax.swing.JButton();
        DisplayNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Inc.setText("+");
        Inc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IncActionPerformed(evt);
            }
        });

        Dec.setText("-");
        Dec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DecActionPerformed(evt);
            }
        });

        DisplayNumber.setBackground(new java.awt.Color(255, 255, 255));
        DisplayNumber.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        DisplayNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DisplayNumber.setText("0");
        DisplayNumber.setFocusable(false);
        DisplayNumber.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Inc, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(Dec, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(99, Short.MAX_VALUE)
                    .addComponent(DisplayNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(97, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Inc, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dec, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(DisplayNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(48, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            int steps = Integer.parseInt(DisplayNumber.getText());
            Pawn pawn = this.player.board.getPawn(this.player.name);
            
            this.player.out.writeInt(this.player.board.setNewPosition(pawn, steps));
            
        } catch (IOException ex) {
            Logger.getLogger(MoveTanuki.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DecActionPerformed
        int number = Integer.parseInt(DisplayNumber.getText());
        
        if (number > (-1 * MaxRange)){
            number --;
        }
        
        DisplayNumber.setText(number+"");
    }//GEN-LAST:event_DecActionPerformed

    private void IncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IncActionPerformed
        int number = Integer.parseInt(DisplayNumber.getText());
        
        if (number < MaxRange){
            number ++;
        }

        DisplayNumber.setText(number+"");
    }//GEN-LAST:event_IncActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Dec;
    private javax.swing.JLabel DisplayNumber;
    private javax.swing.JButton Inc;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}

package screens;

import MainGame.Player;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import minigames.Cards;

public class CardsScreen extends javax.swing.JFrame {
    Player player;
    Cards cards;
    ImageIcon icon;
    
    
    public CardsScreen(Player player, Cards cards) {
        this.cards = cards;
        this.player = player;
        initComponents();
        setTitle("Cards ["+player.name+"]");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelSimbol1 = new javax.swing.JLabel();
        LabelNumber = new javax.swing.JLabel();
        labelSimbol2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ComboBoxNumber = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        ComboBoxSimbol = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        labelSimbol1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cards/hearts.png"))); // NOI18N
        labelSimbol1.setText("jLabel1");
        labelSimbol1.setMaximumSize(new java.awt.Dimension(45, 45));
        labelSimbol1.setMinimumSize(new java.awt.Dimension(45, 45));
        labelSimbol1.setName(""); // NOI18N
        labelSimbol1.setPreferredSize(new java.awt.Dimension(45, 45));

        LabelNumber.setFont(new java.awt.Font("Tw Cen MT", 1, 100)); // NOI18N
        LabelNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelNumber.setText("A");

        labelSimbol2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cards/hearts.png"))); // NOI18N
        labelSimbol2.setText("jLabel1");
        labelSimbol2.setMaximumSize(new java.awt.Dimension(45, 45));
        labelSimbol2.setMinimumSize(new java.awt.Dimension(45, 45));
        labelSimbol2.setName(""); // NOI18N
        labelSimbol2.setPreferredSize(new java.awt.Dimension(45, 45));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(LabelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelSimbol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSimbol2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSimbol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(LabelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(labelSimbol2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        ComboBoxNumber.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ComboBoxNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }));
        ComboBoxNumber.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxNumberItemStateChanged(evt);
            }
        });

        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ComboBoxSimbol.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ComboBoxSimbol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "hearts (♥)", "clubs (♣)", "spades (♠)", "diamonds (♦)" }));
        ComboBoxSimbol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxSimbolItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboBoxSimbol, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(ComboBoxNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxSimbol, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
            this.player.out.writeInt(getNumericValue());

            this.player.out.writeUTF(getSimbol());

            
            this.ComboBoxNumber.setEnabled(false);
            this.ComboBoxSimbol.setEnabled(false);
            
            cards.endGame();
        } catch (IOException ex) {
            Logger.getLogger(CardsScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ComboBoxNumberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxNumberItemStateChanged
        LabelNumber.setText(ComboBoxNumber.getSelectedItem().toString());
    }//GEN-LAST:event_ComboBoxNumberItemStateChanged

    private void ComboBoxSimbolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxSimbolItemStateChanged
        
        icon = new ImageIcon(getClass().getResource("/Cards/"+getSimbol()+".png"));
        labelSimbol1.setIcon(icon);
        labelSimbol2.setIcon(icon);
    }//GEN-LAST:event_ComboBoxSimbolItemStateChanged

    public boolean CompareCards(int num, String str){
        System.out.println("My number : "+getNumericValue()+"nUM GIVEN ---->"+num);
        System.out.println("My Simbol : "+getSimbol()+"SYM GIVEN ---->"+str);
        return  (num == getNumericValue())&&(str.equals(getSimbol()));
    }
    
    private int getNumericValue() {
        String valor = ComboBoxNumber.getSelectedItem().toString();
        
        switch (valor) {
            case "A": return 14;
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            default: return Integer.parseInt(valor);
        }
    }
    
    private String getSimbol() {
        String valor = ComboBoxSimbol.getSelectedItem().toString();
        
        switch (valor) {
            case "hearts (♥)": return "hearts";
            case "clubs (♣)": return "clubs";
            case "spades (♠)": return "spades";
            case "diamonds (♦)": return "diamonds";
            default: return "hearts";
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxNumber;
    private javax.swing.JComboBox<String> ComboBoxSimbol;
    private javax.swing.JLabel LabelNumber;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelSimbol1;
    private javax.swing.JLabel labelSimbol2;
    // End of variables declaration//GEN-END:variables
}

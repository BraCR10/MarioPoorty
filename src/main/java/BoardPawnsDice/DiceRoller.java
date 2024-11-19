package BoardPawnsDice;

import MainGame.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiceRoller extends JFrame {
    private JLabel diceLabel1;
    private JLabel diceLabel2;
    private JButton rollButton;
    private JButton doneButton;
    private int diceResult;
    private Player player;
    private boolean finished = false;

    int dice1;
    int dice2;
    
    public DiceRoller(Player player) {
        setTitle("Dice ["+player.name+"]");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        diceLabel1 = new JLabel("Dice 1: ?");
        diceLabel2 = new JLabel("Dice 2: ?");
        rollButton = new JButton("Roll");
        doneButton = new JButton("Done");

        rollButton.addActionListener(new RollListener());
        doneButton.addActionListener(new DoneListener(player));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(diceLabel1);
        panel.add(diceLabel2);
        panel.add(rollButton);
        panel.add(doneButton);

        add(panel);
        this.setVisible(true);
    }

    private class RollListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random random = new Random();
            dice1 = random.nextInt(6) + 1;
            dice2 = random.nextInt(6) + 1;

            if (dice1 == 6) {
                dice1 = -1;
                diceLabel1.setText("Dice 1: " + dice1+" turn");
            
            } else {diceLabel1.setText("Dice 1: " + dice1);}
            
            if (dice2 == 6) {
                dice2 = -1;
                diceLabel2.setText("Dice 2: " + dice2+" turn");
                
            } else {
                diceLabel2.setText("Dice 2: " + dice2);}
                        
            
        }
    }

    private class DoneListener implements ActionListener {
        private Player player;
        
        public DoneListener(Player player) {
            this.player = player;
        }
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(DiceRoller.this, getResult());
            try {
                if (dice1 == -1){this.player.LoseTurn = -1;}
                if (dice2 == -1){this.player.LoseTurn = -1;} 
                if ((dice1 == -1)&&(dice2 == -1)){this.player.LoseTurn = -2;} 
                
                this.player.out.writeInt(diceResult);
                
            } catch (IOException ex) {
                Logger.getLogger(DiceRoller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dispose();
            // Optionally, you could return diceResult here if calling this from another class.
        }
    }

    private String getResult (){
        if ((dice1 == -1)&&(dice2 == -1)){diceResult = 0; return "You lost 2 turns...";} 
        else if ((dice1 == -1)&&!(dice2 == -1)){diceResult = dice2; return "You rolled : "+dice2+"\nYou lost 2 turns...";} 
        else if (!(dice1 == -1)&&(dice2 == -1)){diceResult = dice1; return "You rolled : "+dice1+"\nYou lost 2 turns...";} 
        else {diceResult = dice1 + dice2; return "You rolled : "+(dice2+dice1);}
    }
   
    
}

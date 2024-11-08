package BoardANDPawns;

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
    private DataOutputStream sentInfo;

    public DiceRoller(DataOutputStream sentInfo) {
        setTitle("Dice Roller");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        this.sentInfo = sentInfo;
        diceLabel1 = new JLabel("Dice 1: ?");
        diceLabel2 = new JLabel("Dice 2: ?");
        rollButton = new JButton("Roll");
        doneButton = new JButton("Done");

        rollButton.addActionListener(new RollListener());
        doneButton.addActionListener(new DoneListener());

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
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;

            diceLabel1.setText("Dice 1: " + dice1);
            diceLabel2.setText("Dice 2: " + dice2);
            diceResult = dice1 + dice2;
        }
    }

    private class DoneListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(DiceRoller.this, "You rolled a total of: " + diceResult);
            try {
                DiceRoller.this.sentInfo.writeInt(diceResult);
            } catch (IOException ex) {
                Logger.getLogger(DiceRoller.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
            // Optionally, you could return diceResult here if calling this from another class.
        }
    }

    public int getDiceResult() {
        return diceResult;
    }


}

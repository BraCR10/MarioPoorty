/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.threads;

import java.awt.Color;
import static java.lang.Thread.sleep;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import poo.mariopoorty.minigames.MemoryPath;
import poo.mariopoorty.screens.MemoryPathScreen;

/**
 *
 * @author Brian Ramirez
 */
public class ThreadCellVerifierMemoryPath extends Thread{

   
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    boolean cellState;
    JLabel cell;
    MemoryPathScreen screen;
    MemoryPath memoryPathSettings;

    public ThreadCellVerifierMemoryPath( boolean cellState,MemoryPathScreen screen, JLabel cell,MemoryPath memoryPathSettings) {
        this.screen=screen;
        this.cellState=cellState;
        this.isRunning=true;
        this.isPaused=false;
        this.cell=cell;
        this.memoryPathSettings=memoryPathSettings;

    }

    @Override
   public void run() {
    while (isRunning) {
        try {
            if (!isPaused) {
                if(MemoryPath.isArrivedFlag()==true ){
                    if (!cellState) {
                        
                       int remainingAttempts = memoryPathSettings.getAttempts() - 1;
                       memoryPathSettings.setAttempts(remainingAttempts);
                       screen.getJtAttemps().setText("Attempts: "+ memoryPathSettings.getAttempts());
                       
                        cell.setIcon(null);
                        sleep(500);
                        if (screen.getCharacter().getParent() == screen.getJpPlayGround()) 
                            screen.getJpPlayGround().remove(screen.getCharacter()); 
                        screen.getJpPlayGround().revalidate();
                        screen.getJpPlayGround().repaint();
                        screen.defaultSttings();
                    } 
                    else if (screen.getCellsLabels()[5][0].equals(cell) || //WIN CASE
                            screen.getCellsLabels()[5][1].equals(cell) || 
                            screen.getCellsLabels()[5][2].equals(cell)) {
                        
                        if (screen.getCharacter().getParent() == screen.getJpPlayGround()){
                            screen.getJpPlayGround().remove(screen.getCharacter());}
                            
                        screen.getJpTarget().add(screen.getCharacter());
                        screen.putCharacter(screen.getJpTarget(), (screen.getJpTarget().getWidth() - screen.getCharacter().getPreferredSize().width) / 2, screen.getJpTarget().getHeight() / 3); // Ajustado a 0 para colocarlo en la parte superior
                        screen.getJpTarget().setComponentZOrder(screen.getCharacter(), 0);
                        screen.getJpPlayGround().revalidate();
                        screen.getJpPlayGround().repaint();
                        
                        screen.getJtAttemps().setText("You win!!!");
                        screen.getJtAttemps().setBackground(Color.GREEN);
                        sleep(1000);
                        memoryPathSettings.endGame();

                    }
                    if(memoryPathSettings.getAttempts()<=0){
                        screen.getJtAttemps().setText("You lost!!!");
                        screen.getJtAttemps().setBackground(Color.red);
                        sleep(1000);
                        memoryPathSettings.endGame();
                    }
                    
                    isRunning=false;
                    MemoryPath.setArrivedFlag(false);
                }      
            }
            sleep(10);
        } catch (InterruptedException e) {}
        }
   }
}




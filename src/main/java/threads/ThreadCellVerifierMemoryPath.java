package threads;

import java.awt.Color;
import static java.lang.Thread.sleep;
import javax.swing.JLabel;
import minigames.MemoryPath;
import screens.MemoryPathScreen;


/**
 *
 * @author Brian Ramirez
 */
public class ThreadCellVerifierMemoryPath extends Thread {

    private  boolean isRunning = true;
    private  boolean isPaused = false;
    private final boolean cellState;
    private final JLabel cell;
    private final MemoryPathScreen screen;
    private final MemoryPath memoryPathSettings;

    public ThreadCellVerifierMemoryPath(boolean cellState, MemoryPathScreen screen, JLabel cell, MemoryPath memoryPathSettings) {
        this.cellState = cellState;
        this.screen = screen;
        this.cell = cell;
        this.memoryPathSettings = memoryPathSettings;
       
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                if (!isPaused && screen.isIsArrivedFlag()) {
                    processCell();
                    if (memoryPathSettings.getAttempts() <= 0) {
                        displayLoss();
                        memoryPathSettings.endGame();
                    }
                    isRunning = false;
                    screen.setIsArrivedFlag(false);
                } 
                sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processCell() throws InterruptedException {
        if (!cellState) {
            handleIncorrectCell();
        } else if (isWinningCell()) {
            handleWin();
            memoryPathSettings.endGame();
        }
    }

    private void handleIncorrectCell() throws InterruptedException {
        memoryPathSettings.setAttempts(memoryPathSettings.getAttempts() - 1);
        screen.getJtAttempts().setText("Attempts: " + memoryPathSettings.getAttempts());
        cell.setIcon(screen.getMysteryBoxIncorrect());
        Thread.sleep(700);

        if (screen.getCharacter().getParent() == screen.getJpPlayGround()) {
            screen.getJpPlayGround().remove(screen.getCharacter());
        }

        screen.getJpPlayGround().revalidate();
        screen.getJpPlayGround().repaint();
        screen.defaultSettings();
    }

    private void handleWin() throws InterruptedException {
        if (screen.getCharacter().getParent() == screen.getJpPlayGround()) {
            screen.getJpPlayGround().remove(screen.getCharacter());
        }

        screen.getJpTarget().add(screen.getCharacter());
        screen.putCharacter(screen.getJpTarget(), (screen.getJpTarget().getWidth() - screen.getCharacter().getPreferredSize().width) / 2, screen.getJpTarget().getHeight() / 3);
        screen.getJpTarget().setComponentZOrder(screen.getCharacter(), 0);
        screen.getJpPlayGround().revalidate();
        screen.getJpPlayGround().repaint();
        screen.getJtAttempts().setText("You win!!!");
        screen.Win();
        screen.getJtAttempts().setBackground(Color.GREEN);
        sleep(1000);
    }

    private void displayLoss() throws InterruptedException {
        screen.getJtAttempts().setText("You lost!!!");
        screen.getJtAttempts().setBackground(Color.BLACK);
        sleep(1500);
    }

    private boolean isWinningCell() {
        return screen.getCellsLabels()[5][0].equals(cell) ||
               screen.getCellsLabels()[5][1].equals(cell) ||
               screen.getCellsLabels()[5][2].equals(cell);
    }
}




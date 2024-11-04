package poo.mariopoorty.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import poo.mariopoorty.minigames.BomberMario;
import poo.mariopoorty.minigames.LoadImage;

public class BomberMarioScreen extends JFrame {
    private int boardSize ;
    private JLabel[][] matrizButtons;
    private JPanel jpBoard;
    private JPanel screenPanel;
    private ImageIcon misteryBox,misteryBoxEmpty,bomb_simple,bomb_double,bomb_cross
            ,bomb_line,chest,chest_topleft,chest_topright,chest_bottomleft,chest_bottomright,explotion;
    private static final String RESOURCEPATH = "/BomberMario/";
    private BomberMario settings;
    private  JTextField[] bombLabels ;
    private boolean enableExplotionFlag;
    private  JLabel labelCounter;
    
    
    public BomberMarioScreen(BomberMario settings) {
         this.settings=settings;
    }

    private void initComponents() {
        setTitle("Bomber Mario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenPanel = new JPanel(new BorderLayout());
        jpBoard = new JPanel(new GridLayout(boardSize, boardSize));
        matrizButtons = new JLabel[boardSize][boardSize];

        int bombSize = 90;
        loadImages(bombSize);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                matrizButtons[i][j] = new JLabel(misteryBox);
                matrizButtons[i][j].setBackground(Color.WHITE);
                matrizButtons[i][j].setPreferredSize(new Dimension(10, 10));
                jpBoard.add(matrizButtons[i][j]);
                labelsMisteryBoxEvents(matrizButtons[i][j]);
            }
        }

        screenPanel.add(jpBoard, BorderLayout.CENTER);
        add(screenPanel, BorderLayout.CENTER);

        JPanel leftPanel = createLeftPanel();
        add(leftPanel, BorderLayout.WEST);

        pack();
        setSize(1250, 650);
        setLocationRelativeTo(null);
        leftPanel.revalidate();
        leftPanel.repaint();
        
        enableExplotionFlag=true;
    }

    private void loadImages(int bombSize) {
        misteryBox = LoadImage.loadImageAdjusted(RESOURCEPATH + "misteryBox.jpg", boardSize + 10, boardSize + 10);
        misteryBoxEmpty = LoadImage.loadImageAdjusted(RESOURCEPATH + "misteryBoxEmpty.jpg", boardSize + 10, boardSize + 10);
        bomb_simple = LoadImage.loadImageAdjusted(RESOURCEPATH + "simple_bomb.png", bombSize, bombSize);
        bomb_double = LoadImage.loadImageAdjusted(RESOURCEPATH + "double_bomb.png", bombSize, bombSize);
        bomb_cross = LoadImage.loadImageAdjusted(RESOURCEPATH + "cross_bomb.PNG", bombSize, bombSize);
        bomb_line = LoadImage.loadImageAdjusted(RESOURCEPATH + "line_bomb.PNG", bombSize, bombSize);
        chest = LoadImage.loadImageAdjusted(RESOURCEPATH + "chest.png", bombSize, bombSize);
        chest_topleft = LoadImage.loadImageAdjusted(RESOURCEPATH + "chest_top_left.png",  boardSize + 10, boardSize + 10);
        chest_topright = LoadImage.loadImageAdjusted(RESOURCEPATH + "chest_top_right.png",  boardSize + 10, boardSize + 10);
        chest_bottomleft = LoadImage.loadImageAdjusted(RESOURCEPATH + "chest_bottom_left.png",  boardSize + 10, boardSize + 10);
        chest_bottomright = LoadImage.loadImageAdjusted(RESOURCEPATH + "chest_bottom_right.png",  boardSize + 10, boardSize + 10);
        explotion = LoadImage.loadImageAdjusted(RESOURCEPATH + "explotion.png", boardSize *3, boardSize *3);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(128, 0, 0, 128));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelCounter = new JLabel("Enable bombs: " + settings.getBombCounter());
        labelCounter.setForeground(Color.WHITE);
        labelCounter.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(labelCounter);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        addBombLabels(leftPanel);

        return leftPanel;
    }

    private void addBombLabels(JPanel leftPanel) {
        bombLabels= new  JTextField[4];
        bombLabels[0] = new  JTextField("Simple bomb : ");
        bombLabels[1] = new  JTextField("Double bomb: ");
        bombLabels[2] = new  JTextField("Cross bomb: ");
        bombLabels[3] = new  JTextField("Line bomb: ");
            

        JLabel[] bombIcons = {
            new JLabel(bomb_simple), 
            new JLabel(bomb_double), 
            new JLabel(bomb_cross), 
            new JLabel(bomb_line)
        };

        for (int i = 0; i < bombLabels.length; i++) {
            bombLabels[i].setForeground(Color.BLACK);
            bombLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            bombLabels[i].setEditable(false);
            bombLabels[i].setBackground(new Color(183, 119, 119));
            leftPanel.add(bombLabels[i]);
            leftPanel.add(bombIcons[i]);
            labelsBombsEvents(bombIcons[i]);
            leftPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        }
            
            JLabel JlabelChestMsg =new JLabel("You need to find: ");
            JLabel JlabelChest =new JLabel(chest);
            JlabelChest.setForeground(Color.BLACK);
            JlabelChest.setAlignmentX(Component.CENTER_ALIGNMENT);
            JlabelChestMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
            JlabelChest.setBackground(new Color(183, 119, 119));
            
            leftPanel.add(JlabelChestMsg);
            leftPanel.add(JlabelChest);
            leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
    }

    public void drawScreen() {
        initComponents(); 
    }
    public void labelsBombsEvents(JLabel j) {
        j.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bombActionPerformed(e);
            }
        });
    }
    public void labelsMisteryBoxEvents(JLabel j) {
        j.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                misteryBoxActionPerformed(e);
            }
        });
    }

    public void bombActionPerformed(MouseEvent e) {
        String check = "(*)";
        JLabel sourceLabel = (JLabel) e.getSource();

        for ( JTextField bombLabel : bombLabels) {
            String text = bombLabel.getText();
            bombLabel.setText(text.replace(check, ""));
        }

        if (sourceLabel.getIcon() == bomb_simple) {
            bombLabels[0].setText(bombLabels[0].getText() + check);
        } else if (sourceLabel.getIcon() == bomb_double) {
            bombLabels[1].setText(bombLabels[1].getText() + check);
        } else if (sourceLabel.getIcon() == bomb_cross) {
            bombLabels[2].setText(bombLabels[2].getText() + check);
        } else if (sourceLabel.getIcon() == bomb_line) {
            bombLabels[3].setText(bombLabels[3].getText() + check);
        }
    }
    public void misteryBoxActionPerformed(MouseEvent e) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(((JLabel)e.getSource())==matrizButtons[i][j] && enableExplotionFlag){
                    settings.produceExplotion(i, j,((JLabel)e.getSource()).getIcon());
                }
            }
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public JLabel[][] getMatrizButtons() {
        return matrizButtons;
    }

    public JPanel getJpBoard() {
        return jpBoard;
    }

    public JPanel getScreenPanel() {
        return screenPanel;
    }

    public ImageIcon getMisteryBox() {
        return misteryBox;
    }

    public ImageIcon getMisteryBoxEmpty() {
        return misteryBoxEmpty;
    }

    public ImageIcon getBomb_simple() {
        return bomb_simple;
    }

    public ImageIcon getBomb_double() {
        return bomb_double;
    }

    public ImageIcon getBomb_cross() {
        return bomb_cross;
    }

    public ImageIcon getBomb_line() {
        return bomb_line;
    }

    public ImageIcon getChest() {
        return chest;
    }

    public ImageIcon getChest_topleft() {
        return chest_topleft;
    }

    public ImageIcon getChest_topright() {
        return chest_topright;
    }

    public ImageIcon getChest_bottomleft() {
        return chest_bottomleft;
    }

    public ImageIcon getChest_bottomright() {
        return chest_bottomright;
    }

    public ImageIcon getExplotion() {
        return explotion;
    }

    public static String getRESOURCEPATH() {
        return RESOURCEPATH;
    }

    public BomberMario getSettings() {
        return settings;
    }

    public JTextField[] getBombLabels() {
        return bombLabels;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public boolean isEnableExplotionFlag() {
        return enableExplotionFlag;
    }

    public void setEnableExplotionFlag(boolean enableExplotionFlag) {
        this.enableExplotionFlag = enableExplotionFlag;
    }

    public JLabel getLabelCounter() {
        return labelCounter;
    }

   
       
}
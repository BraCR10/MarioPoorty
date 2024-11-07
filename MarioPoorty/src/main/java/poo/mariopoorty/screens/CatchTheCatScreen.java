/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.mariopoorty.screens;


import poo.mariopoorty.minigames.SpriteAreaSelector;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import poo.mariopoorty.minigames.CatchTheCat;
import poo.mariopoorty.minigames.LoadImage;

/**
 *
 * @author Brian Ramirez
 */
public class CatchTheCatScreen extends JFrame {
    private static  int ROWS ;
    private static  int COLS ;
    private static final String RESOURCEPATH = "/CatchTheCatGame/";
    private final JLabel[][] matrizSpacesLabels;
    private ImageIcon spaceImage;
    private ImageIcon spaceImageDimmed;
    private JLabel characterLabel;
    private JLabel gameStateLabel;
    private JLayeredPane jpPlayGround;
    private JPanel jpBorders;
    private final int yOFFSET = 55;
    private final int xOFFSET = 60;
    private final int characterCentreAjust = 15;
    private CatchTheCat settings;
    private SpriteAreaSelector characterImage;
    

    public CatchTheCatScreen(CatchTheCat settings) {
        ROWS=settings.getROWS();
        COLS=settings.getCOLS();
        
        initComponents();
        setTitle("Catch the Cat");
        setSize(1250, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());


        jpPlayGround = new JLayeredPane();


        jpBorders=new  JPanel();
        jpBorders.setLayout(new BorderLayout());


        JPanel labelPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanelLeft.setBackground(Color.WHITE);
        JPanel labelPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        labelPanelRight.setBackground(Color.WHITE);


        
    
        gameStateLabel = new JLabel("Catch the cat!", SwingConstants.CENTER);
        gameStateLabel.setForeground(Color.BLACK);
        gameStateLabel.setOpaque(true); 
        gameStateLabel.setBackground(Color.WHITE); 
        gameStateLabel.setFont(new Font("SansSerif", Font.BOLD, 16)); 
        
        labelPanelRight.setPreferredSize(new  Dimension(280,0));
        labelPanelRight.add(gameStateLabel);
        
        labelPanelRight.setPreferredSize(new Dimension(280, 0));
        labelPanelRight.setLayout(new BorderLayout()); 
        labelPanelRight.add(gameStateLabel, BorderLayout.CENTER);
        
        labelPanelLeft.setPreferredSize(new Dimension(280,0));

        jpBorders.add(labelPanelLeft, BorderLayout.EAST);
        jpBorders.add(labelPanelRight, BorderLayout.WEST);
        jpBorders.add(jpPlayGround, BorderLayout.CENTER);

        
        this.settings = settings;
        spaceImage = LoadImage.loadImageAdjusted(RESOURCEPATH + "base.png", xOFFSET, yOFFSET);
        spaceImageDimmed = LoadImage.loadImageAdjusted(RESOURCEPATH + "baseDimmed.png", xOFFSET, yOFFSET);
        characterImage = new SpriteAreaSelector(RESOURCEPATH + "cat.png");
        matrizSpacesLabels = new JLabel[ROWS][COLS];

        
        putSpaces();
        initCharacter();

        
        add(jpBorders, BorderLayout.CENTER);

       
        setLocationRelativeTo(null);
    }

    private void putSpaces() {
        jpPlayGround.setLayout(null);
        jpPlayGround.setPreferredSize(new Dimension(COLS * xOFFSET, ROWS * yOFFSET));

        int margin = 1;

        for (int j = 0; j < ROWS; j++) {
            for (int i = 0; i < COLS; i++) {
                matrizSpacesLabels[i][j] = new JLabel(spaceImage);
                matrizSpacesLabels[i][j].setBounds(i * xOFFSET + margin, j * yOFFSET + margin, xOFFSET - 2 * margin, yOFFSET - 2 * margin);
                matrizSpacesLabels[i][j].setBorder(new EmptyBorder(margin, margin, margin, margin));
                matrizSpacesLabels[i][j].setVisible(true);
                jpPlayGround.add(matrizSpacesLabels[i][j], JLayeredPane.DEFAULT_LAYER);

                matrizSpacesLabels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        spaceMouseClicked(evt);
                    }
                });
            }
        }

        jpPlayGround.revalidate();
        jpPlayGround.repaint();
    }

    private void initCharacter() {
        characterLabel = new JLabel(characterImage.getSpriteLabel());
        characterLabel.setSize(xOFFSET, yOFFSET);
        int centerX = 5 ;
        int centerY = 5 ;
        putCharacter(centerX, centerY);
    }

    public void putCharacter(int x, int y) {
        jpPlayGround.setLayout(null);
        jpPlayGround.add(characterLabel, JLayeredPane.PALETTE_LAYER);
        characterLabel.setBounds(x*xOFFSET+characterCentreAjust, (y*yOFFSET-characterCentreAjust), characterLabel.getPreferredSize().width, characterLabel.getPreferredSize().height);
        jpPlayGround.revalidate();
        jpPlayGround.repaint();
    }

    private void spaceMouseClicked(MouseEvent evt) {
        if(settings.isEnableCatMovement()){
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (evt.getSource().equals(matrizSpacesLabels[i][j])  &&
                       !matrizSpacesLabels[i][j].getIcon().equals(spaceImageDimmed)) {
                        try {
                            settings.moveCharacter(i, j);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CatchTheCatScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    public JLabel getCharacterLabel() {
        return characterLabel;
    }

    public void setCharacterLabel(JLabel characterLabel) {
        this.characterLabel = characterLabel;
    }

    public JLabel[][] getMatrizSpacesLabels() {
        return matrizSpacesLabels;
    }

    public static String getRESOURCEPATH() {
        return RESOURCEPATH;
    }

    public int getyOFFSET() {
        return yOFFSET;
    }

    public int getxOFFSET() {
        return xOFFSET;
    }

    public int getCharacterCentreX() {
        return characterCentreAjust;
    }

    public int getCharacterCentreY() {
        return characterCentreAjust;
    }

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLS() {
        return COLS;
    }

    public  ImageIcon getSpaceImageDimmed() {
        return spaceImageDimmed;
    }

    public SpriteAreaSelector getCharacterImage() {
        return characterImage;
    }

    public CatchTheCat getSettings() {
        return settings;
    }

    public JLabel getGameSatate() {
        return gameStateLabel;
    }

 

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

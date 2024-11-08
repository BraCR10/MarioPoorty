package com.mycompany.proyect2;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Space {
    int positionBoard;
    
    int posX;
    int posY;
    
    int width = 70;
    int height = 70;
    
    
    String type;
    public JLabel panel;

    
    public Space(int positionBoard, String type) {
        
        this.positionBoard = positionBoard;
        this.type = type;
 
        
        
       
        this.panel = new JLabel();
        this.panel.setVisible(true);
        this.panel.setOpaque(true);
        
        SetPosition(positionBoard);
        setType(type);
        
        this.panel.setLocation(this.posX, this.posY);
        this.panel.setSize(this.width, this.height);
        this.panel.setBackground(Color.white);
        
        
    }
    
    
    private void SetPosition(int i){
        
            if (0 <= i && i <= 7){
                this.posX = 100;
                this.posY = 25 + (76 * (7 - i));
            }
            
            else if (8 <= i && i <= 13){
                this.posX = 100 + (76 * (i - 8 + 1));
                this.posY = 25;
               
            }
            
            else if (14 <= i && i <= 21){
                this.posX = 633;
                this.posY = 25 + (76 * (i-14));

            }
            
            else if (22 <= i && i <= 27){
                this.posX = 100 + (76 * (27 - i + 1));
                this.posY = 557;

            }
    
    }
    
    private void setType(String type){
        this.panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Spaces/"+type+".png")));

    }
    
}

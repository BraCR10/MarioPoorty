package BoardANDPawns;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Pawn extends JLabel{
    public String name;
    public int initialPosition = 0;
    public int finalPosition = 0;

    public Pawn(String name) {
        super();
        this.name = name;
        this.setIcon(new ImageIcon(getClass().getResource("/Characters/"+name+".png")));

        this.setVisible(true);
        this.SetPosition(0);
        this.setSize(30, 30);
        
    }
    
    public void SetPosition(int i){
        
        int x = 0;
        int y = 0;
        
        if (0 <= i && i <= 7){
            x = 100;
            y = 25 + (76 * (7 - i));
        }

        else if (8 <= i && i <= 13){
            x = 100 + (76 * (i - 8 + 1));
            y = 25;

        }

        else if (14 <= i && i <= 21){
            x = 633;
            y = 25 + (76 * (i-14));

        }

        else if (22 <= i && i <= 27){
            x = 100 + (76 * (27 - i + 1));
            y = 557;

        }
        
        x = (x + (this.getWidth()/2));
        y = (y + (this.getHeight()/2));
        
        
        this.setLocation(x, y);
    
    }
    
}
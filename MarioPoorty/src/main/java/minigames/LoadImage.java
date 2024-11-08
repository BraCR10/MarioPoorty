/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Brian Ramirez
 */
public  class LoadImage {
    public static ImageIcon loadImageAdjusted(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(LoadImage.class.getResource(path)); 
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
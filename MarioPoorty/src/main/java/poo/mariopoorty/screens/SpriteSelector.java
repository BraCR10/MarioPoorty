/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.screens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Brian Ramirez
 */
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSelector extends ImageIcon  {
    private BufferedImage spriteSheet;
    private BufferedImage sprite;

    public SpriteSelector(String spritePath,int x, int y , int width, int height) {
        try {
            spriteSheet = ImageIO.read(new File("src/main/resources/" + spritePath));

            sprite = spriteSheet.getSubimage(x, y, width, height);

            this.setImage(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageIcon  getSpriteLabel() {
        return this;
    }
}
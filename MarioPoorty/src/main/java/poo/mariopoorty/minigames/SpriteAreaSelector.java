/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;


/**
 *
 * @author Brian Ramirez
 */
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class SpriteAreaSelector extends ImageIcon {
    private BufferedImage spriteSheet;
    private BufferedImage sprite;

    public SpriteAreaSelector(String spritePath) {
        try {
            spriteSheet = ImageIO.read(new File("src/main/resources/" + spritePath));
            changeSprite(SpriteCatPositionsEnum.FRONT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeSprite(SpriteCatPositionsEnum newPosition) {
        int x = 300, y = 400, width = 70, height = 80; // Default values for FRONT
        switch (newPosition) {
            case LEFT:
                x = 600;
                y = 300;
                break;
            case RIGHT:
                x = 600;
                y = 90;
                break;
            case BACK:
                x = 600;
                y = 190;
                break;
            case JUMP_RIGHT:
                x = 500;
                y = 500;
                break;
            case JUMP_LEFT:
                x = 400;
                y = 300;
                break;
        }
        sprite = spriteSheet.getSubimage(x, y, width, height);
        this.setImage(sprite);
    }

    public ImageIcon getSpriteLabel() {
        return this;
    }
}
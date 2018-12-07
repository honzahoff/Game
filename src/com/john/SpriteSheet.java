package com.john;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    //spojení s res/sprite_sheet.png a čtení částí obrázku z něho

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col * 32)-32, (row * 32) - 32, width, height);
        return  img;
    }
}

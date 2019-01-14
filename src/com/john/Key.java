package com.john;

import java.awt.*;

public class Key extends Sprite {

    public Key(int x, int y) {
        super(x, y);
        loadImage();
    }
    private void loadImage(){
        loadImage("res/klic.png", 0);
        getImageDimensions();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width-20, height-20);
    }

}

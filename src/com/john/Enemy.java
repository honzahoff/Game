package com.john;

import java.awt.*;

public class Enemy extends Sprite{

    public Enemy(int x, int y) {
        super(x, y);
        loadImage();
    }

    private void loadImage(){
        loadImage("res/krysa.png", 1);
        getImageDimensions();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width-20, height-20);
    }


}

package com.john;

import java.awt.*;

public class Wall extends  Sprite{

    //třída zdi, která dědí ze sprite
    public Wall(int x, int y) {
        super(x, y);
        loadImage();
    }

    public void loadImage(){
        loadImage("res/wall2.png", 0);
        getImageDimensions();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

package com.john;

public class Enemy extends Sprite{

    public Enemy(int x, int y) {
        super(x, y);
        loadImage();
    }

    private void loadImage(){
        loadImage("res/krysa.png", 1);
        getImageDimensions();
    }


}

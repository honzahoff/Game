package com.john;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Box{
    private int x = 300;
    private int y = 300;
    private int w;
    private int h;
    private boolean vis;
    private BufferedImage image;
    ImageLoader loader = new ImageLoader();


    public Box(){


        loadImage();

    }

    private void loadImage(){
        loader.init();
        image = loader.box;

        w = image.getWidth();
        h = image.getHeight();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }

    public BufferedImage getImage(){
        return image;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    public void setVisible(Boolean visible) {
        this.vis = visible;
    }
}

package com.john;

import java.awt.image.BufferedImage;

public class Floor {

    private int x = 0;
    private int y = 0;
    private int w;
    private int h;
    private BufferedImage image;
    ImageLoader loader = new ImageLoader();

    public Floor(){
        loadImage();
    }

    private void loadImage(){
        loader.init();
        image = loader.bck;

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

}

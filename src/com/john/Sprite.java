package com.john;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    //načtení obrázku
    protected void loadImage(String path, int scale){
        ImageIcon ii = new ImageIcon(path);
        Image i = ii.getImage();

        int w = ii.getIconWidth();
        int h = ii.getIconHeight();
        if (scale == 1){

            image = i.getScaledInstance(w*2, h*2, Image.SCALE_DEFAULT);
        }
        else if (scale == 2){
            image = i.getScaledInstance(w*4, h*4, Image.SCALE_DEFAULT);
        }
        else if(scale == 0){
            image = ii.getImage();
        }
        else{
            System.out.println("Wrong number!");
        }
    }

    //získání rozměrů obrázku
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    //gettery
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW(){ return width;}

    public int getH(){ return height;}



}

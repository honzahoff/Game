package com.john;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.security.Key;

public class Player{

    private int dx;
    private int dy;
    private int x = 200;
    private int y = 200;
    private int w;
    private int h;
    private BufferedImage image;
    ImageLoader loader = new ImageLoader();


    public Player(){

        loadImage();

    }

    //načtení obrázku hráče
    private void loadImage(){
        loader.init();
        image = loader.playerS2;

        w = image.getWidth();
        h = image.getHeight();
    }

    //metoda pro pohyb
    public void move(){
        x += dx;
        y += dy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getW(){
        return  w;
    }

    public int getH(){
        return h;
    }

    public BufferedImage getImage(){
        return image;
    }

    //získání zmáčknuté klávesy
    public void keyPressed(KeyEvent e){

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            dy = -1;}
        if(code == KeyEvent.VK_S){dy = 1;}
        if(code == KeyEvent.VK_D){dx = 1;}
        if(code == KeyEvent.VK_A){dx = -1;}
    }

    public void keyReleased(KeyEvent e){

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){dy = 0;}
        if(code == KeyEvent.VK_S){dy = 0;}
        if(code == KeyEvent.VK_D){dx = 0;}
        if(code == KeyEvent.VK_A){dx = 0;}
    }

}


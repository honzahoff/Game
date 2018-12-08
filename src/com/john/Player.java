package com.john;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.security.Key;

public class Player extends Sprite{

    public int dx;
    public int dy;
    public int x = 200;
    public int y = 200;
    private int w;
    private int h;
    private BufferedImage image;
    ImageLoader loader = new ImageLoader();


    public Player(int x, int y){
        super(x,y);
        loadImage();

    }

    //načtení obrázku hráče
    private void loadImage(){
        loader.init();
        image = loader.player;

        w = image.getWidth();
        h = image.getHeight();
    }

    //metoda pro pohyb
    public void move(){

        if (x > 0 && x < 960 - this.w && y > 0 && y < 540 - this.h - 20) {

            x += dx;
            y += dy;
        }

        else if (y < 0 + 16) {
            this.y = y + 10;
        }

        else if (y > 540 - this.h - 20) {
            this.y = y - 10;
        }

        else if (x > 960 - this.w) {
            this.x = x - 10;

        } else {
            this.x = x + 10;

        }

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
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

}


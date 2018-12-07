package com.john;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.sql.Time;

public class Board extends JPanel implements ActionListener, KeyListener{

    private Timer timer;
    private final int DELAY = 10;
    private Player player;
    private Floor floor;
    public int live = 3;
    public boolean isAlive = true;
    ImageLoader loader = new ImageLoader();

    public Board(){
        initBoard();
    }


    //inicializace plochy
    public void initBoard(){

        addKeyListener(this);
        player = new Player();
        setFocusable(true);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    //implementovaná třída
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();


    }


    //základní kreslení
    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        loader.init();
        paintHearts(g);

        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

    }

    //kreslení srdíček
    private void paintHearts(Graphics g){

        loader.init();
        if (live == 3) {
            for (int i = 0; i < 3; i++) {
                g.drawImage(loader.heart, i * 32, 0, null);
            }
        } else if (live == 2)
            for (int i = 0; i < 2; i++) {
                g.drawImage(loader.heart, i * 32, 0, null);
                g.drawImage(loader.blackHeart, 64, 0, null);
            }
        else if (live == 1) {
            for (int i = 0; i < 1; i++) {
                g.drawImage(loader.heart, i * 32, 0, null);
                g.drawImage(loader.blackHeart, 64, 0, null);
                g.drawImage(loader.blackHeart, 32, 0, null);
            }


        } else if (live == 0) {
            for (int i = 0; i < 3; i++) {
                g.drawImage(loader.blackHeart, i * 32, 0, null);
                isAlive = false;
            }
        } else {
            for (int i = 0; i < 3; i++) {
                g.drawImage(loader.blackHeart, i * 32, 0, null);
                isAlive = false;
            }
        }
    }

    //reakce na stisk klávesy
    public void actionPerformed(ActionEvent e){

        step();
    }

    //provedení pohybu
    private void step(){
        player.move();
        repaint(player.getX() -1, player.getY() - 1, player.getW() +2, player.getH()+2);
    }

    //reakce na r - odečtení života: provizorní
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_R){
            live -= 1;
            repaint();
        }
        else {
            player.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    public void keyTyped(KeyEvent e){}
}
/*


    Timer t = new Timer(5, this);

    int x = 10;
    int y = 10;

    int width = 960;
    int height = 540;

    int speed = 5;

    double angle;

    double velx = 0;
    double vely = 0;

    int live = 3;
    boolean isAlive = true;

    ImageLoader il = new ImageLoader();
    Handler h = new Handler();


    public Board() {
        t.start();
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = AffineTransform.getRotateInstance(angle, x + h.spiderW / 2, y + h.spiderH / 2);


        il.init();
        paintBackground(g);
        paintHeart(g);
        paintWalls(g);


        g2d.setTransform(at);
        g2d.drawImage(il.playerScaled, x, y, null);
        g2d.setTransform(backup);
    }

    public void paintBackground(Graphics g) {

        for (int i = 0; i < width / 32; i++) {
            for (int j = 0; j < height / 32 + 1; j++) {
                g.drawImage(il.bck, i * 32, j * 32, this);
            }
        }
    }

    public void paintHeart(Graphics g) {
        if (live == 3) {
            for (int i = 0; i < 3; i++) {
                g.drawImage(il.heart, i * 32, 0, null);
            }
        } else if (live == 2)
            for (int i = 0; i < 2; i++) {
                g.drawImage(il.heart, i * 32, 0, null);
                g.drawImage(il.blackHeart, 64, 0, null);
            }
        else if (live == 1) {
            for (int i = 0; i < 1; i++) {
                g.drawImage(il.heart, i * 32, 0, null);
                g.drawImage(il.blackHeart, 64, 0, null);
                g.drawImage(il.blackHeart, 32, 0, null);
            }


        } else if (live == 0) {
            for (int i = 0; i < 3; i++) {
                g.drawImage(il.blackHeart, i * 32, 0, null);
                isAlive = false;
            }
        } else {
            for (int i = 0; i < 3; i++) {
                g.drawImage(il.blackHeart, i * 32, 0, null);
                isAlive = false;
            }
        }
    }

    public void paintWalls(Graphics g) {

        g.drawImage(il.box, h.boxX, h.boxY, null);
    }

    public void actionPerformed(ActionEvent e) {
        if (x > 0 && x < 960 - h.spiderW && y > 0 && y < 540 - h.spiderH - 20) {
            repaint();

            x += velx;
            y += vely;
        } else if (y < 0 + 16) {
            this.y = y + 10;
        } else if (y > 540 - h.spiderH - 20) {
            this.y = y - 10;
        } else if (x > 960 - h.spiderW) {
            this.x = x - 10;

        } else {
            this.x = x + 10;

        }


    }

    public void up() {
        vely = -speed;
        velx = 0;
        angle = 0;


    }

    public void down() {
        vely = speed;
        velx = 0;
        angle = Math.toRadians(180);
    }

    public void left() {
        vely = 0;
        velx = -speed;
        angle = Math.toRadians(270);
    }

    public void right() {
        vely = 0;
        velx = speed;
        angle = Math.toRadians(90);
    }

    public void stop() {
        vely = 0;
        velx = 0;
    }

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            up();
        }
        if (code == KeyEvent.VK_S) {
            down();
        }
        if (code == KeyEvent.VK_D) {
            right();
        }
        if (code == KeyEvent.VK_A) {
            left();
        }

        if (code == KeyEvent.VK_R) {

            if (live > 0) {
                live -= 1;
            } else {
                isDead();
            }
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        stop();
    }

    public void isDead() {
        if (live >= 0) {
            isAlive = false;
            JOptionPane.showMessageDialog(h.frame, "You have died!");
            //Board w = new Board("Game");

        }
    }
}
*/






package com.john;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Board extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private final int DELAY = 10;
    private Menu m = new Menu();
    private Player player;
    public int live = 3;
    ImageLoader loader = new ImageLoader();

    public static enum STATE{
        GAME,
        MENU
    };
    public static STATE State = STATE.MENU;

    public Board() {


            initBoard();

    }


    //inicializace plochy
    public void initBoard() {

        addMouseListener(new Mouse());
        setFocusable(true);



            addKeyListener(this);
            player = new Player(200, 200);
            timer = new Timer(DELAY, this);
            timer.start();



    }

    //implementovaná třída
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (State == STATE.GAME) {
            doDrawing(g);
            paintHearts(g);
            Toolkit.getDefaultToolkit().sync();
            repaint();
        }
        else if (State == STATE.MENU){
         m.render(g);
        }
    }


    //vykreslení
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        loader.init();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 16; j++) {
                g.drawImage(loader.bck, i * 32, j * 32, this);
            }
        }

        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);


    }

    //kreslení srdíček
    private void paintHearts(Graphics g) {

        loader.init();

        for (int i = 0; i < 3; i++) {

            BufferedImage img2;
            if (i < live) {
                img2 = loader.heart;
            } else {
                img2 = loader.blackHeart;

            }

            g.drawImage(img2, i * 32, 0, null);
            g.drawString("Live:" + live, 50, 50);
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
        if (State == STATE.GAME) {
            if (code == KeyEvent.VK_R) {
                live -= 1;
                repaint();
            } else {
                player.keyPressed(e);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    if (State == STATE.GAME) {
        player.keyReleased(e);
        }
    }

    public void keyTyped(KeyEvent e){}

}





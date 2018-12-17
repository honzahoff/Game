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
        LVL1,
        LVL2,
        LVL3,
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

            timer = new Timer(DELAY, this);
            timer.start();

            loadLevel("res/level1.png");






    }

    //implementovaná třída
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (State == STATE.LVL1) {
            doDrawing(g);
            paintHearts(g);
            Toolkit.getDefaultToolkit().sync();
            repaint();
        }
        else if (State == STATE.MENU){
         m.render(g);
         repaint();
        }
    }


    //vykreslení
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;



        loader.init();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 17; j++) {
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
            if(code == KeyEvent.VK_SPACE){
                State = STATE.MENU;
                repaint();
            }
            if (State == STATE.LVL1) {
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
    if (State == STATE.LVL1) {
        player.keyReleased(e);
        }
    }

    public void keyTyped(KeyEvent e){}

    public void loadLevel(String path){

        ImageIcon ii = new ImageIcon(path);
        Image image = ii.getImage();
        BufferedImage i = loader.toBufferedImage(image);

        int w = image.getWidth(null);
        int h = image.getHeight(null);

        for (int xx = 0; xx<w; xx++){
            for (int yy = 0; yy<h; yy++) {
                int pixel = i.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255){

                }

                if (blue == 255){
                    player = new Player(xx * 7, yy * 7);

                }

                if (green == 255){

                }

            }
        }
    }

}





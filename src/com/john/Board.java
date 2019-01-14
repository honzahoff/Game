package com.john;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener, KeyListener {


    //proměnné
    private Timer timer;
    private final int DELAY = 10;
    private Menu m = new Menu();
    private Player player;
    private JButton menuBtn = new JButton();
    boolean alive = true;

    public int live = 3;
    ImageLoader loader = new ImageLoader();

    ArrayList<Enemy> enemyList =new ArrayList<Enemy>();
    ArrayList<Wall> wallList = new ArrayList<Wall>();


    //enumy - určují, jaká scéna je aktivní
    public enum STATE{
        LVL,
        MENU

    };

    //zapnutí statu menu na začátku
    public static STATE State = STATE.MENU;

    public Board() {


        //inicializace
            initBoard();

    }


    //inicializace plochy
    public void initBoard() {

        setFocusable(true);


        
        addKeyListener(this);

        timer = new Timer(DELAY, this);
        timer.start();
        //načítá level - png obrázek
        loadLevel("res/level1.png");







    }

    //implementovaná třída
    @Override
    public void paintComponent(Graphics g) {

        //vykresluje menu a level pomocí statu
        super.paintComponent(g);
        if (State == STATE.LVL) {
            doDrawing(g);
            paintHearts(g);
            Toolkit.getDefaultToolkit().sync();
            repaint();
        }
        else if (State == STATE.MENU) {
            m.render(g);
            menuBtn.setBounds(960 / 2 - 100, 540 / 2 - 50, 200, 100);
            menuBtn.setText("LEVEL START");

            //lambda příkaz na actionlisteneru
            menuBtn.addActionListener(e -> {
                State = STATE.LVL;
                menuBtn.setVisible(false);
                live = 1;
                timer.start();

                doDrawing(g);
                player.move();
                player.stopPlayer();


            });

            add(menuBtn);
            repaint();

        }
    }


    //vykreslení
    private void doDrawing(Graphics g) {

        //vykreslování objektů na ploše
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = AffineTransform.getRotateInstance(player.angle, player.getX() + 64/2, player.getY() + 64/2);

        loader.init();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 17; j++) {
                g.drawImage(loader.bck, i * 32, j * 32, this);
            }
        }

        System.out.println(player.getW());

        
        for(int i =0;i<enemyList.size();i++) {
            Enemy temp = enemyList.get(i);
            g2d.drawImage(temp.getImage(), temp.getX(), temp.getY(), this);
        }

        for(int i =0;i<wallList.size();i++) {
            Wall temp = wallList.get(i);
            g2d.drawImage(temp.getImage(), temp.getX(), temp.getY(), this);
        }

        g2d.setTransform(at);
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        g2d.setTransform(backup);
    }

    //kreslení srdíček
    private void paintHearts(Graphics g) {

        loader.init();

        for (int i = 0; i < 1; i++) {

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
        checkCollisions();
        isAlive();




    }



    //provedení pohybu
    private void step(){
        //krok hráče
        player.move();
        repaint(player.getX() -1, player.getY() - 1, player.getW() +2, player.getH()+2);
    }

    //reakce na r - odečtení života: provizorní
    @Override
    public void keyPressed(KeyEvent e) {

            int code = e.getKeyCode();
            if(code == KeyEvent.VK_SPACE){
                State = STATE.MENU;
                menuBtn.setVisible(true);
                repaint();
            }
            if (State == STATE.LVL) {
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
        //mezerník zapne pause menu
    if (State == STATE.LVL) {
        player.keyReleased(e);
        }
    }

    public void keyTyped(KeyEvent e){}


    public void isAlive(){

        //jestli je naživu
        if (live > 0){
            alive = true;
        }
        else{
            alive = false;
            State = STATE.MENU;
            menuBtn.setVisible(true);
            resetPlayer(50, 400);
            timer.stop();

        }
    }

    public void checkCollisions(){

        //konroliuje kolize
        Rectangle r1 = player.getBounds();


        for (Wall wall:wallList) {
            Rectangle r2 = wall.getBounds();

            if (r1.intersects(r2)){

            }
        }

        for (Enemy enemy:enemyList){
            Rectangle r3 = enemy.getBounds();

            if (r1.intersects(r3)){
                live -=1;
                repaint();
            }
        }
    }

    public void resetPlayer(int x, int y){
        //resetuje hráče po zabití na původní pozici
        player.x = x;
        player.y = y;
    }

    public void loadLevel(String path){

        //načíta level pomocí barevných kódů
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
                    enemyList.add(new Enemy(xx*15, yy*15));
                }

                if (blue == 255 && red == 0){
                    player = new Player(xx * 15, yy * 15);

                }

                if (blue == 255 && red == 255){

                }

                if (green == 255){
                    wallList.add(new Wall(xx*15, yy*15));
                }

            }
        }
    }

}





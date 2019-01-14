package com.john;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu extends JFrame {

    //stará se o vykreslení menu
    public BufferedImage i;
    ImageLoader l = new ImageLoader();

    public void render(Graphics g){


        ImageIcon ii = new ImageIcon("res/menu.png");
        i = l.toBufferedImage(ii.getImage());


        g.setColor(Color.BLACK);
        g.drawImage(i, 0,0, 960,540, null);

        Font fnt = new Font("impact", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.BLUE);
        g.drawString("SPAIDRZ vs. DINOZORS", 965/4, 105);
        g.setColor(Color.WHITE);
        g.drawString("SPAIDRZ vs. DINOZORS", 960/4, 100);



    }
}

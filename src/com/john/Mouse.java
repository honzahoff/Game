package com.john;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();

        System.out.println(e.getX() + ", " + e.getY());


    /*public Rectangle lvl1 = new Rectangle(960/4 + 120, 150,200,70);
    public Rectangle lvl2 = new Rectangle(960/4 + 120, 250, 200, 70);
    public Rectangle lvl3 = new Rectangle(960/4 + 120, 350, 200, 70);
    */

    if (mx >= 360 && mx <= 560){
            if (my >= 150 && my <= 220){
                System.out.println("LVL1");
                Board.State = Board.STATE.GAME;
                System.out.println(Board.State);
            }
            else if(my >= 250 && my <= 320){
                System.out.println("LVL2");
            }
            else if (my >= 350 && my <= 420){
                System.out.println("LVL3");
            }
        }

    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

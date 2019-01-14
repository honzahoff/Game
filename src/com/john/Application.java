package com.john;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

   private int w;
   private int h;
    public Application(){
        initUI();

    }

    //inicializace plochy
    public void initUI(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        w = screenSize.width;
        h = screenSize.height;

        //přidání Boardu - plochy s objekty a grafikou do JFrame
        add(new Board());
        setSize(960,540);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon ii = new ImageIcon("res/icon.png");
        Image i = ii.getImage();
        setIconImage(i);
    }

    //vytvoření nového vlákna a zapnutí aplikace na něm
    public static void main(String[] args) {
        EventQueue.invokeLater(()-> {
            Application ex = new Application();
            ex.setVisible(true);
        });

    }


    //gettery
    public int getWidth(){
        return w;
    }

    public int getHeight(){
        return h;
    }




}

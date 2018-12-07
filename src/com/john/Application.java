package com.john;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application(){
        initUI();
    }

    //inicializace plochy
    public void initUI(){

        add(new Board());
        setSize(960,540);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(()-> {
            Application ex = new Application();
            ex.setVisible(true);
        });

    }
}

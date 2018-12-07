package com.john;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Menu implements ActionListener {

    public JFrame frame;
    private JPanel jp;
    public JButton menuBt;
    public JButton lvl1Bt;
    Handler h = new Handler();

    public Menu() {
        frame = new JFrame();
        jp = new JPanel();

        frame.setSize(new Dimension(500,500));
        menuBt = new JButton("Play game!");

        menuBt.setBackground(Color.RED);
        menuBt.addActionListener(this);
        menuBt.setPreferredSize(new Dimension(100,100));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        jp.add(menuBt);
        jp.add(lvl1Button());

        frame.add(jp);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Board w = new Board("Game");
        System.out.println("Close");
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

    }

    public void lvl1(ActionEvent e){
        h.level = 1;

        //Board w = new Board("Game");
        System.out.println("Close");
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public JButton lvl1Button(){
        lvl1Bt = new JButton("Level 1");
        lvl1Bt.setBackground(Color.BLUE);
        lvl1Bt.addActionListener(this::lvl1);
        lvl1Bt.setPreferredSize(new Dimension(100,100));

        return lvl1Bt;
    }
}

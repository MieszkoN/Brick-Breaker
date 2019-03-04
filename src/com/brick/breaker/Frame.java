package com.brick.breaker;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    //private int ballPosX = 120;
    //private int ballPosY = 350;
    
    public Frame() {
        super("Brick Breaker");
        setBounds(650, 200, 700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    /*
    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(0, 0, 700, 700);
        
        //borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 699);
        g.fillRect(0, 30, 697, 3);
        g.fillRect(697, 0, 3, 699);
        
        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 16, 16);
        
        //the paddle
        g.setColor(Color.green);
        g.fillRect(330, 670, 100, 12);
        
        g.dispose();
    }
    */
}
package com.brick.breaker;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int totalBricks = 24;
    
    private Timer time;
    private int delay = 7;
    
    private int playerX = 310;
    

    private int ballXdir = -1;
    private int ballYdir = -2;
    
    private MapGenerator map;
    
    public Gameplay() {
        map = new MapGenerator(4, 6);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }
    
        public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(0, 0, 700, 700);
        
        //borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 699);
        g.fillRect(0, 0, 697, 3);
        g.fillRect(697, 0, 3, 699);
        
        //map
        map.draw((Graphics2D)g);
        
        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 16, 16);
        
        //the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 630, 100, 12);
        
        //score
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD, 25));
        g.drawString("" + score, 610, 30);
        
        if(totalBricks == 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("You won!", 200, 350);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press Enter to play again.", 215, 390);
        }
        
        if(ballPosY > 695) {
            play = false;
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game over!", 200, 350);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press enter to play again.", 218, 390);
        } 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if(play) {
            if(new Rectangle(ballPosX, ballPosY, 15, 15).intersects(new Rectangle(playerX, 630, 30, 8))){
                ballYdir = -ballYdir;
                ballXdir = -1;
            } else if(new Rectangle(ballPosX, ballPosY, 15, 15).intersects(new Rectangle(playerX+70, 630, 30,8))) {
                ballYdir = -ballYdir;
                ballXdir = 1;
            } else if(new Rectangle(ballPosX, ballPosY, 15, 15).intersects(new Rectangle(playerX+30, 630, 40, 8))) {
                ballYdir = -ballYdir;
            }
            
            A: for(int i = 0; i < map.row; i++) {
                for(int j = 0; j < map.col; j++) {
                    int brickX = j * map.brickWidth + 60;
                    int brickY = i * map.brickHeight + 60;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;
                    
                    Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 15, 15);
                    Rectangle brickRect = rect;
                    
                    if(ballRect.intersects(rect) && map.map[i][j] != 0) {
                        map.map[i][j] = 0;
                        totalBricks--;
                        score += 5;
                        
                       if(ballPosX  + 14 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
                           ballXdir = -ballXdir;
                       } else {
                           ballYdir = -ballYdir;
                       }
                     break A;
                    }
                }
            }
            
            ballPosX += ballXdir;
            ballPosY += ballYdir;
            if(ballPosX < 0) {
                ballXdir = -ballXdir;
            } else if(ballPosY < 0) {
                ballYdir = -ballYdir;
            } else if(ballPosX > 690) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 595) {
                playerX = 595;
            } else {
                moveRight();
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX <= 5) {
                playerX = 5;
            } else {
                moveLeft();
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play) {
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(4, 6);
            }
        }
    }
    
    public void moveLeft() {
        play = true;
        playerX -= 20;
    }
    
    public void moveRight() {
        play = true;
        playerX += 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
    }

}
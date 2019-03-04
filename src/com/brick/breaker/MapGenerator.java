package com.brick.breaker;

import java.awt.*;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    public int row, col;
    
    public MapGenerator(int row, int col) {
        this.row = row;
        this.col = col;
        map = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                map[i][j] = 1;
            }
        }
        
        brickWidth = 390/row;
        brickHeight = 330/col;
    }
    
    public void draw(Graphics2D g) {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(map[i][j] > 0) {
                    g.setColor(Color.white);
                    g.fillRect(j * brickWidth + 60, i * brickHeight + 50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 60, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
}

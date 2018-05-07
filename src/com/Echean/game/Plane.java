package com.Echean.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {
    boolean left,right,up,down;
    @Override
    public void drawSelf(Graphics g) {
        g.drawImage(img,(int)x,(int)y,null);
        if(left){
            x-=speed;
        }else if(right){
            x+=speed;
        }else if(up){
            y-=speed;
        }else if(down){
            y+=speed;
        }
    }
    public Plane(Image img, int x,int y,int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    public void subDirection(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
}

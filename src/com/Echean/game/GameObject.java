package com.Echean.game;
import java.awt.*;

public class GameObject {
    Image img;
    double x,y;
    int speed;
    int width,heigth;
    public void drawSelf(Graphics g) {
        g.drawImage(img,(int)x,(int)y,null);
    }

    public GameObject(Image img, double x, double y, int speed, int width, int heigth) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.heigth = heigth;
    }

    public GameObject(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public GameObject() {
    }

    public Rectangle getRect() {
        return new Rectangle((int)x,(int)y,width,heigth);
    }
}

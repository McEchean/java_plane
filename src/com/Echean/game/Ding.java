package com.Echean.game;

import java.awt.*;

public class Ding extends GameObject {
    double degree;
    public Ding(Image image){
        img = image;
        x = 200;
        y = 200;
        width = 10;
        heigth = 10;
        speed = 3;
        degree = Math.random() * Math.PI * 2;
    }
    @Override
    public void drawSelf(Graphics g) {
        g.drawImage(img,(int)x,(int)y,null);
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);

        if(x < 0 || x > Constant.GAME_WIDTH - 40){
            degree = Math.PI - degree;
        }
        if(y < 30 || y > Constant.GAME_HEIGHT - 40){
            degree = - degree;
        }
    }

}

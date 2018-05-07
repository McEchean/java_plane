package com.Echean.game;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGameFrame extends JFrame {

    Image ball = GameUtil.getImage("Images/ball.jpg");
    Image background = GameUtil.getImage("Images/bj.jpg");
    Image ding = GameUtil.getImage("Images/ding.jpg");

    Plane plane = new Plane(ball,100,200,2);
    Ding[] dings = new Ding[10];


    @Override
    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.BLUE);
//        Font f = g.getFont();
//        g.drawLine(100,100,300,300);
//        g.drawRect(100,100,300,300);
//        g.drawOval(100,100,300,300);
//        g.fillRect(100,100,40,40);
//        g.setFont(new Font("宋体", Font.BOLD,20));
//        g.drawString("who am I",300,300);
        g.clearRect(0,0,500,500);
        g.drawImage(background,0,0,null);
//        g.drawImage(ball,planeX,planeY,null);
        plane.drawSelf(g);
//        ding1.drawSelf(g);
        for(int i = 0; i < 10; i++){
            dings[i].drawSelf(g);
        }

//        g.setColor(c);
//        g.setFont(f);
    }
    class PaintThread extends Thread {
        @Override
        public void run() {
            while(true){
//                System.out.println("重划");
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    *上：38
    *下：40
    *左：37
    *右：39
    */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.subDirection(e);
        }
    }

    public void launchFrame(){
        this.setTitle("复习");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        this.setLocation(750,300);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();
        addKeyListener(new KeyMonitor());//增加键盘监听

        //初始化50个炮弹
        for(int i = 0; i < 10; i++){
            dings[i] = new Ding(ding);
        }
    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
}

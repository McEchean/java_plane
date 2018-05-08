package com.Echean.game;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.JFrame;
//import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame {

    Image ball = GameUtil.getImage("Images/ball.jpg");
    Image background = GameUtil.getImage("Images/bj.jpg");
    Image ding = GameUtil.getImage("Images/ding.jpg");

    Plane plane = new Plane(ball,100,200,3);
    Ding[] dings = new Ding[10];
    Explode bao;
    Date starttime = new Date();
    Date endtime;
    int period;
    boolean flag = true;


    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
//        g.setColor(Color.BLUE);
        Font f = g.getFont();
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
            boolean peng = dings[i].getRect().intersects(plane.getRect());
            if(peng) {
//                System.out.println(peng);
//                System.out.println("碰撞了");
                plane.live = false;
                if (bao == null) {
                    bao = new Explode(plane.x, plane.y);
                }
                bao.draw(g);
                if(flag){
                    endtime = new Date();
                    period = (int) ((endtime.getTime() - starttime.getTime()) / 1000);
                    flag = false;
                }


            }
        }
        if(!plane.live){
            g.setColor(Color.red);
            Font f1 = new Font("宋体",Font.BOLD,50);
            g.setFont(f1);
            g.drawString("存活时间" + period +"秒",110,250);
        }

        g.setColor(c);
        g.setFont(f);
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

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}

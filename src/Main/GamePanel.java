package Main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16; //16x16
    final int scale = 3;
    public  final int tileSize =  originalTileSize * scale; // 48X48
 
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screeWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;// 576 pixels

    // FPS
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler KeyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, KeyH);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screeWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();;
    }

    @Override
//    public void run() {
//        double drawInterval = 1000000000/FPS; //0.01666 seconds;
//        double nextDrawTime = System.nanoTime() +  drawInterval;
//
//        while (gameThread != null){
//            // 1 UPDATE: update information such as character positions
//            update();
//
//
//
//            // 2 DRAW: draw the screen with the update information
//            repaint();
//
//            try{
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000;
//
//                if (remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long)remainingTime);
//
//                nextDrawTime += drawInterval;
//            }
//            catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//
//
//        }
//    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawcCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawcCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawcCount);
                drawcCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 =  (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();


    }
}

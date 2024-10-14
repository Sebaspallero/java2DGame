package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS

    private final int ORIGINAL_SIZE_TILE = 16; //16 x 16 tile
    private final int SCALE = 3; // we scale the tile bcs 16x16 looks too small on modern screens

    private final int TILE_SIZE = ORIGINAL_SIZE_TILE * SCALE; // 48 x 48 tile on modern screens

    private final int MAX_SCREEN_COL = 16; // total of tiles that can be showed on one screen horizontally
    private final int MAX_SCREEN_ROW = 12; // total of tiles that can be showed on one screen vertically

    //SIZE OF  GAME SCREEN
    private final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    private final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels 

    //CHECK KEY INPUT
    KeyHandler keyH = new KeyHandler();

    //GAME LOOP
    Thread gameThread;

    //CREATE PLAYER
    Player player = new Player(this, keyH);

    //CREATE TILE

    TileManager tileManager = new TileManager(this);

    //FPS: Frames Per Second, how many times the screen updates per second (30 or 60)
    int FPS = 60;



    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        

            double drawInterval = 1000000000 / FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            int drawCount = 0;

            while (gameThread != null) {
                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    // 1 UPDATE: Update information such as the player position
                    update();
                    // 2 PAINT: Paint the screen with the updated information
                    repaint();
                    delta--;
                    drawCount++;
                }

                //PRINT THE FPS ON TERMINAL
                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);

        player.draw(g2);

        g2.dispose();

    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }

    public int getMAX_SCREEN_COL() {
        return MAX_SCREEN_COL;
    }

    public int getMAX_SCREEN_ROW() {
        return MAX_SCREEN_ROW;
    }

}
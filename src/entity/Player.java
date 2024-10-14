package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH; 

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        //SET PLAYER INITIAL POSITION
        x = 100;
        y = 100;

        //HOW MUCH WILL THE PLAYER MOVE ON THE GAME SCREEN
        speed = 4;

        //INITIAL DIRECTION
        direction = "down";
    }

    

    public void getPlayerImage(){
        
        try {

            up1 = ImageIO.read(getClass().getResource("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResource("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResource("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResource("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResource("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResource("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("/res/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
       //UPDATE THE PLAYER POSITION
        if (keyH.upPressed == true) {
            direction = "up";
            y = y - speed;
        } 

        else if (keyH.downPressed == true) {
            direction = "down";
            y = y + speed;
        }

        else if (keyH.leftPressed == true) {
            direction = "left";
            x = x - speed;
        }

        else if (keyH.rightPressed == true) {
            direction = "right";
            x = x + speed;
        }

        //THE SPRITE CHANGES EVERY 10 FRAMES
        spriteCounter++;

        if (spriteCounter > 15) {
            if(spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
            }
            case "down" -> {
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
            }
            case "left" -> {
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
            }
            case "right" -> {
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
            }
            default -> image = down1;
        }

        g2.drawImage(image, x, y, gp.getTILE_SIZE(), gp.getTILE_SIZE(), null);
    }
}
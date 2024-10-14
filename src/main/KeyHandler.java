package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    //We check if the player pressed a key to move and set value to true to move the character in that direction
    @Override
    public void keyPressed(KeyEvent e) {
       int code = e.getKeyCode();

       if (code == KeyEvent.VK_W) {
            upPressed = true;
       }

       if (code == KeyEvent.VK_A) {
            leftPressed = true;
       }

       if (code == KeyEvent.VK_S) {
            downPressed = true;
       }

       if (code == KeyEvent.VK_D) {
            rightPressed = true;
       }
    }

    //We check if the player released a key to stop move and set value to false to stop the character
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
             upPressed = false;
        }
 
        if (code == KeyEvent.VK_A) {
             leftPressed = false;
        }
 
        if (code == KeyEvent.VK_S) {
             downPressed = false;
        }
 
        if (code == KeyEvent.VK_D) {
             rightPressed = false;
        }
    }

}

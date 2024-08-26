package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    
    public Player(GamePanel gp , KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues(); 
        getPlayerImage();

    }
    public void setDefaultValues(){ // set player's default values
        x = 100;
        y = 100;
        speed = 2;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            down1 = ImageIO.read(getClass().getResource("/res/player-walk/tile001.png"));
            down2 = ImageIO.read(getClass().getResource("/res/player-walk/tile004.png"));
            
            left1 = ImageIO.read(getClass().getResource("/res/player-walk/tile007.png"));
            left2 = ImageIO.read(getClass().getResource("/res/player-walk/tile010.png"));
            
            right1 = ImageIO.read(getClass().getResource("/res/player-walk/tile012.png"));
            right2 = ImageIO.read(getClass().getResource("/res/player-walk/tile016.png"));
            
            up1 = ImageIO.read(getClass().getResource("/res/player-walk/tile019.png"));
            up2 = ImageIO.read(getClass().getResource("/res/player-walk/tile022.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            
            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;

            } else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2){
        // FOR DEBUGGING
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1)
                    image = up1;    
                if(spriteNum == 2)
                    image = up2;    
                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;    
                if(spriteNum == 2)
                    image = down2;    
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;    
                if(spriteNum == 2)
                    image = left2;    
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;    
                if(spriteNum == 2)
                    image = right2;    
                break;
            
        }

        // g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);   
        
        // SCALING IMAGE [EXPERIMENTAL]
        int scaleTile= gp.tileSize * 4; 
        g2.drawImage(image, x, y, scaleTile, scaleTile, null);
        
    }
}

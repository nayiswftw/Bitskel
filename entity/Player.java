package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public final class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX, screenY;

    public Player(GamePanel gp , KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize /2); // multiplied by 3 to match the player scale
        screenY = gp.screenHeight/2 - (gp.tileSize /2 );  // multiplied by 3 to match the player scale

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues(); 
        getPlayerImage();

    }
    public void setDefaultValues(){ // set player's default values
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
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
                

            } else if (keyH.downPressed == true) {
                direction = "down";
                
            } else if (keyH.leftPressed == true) {
                direction = "left";
                
            } else if (keyH.rightPressed == true) {
                direction = "right";
                
            }
            
            //check tile collision
            collisionOn=false;
            gp.cChecker.checkTile(this);
            
            //if collision is false, player can move
            if(collisionOn == false){
                switch (direction) {
                    case "up" -> worldY -= speed;  
                    case "down" -> worldY += speed; 
                    case "left" -> worldX -= speed; 
                    case "right" -> worldX += speed; 
                    
                }
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
            case "up" -> image = (spriteNum == 1) ? up1 : (spriteNum == 2) ? up2 : null;
            case "down" -> image = (spriteNum == 1) ? down1 : (spriteNum == 2) ? down2 : null;
            case "left" -> image = (spriteNum == 1) ? left1 : (spriteNum == 2) ? left2 : null;
            case "right" -> image = (spriteNum == 1) ? right1 : (spriteNum == 2) ? right2 : null;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        
        // SCALING IMAGE [EXPERIMENTAL]
        // int scaleTile= gp.tileSize * 3; 
        // g2.drawImage(image, screenX, screenY, scaleTile, scaleTile, null);
        
    }
}

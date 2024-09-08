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
    int hasKey = 0;


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
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues(); 
        getPlayerImage();

    }
    public void setDefaultValues(){ // set player's default values
        worldX = gp.tileSize * 13;
        worldY = gp.tileSize * 10;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            down1 = ImageIO.read(getClass().getResource("/res/player-walk/down000.png"));
            down2 = ImageIO.read(getClass().getResource("/res/player-walk/down001.png"));
            down3 = ImageIO.read(getClass().getResource("/res/player-walk/down002.png"));
            down4 = ImageIO.read(getClass().getResource("/res/player-walk/down003.png"));

            left1 = ImageIO.read(getClass().getResource("/res/player-walk/left000.png"));
            left2 = ImageIO.read(getClass().getResource("/res/player-walk/left001.png"));
            left3 = ImageIO.read(getClass().getResource("/res/player-walk/left002.png"));
            left4 = ImageIO.read(getClass().getResource("/res/player-walk/left003.png"));

            right1 = ImageIO.read(getClass().getResource("/res/player-walk/right000.png"));
            right2 = ImageIO.read(getClass().getResource("/res/player-walk/right001.png"));
            right3 = ImageIO.read(getClass().getResource("/res/player-walk/right002.png"));
            right4 = ImageIO.read(getClass().getResource("/res/player-walk/right003.png"));

            up1 = ImageIO.read(getClass().getResource("/res/player-walk/up000.png"));
            up2 = ImageIO.read(getClass().getResource("/res/player-walk/up001.png"));
            up3 = ImageIO.read(getClass().getResource("/res/player-walk/up002.png"));
            up4 = ImageIO.read(getClass().getResource("/res/player-walk/up003.png"));
            
        } catch (IOException e) {
        } catch (IllegalArgumentException e) {
            System.err.println("Image file not found: " + e.getMessage());
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

            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

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
            if (spriteCounter > 12) {
                spriteNum++;
                if (spriteNum > 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key" -> {
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKey);
                }
                
                case "Chest" -> { 
                    if(hasKey > 0){
                        hasKey--;
                        gp.obj[i] = null;
                        System.out.println("Key: " + hasKey);
                    
                    }
                }
            }
        }

    }
    public void draw(Graphics2D g2){
        // FOR DEBUGGING
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up" -> image = (spriteNum == 1) ? up1 : (spriteNum == 2) ? up2 : (spriteNum == 3) ? up3 : (spriteNum == 4) ? up4 : null;
            case "down" -> image = (spriteNum == 1) ? down1 : (spriteNum == 2) ? down2 :(spriteNum == 3) ? down3 : (spriteNum == 4) ? down4 : null;
            case "left" -> image = (spriteNum == 1) ? left1 : (spriteNum == 2) ? left2 :  (spriteNum == 3) ? left3 : (spriteNum == 4) ? left4 : null;
            case "right" -> image = (spriteNum == 1) ? right1 : (spriteNum == 2) ? right2 : (spriteNum == 3) ? right3 : (spriteNum == 4) ? right4 : null;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        
        // SCALING IMAGE [EXPERIMENTAL]
        // int scaleTile= gp.tileSize * 3; 
        // g2.drawImage(image, screenX, screenY, scaleTile, scaleTile, null);
        
    }
}

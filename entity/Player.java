package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;


public final class Player extends Entity {

   
    KeyHandler keyH;
    public final int screenX, screenY;
   
    int standCounter = 0;
    boolean moving = false;
    int pixelCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
    
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // multiplied by 3 to match the player scale
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // multiplied by 3 to match the player scale

        solidArea = new Rectangle();
        solidArea.x = 1;
        solidArea.y = 1;
        solidArea.width = 46;
        solidArea.height = 46;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() { // set player's default values
        worldX = gp.tileSize * 13;
        worldY = gp.tileSize * 10;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        // image is same as player h

        down1 = setup("down000");
        down2 = setup("down001");
        down3 = setup("down002");
        down4 = setup("down003");

        left1 = setup("left000");
        left2 = setup("left001");
        left3 = setup("left002");
        left4 = setup("left003");

        right1 = setup("right000");
        right2 = setup("right001");
        right3 = setup("right002");
        right4 = setup("right003");

        up1 = setup("up000");
        up2 = setup("up001");
        up3 = setup("up002");
        up4 = setup("up003");

    }

    
    public void update() {

        if (moving == false) {
            if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                    || keyH.rightPressed == true) {

                if (keyH.upPressed == true) {
                    direction = "up";

                } else if (keyH.downPressed == true) {
                    direction = "down";

                } else if (keyH.leftPressed == true) {
                    direction = "left";

                } else if (keyH.rightPressed == true) {
                    direction = "right";

                }
                moving = true;

                // check tile collision
                collisionOn = false;
                gp.cChecker.checkTile(this);

                // check object collision
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);
             }
             else {
                standCounter++;
                if (standCounter == 10) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }

        //check  npc collision
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        if (moving == true) {
            // if collision is false, player can move
            if (collisionOn == false) {
                switch (direction) {
                    case "up" ->
                        worldY -= speed;
                    case "down" ->
                        worldY += speed;
                    case "left" ->
                        worldX -= speed;
                    case "right" ->
                        worldX += speed;

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
            pixelCounter += speed;
            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;

            }
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            
        
            }
        }
   public void interactNPC(int i){
    if (i != 999) {
           System.out.println("you are hitting an npc!"); 
        
    }
   }

    @Override
    public void draw(Graphics2D g2, GamePanel aThis) {
        // FOR DEBUGGING
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        // g2.drawString("Playtime: " + dFormat.format(playTime), 50, 50);

        BufferedImage image = null;

        switch (direction) {
            case "up" ->
                image = (spriteNum == 1) ? up1
                        : (spriteNum == 2) ? up2 : (spriteNum == 3) ? up3 : (spriteNum == 4) ? up4 : null;
            case "down" ->
                image = (spriteNum == 1) ? down1
                        : (spriteNum == 2) ? down2 : (spriteNum == 3) ? down3 : (spriteNum == 4) ? down4 : null;
            case "left" ->
                image = (spriteNum == 1) ? left1
                        : (spriteNum == 2) ? left2 : (spriteNum == 3) ? left3 : (spriteNum == 4) ? left4 : null;
            case "right" ->
                image = (spriteNum == 1) ? right1
                        : (spriteNum == 2) ? right2 : (spriteNum == 3) ? right3 : (spriteNum == 4) ? right4 : null;
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}


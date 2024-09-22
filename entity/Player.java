package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public final class Player extends Entity {

    public BufferedImage up1, up2, up3, up4, up5, up6, down1, down2, down3, down4, down5, down6;
    public BufferedImage left1, left2, left3, left4, left5, left6, right1, right2, right3, right4, right5, right6;

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
        BufferedImage image;
        try {
            image = ImageIO.read(getClass().getResource("/res/player/player-run.png"));


            down1 = setup(image.getSubimage(0, 0, 16, 16));
            down2 = setup(image.getSubimage(16, 0, 16, 16));
            down3 = setup(image.getSubimage(32, 0, 16, 16));
            down4 = setup(image.getSubimage(48, 0, 16, 16));
            down5 = setup(image.getSubimage(64, 0, 16, 16));
            down6 = setup(image.getSubimage(80, 0, 16, 16));
            
            up1 = setup(image.getSubimage(0, 16, 16, 16));         
            up2 = setup(image.getSubimage(16, 16, 16, 16));         
            up3 = setup(image.getSubimage(32, 16, 16, 16));         
            up4 = setup(image.getSubimage(48, 16, 16, 16));
            up5 = setup(image.getSubimage(64, 16, 16, 16));
            up6 = setup(image.getSubimage(80, 16, 16, 16));

            left1 = setup(image.getSubimage(0, 32, 16, 16));
            left2 = setup(image.getSubimage(16, 32, 16, 16));
            left3 = setup(image.getSubimage(32, 32, 16, 16));
            left4 = setup(image.getSubimage(48, 32, 16, 16));
            left5 = setup(image.getSubimage(64, 32, 16, 16));
            left6 = setup(image.getSubimage(80, 32, 16, 16));

            right1 = setup(image.getSubimage(0, 48, 16, 16));
            right2 = setup(image.getSubimage(16, 48, 16, 16));
            right3 = setup(image.getSubimage(32, 48, 16, 16));
            right4 = setup(image.getSubimage(48, 48, 16, 16));
            right5 = setup(image.getSubimage(64, 48, 16, 16));
            right6 = setup(image.getSubimage(80, 48, 16, 16));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            } else {
                standCounter++;
                if (standCounter == 10) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }

        // check npc collision
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
                if (spriteNum > 6) {
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

    public void interactNPC(int i) {
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
                : (spriteNum == 2) ? up2
                : (spriteNum == 3) ? up3
                : (spriteNum == 4) ? up4
                : (spriteNum == 5) ? up5
                : (spriteNum == 6) ? up6 : null;
            case "down" ->
            image = (spriteNum == 1) ? down1
                : (spriteNum == 2) ? down2
                : (spriteNum == 3) ? down3
                : (spriteNum == 4) ? down4
                : (spriteNum == 5) ? down5
                : (spriteNum == 6) ? down6 : null;
            case "left" ->
            image = (spriteNum == 1) ? left1
                : (spriteNum == 2) ? left2
                : (spriteNum == 3) ? left3
                : (spriteNum == 4) ? left4
                : (spriteNum == 5) ? left5
                : (spriteNum == 6) ? left6 : null;
            case "right" ->
            image = (spriteNum == 1) ? right1
                : (spriteNum == 2) ? right2
                : (spriteNum == 3) ? right3
                : (spriteNum == 4) ? right4
                : (spriteNum == 5) ? right5
                : (spriteNum == 6) ? right6 : null;
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}

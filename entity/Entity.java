package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.UtilityTool;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4;
    public BufferedImage left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int ActionLookCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

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

    }

    public void draw(Graphics2D g2, GamePanel aThis) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

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

    public BufferedImage setup(BufferedImage imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = imagePath;
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;

    }
}

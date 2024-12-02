package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Heart;
import object.SuperObject;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;

    public boolean messageOn = false;
    public String message = "";
    // int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = " ";
    public int commandNum = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        //create HUD Object
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //title state
        if(gp.gameState == gp.titleState) {
            drawTitleScreen(g2);
        }
        //Play state
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            
        }
        //pause state
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        //dialogue state
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawPlayerLife(){

        gp.player.life = 5;  // change
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i =0;

        //Draw balnk heart 
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+= gp.tileSize;
        }

        //Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i =0;

        //Draw current life 
        while(i < gp.player.life){
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x+= gp.tileSize;
        }

    }
    public void drawTitleScreen(Graphics2D g21){

        g2.setColor(new Color(0,0,40));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        String text = "Blue Boy Adventure" ;
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        // Main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //Blue boy image
        x = gp.screenWidth/2 - (gp.titleState*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1,x,y ,gp.tileSize*2,gp.tileSize*2,null);
          
        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }


        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1){
        g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 2){
        g2.drawString(">", x-gp.tileSize, y);
        }
    }
     

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);

        g2.setFont(arial_80B);
    }

    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}

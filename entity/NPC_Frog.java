package entity;

import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import main.GamePanel;

public class NPC_Frog extends Entity {
    public NPC_Frog(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
    }

    public void getImage() {
        BufferedImage image;
        try {
            image = ImageIO.read(getClass().getResource("/res/npc/frog-spritesheet.png"));


            down1 = setup(image.getSubimage(0, 0, 16, 16));
            down2 = setup(image.getSubimage(16, 0, 16, 16));
            down3 = setup(image.getSubimage(32, 0, 16, 16));
            down4 = setup(image.getSubimage(48, 0, 16, 16));
            
            up1 = setup(image.getSubimage(0, 16, 16, 16));         
            up2 = setup(image.getSubimage(16, 16, 16, 16));         
            up3 = setup(image.getSubimage(32, 16, 16, 16));         
            up4 = setup(image.getSubimage(48, 16, 16, 16));

            right1 = setup(image.getSubimage(0, 32, 16, 16));
            right2 = setup(image.getSubimage(16, 32, 16, 16));
            right3 = setup(image.getSubimage(32, 32, 16, 16));
            right4 = setup(image.getSubimage(48, 32, 16, 16));

            left1 = setup(image.getSubimage(0, 48, 16, 16));
            left2 = setup(image.getSubimage(16, 48, 16, 16));
            left3 = setup(image.getSubimage(32, 48, 16, 16));
            left4 = setup(image.getSubimage(48, 48, 16, 16));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
        ActionLookCounter++;

        if (ActionLookCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number form 1 to 100
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            ActionLookCounter = 0;
        }
    }
}

package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_OldMan extends Entity {
     @SuppressWarnings("OverridableMethodCallInConstructor")
     public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
     }

    public void getImage() {

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
    public void setDialogue() {
        dialogues[0] = "Hello , lad !!";
        dialogues[1] = "So have you come to this insland to \n find the treasure?";
        dialogues[2] = "I used to be a great wizard but now...\n  I'm a bit too old for taking an adventure.";
        dialogues[3] = "Well, good luck on you.";

    }
     @Override
    public void setAction(){
        ActionLookCounter ++;

        if(ActionLookCounter==120){
        Random random = new Random();
        int i = random.nextInt(100)+1; //pick up a number form 1 to 100
        if(i<=25){
         direction = "up";
        }
        if(i>25 && i <= 50){
         direction = "down";
        }
        if(i>50 && i<=75){
         direction = "left";
        }
        if(i>75 && i<=100){
         direction = "right";
        }
        ActionLookCounter=0;
    }}
 
     @Override
 public void speak(){
    super.speak();
}
   
 }

    

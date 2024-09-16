package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;
    public OBJ_Chest( GamePanel gp) {
        
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResource("/res/objects/chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(IOException e){
        }
        collision = true;
    }
}


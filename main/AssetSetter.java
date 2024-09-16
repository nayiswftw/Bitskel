package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 13 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new OBJ_Chest(gp);
        gp.obj[1].worldX = 30 * gp.tileSize;
        gp.obj[1].worldY = 23 * gp.tileSize;


        gp.obj[2] = new OBJ_Boots(gp);
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 17 * gp.tileSize;

        


    }
}

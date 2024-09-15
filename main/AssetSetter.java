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
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 7 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 25 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 7 * gp.tileSize;
        gp.obj[2].worldY = 11 * gp.tileSize;

        gp.obj[3] = new OBJ_Chest();
        gp.obj[3].worldX = 22 * gp.tileSize;
        gp.obj[3].worldY = 25 * gp.tileSize;

        gp.obj[4] = new OBJ_Boots();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 17 * gp.tileSize;

        


    }
}

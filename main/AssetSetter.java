package main;

import object.OBJ_Chest;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 25 * gp.tileSize;
        gp.obj[0].worldY = 25 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 25 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 25 * gp.tileSize;
        gp.obj[2].worldY = 22 * gp.tileSize;

        gp.obj[3] = new OBJ_Chest();
        gp.obj[3].worldX = 22 * gp.tileSize;
        gp.obj[3].worldY = 25 * gp.tileSize;
        

    }
}

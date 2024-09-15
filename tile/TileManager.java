package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public final class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[72];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/map.txt");
    }

    public void getTileImage() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/res/maps/tiledata.txt")))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResource("/res/tiles/" + line));
                line = br.readLine();
                tile[i].collision = Boolean.parseBoolean(line);
                i++;
            }
            
        } catch (IOException e) {
           System.out.println("Tile image not found");
        }
    }
    
    public void loadMap(String filePath){
     
        InputStream is = getClass().getResourceAsStream(filePath);
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();
                
                while(col < gp.maxWorldCol) {
                    
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num; //error
                    col++;
                    
                }
                if(col == gp.maxWorldCol){
                    col=0;
                    row++;
                    
                }
            }
            br.close();
            
            
        } catch (IOException e) {
        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

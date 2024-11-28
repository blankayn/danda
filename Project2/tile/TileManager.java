package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;  // Should be 'tiles' to match class 'Tile'
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];  // Array of tile objects (should be 'tiles', plural)
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/mapkey.txt");  // Ensure the map path is correct
    }

    // Load the images for tiles
    public void getTileImage() {
        try {
            // Initialize each tile
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/walls.png"));
            tiles[0].collision = true;

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/ground.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/planks.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the map (tile layout)
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Draw the tiles on screen
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Switch based on player direction (handling screen boundaries)
            switch (gp.player.direction) {
                case "up":
                    if (worldX - (gp.tileSize * 6) > gp.player.worldX - gp.player.screenX &&
                        worldX + (gp.tileSize * 6) < gp.player.worldX + gp.player.screenX &&
                        worldY - (gp.tileSize * 2) > gp.player.worldY - gp.player.screenY &&
                        worldY + (gp.tileSize * 5) < gp.player.worldY + gp.player.screenY) {
                        g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
                    break;
                case "down":
                    if (worldX - (gp.tileSize * 6) > gp.player.worldX - gp.player.screenX &&
                        worldX + (gp.tileSize * 6) < gp.player.worldX + gp.player.screenX &&
                        worldY - (gp.tileSize * 5) > gp.player.worldY - gp.player.screenY &&
                        worldY + (gp.tileSize * 2) < gp.player.worldY + gp.player.screenY) {
                        g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
                    break;
                case "left":
                    if (worldX - (gp.tileSize * 2) > gp.player.worldX - gp.player.screenX &&
                        worldX + (gp.tileSize * 7) < gp.player.worldX + gp.player.screenX &&
                        worldY - (gp.tileSize * 4) > gp.player.worldY - gp.player.screenY &&
                        worldY + (gp.tileSize * 4) < gp.player.worldY + gp.player.screenY) {
                        g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
                    break;
                case "right":
                    if (worldX - (gp.tileSize * 7) > gp.player.worldX - gp.player.screenX &&
                        worldX + (gp.tileSize * 2) < gp.player.worldX + gp.player.screenX &&
                        worldY - (gp.tileSize * 4) > gp.player.worldY - gp.player.screenY &&
                        worldY + (gp.tileSize * 4) < gp.player.worldY + gp.player.screenY) {
                        g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
                    break;
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

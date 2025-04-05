package tile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.UtilityTool;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {


        tile[0] = new Tile();
        tile[0].image = loadTile("res/tiles/grass01.png", gp.tileSize, gp.tileSize);

        tile[1] = new Tile();
        tile[1].image = loadTile("res/tiles/wall.png", gp.tileSize, gp.tileSize);
       
        tile[2] = new Tile();
        tile[2].image = loadTile("res/tiles/water00.png", gp.tileSize, gp.tileSize);

        tile[3] = new Tile();
        tile[3].image = loadTile("res/tiles/road00.png", gp.tileSize, gp.tileSize);

        tile[4] = new Tile();
        tile[4].image = loadTile("res/tiles/road01.png", gp.tileSize, gp.tileSize);

        tile[5] = new Tile();
        tile[5].image = loadTile("res/tiles/road02.png", gp.tileSize, gp.tileSize);




    }

    public BufferedImage loadTile(String pathImage, int width, int height) {
        System.out.println(pathImage);
        File ImageTile = new File(pathImage);
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(ImageTile);
            image = uTool.scaleImage(image, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void loadMap() {
        try {
            // File mapTxt = new File("/res/map/map01.txt"); 
            BufferedReader br = new BufferedReader(new FileReader("res/map/map01.txt"));
    
            int row = 0;
            while (row < gp.maxScreenRow) {
                String line = br.readLine();
                if (line == null) break; // Evita erro caso o arquivo seja menor que o esperado
                System.out.println("entrou em load map");
                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    if (col < numbers.length) {
                        mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                    } else {
                        mapTileNum[col][row] = 0; // Preenche com 0 caso faltem números
                    }
                }
                row++;
            }
    
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

}

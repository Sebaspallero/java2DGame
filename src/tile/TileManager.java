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
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.getMAX_SCREEN_COL()][gp.getMAX_SCREEN_ROW()];

        getTileImage();
        loadMap("/res/map/map001.txt");
    }

    public void getTileImage(){
        try {
            //GRASS
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/001.png"));

            //WALL
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/002.png"));

            //WATER
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/003.png"));

            //EARTH
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/004.png"));


            //TREE
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/005.png"));

            //SAND
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/006.png"));

    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            //IMPORT TXT FILE
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            //READ CONTENT OF TXT FILE
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gp.getMAX_SCREEN_COL() && row < gp.getMAX_SCREEN_ROW()) {
                String line = bufferedReader.readLine(); //READ A SINGLE LINE OF TEXT
                while (col < gp.getMAX_SCREEN_COL()) {
                    String numbers[] = line.split(" ");  // PUT THE NUMBERS(STRING) OF THE MAP INTO AN ARRAY -> numbers[0] = 0 
                    int num = Integer.parseInt(numbers[col]); // CONVERT THE NUMBER(STRING) INTO AN INT

                    mapTileNum[col][row] = num; //WE STORE THE EXTRACTED NUMBER INTO THE MAP ARRAY
                    col++;
                }
                if (col == gp.getMAX_SCREEN_COL()) {
                    col = 0;
                    row++;
                }
            } 
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;

       while (col < gp.getMAX_SCREEN_COL() && row < gp.getMAX_SCREEN_ROW()) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.getTILE_SIZE(), gp.getTILE_SIZE(), null);
            col++; //WE MOVE ONE COLUMN
            x += gp.getTILE_SIZE(); // WE MOVE 48 PIXELS ON X AXIS TO DRAW THE NEXT TILE

            if (col == gp.getMAX_SCREEN_COL()) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTILE_SIZE();
            }

       }
    }


   
}

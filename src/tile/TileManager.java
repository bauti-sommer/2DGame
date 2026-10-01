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
	public Tile[] tile;
	public int mapTileNum[][];
	public int roomY;
	public int roomX;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		getTileImage();
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		roomX=0;
		roomY=0;
		loadMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass02.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[3].collision = true;
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bush01.png"));
			
			
			
			
		} catch(IOException e) {
			
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
			
		} catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) { 
		int screenCol = 0;
		int screenRow = 0;

		
		
		while(screenCol < gp.maxScreenCol && screenRow < gp.maxScreenRow) {
			
			int worldCol = screenCol + (roomX * gp.maxScreenCol);
			int worldRow = screenRow + (roomY * gp.maxScreenRow);
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int screenX = screenCol * gp.tilesize;
	        int screenY = screenRow * gp.tilesize;

			
	        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tilesize, gp.tilesize, null);
	
	        screenCol++;
			if (screenCol == gp.maxScreenCol) {
				screenCol= 0;
				screenRow++;
			}
			
	}
	}
}

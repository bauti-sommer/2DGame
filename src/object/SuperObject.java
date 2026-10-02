package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;

	
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int roomX = worldX / gp.screenWidth;
		int roomY = worldY / gp.screenLength;
		
		if(roomX == gp.tileM.roomX && roomY == gp.tileM.roomY) {
			int screenX = worldX - roomX * gp.screenWidth;
			int screenY = worldY - roomY * gp.screenLength;
			g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
		}
	}	
}


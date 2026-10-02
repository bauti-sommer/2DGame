package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {


	
	GamePanel gp;
	KeyHandler keyH;
	
	public int screenX;
	public int screenY;
	public int keys = 0;

	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = 100;
		worldY = 100;
		screenX = 100;
		screenY = 100;
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		try {
			up0 = ImageIO.read(getClass().getResourceAsStream("/player/up0.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			down0 = ImageIO.read(getClass().getResourceAsStream("/player/down0.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			left0 = ImageIO.read(getClass().getResourceAsStream("/player/left0.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			right0 = ImageIO.read(getClass().getResourceAsStream("/player/right0.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
		
		if(keyH.upPressed == true) { 
			
			direction = "up";
			
		}
		if(keyH.downPressed == true) {
	
			direction = "down";
			
		}
		if(keyH.leftPressed == true) {

			direction = "left";
			
		}
		if(keyH.rightPressed == true) {
	
			direction = "right";
			
		}
		collisionOn = false;
		gp.cChecker.checkTile(this);
		int index = gp.cChecker.checkObject(this, true);
		pickUpObject(index);
		
		if (collisionOn == false) {
		    switch(direction) {
		    case "up":  
		        
		        screenY -= speed;
		        if (screenY < 0) {
		            gp.tileM.roomY--;
		            screenY = gp.screenLength; 
		        }
		        break;

		    case "down":
		        
		        screenY += speed;
		        if (screenY >= gp.screenLength) {
		            gp.tileM.roomY++;
		            screenY = 0; 
		        }
		        break;

		    case "left":
		        
		        screenX -= speed;
		        if (screenX < 0) {
		            gp.tileM.roomX--;
		            screenX = gp.screenWidth;
		        }
		        break;

		    case "right": 
		        
		        screenX += speed;
		        if (screenX >= gp.screenWidth) {
		            gp.tileM.roomX++;
		            screenX = 0; 
		        }
		        break;
		    }
		}
		worldX = (gp.tileM.roomX * gp.screenWidth) + screenX;
		worldY = (gp.tileM.roomY * gp.screenLength) + screenY;
		spriteCounter++;
		
		if(spriteCounter > 10) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			spriteCounter = 0;
		}}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up0;
			}
			if(spriteNum == 2) {
				image = up1;
			}
		break;
		case "down": 
			if(spriteNum == 1) {
				image = down0;
			}
			if(spriteNum == 2) {
				image = down1;
			}
		break;
		case "left": 
			if(spriteNum == 1) {
				image = left0;
			}
			if(spriteNum == 2) {
				image = left1;
			}
		break;
		case "right":
			if(spriteNum == 1) {
				image = right0;
			}
			if(spriteNum == 2) {
				image = right1;
			}
		break;
		}
		
		g2.drawImage(image, screenX, screenY,gp.tilesize, gp.tilesize, null);
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
			String name = gp.obj[i].name;
			
			switch(name) {
			case "Key":
				keys++;
				gp.obj[i] = null;
				break;
			case "Door":
				if(keys>0) {
					gp.obj[i] = null;
				}
				break;
			}
		}
	}
	
}

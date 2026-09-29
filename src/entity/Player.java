package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {


	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
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
		if(keyH.upPressed == true) { 
			y -= speed;
			direction = "up";
			spriteCounter++;
		}
		if(keyH.downPressed == true) {
			y += speed;
			direction = "down";
			spriteCounter++;
		}
		if(keyH.leftPressed == true) {
			x -= speed;
			direction = "left";
			spriteCounter++;
		}
		if(keyH.rightPressed == true) {
			x += speed;
			direction = "right";
			spriteCounter++;
		}
		
		
		if(spriteCounter > 10) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			spriteCounter = 0;
		}
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
		
		g2.drawImage(image, x, y,gp.tilesize, gp.tilesize, null);
	}
}

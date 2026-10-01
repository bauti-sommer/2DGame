package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tilesize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	public final int screenWidth = maxScreenCol * tilesize;
	public final int screenLength = maxScreenRow * tilesize;
	
	public final int maxWorldCol = 48;
	public final int maxWorldRow = 24;
	public final int worldWidth = tilesize * maxWorldCol;
	public final int worldHeight = tilesize * maxWorldRow;

	
	int fps = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public TileManager tileM = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	
	
	
	
	public GamePanel (){
		this.setPreferredSize(new Dimension(screenWidth, screenLength));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;

			lastTime= currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
			
		}
		
	}
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; 
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
	
	

}

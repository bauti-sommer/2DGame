package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
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

	
	int fps = 60;
	
	//System
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public TileManager tileM = new TileManager(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Sound sound = new Sound();
	
	//Entity y Object
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	
	
	
	public GamePanel (){
		this.setPreferredSize(new Dimension(screenWidth, screenLength));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUpGame() {
		aSetter.setObject();
		playMusic(0);
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
		
		for( int i = 0; i < obj.length ; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		player.draw(g2);
		
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
		
	}
	
	public void stopMusic(int i) {
		sound.stop();
	}
	
	public void playSF(int i) {
		sound.setFile(i);
		sound.play();
	}

}

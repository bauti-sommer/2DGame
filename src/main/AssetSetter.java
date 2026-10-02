package main;

import object.*;


public class AssetSetter {
	public GamePanel gp;
	
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJKey();
		gp.obj[0].worldX = 5 * gp.tilesize;
		gp.obj[0].worldY = 5 * gp.tilesize;
		gp.obj[1] = new OBJDoor();
		gp.obj[1].worldX = 5 * gp.tilesize;
		gp.obj[1].worldY = 6 * gp.tilesize;
		
	}
}

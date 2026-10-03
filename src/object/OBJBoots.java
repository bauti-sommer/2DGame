package object;

import javax.imageio.ImageIO;

public class OBJBoots extends SuperObject {
	
	public OBJBoots() {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch(Exception e) {
			e.printStackTrace();
		
		}
	}
}

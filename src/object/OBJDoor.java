package object;

import javax.imageio.ImageIO;

public class OBJDoor extends SuperObject {
	
	public OBJDoor() {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch(Exception e) {
			e.printStackTrace();
		
		}
		collision = true;
	}
}

package main;

import object.OBJ_Balls;
import object.OBJ_Door;
import object.OBJ_Granny;
import object.OBJ_Stairs;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
		
	}
	
	public void setObject() {
//		gp.obj[0] = new OBJ_Balls();
//		gp.obj[0].worldX = 2 * gp.tileSize;
//		gp.obj[0].worldY = 3 * gp.tileSize;
		
		gp.obj[0] = new OBJ_Balls();
		gp.obj[0].worldX = 26 * gp.tileSize;
		gp.obj[0].worldY = 25 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Door();
		gp.obj[1].worldX = 27 * gp.tileSize;
		gp.obj[1].worldY = 47 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Stairs();
		gp.obj[2].worldX = 24 * gp.tileSize;
		gp.obj[2].worldY = 47 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Granny();
		gp.obj[3].worldX = 48 * gp.tileSize;
		gp.obj[3].worldY = 21 * gp.tileSize;
		
		
	}
	
	
}

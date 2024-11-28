package entity;

import java.awt.Rectangle;
import main.GamePanel;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX,worldY;
	public int speed;
	   protected GamePanel gp;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, avatar;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public int  solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	 
	
	    public Entity(int worldX, int worldY, int speed, String direction) {
	        this.worldX = worldX;
	        this.worldY = worldY;
	        this.speed = speed;
	        this.direction = direction;
	    }
	}
	



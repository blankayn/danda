package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		 super(0, 0, 4, "down");
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32  ;
		
		
		setDefaultValues(); 
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 25;
//		worldX = gp.tileSize * 2;
//		worldY = gp.tileSize * 3;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			System.out.print("hi");
			up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/top1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/top2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/bottom1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/bottom2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));
			avatar = ImageIO.read(getClass().getResourceAsStream("/res/player/Avatar .png"));
			
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
				
			}
			
			else if(keyH.downPressed == true) {
				direction = "down";
				
			}
			
			else if(keyH.leftPressed == true) {
				direction = "left";
				
			}
			
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//checkCollison
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			int objIndex =  gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			
			//if collison is false.player can move
			if(collisionOn == false) {
				
				if(keyH.shift == true) {
					speed = 7;
				}else {
					speed = 4;
				}
				
				
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		}
		
		
		
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "Balls":
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("Finally, I found one of my Balls!\nI can leave now");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSE(3);
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("The door's unlocked.\nLet's see what's ahead");
				}else {
				gp.ui.showMessage("I cant leave yet. I need to find one \nof my balls first");
				}
				break;
			case "Granny":
				gp.playSE(2);
				speed += 4;
				gp.obj[i] = null;
				break; 
			case "Stairs":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				break;
			}
		}
	}
	
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		
		
	}
}

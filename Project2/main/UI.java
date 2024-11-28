package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Balls;

public class UI {
	
	GamePanel gp;
	Font arial_40;
	Font arial_80;
	BufferedImage keyImage;
	BufferedImage av;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	
	int x;
	int y;
	
	int width;
	int height;
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 30);
		arial_80 = new Font("Arial", Font.BOLD, 70);
		
		
		
		OBJ_Balls key = new OBJ_Balls();
		keyImage = key.image;
		
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		if(gameFinished == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found one of your Balls";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = (gp.tileSize*4) + 25;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80);
			g2.setColor(Color.white);
			text = "Congratulations";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.tileSize*3;
			y = gp.screenHeight/2 + (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		}
		
		else {

			x = 10;
			y = 10;
			width = gp.tileSize*4;
			height = gp.tileSize * 2;
			Color c = new Color(0, 0, 0);
			
			g2.setFont(arial_40);
			g2.setColor(c);
			g2.fillRoundRect(x, y, width, height, 35, 35);
			
			c = new Color(255, 255, 255);
			
			g2.setColor(c);
			g2.setStroke(new BasicStroke(5));
			g2.drawRoundRect( x+5, y+5, width - 10, height - 10, 25, 25);
			
			
			
			g2.drawImage(keyImage, gp.tileSize, gp.tileSize/2, gp.tileSize + 11, gp.tileSize + 11, null);
			g2.drawString(" x " + gp.player.hasKey, 100, 65);
			
			
			//MESSAGE
			
			if(messageOn == true) {
				
				x = (gp.tileSize * 2) - 20;
				y = (gp.tileSize * 8) + 20;
				width = gp.tileSize*10;
				height = gp.tileSize * 3;
				av = gp.player.avatar;
				
				
				c = new Color(0, 0, 0, 122);
				
				
				g2.setFont(arial_40);
				g2.setColor(c);
				g2.fillRoundRect(x, y, width, height, 35, 35);
				
				c = new Color(255, 255, 255);
				
				g2.setColor(c);
				g2.setStroke(new BasicStroke(5));
				g2.drawRoundRect( x+130, y+5, width - 10, height - 10, 25, 25);
				
				for(String line : message.split("\n")) {
					g2.drawString(line, x+150, y+60);
					y += 40;
				}
				g2.drawImage(av, gp.tileSize - 30, (gp.tileSize * 8) - 20, gp.tileSize * 4, gp.tileSize * 4, null);
				
				messageCounter++;
				
				if(messageCounter > 240) {
					messageCounter = 0;
					messageOn = false;
				}
				
			}
		}
		
		
	}

}

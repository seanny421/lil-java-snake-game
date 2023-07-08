
			 //subclass inherits super

import java.awt.Color;
import java.awt.Graphics;

public class Snake extends GameEntity {
	GamePanel gp;
	KeyHandler keyH;
	

	
	
	
	public Snake(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		//inherit from super
		x = 100;
		y = 100;
		speed = 4;
		
	}
	
	public void update() {
		if(keyH.upPressed && y > 0) {
			y -= speed;
		}
		else if(keyH.downPressed) {
			y += speed;
		}
		else if(keyH.leftPressed && x > 0) {
			x -= speed;
		}
		else if(keyH.rightPressed) {
			x += speed;
		}
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, gp.tileSize, gp.tileSize);
	}

}

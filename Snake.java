

import java.awt.Color;
import java.awt.Graphics.*;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	GamePanel gp;
	KeyHandler keyH;
	int snakeLength;
	char direction = 'R'; //direction snake is moving
	
	
	final int x[];
	final int y[];
	
	
	public Snake(GamePanel gp, KeyHandler keyH) {
		x = new int[gp.GAME_UNITS];
		y = new int[gp.GAME_UNITS];
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		snakeLength = 1;
	}
	
	//check if head collides with apple co-ordinates
	public boolean checkApple(int appleX, int appleY) {
		if(x[0] == appleX && y[0] == appleY) {
			snakeLength++;
			return true;
		}
		return false;
	}
	
	public void checkCollisions() {
		//check for walls
		if(x[0] > gp.SCREEN_WIDTH || x[0] < 0 || y[0] > gp.SCREEN_HEIGHT || y[0] < 0) {
			gp.running = false;
		}

		//check for eating itself - better way to do this? FIXME
		for(int i = 1; i < x.length; i++) {
			for(int j = 1; j < y.length; j++) {
				if(x[0] == x[i] && y[0] == y[i] ) {
					gp.running = false;
					System.out.println("EATING ITSEFLF");
					break;//no need to continue loop
				}
			}
		}
		
	}
	
	public void gameOver(Graphics g) {
		
	}
	
	public void move() {
		for(int i = snakeLength; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'R':
			x[0] += gp.UNIT_SIZE;
			break;
		case 'L':
			x[0] -= gp.UNIT_SIZE;
			break;
		case 'U':
			y[0] -= gp.UNIT_SIZE;
			break;
		case 'D':
			y[0] += gp.UNIT_SIZE;
			break;
		}
		
	}
	
	public void updateDirection() {
		if(keyH.upPressed && direction != 'D') {
			direction = 'U';
		}
		else if (keyH.downPressed && direction != 'U') {
			direction = 'D';
		}
		else if (keyH.leftPressed && direction != 'R') {
			direction = 'L';
		}
		else if (keyH.rightPressed && direction != 'L') {
			direction = 'R';
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		FontMetrics metrics = g.getFontMetrics();
		g.drawString("Score: " + gp.score, (gp.SCREEN_WIDTH - metrics.stringWidth("Score: 0"))/2, 10);
		for(int i = 0; i < snakeLength; i++) {
			if(i == 0) {
				g.setColor(new Color(11, 163, 52));
			}
			else
				g.setColor(new Color(7, 133, 41));
			g.fillRect(x[i], y[i], gp.UNIT_SIZE, gp.UNIT_SIZE);
		}
	}

}

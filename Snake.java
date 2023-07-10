
			 //subclass inherits super

import java.awt.Color;
import java.awt.Graphics.*;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake extends GameEntity {
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
		ArrayList<Integer> xCopy = new ArrayList<Integer>();
		ArrayList<Integer> yCopy = new ArrayList<Integer>();
		for(int i = 1; i < x.length; i++) {
			xCopy.add(x[i]);
			
		}
		for(int i = 1; i < y.length; i++) {
			yCopy.add(y[i]);
		}
		
		if(xCopy.contains(x[0]) && yCopy.contains(y[0])) {
			System.out.println("OVERLAP");
			gp.running = false;
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
	
	public void update() {
		//check for game info (screen borders + apple
		//move the snake
		
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

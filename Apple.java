
		    //subclass inherits super

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Apple {
	Random rand = new Random();
	GamePanel gp;
	int x, y;

	
	public Apple(GamePanel gp) {
		this.gp = gp;
		setDefaultValues();
	}
	
	private void setDefaultValues(){
		x = rand.nextInt(0, (int) gp.SCREEN_WIDTH/gp.UNIT_SIZE)*gp.UNIT_SIZE;
		y = rand.nextInt(0, (int) gp.SCREEN_HEIGHT/gp.UNIT_SIZE)*gp.UNIT_SIZE;
		if(gp.player != null) {
			int playerX[] = gp.player.x;
			int playerY[] = gp.player.y;
			//check that apple doesn't spawn in player's body
			//assumes x & y are of same length
			for(int i = 0; i < playerX.length; i++) {
				if(x == playerX[i] && y == playerY[i]) 
					setDefaultValues();
			}
			
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, gp.UNIT_SIZE, gp.UNIT_SIZE);
	}

}

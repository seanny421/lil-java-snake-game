
		    //subclass inherits super

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple extends GameEntity{
	Random rand = new Random();
	GamePanel gp;

	
	public Apple(GamePanel gp) {
		this.gp = gp;
		setDefaultValues();
		
	}
	
	private void setDefaultValues(){
		x = rand.nextInt(0, gp.screenWidth);
		y = rand.nextInt(0, gp.screenWidth);
		speed = 0; //doesn't move with player
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, gp.tileSize, gp.tileSize);
	}

}

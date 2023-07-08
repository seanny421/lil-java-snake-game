import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

			//subclass inherits	super   using 	interface
public class GamePanel extends JPanel implements Runnable {
	//Screen settings
	final int originalTileSize = 16;  //16x16 tile
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale; //32x32
	final int maxScreenCol = 20;
	final int maxScreenRow = 20;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60;

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Snake player = new Snake(this, keyH);
	Apple apple = new Apple(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start(); //auto calls run method
	
	}
	
	@Override
	public void run() {
		//game loop in here
		while(gameThread != null) {
			//create gameClock, only do process 60 times per second
			double drawInterval = 1000000000 / FPS;
			double nextDrawTime = System.nanoTime() + drawInterval;
			
			//UPDATE info such as character positions
			update();

			//DRAW the screen with updated info
			repaint();//calls paintComponent - I know it's weird
			
			
			//stop the system updating for the remaining times this loop would execute 
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				if(remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void update(){
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);//call super (JPanel.paintComponent)
		player.draw(g);
		apple.draw(g);
		g.dispose();
		
		
	}
	

}
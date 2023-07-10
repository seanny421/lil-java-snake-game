import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

			//subclass inherits	super   using 	interface
public class GamePanel extends JPanel implements ActionListener {
	
	
	boolean running = false;
	//screen settings
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	//game settings
	int score = 0;

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Snake player = new Snake(this, keyH);
	Apple apple = new Apple(this);
	Timer timer;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		startGame();
	}
	
	
	public void startGame() {
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);//call super (JPanel.paintComponent)
//		draw(g); //draw grid
		player.draw(g);
		apple.draw(g);
		g.dispose();
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
			g.setColor(Color.WHITE);
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0, i*UNIT_SIZE, SCREEN_HEIGHT, i*UNIT_SIZE);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		boolean updateApple;
		// TODO Auto-generated method stub
		if(running) {
			player.updateDirection();
			updateApple = player.checkApple(apple.x, apple.y);
			if(updateApple) {
				score++;
				apple = new Apple(this);
			}
			player.move();
			player.checkCollisions();
		}
		repaint();
		
	}
	

}
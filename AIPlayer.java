
			//subclass inherits super

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AIPlayer extends Snake{

	public AIPlayer(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		// TODO Auto-generated constructor stub
	}
	
	public void bestFirstSearch() {
		ArrayList<int[]> m = findNextPossibleMove(); 
		ArrayList<Integer> heuristics = new ArrayList<>();
		PriorityQueue<Node> moves = new PriorityQueue<>();
		for(int i = 0; i < m.size(); i++) {
			moves.add(new Node(m.get(i)[0], m.get(i)[1], gp.apple.x, gp.apple.y));
		}

		Node bestMove = moves.peek();

		if(bestMove != null && x[0] > bestMove.x) {
			direction = 'L';
		}
		if(bestMove != null && x[0] < bestMove.x) {
			direction = 'R';
		}
		if(bestMove != null && y[0] > bestMove.y) {
			direction = 'U';
		}
		if(bestMove != null && y[0] < bestMove.y) {
			direction = 'D';
		}
	}
	
	//what should we store each possible move as?
	//a node? that contains x, y, direction from original?
	
	public ArrayList<int[]> findNextPossibleMove() {
		ArrayList<int[]> moves = new ArrayList<>();
		System.out.println("(" + x[0] + ", " + y[0] + ")");
		//next possible moves
		int posNextX = x[0] + gp.UNIT_SIZE;
		int posNextY = y[0] + gp.UNIT_SIZE;
		int negNextX = x[0] - gp.UNIT_SIZE;
		int negNextY = y[0] - gp.UNIT_SIZE;
		
		
		if(posNextX <= gp.SCREEN_WIDTH-gp.UNIT_SIZE && direction != 'L') {
			boolean shouldAdd = true;
			for(int i = 1; i < snakeLength; i++) {
				if(x[i] == posNextX && y[0] == y[i]) {
					shouldAdd = false;
				}
			}

			if(shouldAdd) {moves.add(new int[] {posNextX, y[0]});}
		}

		if(posNextY <= gp.SCREEN_HEIGHT-gp.UNIT_SIZE && direction != 'U') {
			boolean shouldAdd = true;
			for(int i = 1; i < snakeLength; i++) {
				if(y[i] == posNextY && x[i] == x[0]) {
					shouldAdd = false;
				}
			}
			if(shouldAdd) {moves.add(new int[] {x[0], posNextY});}
		}
		
		if(negNextX >= 0 && direction != 'R') {
			boolean shouldAdd = true;
			for(int i = 1; i < snakeLength; i++) {
				if(x[i] == negNextX && y[i] == y[0]) {
					shouldAdd = false;
				}
			}
			if(shouldAdd) {moves.add(new int[] {negNextX, y[0]});}
		}

		if(negNextY >= 0 && direction != 'D') {
			boolean shouldAdd = true;
			for(int i = 1; i < snakeLength; i++) {
				if(y[i] == negNextY && x[0] == x[i]) {
					shouldAdd = false;
				}
			}
			if(shouldAdd) {moves.add(new int[] {x[0], negNextY});}
		}
		
		return moves;
		

	}
	
	@Override
	public void updateDirection() {//basic AI
		bestFirstSearch();
	}

}

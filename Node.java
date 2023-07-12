import java.util.ArrayList;

public class Node implements Comparable<Node>{
	public int x,y,heuristic;
	public ArrayList<Node> neighbours;

	public Node(int x, int y, int appleX, int appleY) {
		this.x = x;
		this.y = y;
		this.heuristic = calculcateHueristic(appleX, appleY, x, y);
	}
	
	
	public int calculcateHueristic(int appleX, int appleY, int x, int y) {
		return Math.abs(appleX - x) + Math.abs(appleY - y);//manhanttan distance
	}

	@Override
	public int compareTo(Node n) {
		// TODO Auto-generated method stub
		return this.heuristic - n.heuristic;
	}
	
	

}

package com.mine;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Percolation {

	private int N;
	private Node grid[][];
	private Node source;
	private Node target;
	private PriorityQueue<Node> openList = new PriorityQueue<Node>(10,
			new NodeComparator());

	public Percolation(int N) {
		this.N = N;
		grid = new Node[N][N];
		for (int i = 0; i < this.N; i++)
			for (int j = 0; j < this.N; j++) {
				grid[i][j] = new Node(i, j);
				grid[i][j].setBlockstatus(false);// all are initially open
			}
		// will try and remove the next two lines
		setTarget(N - 1, N - 1);
		setSource(0, 0); // setSource uses target, so order is imp
	}

	public void init() { // A* init
		for (int i = 0; i < this.N; i++)
			for (int j = 0; j < this.N; j++) {
				grid[i][j].setFval(Integer.MAX_VALUE);
				grid[i][j].setGval(Integer.MAX_VALUE);
				grid[i][j].setParent(null);
				grid[i][j].setAlreadyVisited(false);
				grid[i][j].setOnPath(false);
			}
		setTarget(N - 1, N - 1);
		setSource(0, 0);
	}

	public void close(int i, int j) {
		grid[i][j].setBlockstatus(true);
	}

	public boolean isClosed(int i, int j) {
		return grid[i][j].isBlockstatus();
	}

	public boolean isOnPath(int i, int j) {
		return grid[i][j].isOnPath();
	}

	public void setSource(int i, int j) {
		this.source = grid[i][j];
		this.source.setGval(0);
		this.source.setParent(null);
		this.source.setFval(this.source.getGval()
				+ Node.heurisitic(this.source, this.target));
		openList.clear();
		openList.add(this.source);
	}

	public void setTarget(int i, int j) {
		this.target = grid[i][j];
	}

	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	public boolean isSource(int i, int j) {
		if (this.getSource() == null)
			return false;
		if (this.getSource().getRow() == i && this.getSource().getCol() == j)
			return true;
		return false;
	}

	public boolean isTarget(int i, int j) {
		if (this.getTarget() == null)
			return false;
		if (this.getTarget().getRow() == i && this.getTarget().getCol() == j)
			return true;
		return false;
	}

	public void AStar() {
		System.out.println("called Astar");
		while (!openList.isEmpty()) {
			System.out.println(openList.peek());
			Node curr = openList.poll();

			if (curr.equals(this.target)) {
				constructPath();
				// when we come to above point
				// the parent of target is set by previous iteration
				break;// vv imp
			}

			curr.setAlreadyVisited(true);// keeping in closed list

			processNeighbours(curr);
		}
		System.out.println("no PATH!!!");
		// failure if we arrive here, so no onPath set
	}

	// grid end points(out-of-bounds) need to be seriously taken care of
	private void processNeighbours(Node curr) {
		Node[] neighbours = new Node[4];
		neighbours[0] = getNodeFromGrid(curr.getRow() + 1, curr.getCol());
		neighbours[1] = getNodeFromGrid(curr.getRow() - 1, curr.getCol());
		neighbours[2] = getNodeFromGrid(curr.getRow(), curr.getCol()+1);
		neighbours[3] = getNodeFromGrid(curr.getRow(), curr.getCol()-1);
		processIndividualNeighbour(curr, neighbours[0]);
		processIndividualNeighbour(curr, neighbours[1]);
		processIndividualNeighbour(curr, neighbours[2]);
		processIndividualNeighbour(curr, neighbours[3]);
		System.out.println(neighbours[0]+"|"+neighbours[1]+"|"+neighbours[2]+"|"+neighbours[3]+"|");
	}
	
	private Node getNodeFromGrid(int row, int col)
	{
		if(row<0||row>N-1)
			return null;
		if(col<0||col>N-1)
			return null;
		return grid[row][col];
	}

	private void processIndividualNeighbour(Node curr, Node neighbour) {
		if(neighbour==null)
			return;
		System.out.println(neighbour.isAlreadyVisited());
		System.out.println(neighbour.isBlockstatus());
		if (neighbour.isAlreadyVisited() || neighbour.isBlockstatus())
			return;
		int tentative_g_score = curr.getGval() + Node.MOVEMENT_COST;
		// check is on gval and if the node is not in open list
		if (!openList.contains(neighbour)
				|| tentative_g_score < neighbour.getGval()) {
			System.out.println("parent set for "+neighbour);
			neighbour.setParent(curr);
			neighbour.setGval(tentative_g_score);
			neighbour.setFval(tentative_g_score
					+ Node.heurisitic(neighbour, this.target));
		}
		if(!openList.contains(neighbour))
			openList.add(neighbour);
	}

	// be careful to do not change the colour of source and target here
	private void constructPath() {
		System.out.println("called constructPath");
		Node curr = this.target.getParent();
		while (!(curr.equals(this.source))) {
			curr.setOnPath(true);
			curr = curr.getParent();
		}
	}

}

class NodeComparator implements Comparator<Node> {
	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		return o1.getFval() - o2.getFval();
	}
}
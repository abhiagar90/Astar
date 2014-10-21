package com.mine;

public class Node {

	private int row;
	private int col;
	private int fval;
	private int gval;

	private boolean blockstatus;// true if blocked
	private boolean onPath; // for the orange color path on screen, a hack
	private boolean alreadyVisited;// for the closed list

	private Node parent;// for the recursive path

	public static int MOVEMENT_COST = 1;

	public Node(int row, int col) {
		this.row = row;
		this.col = col;
		this.blockstatus = false;// trivial, re-doing
		// all are initially open
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isBlockstatus() {
		return blockstatus;
	}

	public void setBlockstatus(boolean blockstatus) {
		this.blockstatus = blockstatus;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getFval() {
		return fval;
	}

	public void setFval(int fval) {
		this.fval = fval;
	}

	public int getGval() {
		return gval;
	}

	public void setGval(int gval) {
		this.gval = gval;
	}

	public static int heurisitic(Node a, Node b) {
		return Math.abs(a.getRow() - b.getRow())
				+ Math.abs(a.getCol() - b.getCol());
	}

	public boolean isAlreadyVisited() {
		return alreadyVisited;
	}

	public void setAlreadyVisited(boolean alreadyVisited) {
		this.alreadyVisited = alreadyVisited;
	}

	public boolean isOnPath() {
		return onPath;
	}

	public void setOnPath(boolean onPath) {
		this.onPath = onPath;
	}
	
	public String toString(){
		return ""+getRow()+","+getCol();
	}
}
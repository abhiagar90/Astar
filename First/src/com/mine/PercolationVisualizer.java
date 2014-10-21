package com.mine;

public class PercolationVisualizer {

	// delay in miliseconds (controls animation speed)
	private final static int DELAY = 100;

	// draw N-by-N percolation system
	public static void draw(Percolation perc, int N) {

		perc.init();// this is A* init(), source and target could be passed in
					// future
		perc.AStar();

		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, N);
		StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);

		// draw N-by-N grid
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (perc.isClosed(row, col)) { // blocked
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				} else if (perc.isOnPath(row, col)) // open and on path
				{
					StdDraw.setPenColor(StdDraw.ORANGE);
				} else { // open and not on path
					StdDraw.setPenColor(StdDraw.WHITE);
				}

				if (perc.isSource(row, col)) {
					StdDraw.setPenColor(StdDraw.BLUE);
				} else if (perc.isTarget(row, col)) {
					StdDraw.setPenColor(StdDraw.RED);
				}

				StdDraw.filledSquare(col + 0.5, N - row - 0.5, 0.45);
			}
		}
	}

	private static void simulateFromFile(String filename) {
		In in = new In(filename);
		int N = in.readInt();
		Percolation perc = new Percolation(N);

		// turn on animation mode
		StdDraw.show(0);

		// repeatedly read in sites to open and draw resulting system
		draw(perc, N);
		StdDraw.show(DELAY);
		while (!in.isEmpty()) {
			int i = in.readInt();
			int j = in.readInt();
			perc.close(i, j);
			draw(perc, N);
			StdDraw.show(DELAY);
		}
	}

	public static void main(String[] args) {
		String filename = args[0];
		simulateFromFile(filename);
	}
}
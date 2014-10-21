package com.mine;

public class InteractivePercolationVisualizer {
	private final static int DELAY = 20;

	public static void main(String[] args) {
		// N-by-N percolation system (read from command-line, default = 10)
		int N = 5;
		if (args.length == 1)
			N = Integer.parseInt(args[0]);

		// turn on animation mode
		StdDraw.show(0);

		// repeatedly open site specified my mouse click and draw resulting
		// system
		System.out.println(N);

		Percolation perc = new Percolation(N);
		PercolationVisualizer.draw(perc, N);
		
		StdDraw.show(DELAY);
		int count = 0;
		
		while (true) {

			// detected mouse click
			if (StdDraw.mousePressed()) {
				count++;
				// screen coordinates
				double x = StdDraw.mouseX();
				double y = StdDraw.mouseY();

				// convert to row i, column j
				int i = (int) (N - Math.floor(y) - 1);
				int j = (int) (Math.floor(x));

				// open site (i, j) provided it's in bounds
				if (i >= 0 && i < N && j >= 0 && j < N) {
					/*if (count == 1) {
						System.out.println("source set");
						perc.setSource(i, j);
					} else if (count == 2) {
						System.out.println("target set");
						perc.setTarget(i, j);
					}*/
					if (!perc.isSource(i, j)
							&& !perc.isTarget(i, j)) {
						System.out.println(i + " " + j);
						perc.close(i, j);
					}
				}

				// draw N-by-N percolation system
				PercolationVisualizer.draw(perc, N);
			}
			StdDraw.show(DELAY);
		}
	}
}
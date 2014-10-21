package com.mine;

public class FirstGrid {
private static int N=10;
	public static void main(String[] args) {
		
		StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);

        // draw N-by-N grid
        int opened = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                //if (perc.isFull(row, col)) {
            	if(true){
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                //else if (perc.isOpen(row, col)) {
                //   StdDraw.setPenColor(StdDraw.WHITE);
                //    opened++;
                //}
                //else
                 //   StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col + 0.5, N - row - 0.5, 0.45);
            }
        }

	
		
	}

}

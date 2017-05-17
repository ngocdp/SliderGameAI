package ai.vuh2ndinh1;

/**
 * COMP30004 Artificial Intelligence Assignment (Part B)
 * Group member: 
 * Dinh Phuc Ngoc - 784736 
 * Duy Vu - 741907
 * 
 **/

/**
 * BoardValue Class:  this class is used to evaluate the value of the board
 */

public class BoardValue {
	
	// Initialize variables
	private int[] values = new int[2]; 
	private static final int H_i = 0;
	private static final int V_i = 1;

	
	/**
	 * Constructor of BoardValue:
	 * Initialize the value
	 */
	public BoardValue() {
		this.values[0] = 0;
		this.values[1] = 0;
	}

	/**
	 * Function to calculate the evaluation value of the board
	 * @param board : the board we would like to evaluate
	 * @param player : the character we want to evaluate the 
	 * @param justFinish : if there is a finishing move on the board, extra value will be credited 
	 * @return The evaluated value for that board
	 */
	public int eval(Board board, char player, boolean justFinish) {
		char cellType;
		int bsize = board.getBoard_size();
				
		for (int row = 0; row < bsize; row++) {
			for (int column = 0; column < bsize; column++) {
				cellType = board.getSquare(column, row).getType();	
				
				if (cellType == player && cellType == MySliderPlayer.TYPE_V) {
					// score on position of sliders on board
					values[V_i] += (bsize * column + bsize * (row+1) * bsize);	
					
					// add direct blocking point move: exist H on the direct left
					if (column-1>0 && board.getSquare(column-1, row).getType() == MySliderPlayer.TYPE_H) {
						values[V_i] += bsize*bsize*bsize*column + bsize;
					}
				}	
				if (cellType == player && cellType == MySliderPlayer.TYPE_H) {
					// score on position of sliders on board
					values[H_i] += (bsize * row + bsize * (column+1) * bsize);

					// add direct blocking point move: exist V on the direct down
					if (row-1>0 && board.getSquare(column, row-1).getType() == MySliderPlayer.TYPE_V) {
						values[H_i] += bsize*bsize*bsize*row + bsize;
					}
					
				}		
			}
		}
		
		// if there is a winning move just happened on the board, additional score will be added
		int addon;
		if (justFinish) {
			switch (player) {
			case MySliderPlayer.TYPE_V:
				// add value for exiting move
				addon = ((bsize) - board.getVsquare())* bsize * bsize * bsize * bsize;
				values[V_i] += addon;
				break;
	
			case MySliderPlayer.TYPE_H:
				// add value for exiting move
				addon = ((bsize) - board.getHsquare())* bsize * bsize * bsize * bsize;
				values[H_i] += addon;
				break;
			}				
		}
		
		switch (player) {
		case MySliderPlayer.TYPE_V:
			return values[V_i];
			
		case MySliderPlayer.TYPE_H:
			return values[H_i];
			
		default:
			return -1;
		}
	}
	
}

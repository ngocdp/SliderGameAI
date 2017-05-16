package ai.partB;

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
	private int[] values = new int[2]; // H:0, V:1 
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
	 * @param board: the board we would like to evaluate
	 * @param player: the character we want to evaluate the 
	 * @param justFinish: if there is a finishing move on the board, extra value will be credited 
	 * @return the evaluated value for that board
	 */
	public int eval(Board board, char player, boolean justFinish) {
		char cellType;
		int bsize = board.getBoard_size();
		
		// score is awarded based on its position on board
		
		for (int row = 0; row < bsize; row++) {
			for (int column = 0; column < bsize; column++) {
				cellType = board.getSquare(column, row).getType();	
				
				if (cellType == MySliderPlayer.TYPE_V) {
					// values[V_i] += (bsize * Math.abs(column -(int)bsize/2) + bsize * (row+1) * 2);
					values[V_i] += (bsize * column + bsize * (row+1) * 2);
				}	
				if (cellType == MySliderPlayer.TYPE_H) {
					// values[H_i] += (bsize * Math.abs(row -(int)bsize/2) + bsize * (column+1) * 2);
					values[H_i] += (bsize * row  + bsize * (column+1) * 2);
				
				}		
			}
		}
		
		// if there is a winning move just happened on the board, additional score will be added
		int addon;
		if (justFinish) {
			switch (player) {
			case MySliderPlayer.TYPE_V:
				addon = ((bsize) - board.getVsquare())* bsize * bsize * bsize * bsize;
				values[V_i] += addon ;
				
				//System.out.print("==> Value: " +(values[V_i]) + " [V] \n");
				
			case MySliderPlayer.TYPE_H:
					// add value for exiting move
				addon = ((bsize) - board.getHsquare())* bsize * bsize * bsize * bsize;
				values[H_i] += addon ;
				
				//System.out.print("==> Value: " +(values[H_i]) + " [H] \n");
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
	
//	public int eval(Board board, char player) {
//		int value = 0;
//		char cellType;
//		int bsize = board.getBoard_size();
//		for (int row = 0; row < bsize; row++) {
//			for (int column = 0; column < bsize; column++) {
//				cellType = board.getSquare(column, row).getType();
//				if (cellType == player) {
//					if (cellType == MySliderPlayer.TYPE_V) {
//						// score on position of sliders on board
//						value += (bsize * column + bsize * row * 2);	
//					}	
//					else if (cellType == MySliderPlayer.TYPE_H) {
//						value += (bsize * row + bsize * column * 2);
//					}
//				}		
//			}
//		}
//		
//		// add score on remaining sliders on board:
//		if (player == MySliderPlayer.TYPE_V) {
//			value += ((bsize-1) - board.getHsquare()) * bsize*2;
//		}	
//		else if (player == MySliderPlayer.TYPE_H) {
//			value += ((bsize-1) - board.getHsquare()) * bsize*2;
//		}
//		return value;
//	}
}

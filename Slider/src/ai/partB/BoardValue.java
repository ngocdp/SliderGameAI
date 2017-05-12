package ai.partB;

public class BoardValue {

	private int[] values = new int[2]; // H:0, V:1 
	private static final int H_i = 0;
	private static final int V_i = 1;


	public BoardValue() {
		this.values[0] = 0;
		this.values[1] = 0;
	}

	public int eval(Board board, char player) {
		char cellType;
		int bsize = board.getBoard_size();
		for (int row = 0; row < bsize; row++) {
			for (int column = 0; column < bsize; column++) {
				cellType = board.getSquare(column, row).getType();
				
				if (cellType == MySliderPlayer.TYPE_V) {
					// score on position of sliders on board
					values[V_i] += (bsize * column + bsize * row * 2);	
				}	
				if (cellType == MySliderPlayer.TYPE_H) {
					values[H_i] += (bsize * row + bsize * column * 2);
				
				}		
			}
		}
		
		
		switch (player) {
		case Main.TYPE_V:
			// add value for exiting move
			values[V_i] += ((bsize-1) - board.getVsquare()) * bsize * 3 + 1;
			System.out.print("==> Value: " +values[V_i] + " [V] \n");

			return values[V_i];
			
		case Main.TYPE_H:
			// add value for exiting move
			values[H_i] += ((bsize-1) - board.getHsquare()) * bsize * 3 + 1;
			System.out.print("==> " +values[H_i] + "[H] \n");

			return values[H_i];
			
		default:
			//System.out.println("return -1?");
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

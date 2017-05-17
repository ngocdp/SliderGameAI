package ai.partB;

public class BoardValue {

	private int[] values = new int[2]; // H:0, V:1 
	private static final int H_i = 0;
	private static final int V_i = 1;


	public BoardValue() {
		this.values[0] = 0;
		this.values[1] = 0;
	}

	public int eval(Board board, char player, boolean justFinish) {
		char cellType;
		int bsize = board.getBoard_size();
		for (int row = 0; row < bsize; row++) {
			for (int column = 0; column < bsize; column++) {
				cellType = board.getSquare(column, row).getType();
				
				if (cellType == player && cellType == MySliderPlayer.TYPE_V) {
					// score on position of sliders on board
					//values[V_i] += (bsize * Math.abs(1+column -(int)bsize/2) + bsize * (row+1) * 2);	
					values[V_i] += (bsize * column + bsize * (row+1) * bsize);	

					
					// add direct blocking point move: exist H on the direct left
					if (column-1>0 && board.getSquare(column-1, row).getType() == MySliderPlayer.TYPE_H) {
						values[V_i] += bsize*bsize*bsize*column + bsize;
					}
					
					
					
					
				}	
				if (cellType == player && cellType == MySliderPlayer.TYPE_H) {
					//values[H_i] += (bsize * Math.abs(1+row -(int)bsize/2) + bsize * (column+1) * 2);
					
					values[H_i] += (bsize * row + bsize * (column+1) * bsize);

					
					
					// add direct blocking point move: exist V on the direct down
					if (row-1>0 && board.getSquare(column, row-1).getType() == MySliderPlayer.TYPE_V) {
						values[H_i] += bsize*bsize*bsize*row + bsize;
					}
					
					
					
					
					
				
				}		
			}
		}
		int addon;
		switch (player) {
		case MySliderPlayer.TYPE_V:

			// add value for exiting move
			if (justFinish) {
				addon = ((bsize) - board.getVsquare())* bsize * bsize * bsize * bsize;

				//System.out.println("**Finish move add on: " + addon);
				values[V_i] += addon ;
			} else {
				//System.out.println("**Finish move add on: " + 0);
			}
			
			//System.out.print("==> Value: " +(values[V_i]) + " [V] \n");

			return values[V_i];
			
		case MySliderPlayer.TYPE_H:
			// add value for exiting move
			if (justFinish) {
				addon = ((bsize) - board.getHsquare())* bsize * bsize * bsize * bsize;

				//System.out.println("**Finish move add on: " +  addon);

				values[H_i] +=  addon;
			} else {
				//System.out.println("**Finish move add on: " + 0);
			}
			//System.out.print("==> Value: " +(values[H_i]) + "[H] \n");

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

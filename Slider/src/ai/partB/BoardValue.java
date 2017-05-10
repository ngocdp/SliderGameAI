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
				if (cellType == MySliderPlayer.TYPE_V)
					values[V_i] += (bsize * column + bsize * row);
				if (cellType == MySliderPlayer.TYPE_H)
					values[H_i] += (bsize * row + bsize * column);
			}
		}
		

		switch (player) {
		case Main.TYPE_V:
			//System.out.println("Board value eval =  "+  values[V_i]);
			return values[V_i];
		case Main.TYPE_H:
			//System.out.println("Board value eval =  "+  values[H_i]);
			return values[H_i];
		default:
			//System.out.println("return -1?");
			return -1;
		}
	}
}

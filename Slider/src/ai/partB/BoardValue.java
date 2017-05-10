package ai.partB;

public class BoardValue {

	private int[] values = new int[2]; // H:0, V:1 
	private static final int H_i = 0;
	private static final int V_i = 1;


	public BoardValue() {

	}

	public int eval(Board board, char player) {
		char cellType;
		for (int row = board.getBoard_size() - 1; row >= 0; row--) {
			for (int column = 0; column < board.getBoard_size(); column++) {
				cellType = board.getSquare(column, row).getType();
				if (cellType == MySliderPlayer.TYPE_V)
					values[V_i] += (5 * board.getBoard_size() * column + 5 * row);
				if (cellType == MySliderPlayer.TYPE_H)
					values[H_i] += (5 * board.getBoard_size() * row + 5 * column);
			}
		}
		

		switch (player) {
		case Main.TYPE_V:
			System.out.println("Board value eval =  "+  values[V_i]);
			return values[V_i];
		case Main.TYPE_H:
			System.out.println("Board value eval =  "+  values[H_i]);
			return values[H_i];
		default:
			System.out.println("return -1?");
			return -1;
		}
	}
}

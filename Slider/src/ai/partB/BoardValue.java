package ai.partB;

public class BoardValue {

	private int[] values = new int[2];

	public BoardValue() {

	}

	public int eval(Board board, char player) {

		for (int row = board.getBoard_size() - 1; row >= 0; row--) {
			for (int column = 0; column < board.getBoard_size(); column++) {
				if (player == Main.TYPE_V)
					values[0] += (5 * board.getBoard_size() * row + 5 * column);
				if (player == Main.TYPE_H)
					values[1] += -(5 * board.getBoard_size() * column + 5 * row);
			}
		}

		switch (player) {
		case Main.TYPE_V:
			return values[0];
		case Main.TYPE_H:
			return values[1];
		default:
			return -1;
		}
	}
}

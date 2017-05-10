package ai.partB;

public class Board {

	private int board_size;
	//public char player; // not use?
	public Square[][] cells;
	private int hsquare = 0, vsquare = 0;

	public Board(int dimension) {
		this.board_size = dimension;
		this.cells = new Square[board_size][board_size];
	}

	// not use?
	public boolean update(Square sqr) {
		int[] pos = sqr.position;
		cells[pos[0]][pos[1]] = sqr;
		return true;
	}

	public void updateSquare(int[] oldPos, int[] newPos) {
		Square orig = getSquare(oldPos);
		// Check if it is winning move
		if ((orig.getType() == Main.TYPE_H && newPos[0] == board_size)
				|| (orig.getType() == Main.TYPE_V && newPos[1] == board_size)) {
			// update old position
			cells[oldPos[0]][oldPos[1]] = new Square(oldPos, Main.TYPE_F);
			
			// reduce number of players on the board
			if (orig.getType() == Main.TYPE_H)
				hsquare -= 1;
			if (orig.getType() == Main.TYPE_V)
				vsquare -= 1;
		} else {
			
			/* Clear original slot and update new slot. */
			cells[oldPos[0]][oldPos[1]] = new Square(oldPos, Main.TYPE_F);;
			cells[newPos[0]][newPos[1]] = orig;
			orig.position = newPos;
		}
	}
	
	public void printBoard() {
		System.out.println("printBoard func:");
		for (int j = board_size - 1; j >= 0; j--) {
			for (int i = 0; i < board_size; i++) {
				
				System.out.print(cells[i][j].getType());
				System.out.print(' ');
			}
			System.out.print('\n');
		}
	}
	
	public boolean isInside(int[] position) {
		return isInside(position[0], position[1]);
	}

	public boolean isInside(int x, int y) {
		return !(x < 0 || x >= board_size || y < 0 || y >= board_size);
	}

	public boolean isEmpty(int[] position) {
		return isEmpty(position[0], position[1]);
	}

	public boolean isEmpty(int x, int y) {
		return isInside(x, y) && cells[x][y].isOccupied() == false;
	}
	
	public boolean finished() {
		return ((vsquare == 0) || (hsquare == 0) );
	}

	public Square getSquare(int[] pos) {
		return getSquare(pos[0], pos[1]);
	}

	public Square getSquare(int x, int y) {
		return cells[x][y];
	}

	public int getBoard_size() {
		return board_size;
	}

	public void setBoard_size(int board_size) {
		this.board_size = board_size;
	}

	public int getHsquare() {
		return hsquare;
	}

	public void setHsquare(int hsquare) {
		this.hsquare = hsquare;
	}

	public int getVsquare() {
		return vsquare;
	}

	public void setVsquare(int vsquare) {
		this.vsquare = vsquare;
	}
}

package ai.dumb;

public class Board {

	private int board_size;
	//public char player; // not use?
	public Square[][] cells;
	private int hsquare = 0, vsquare = 0;

	public Board(int dimension) {
		this.board_size = dimension;
		this.cells = new Square[board_size+1][board_size+1];
		this.updateFinishArea();
		
	}
	
	public Board(Board cloneBoard) {
		this.board_size = cloneBoard.board_size;
		this.cells = cloneBoard.cells;
		this.hsquare = cloneBoard.hsquare;
		this.vsquare = cloneBoard.vsquare;
	}

	// not use?
	public boolean update(Square sqr) {
		int[] pos = sqr.position;
		cells[pos[0]][pos[1]] = sqr;
		return true;
	}

	public boolean updateSquare(int[] oldPos, int[] newPos) {
		boolean isFinishMove = false;
		//System.out.println("** UPDATE SQUARE **");

		Square orig = getSquare(oldPos);
		this.printBoard();
		// Check if it is winning move
		if ((orig.getType() == MySliderPlayer.TYPE_H && newPos[0] == board_size)
				|| (orig.getType() == MySliderPlayer.TYPE_V && newPos[1] == board_size)) {
			
			//System.out.println("===> EXIT/ WINNING MOVE");
			
			// update old position
			cells[oldPos[0]][oldPos[1]] = new Square(oldPos, MySliderPlayer.TYPE_F);
			cells[newPos[0]][newPos[1]] = orig;

			// reduce number of players on the board
			if (orig.getType() == MySliderPlayer.TYPE_H)
				hsquare -= 1;
			if (orig.getType() == MySliderPlayer.TYPE_V)
				vsquare -= 1;
			
			isFinishMove = true;
		} else {
			
			/* Clear original slot and update new slot. */
			cells[oldPos[0]][oldPos[1]] = new Square(oldPos, MySliderPlayer.TYPE_F);;
			cells[newPos[0]][newPos[1]] = orig;
			orig.position = newPos;
		}
		this.printBoard();
		//System.out.println("** H_Sq = " + this.hsquare + " V_Sq = " + this.vsquare + " **");
		//System.out.println("** FINISH UPDATE SQUARE **");
		return isFinishMove;

	}
	
	public void printBoard() {
		//System.out.println("printBoard func:");
		for (int j = board_size; j >= 0; j--) {
			for (int i = 0; i <=  board_size; i++) {
				
				//System.out.print(cells[i][j].getType());
				//System.out.print(' ');
			}
			//System.out.print('\n');
		}
	}
	
	public void updateFinishArea() {
		// Add ending position Type_Hash:
		for (int i=0; i <= board_size; i++) {
					
			int[] posRow = {i,board_size};
			cells[posRow[0]][posRow[1]] = new Square(posRow, MySliderPlayer.TYPE_HASH);
					
			int[] posCol = {board_size,i};
			cells[posCol[0]][posCol[1]] = new Square(posCol, MySliderPlayer.TYPE_HASH);
					
			int[] posEdge = {board_size, board_size};
			cells[posEdge[0]][posEdge[1]] = new Square(posEdge, MySliderPlayer.TYPE_HASH);

		}
	}
	
	public void rollback(boolean isFinishMove, char player, int[] oldPos, int[] newPos) {
		//System.out.println("***** ROLLBACK *****");
		
		if (isFinishMove) {
			if (player == MySliderPlayer.TYPE_V) {
				this.vsquare++;
			} else {
				this.hsquare++;
			}
		}
		
		this.updateSquare(oldPos, newPos);
		
		
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

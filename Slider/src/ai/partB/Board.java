package ai.partB;

/**
 * COMP30004 Artificial Intelligence Assignment (Part B)
 * Group member: 
 * Dinh Phuc Ngoc - 784736 
 * Duy Vu - 741907
 * 
 **/

/**
 * Board class: This class is the structure of the Board that our AI will be using to stimulating the game. It will
 * keep track of all the squares on the board and the details of them. Our board got an extra winning area for easier 
 * searching and rolling back when doing Alpha Beta Prunning.
 */

public class Board {

	// Initialize variables
	private int board_size;
	public Square[][] cells;
	private int hsquare = 0, vsquare = 0;

	/**
	 * Constructor of Board
	 * @param dimension: the size of the board
	 */
	public Board(int dimension) {
		this.board_size = dimension;
		this.cells = new Square[board_size+1][board_size+1];
		this.updateFinishArea(false);
		
	}
	
	public Board(Board cloneBoard) {
		this.board_size = cloneBoard.board_size;
		this.cells = cloneBoard.cells;
		this.hsquare = cloneBoard.hsquare;
		this.vsquare = cloneBoard.vsquare;
	}
	
	
	/**
	 * Update the board after a move being made and it will tell if it is a finishing move of that character
	 * @param oldPos: the position of character being made to move
	 * @param newPos: the position that character being moved to 
	 * @return true if it is a finishing move, else false
	 */
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
	
	public void printMainBoard() {
		//System.out.println("printBoard func:");
		for (int j = board_size; j >= 0; j--) {
			for (int i = 0; i <=  board_size; i++) {
				
				//System.out.print(cells[i][j].getType());
				//System.out.print(' ');
			}
			//System.out.print('\n');
		}
	}
	
	
	
	/**
	 * This function will create an extra row on top and extra column on the right of the board and we call it 
	 * finishing area for V and H. This would help us futher in storing the wining move and able to roll back after 
	 * that when doing Alpha Beta Pruning
	 * 
	 * @param isRollback : We set it true when we start doing roll back; we will make all upper row be V and all 
	 * righter column be H so that we can roll back a piece if one of the move is winning move. Else for every time, 
	 * it will be false; and it outer layer will be free square to assist in moving when doing alpha beta searching.
	 */
	public void updateFinishArea(boolean isRollback) {
		// Add ending position Type_Hash:
		for (int i=0; i <= board_size; i++) {
			
			if (isRollback) {
				int[] posRow = {i,board_size};
				cells[posRow[0]][posRow[1]] = new Square(posRow, MySliderPlayer.TYPE_V);
						
				int[] posCol = {board_size,i};
				cells[posCol[0]][posCol[1]] = new Square(posCol, MySliderPlayer.TYPE_H);
			} else {
				int[] posRow = {i,board_size};
				cells[posRow[0]][posRow[1]] = new Square(posRow, MySliderPlayer.TYPE_F);
						
				int[] posCol = {board_size,i};
				cells[posCol[0]][posCol[1]] = new Square(posCol, MySliderPlayer.TYPE_F);
			}
		}
	}
	
	/**
	 * This function will roll back a previous move on the board
	 * @param isFinishMove : if that was a finishing move
	 * @param player : type of character making that move
	 * @param oldPos : the position of the piece we would like to roll back
	 * @param newPos : the position of the piece we would like to roll back to
	 */
	public void rollback(boolean isFinishMove, char player, int[] oldPos, int[] newPos) {
		//System.out.println("***** ROLLBACK *****");
		this.updateFinishArea(true);
		if (isFinishMove) {
			if (player == MySliderPlayer.TYPE_V) {
				this.vsquare++;
			} else {
				this.hsquare++;
			}
		}
		
		this.updateSquare(oldPos, newPos);
		
		this.updateFinishArea(false);		
	}
	
	/**
	 * This function check if a position is within board field
	 * @param position : the position to check
	 * @return : true if that position is within board field, else false
	 */
	public boolean isInside(int[] position) {
		return isInside(position[0], position[1]);
	}

	/**
	 * This function check if a position is within board field
	 * @param x : column
	 * @param y : row
	 * @return :true if that position is within board field, else false
	 */
	public boolean isInside(int x, int y) {
		return !(x < 0 || x >= board_size || y < 0 || y >= board_size);
	}

	/**
	 * This function check if the square at a position is empty/free or being occupied 
	 * @param position: the position of the square to check
	 * @return : true if the square is empty/free, else false
	 */
	public boolean isEmpty(int[] position) {
		return isEmpty(position[0], position[1]);
	}

	/**
	 * This function check if the square at a position is empty/free or being occupied 
	 * @param x : column
	 * @param y : row
	 * @return : true if the square is empty/free, else false
	 */
	public boolean isEmpty(int x, int y) {
		return isInside(x, y) && cells[x][y].isOccupied() == false;
	}
	
	/**
	 * this function will check if the game has been done when either one of the player has no piece left on the board
	 * @return true if the game has finished 
	 */
	public boolean finished() {
		return ((vsquare == 0) || (hsquare == 0) );
	}

	/**
	 * This function will give the details the square at given position
	 * @param pos: the position to get the square
	 * @return : the details of the Square in Square type
	 */
	public Square getSquare(int[] pos) {
		return getSquare(pos[0], pos[1]);
	}

	/**
	 * This function will give the details the square at given position on the board 
	 * @param x : column
	 * @param y : row
	 * @return : the details of the Square in Square type
	 */
	public Square getSquare(int x, int y) {
		return cells[x][y];
	}
	
	/**
	 * @return the size of the board
	 */
	public int getBoard_size() {
		return board_size;
	}
	
	/**
	 * @param board_size: the size of the board to set to
	 */
	public void setBoard_size(int board_size) {
		this.board_size = board_size;
	}

	/**
	 * @return the number of H square on the board
	 */
	public int getHsquare() {
		return hsquare;
	}

	/**
	 * @param hsquare: the number of H squares to set
	 */
	public void setHsquare(int hsquare) {
		this.hsquare = hsquare;
	}

	/**
	 * @return the number of V squares on the board
	 */
	public int getVsquare() {
		return vsquare;
	}

	/**
	 * @param vsquare: the number of V squares to set
	 */
	public void setVsquare(int vsquare) {
		this.vsquare = vsquare;
	}
}

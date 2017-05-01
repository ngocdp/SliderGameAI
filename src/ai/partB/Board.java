package ai.partB;

public class Board {
	
	private int board_size;
	public char player;
	public Square[][] cells;
	
	public Board(int dimension){
		this.board_size = dimension;
		this.cells = new Square[board_size][board_size];
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
		return isInside(x, y) && cells[x][y] == null;
	}
	
	public boolean update(Square sqr) {
        int[] pos = sqr.position;
        cells[pos[0]][pos[1]] = sqr;
        return true;
    }
	
	public void updateSquare(int[]oldPos, int[] newPos) {
        Square orig = getSquare(oldPos);
        Square inNewPos = getSquare(newPos);
        /* If the new slot has been taken by another player, then it will be invalid.*/
        if (inNewPos != null)
            return;
        /* Clear original slot and update new slot.*/
        int[] origPos = orig.position;
        cells[origPos[0]][origPos[1]] = null;
        cells[newPos[0]][newPos[1]] = orig;
        orig.position = newPos;
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
}

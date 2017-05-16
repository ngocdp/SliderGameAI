package ai.partB;

/**
 * COMP30004 Artificial Intelligence Assignment 1
 * Group member: 
 * Dinh Phuc Ngoc - 784736 
 * Duy Vu - 741907
 * 
 **/

/** Class Square
 * 	This class contains the structure of a square on the board
 */

public class Square {
	
	// Initialize variable
	public int[] position = new int[2];
	private char type;
	private boolean occupied;

	/**
	 * Constructor of Square
	 * @param position: a array storing the position of the square on the board with structure [column, row]
	 * @param type: the character appear on that square (H/V/B/F)
	 * 
	 * 	We also assign the occupy status depend on the type of the square
	 */
	public Square(int[] position, char type) {
		this.type = type;
		this.position = position;
		
		switch (type) {
		case MySliderPlayer.TYPE_H:
			this.occupied = true;
			break;
		case MySliderPlayer.TYPE_V:
			this.occupied = true;
			break;
		case MySliderPlayer.TYPE_B:
			this.occupied = true;
			break;
		case MySliderPlayer.TYPE_F:
			this.occupied = false;
			break;
		}
	}

	/**
	 * Constructor of Square
	 * @param sqr: an existed square
	 */
	public Square(Square sqr) {
		this.setPosition(sqr.getPosition());
		this.setType(sqr.getType());
		this.setOccupied(sqr.isOccupied());
	}

	/**
	 * @return the type on the square
	 */
	public char getType() {
		return type;
	}

	/**
	 * set type for a square
	 * @param type: name of type (H/V/B/F)
	 */
	public void setType(char type) {
		this.type = type;
	}

	/**
	 * @return if the square is being occupied or free
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * @param occupied:the occupy status for the square 
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * @return the position of the square 
	 */
	public int[] getPosition() {
		return position;
	}

	/**
	 * @param position: the position to set
	 */
	public void setPosition(int[] position) {
		this.position = position;
	}

}

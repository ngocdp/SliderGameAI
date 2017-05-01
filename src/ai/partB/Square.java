package ai.partB;

/**
 * COMP30004 Artificial Intelligence Assignment 1 Group member: Dinh Phuc Ngoc -
 * 784736 Duy Vu - 741907
 * 
 **/
public class Square {
	private int row;
	private int column;
	public int[] position = new int[2];
	private char type;
	private boolean occupied;

	public Square(int[] position, char type) {
		this.type = type;
		this.position = position;
	}

	public Square(Square sqr) {
		this.setPosition(sqr.getPosition());
		this.setType(sqr.getType());
		this.setOccupied(sqr.isOccupied());
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * @return the position
	 */
	public int[] getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int[] position) {
		this.position = position;
	}

}

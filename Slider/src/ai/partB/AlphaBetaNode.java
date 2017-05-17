package ai.partB;

/**
 * COMP30004 Artificial Intelligence Assignment (Part B)
 * Group member: 
 * Dinh Phuc Ngoc - 784736 
 * Duy Vu - 741907
 * 
 **/

/**
 * AlphaBetaNode Class: this class represent the details a node when exploring the board using Alpha Beta Pruning 
 */

public class AlphaBetaNode {
	
	// Initialize variable
	private char player;
    private int[] from;
    private int[] to;
    private int value;

    /**
     * Constructor of AlphaBetaNode
     * @param player the type of character being moved
     * @param from position that character being moved from
     * @param to position that character being moved to
     */
    public AlphaBetaNode(char player, int[] from, int[] to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }
    
    /**
     * 
     */
    public void printNode() {
    	System.out.println("Node of " + this.player + ":[value = "+ this.value+"] (" + from[0] + ", " + from[1] + ") ~> (" + to[0] + ", " + to[1] + ")");
    }

	/**
	 * @return the type in the node
	 */
	public char getPlayer() {
		return player;
	}

	/**
	 * @return the position that the character is being moved from
	 */
	public int[] getFrom() {
		return from;
	}

	/**
	 * @return the position that the character is being moved to
	 */
	public int[] getTo() {
		return to;
	}

	/**
	 * @return the evaluated value of that move when running Alpha Beta Pruning
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param player the character that we want to set in the node
	 */
	public void setPlayer(char player) {
		this.player = player;
	}

	/**
	 * @param from set the position that we want a character to move from
	 */
	public void setFrom(int[] from) {
		this.from = from;
	}

	/**
	 * @param to set the position that we want a character to move to
	 */
	public void setTo(int[] to) {
		this.to = to;
	}

	/**
	 * @param value set the evaluated value for the node
	 */
	public void setValue(int value) {
		this.value = value;
	}
}

package ai.partB;

/**
 * COMP30004 Artificial Intelligence Assignment (Part B)
 * Group member: 
 * Dinh Phuc Ngoc - 784736 
 * Duy Vu - 741907
 * 
 **/

import java.util.ArrayList;

/**
 * Rules Class: this class will contain all possible moves of H/V according to the rules in specification
 * 	It will also help to generate all possible legal moves of a character on the board based on its current position
 */

public class Rules {
	
	// Initialize variable
	private static int[] pos;
    private static Board board;
    
    /**
     * This function will generate all possible move of a character on the board based on its current position
     * @param type : type of character being moved
     * @param pos : its current position
     * @param board : the current board
     * @return An arraylist of possible moves of that character; return null if there are no possible move
     */
    public static ArrayList<int[]> getNextMove(char type, int[] pos, Board board) {
        Rules.pos = pos;
        Rules.board = board;
        
        switch (type) {
            case MySliderPlayer.TYPE_H:
                return hRules();
            case MySliderPlayer.TYPE_V:
                return vRules();
            default:
                return null;
        }
    }
	
    /**
     * @return The possible moves of a character V
     */
    private static ArrayList<int[]> vRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        
        int[][] target = new int[][]{{0, 1}, {-1, 0}, {1, 0}}; // UP, LEFT, RIGHT
        
        for (int[] aTarget : target) {
            int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]}; // generate new possible position
            
            if (e[1] == board.getBoard_size()) // check if it is a winning move, make that move a legal move
				moves.add(e);
			else if (board.isEmpty(e)) // check if the new position is free/unoccupied, else it is a illegal move
				moves.add(e);
        }
        return moves;
    }
    
	/**
	 * @return The possible moves of character H
	 */
	private static ArrayList<int[]> hRules() {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		
		int[][] target = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 } }; // UP, RIGHT, DOWN 
		
		for (int[] aTarget : target) {
			int[] e = new int[] { pos[0] + aTarget[0], pos[1] + aTarget[1] };// generate new possible position
			
			if (e[0] == board.getBoard_size()) // check if it is a winning move, make that move a legal move
				moves.add(e);
			else if (board.isEmpty(e)) // check if the new position is free/unoccupied, else it is a illegal move
				moves.add(e);
		}
		return moves;
	}
}
	
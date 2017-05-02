package ai.partB;

/** COMP30004 Artificial Intelligence
 * Assignment 1
 * Group member:
 * Dinh Phuc Ngoc - 784736
 * Duy Vu - 741907
 * 
 * This program should count the number of legal moves available to each player in a given board configuration for the game Slider.
 * 
**/
import java.util.InputMismatchException;
import java.util.Scanner;

import aiproj.slider.Move;

import java.lang.String;

public class Main {

	public static void main(){
		
	}

	// board of the game
	public static Board board;

	// type of piece
	final static char TYPE_H = 'H';
	final static char TYPE_V = 'V';
	final static char TYPE_B = 'B';
	final static char TYPE_F = '+';
	
	// Player type
	public static char p;

	public void init(int dimension, String board_arrangment, char player) {
		p = player;
		Scanner data = new Scanner(board_arrangment);

		try {
			board = new Board(dimension);

			/*
			 * Scan the information of the board from Scanner.Start with
			 * (board_size - 1,0) corresponds to the top-left position then load
			 * into corresponding Array type and 'board'
			 */
			for (int row = dimension - 1; row >= 0; row--) {
				for (int column = 0; column < dimension; column++) {
					int[] pos = {column, row};
					char type = data.next().toCharArray()[0];
					board.cells[column][row] = new Square(pos, type);
				}
			}
			
			//for (Map.Entry<Character, Square> charPieceEntry : board.squares.entrySet()) 
				//board.update(charPieceEntry.getValue());

			data.close();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		
		
	}

	public void moveSquare(int[] oldPos, int[] newPos, Board board) {
        board.updateSquare(oldPos, newPos);
    }

	public void update(Move move){
		int[] oldPos = {move.i, move.j};
		// Moving UP
		if (move.d == Move.Direction.UP) {
			int[] newPos = {move.i, move.j+1};
			moveSquare(oldPos, newPos, board);
		}
		// Moving DOWN
		else if (move.d == Move.Direction.DOWN) {
			int[] newPos = {move.i, move.j-1};
			moveSquare(oldPos, newPos, board);
		}
		// Moving LEFT
		else if (move.d == Move.Direction.LEFT) {
			int[] newPos = {move.i+1, move.j};
			moveSquare(oldPos, newPos, board);
		}// Moving RIGHT
		else {
			int[] newPos = {move.i-1, move.j};
			moveSquare(oldPos, newPos, board);
		}
	}

	public Move move() {
		// TODO Auto-generated method stub
		return null;
	}

}

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

	public static void main() {

	}

	// board of the game
	public static Board board;

	// type of piece
	final static char TYPE_H = 'H';
	final static char TYPE_V = 'V';
	final static char TYPE_B = 'B';
	final static char TYPE_F = '+';

	// Player type
	public static char player;
	public static char opponent;

	public void init(int dimension, String board_arrangment, char player) {
		Main.player = player;
		if (player == TYPE_H)
			opponent = TYPE_V;
		else if (player == TYPE_V)
			opponent = TYPE_H;

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
					int[] pos = { column, row };
					char type = data.next().toCharArray()[0];
					board.cells[column][row] = new Square(pos, type);
					if (type == TYPE_H)
						board.setHsquare(board.getHsquare() + 1);
					if (type == TYPE_V)
						board.setVsquare(board.getVsquare() + 1);
				}
			}

			data.close();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}

	}

	public void moveSquare(int[] oldPos, int[] newPos, Board board) {
		board.updateSquare(oldPos, newPos);
	}

	public void update(Move move) {
		if (move == null) {
			return;
		} else {
			int[] oldPos = { move.i, move.j };

			// Moving UP
			if (move.d == Move.Direction.UP) {
				int[] newPos = { move.i, move.j + 1 };
				moveSquare(oldPos, newPos, board);
			}
			// Moving DOWN
			else if (move.d == Move.Direction.DOWN) {
				int[] newPos = { move.i, move.j - 1 };
				moveSquare(oldPos, newPos, board);
			}
			// Moving LEFT
			else if (move.d == Move.Direction.LEFT) {
				int[] newPos = { move.i + 1, move.j };
				moveSquare(oldPos, newPos, board);
			} // Moving RIGHT
			else {
				int[] newPos = { move.i - 1, move.j };
				moveSquare(oldPos, newPos, board);
			}
		}
	}

	public Move move() {

		Search search = new Search();
		AlphaBetaNode result = search.search(board);
		if (result == null) {
			return null;
		} else {
			int[] from = result.getFrom();
			int[] to = result.getTo(); // getTo()???

			Move move; 

			if (from[1] > to[1]) {
				move = new Move(from[0], from[1], Move.Direction.LEFT);
			} else if (from[1] < to[1]) {
				move = new Move(from[0], from[1], Move.Direction.RIGHT);
			} else if (from[0] < to[0]) {
				move = new Move(from[0], from[1], Move.Direction.UP);
			} else {
				move = new Move(from[0], from[1], Move.Direction.DOWN);
			}
			update(move);
			return move;
		}
	}

}

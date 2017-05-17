package ai.vuh2ndinh1;

/**
 * COMP30004 Artificial Intelligence Assignment (Part B)
 * Group member: 
 * Dinh Phuc Ngoc - 784736 
 * Duy Vu - 741907
 * 
 **/

import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.String;

/**
 * MySliderPlayer Class: This is the main class of this project. It will implement the SliderPlayer.
 */

public class MySliderPlayer implements SliderPlayer {
	
	// board of the game
	private Board board;

	// type of piece
	final static char TYPE_H = 'H';
	final static char TYPE_V = 'V';
	final static char TYPE_B = 'B';
	final static char TYPE_F = '+';


	// Player type
	private char player;
	private char opponent;
	
	@Override
	public void init(int dimension, String board_arrangment, char player) {
		// determine the my player and opponent player
		this.player = player;
		
		if (player == TYPE_H)
			this.opponent = TYPE_V;
		else if (player == TYPE_V)
			this.opponent = TYPE_H;

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

	@Override
	public void update(Move move) {
		// if no move is passed from Referee, return null (no change to the board)
		if (move == null) {
			return;
		} else {
			// indicate the position of the square that being moved
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
				int[] newPos = { move.i - 1, move.j };
				moveSquare(oldPos, newPos, board);
			} // Moving RIGHT
			else {
				int[] newPos = { move.i + 1, move.j };
				moveSquare(oldPos, newPos, board);
			}
		}
	}
	
	/**
	 * Moving the Square from a position to other position on the board
	 * @param oldPos : old position
	 * @param newPos : new position that we want it to move to
	 * @param board : the current board
	 */
	public void moveSquare(int[] oldPos, int[] newPos, Board board) {
		board.updateSquare(oldPos, newPos);
	}

	@Override
	public Move move() {
		
		Search search = new Search(this.player, this.opponent);
		Board newBoard = new Board(board);

		AlphaBetaNode result = search.search(newBoard);

		if (result == null) {
			return null;
		} else {
			int[] from = result.getFrom();
			int[] to = result.getTo(); 
			
			Move move; 

			if (from[1] > to[1]) {
				move = new Move(from[0], from[1], Move.Direction.DOWN);
			} else if (from[1] < to[1]) {
				move = new Move(from[0], from[1], Move.Direction.UP);
			} else if (from[0] < to[0]) {
				move = new Move(from[0], from[1], Move.Direction.RIGHT);
			} else {
				move = new Move(from[0], from[1], Move.Direction.LEFT);
			}
			
			update(move);
			return move;
		}	
	}
	
	/**
	 * @return The character of my player
	 */
	public char getPlayer() {
		return player;
	}

	/**
	 * @return The character of opponent
	 */
	public char getOpponent() {
		return opponent;
	}

}

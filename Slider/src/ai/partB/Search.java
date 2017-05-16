package ai.partB;

/**
 * COMP30004 Artificial Intelligence Assignment 1
 * Group member: 
 * Dinh Phuc Ngoc - 784736 
 * Duy Vu - 741907
 * 
 **/

import java.util.ArrayList;

/**
 * Search Class: in this class, we implement the search method. We will generate all possible move down to a certain 
 * depth and evaluate the value of the board at each move. After that using Alpha Beta to find the best possible move
 * for that character.
 */
public class Search {
	
	// Initialize Variable
	private int DEPTH;
	private char player;
	private char opponent;
	private AlphaBetaNode chosen;

	/**
	 * Constructor of Search function
	 * @param player: my character
	 * @param opponent: opponent's character
	 */
	public Search(char player, char opponent) {
		this.player = player; 
		this.opponent = opponent;
	}

	public AlphaBetaNode search(Board board) {
		if(board.getBoard_size() == 5){
			DEPTH = 9;
		}else if (board.getBoard_size() == 6){
			DEPTH = 8;
		}else {
			DEPTH = 7;
		}
		
		this.chosen = null;
		
		// generate all 
		//System.out.print("Search all move: ");
		ArrayList<AlphaBetaNode> moves = generateAllMoves(board, this.player);
		
		if (moves.size() == 0) {
			System.out.println("No moves avail");
			return null;
		}
		
		if (moves.size() == 1) {
			System.out.println("Only 1 move avail");
			return moves.get(0);
		}
		

		// Replaced part as Wiki psudocode: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
		int evalValue = alphaBeta(new Board(board), DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true, false);
		//System.out.println("evalValue = " + evalValue);
//		if (this.chosen != null) {
//			this.chosen.printNode();
//		}
		return this.chosen; 
}
		
	// Replaced part as Wiki psudocode: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
	/**
	 * This function implementing the Alpha Beta Pruning
	 * @param board: the current board
	 * @param depth: the depth searching at (depth 0 is the farthest)
	 * @param alpha: alpha cut-off point, represent the maximum score that the maximizing player is assured of
	 * @param beta: beta cut-off point, represent the minimum score that the minimizing player is assured of
	 * @param isMax: 
	 * @param justFinish: if it is a finishing move, this will help to evaluate the board value
	 * @return the highest evaluated value
	 */
	private int alphaBeta(Board board, int depth, int alpha, int beta, boolean isMax, boolean justFinish) {
		//System.out.println("***** ALPHABETA *****");
		
		
		/* Return evaluation if reaching maximum depth/ leaf node or any side won or terminal node. */
		if (depth == 0 || board.finished()) {
			return new BoardValue().eval(board, this.player, justFinish);
		}
		
		int v;
		boolean isFinishMove;
		// Maximizer
		if (isMax) {
			v = Integer.MIN_VALUE;
			//System.out.print("MAX: [depth = "+ depth+"]: ");
			for (AlphaBetaNode child : this.generateAllMoves(board, this.player)) {
				
				// Simulate child node move
				Board child_board = new Board(board);
				isFinishMove = child_board.updateSquare(child.getFrom(), child.getTo());
				
				int alphaBetaV = this.alphaBeta(child_board, depth-1, alpha, beta, false, isFinishMove);
				
				if (alphaBetaV > v ) {
					v = alphaBetaV;
					if (this.getDEPTH() == depth) {
						this.chosen = child;
						this.chosen.setValue(v);
					}
				}
				//v = Math.max(v, alphaBetaV);
				alpha = Math.max(alpha, v);
				
				// Rollback board: 
				child_board.rollback(isFinishMove, child.getPlayer(),child.getTo(), child.getFrom());
				
				// Cut-off
				if (beta <= alpha) {
					//System.out.println("***** Prune *****");
					break;
				}
			}
			
			return v;
			
		// Minimizer	
		} else {
			
			v = Integer.MAX_VALUE;
			
			//System.out.print("MIN: [depth = "+ depth+"]: ");
			for (AlphaBetaNode child : this.generateAllMoves(board, this.opponent)) {
				
				// Simulate child node move
				Board child_board = board;
				isFinishMove = child_board.updateSquare(child.getFrom(), child.getTo());
				
				v = Math.min(v, this.alphaBeta(child_board, depth-1, alpha, beta, true, isFinishMove));
				beta = Math.min(beta, v);
				
				// Rollback board: 
				child_board.rollback(isFinishMove, child.getPlayer(),child.getTo(), child.getFrom());
				
				// Cut-off
				if (beta <= alpha) {
					break;
				}
			}
			return v;	
		}		
	}

	/**
	 * Generate all moves of a player from board condition
	 * @param board: the current board
	 * @param player: the character that we want to generate move from H/V
	 * @return : an arraylist of all possible move of that character on the board
	 */
	private ArrayList<AlphaBetaNode> generateAllMoves(Board board, char player) {
		//System.out.print("{" + player + "}");
		
		ArrayList<AlphaBetaNode> moves = new ArrayList<AlphaBetaNode>();
		
		for (int row = board.getBoard_size() - 1; row >= 0; row--) {
			for (int column = board.getBoard_size() - 1; column >=0; column--) {
				Square sqr = board.getSquare(column, row);
				
				if ((sqr.getType() == player) && (sqr.getType() == MySliderPlayer.TYPE_H || sqr.getType() == MySliderPlayer.TYPE_V )) {
					for (int[] nxt : Rules.getNextMove(sqr.getType(), sqr.getPosition(), board)) {
						// only takes moves of the current player:
						//System.out.print(" (" + sqr.getPosition()[0] + ", " + sqr.getPosition()[1] + ") " + "->(" + nxt[0] + ", " + nxt[1] + ") ||");
						moves.add(new AlphaBetaNode(sqr.getType(), sqr.getPosition(), nxt));
					}
				}			
			}
		}
		return moves;
	}
	
	/**
	 * @return the DEPTH that we are using for this board
	 */
	public int getDEPTH() {
		return DEPTH;
	}

	/**
	 * @param dEPTH: the DEPTH to set
	 */
	public void setDEPTH(int dEPTH) {
		DEPTH = dEPTH;
	}
}

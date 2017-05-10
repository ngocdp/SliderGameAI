package ai.partB;

import java.util.ArrayList;

public class Search {
	private static int DEPTH = 3;
	private char player;
	private char opponent;
	private AlphaBetaNode chosen;



	public Search(char player, char opponent) {
		this.player = player; 
		this.opponent = opponent;
	}

	public AlphaBetaNode search(Board board) {
		this.chosen = null;
		
		// generate all 
		System.out.print("Search(): ");
		ArrayList<AlphaBetaNode> moves = generateMovesForAll(board, this.player);
		
		if (moves.size() == 0) {
			System.out.println("No moves avail");
			return null;
		}
		

		// Replaced part as Wiki psudocode: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
		int evalValue = alphaBeta(new Board(board), DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		System.out.println("evalValue = " + evalValue);
		
		this.chosen.printNode();
		return this.chosen;
		
		
//		for (AlphaBetaNode n : moves) {
//		
//		// Simulate next board position on that move
//		board.updateSquare(n.getFrom(), n.getTo());
//		
//		// Evaluate the simulating play
//		int evalValue = alphaBeta(DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
//		n.setValue(evalValue);
//		//System.out.println("Alpha-beta value return: " + evalValue);
//		if (best == null || n.getValue() >= best.getValue()) {
//			best = n;
//		}
//		
//		// Rollback board
//		board.updateSquare(n.getTo(), n.getFrom());
//	}
}

//	private int old_alphaBeta(int depth, int alpha, int beta, boolean isMax) {
//		/* Return evaluation if reaching leaf node or any side won. */
//		if (depth == 0 || board.finished()) {
//			System.out.println("Eval()");
//			return new BoardValue().eval(board, this.player);
//		}
//	
//		ArrayList<AlphaBetaNode> moves = generateMovesForAll(isMax);
//
//		synchronized (this) {
//			for (final AlphaBetaNode n : moves) {
//				board.updateSquare(n.getFrom(), n.getTo());
//				/* Is maximizing player? */
//				final int finalBeta = beta;
//				final int finalAlpha = alpha;
//				final int finalDepth = depth;
//				final int[] temp = new int[1];
//
//				if (depth == 2) {
//					if (isMax) {
//						new Thread(new Runnable() {
//							@Override
//							public void run() {
//								temp[0] = Math.max(finalAlpha, old_alphaBeta(finalDepth - 1, finalAlpha, finalBeta, false));
//							}
//						}).run();
//						alpha = temp[0];
//					} else {
//						new Thread(new Runnable() {
//							@Override
//							public void run() {
//								temp[0] = Math.min(finalBeta, old_alphaBeta(finalDepth - 1, finalAlpha, finalBeta, true));
//							}
//						}).run();
//						beta = temp[0];
//					}
//				} else {
//					if (isMax)
//						alpha = Math.max(alpha, old_alphaBeta(depth - 1, alpha, beta, false));
//					else
//						beta = Math.min(beta, old_alphaBeta(depth - 1, alpha, beta, true));
//				}
//				board.updateSquare(n.getTo(), n.getFrom());
//
//				/* Cut-off */
//				if (beta <= alpha)
//					break;
//			}
//		}
//		return isMax ? alpha : beta;
//	}
	
	
	// Replaced part as Wiki psudocode: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
	private int alphaBeta(Board board, int depth, int alpha, int beta, boolean isMax) {
		int v;
		/* Return evaluation if reaching maximum depth/ leaf node or any side won or terminal node. */
		if (depth == 0 || board.finished()) {
			return new BoardValue().eval(board, this.player);
		}
		
		
		// Maximizer
		if (isMax) {
			v = Integer.MIN_VALUE;
			System.out.print("MAX: [depth = "+ depth+"]: ");
			for (AlphaBetaNode child : this.generateMovesForAll(board, this.player)) {
				
				// Simulate child node move
				Board child_board = new Board(board);
				child_board.updateSquare(child.getFrom(), child.getTo());
				
				int alphaBetaV = this.alphaBeta(child_board, depth-1, alpha, beta, false);
				
				if (alphaBetaV > v ) {
					v = alphaBetaV;
					if (Search.DEPTH == depth) {
						this.chosen = child;
						this.chosen.setValue(v);
					}
				}
				//v = Math.max(v, alphaBetaV);
				alpha = Math.max(alpha, v);
				
				// Rollback board: 
				child_board.updateSquare(child.getTo(), child.getFrom());
				
				// Cut-off
				if (beta <= alpha) {
					break;
				}
			}
			
			return v;
			
		// Minimizer	
		} else {
			
			v = Integer.MAX_VALUE;
			
			System.out.print("MIN: [depth = "+ depth+"]: ");
			for (AlphaBetaNode child : this.generateMovesForAll(board, this.opponent)) {
				
				// Simulate child node move
				Board child_board = board;
				child_board.updateSquare(child.getFrom(), child.getTo());
				
				v = Math.min(v, this.alphaBeta(child_board, depth-1, alpha, beta, true));
				beta = Math.min(beta, v);
				
				// Rollback board: 
				child_board.updateSquare(child.getTo(), child.getFrom());
				
				// Cut-off
				if (beta <= alpha) {
					break;
				}
			}
			return v;	
		}		
	}

	
	// Generate all moves of Player "player" from 'board' condition
	private ArrayList<AlphaBetaNode> generateMovesForAll(Board board, char player) {
		System.out.print("{" + player + "}");
		
		ArrayList<AlphaBetaNode> moves = new ArrayList<AlphaBetaNode>();
		
		for (int row = board.getBoard_size() - 1; row >= 0; row--) {
			for (int column = 0; column < board.getBoard_size(); column++) {
				Square sqr = board.getSquare(column, row);
			
//				char opponent;
//				// Temporally here - delete later
//				if (player == Main.TYPE_H)
//					opponent = Main.TYPE_V;
//				else 
//					opponent = Main.TYPE_H;
//				
//				//
				
//				if ((sqr.getType() == MySliderPlayer.TYPE_B || sqr.getType() == MySliderPlayer.TYPE_F )) //|| (isMax && sqr.getType() == opponent) || (!isMax && sqr.getType() == this.player))
//					continue;
				
				if ((sqr.getType() == player) && (sqr.getType() == MySliderPlayer.TYPE_H || sqr.getType() == MySliderPlayer.TYPE_V )) {
					for (int[] nxt : Rules.getNextMove(sqr.getType(), sqr.getPosition(), board)) {
						// only takes moves of the current player:
						
						System.out.print(" (" + sqr.getPosition()[0] + ", " + sqr.getPosition()[1] + ") " + "->(" + nxt[0] + ", " + nxt[1] + ") ||");
						moves.add(new AlphaBetaNode(sqr.getType(), sqr.getPosition(), nxt));
						
					}

				}

					
			}
		}

		System.out.print("\n");

		return moves;
	}
	


}

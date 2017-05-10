package ai.partB;

import java.util.ArrayList;

public class Search {
	private static int DEPTH = 3;
	private Board board;
	private char player;


	public AlphaBetaNode search(Board board, char player) {
		this.board = board;
		this.player = player; // bad practices

		AlphaBetaNode best = null;
		ArrayList<AlphaBetaNode> moves = generateMovesForAll(true);
		if (moves.size() == 0)
				return null;
		for (AlphaBetaNode n : moves) {
			
			// Simulate next board position on that move
			board.updateSquare(n.getFrom(), n.getTo());
			int evalValue = old_alphaBeta(DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
			n.setValue(evalValue);
			//System.out.println("Alpha-beta value return: " + evalValue);
			if (best == null || n.getValue() >= best.getValue()) {
				best = n;
			}
			
			// Roll back board
			board.updateSquare(n.getTo(), n.getFrom());
		}
		System.out.print("\n");

		best.printNode();
		return best;
	}

	private int old_alphaBeta(int depth, int alpha, int beta, boolean isMax) {
		/* Return evaluation if reaching leaf node or any side won. */
		if (depth == 0 || board.finished())
			return new BoardValue().eval(board, this.player);
		ArrayList<AlphaBetaNode> moves = generateMovesForAll(isMax);

		synchronized (this) {
			for (final AlphaBetaNode n : moves) {
				board.updateSquare(n.getFrom(), n.getTo());
				/* Is maximizing player? */
				final int finalBeta = beta;
				final int finalAlpha = alpha;
				final int finalDepth = depth;
				final int[] temp = new int[1];

				if (depth == 2) {
					if (isMax) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								temp[0] = Math.max(finalAlpha, old_alphaBeta(finalDepth - 1, finalAlpha, finalBeta, false));
							}
						}).run();
						alpha = temp[0];
					} else {
						new Thread(new Runnable() {
							@Override
							public void run() {
								temp[0] = Math.min(finalBeta, old_alphaBeta(finalDepth - 1, finalAlpha, finalBeta, true));
							}
						}).run();
						beta = temp[0];
					}
				} else {
					if (isMax)
						alpha = Math.max(alpha, old_alphaBeta(depth - 1, alpha, beta, false));
					else
						beta = Math.min(beta, old_alphaBeta(depth - 1, alpha, beta, true));
				}
				board.updateSquare(n.getTo(), n.getFrom());

				/* Cut-off */
				if (beta <= alpha)
					break;
			}
		}
		return isMax ? alpha : beta;
	}
	
	private int alphaBeta(int depth, int alpha, int beta, boolean isMax) {
		return 0;
	}

	private ArrayList<AlphaBetaNode> generateMovesForAll(boolean isMax) {
		ArrayList<AlphaBetaNode> moves = new ArrayList<AlphaBetaNode>();
		
		for (int row = board.getBoard_size() - 1; row >= 0; row--) {
			for (int column = 0; column < board.getBoard_size(); column++) {
				Square sqr = board.getSquare(column, row);

				
				char opponent;
				// Temporally here - delete later
				if (player == Main.TYPE_H)
					opponent = Main.TYPE_V;
				else 
					opponent = Main.TYPE_H;
				
				//
				
				if ((sqr.getType() == Main.TYPE_B || sqr.getType() == Main.TYPE_F ) || (isMax && sqr.getType() == opponent) || (!isMax && sqr.getType() == this.player))
					continue;
				else
					for (int[] nxt : Rules.getNextMove(sqr.getType(), sqr.getPosition(), board)) {
						// only takes moves of the current player:

						if (sqr.getType() == this.player) {
							System.out.print("Type: "+ sqr.getType() + ": (" + sqr.getPosition()[0] + ", " + sqr.getPosition()[1] + ") " + "->(" + nxt[0] + ", " + nxt[1] + ") ");
							moves.add(new AlphaBetaNode(sqr.getType(), sqr.getPosition(), nxt));
						}
					}
			}
		}
		return moves;
	}
	
	public void printAllMoves() {
		
	}

}

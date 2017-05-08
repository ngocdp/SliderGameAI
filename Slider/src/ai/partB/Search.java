package ai.partB;

import java.util.ArrayList;

public class Search {
	private static int DEPTH = 2;
	private Board board;

	public AlphaBetaNode search(Board board) {
		this.board = board;

		AlphaBetaNode best = null;
		ArrayList<AlphaBetaNode> moves = generateMovesForAll(true);
		if (moves.size() == 0)
				return null;
		for (AlphaBetaNode n : moves) {
			board.updateSquare(n.getFrom(), n.getTo());
			n.setValue(alphaBeta(DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false));
			if (best == null || n.getValue() >= best.getValue())
				best = n;
			board.updateSquare(n.getTo(), n.getFrom());
		}
		return best;
	}

	private int alphaBeta(int depth, int alpha, int beta, boolean isMax) {
		/* Return evaluation if reaching leaf node or any side won. */
		if (depth == 0 || board.finished())
			return new BoardValue().eval(board, Main.player);
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
								temp[0] = Math.max(finalAlpha, alphaBeta(finalDepth - 1, finalAlpha, finalBeta, false));
							}
						}).run();
						alpha = temp[0];
					} else {
						new Thread(new Runnable() {
							@Override
							public void run() {
								temp[0] = Math.min(finalBeta, alphaBeta(finalDepth - 1, finalAlpha, finalBeta, true));
							}
						}).run();
						beta = temp[0];
					}
				} else {
					if (isMax)
						alpha = Math.max(alpha, alphaBeta(depth - 1, alpha, beta, false));
					else
						beta = Math.min(beta, alphaBeta(depth - 1, alpha, beta, true));
				}
				board.updateSquare(n.getTo(), n.getFrom());

				/* Cut-off */
				if (beta <= alpha)
					break;
			}
		}
		return isMax ? alpha : beta;
	}

	private ArrayList<AlphaBetaNode> generateMovesForAll(boolean isMax) {
		ArrayList<AlphaBetaNode> moves = new ArrayList<AlphaBetaNode>();
		
		for (int row = board.getBoard_size() - 1; row >= 0; row--) {
			for (int column = 0; column < board.getBoard_size(); column++) {
				Square sqr = board.getSquare(column, row);
				
				if ((sqr.getType() == Main.TYPE_B || sqr.getType() == Main.TYPE_F ) || (isMax && sqr.getType() == Main.player) || (!isMax && sqr.getType() == Main.opponent))
					continue;
				else
					for (int[] nxt : Rules.getNextMove(sqr.getType(), sqr.getPosition(), board))
						moves.add(new AlphaBetaNode(sqr.getType(), sqr.getPosition(), nxt));
			}
		}
		return moves;
	}

}

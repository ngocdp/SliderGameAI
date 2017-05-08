package ai.partB;

import java.util.ArrayList;

public class Rules {
	private static int[] pos;
    private static Board board;
    
    public static ArrayList<int[]> getNextMove(char type, int[] pos, Board board) {
        Rules.pos = pos;
        Rules.board = board;
        switch (type) {
            case Main.TYPE_H:
                return hRules();
            case Main.TYPE_V:
                return vRules();
            default:
                return null;
        }
    }
	
    private static ArrayList<int[]> vRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] target = new int[][]{{0, 1}, {0, -1}, {1, 0}};
        for (int[] aTarget : target) {
            int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
            if (e[1] == board.getBoard_size())
				moves.add(e);
			else if (board.isEmpty(e))
				moves.add(e);
        }
        return moves;
    }
    
	private static ArrayList<int[]> hRules() {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		int[][] target = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 } };
		for (int[] aTarget : target) {
			int[] e = new int[] { pos[0] + aTarget[0], pos[1] + aTarget[1] };
			if (e[0] == board.getBoard_size())
				moves.add(e);
			else if (board.isEmpty(e))
				moves.add(e);
		}
		return moves;
	}
}
	
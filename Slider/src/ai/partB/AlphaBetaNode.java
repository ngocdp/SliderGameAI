package ai.partB;

public class AlphaBetaNode {
	private char player;
    private int[] from;
    private int[] to;
    private int value;

    public AlphaBetaNode(char player, int[] from, int[] to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }

	public char getPlayer() {
		return player;
	}

	public int[] getFrom() {
		return from;
	}

	public int[] getTo() {
		return to;
	}

	public int getValue() {
		return value;
	}

	public void setPlayer(char player) {
		this.player = player;
	}

	public void setFrom(int[] from) {
		this.from = from;
	}

	public void setTo(int[] to) {
		this.to = to;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

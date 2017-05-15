package ai.partB;

public class FinishSquare extends Square {
	
	private int numFinish; // number of finising slider in the finish square

	public FinishSquare(int[] position, char type) {
		super(position, type);
		this.numFinish = 0;
	}

	public FinishSquare(Square sqr) {
		super(sqr);
		this.numFinish = 0;
	}

	public int getNumFinish() {
		return numFinish;
	}

	public void setNumFinish(int numFinish) {
		this.numFinish = numFinish;
	}

}

package ai.partB;

import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;

public class MySliderPlayer implements SliderPlayer {
	
	Main main = new Main();
	
	@Override
	public void init(int dimension, String board_arrangment, char player) {
		main.init(dimension, board_arrangment,player);
	}

	@Override
	public void update(Move move) {
		main.update(move);

	}

	@Override
	public Move move() {		
		return main.move();
	}

}

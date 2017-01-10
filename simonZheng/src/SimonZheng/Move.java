package SimonZheng;

public class Move implements MoveInterface {

	private ButtonInterface button;
	
	public Move(ButtonInterface b){
		button = b;
	}
	
	public ButtonInterface getButton() {
		return button;
	}

}

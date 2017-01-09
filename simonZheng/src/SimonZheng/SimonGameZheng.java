package SimonZheng;

import guiPackage.GuiApplication;
import guiPackage.Screen;

public class SimonGameZheng extends GuiApplication implements Runnable {
	
	public static Screen mainScreen;
	
	public SimonGameZheng() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initScreen() {
		mainScreen = new SimonScreenZheng(getWidth(), getHeight());
		setScreen(mainScreen);
	}

	public static void main(String[] args) {
		SimonGameZheng game = new SimonGameZheng();
		Thread app = new Thread(game);
		app.start();

	}

}

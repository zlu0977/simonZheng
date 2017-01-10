package SimonZheng;

import java.awt.Color;
import java.util.ArrayList;

import guiPackage.ClickableScreen;
import guiPackage.components.Action;
import guiPackage.components.TextLabel;
import guiPackage.components.Visible;

public class SimonScreenZheng extends ClickableScreen implements Runnable {
	
	public ArrayList<ButtonInterface> buttonList;
	public ArrayList<MoveInterface> moveList;
	public int sequenceInt;
	public ProgressInterface progress;
	public TextLabel turn;
	public int round;
	public int sequenceLength;
	public static boolean playerTurn;
	
	public SimonScreenZheng(int width, int height) {
		super(width, height);
		round = 0;
		sequenceLength = 2;
		sequenceInt = 0;
		
		Thread play = new Thread(this);
		play.start();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		buttonList = new ArrayList<ButtonInterface>();
		moveList = new ArrayList<MoveInterface>();
		
		getButtons(viewObjects);
		
		turn = new TextLabel(50, 50, getWidth(), 200, "");
		progress = getProgress();
		moveList.add(getAMove());
		moveList.add(getAMove());
		
		viewObjects.add(turn);
		viewObjects.add((Visible) progress);
		

	}

	private void getButtons(ArrayList<Visible> viewObjects) {
		ButtonInterface button1 = getAButton(15, 150, 50, 50, Color.red);
		ButtonInterface button2 = getAButton(155, 150, 50, 50, Color.blue);
		ButtonInterface button3 = getAButton(15, 300, 50, 50, Color.yellow);
		ButtonInterface button4 = getAButton(155, 300, 50, 50, Color.green);
		
		buttonList.add(button1);
		buttonList.add(button2);
		buttonList.add(button3);
		buttonList.add(button4);
		
		viewObjects.add(button1);
		viewObjects.add(button2);
		viewObjects.add(button3);
		viewObjects.add(button4);
	}
	
	private void changeText(String s)
	{
		try {
			Thread.sleep(1000);
			turn.setText(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		newRound();
		moveList.add(getAMove());
		showMoves();
		
		changeText("Your Turn");
		changeText("");
		playerTurn = true;
		
	}
	
	private void newRound()
	{
		playerTurn = false;
		sequenceInt = 0;
		
		round ++;
		sequenceLength ++;
		
		progress.setRound(round);
		progress.setSequenceLength(sequenceLength);
		changeText("Simon's Turn");
		changeText("");
	}
	
	private void showMoves() {
		for(MoveInterface move: moveList)
		{
			try {
				move.getButton().blink();
				Thread.sleep(1000/round);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void checkMove(ButtonInterface button)
	{
		if(playerTurn)
		{
			if(button.equals(moveList.get(sequenceInt).getButton()))
			{
				sequenceInt++;
				
				if(sequenceInt == moveList.size())
				{
					Thread play = new Thread(this);
					play.start();
				}
			}
			else
			{
				playerTurn = false;
				changeText("You Lost");
			}
		}
	}

	private ButtonInterface getAButton(int x, int y, int width, int height, Color color) {
		ButtonInterface button = new Button(x, y, width, height, color);
		button.setAction(new Action() {
			public void act()
			{
				if(playerTurn){
					Thread blink = new Thread(new Runnable(){

						public void run(){
							button.blink();
							checkMove(button);
						}
						
					});
					blink.start();
				}
			}
		});
		
		return button;
	}
	
	private MoveInterface getAMove() {
		int rand = (int)( Math.random() * buttonList.size());
		
		if(moveList.size() > 0)
		{
			int last = buttonList.indexOf(moveList.get(moveList.size() - 1).getButton());
			while(last == rand)
				rand = (int)( Math.random() * buttonList.size());
		}
		
		return new Move(buttonList.get(rand));
	}
	
	private ProgressInterface getProgress() {
		ProgressInterface progress = new Progress(10, 32, 200, 90, "", round, sequenceLength);
		return progress;
	}
}

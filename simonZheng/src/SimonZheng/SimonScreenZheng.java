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
	public ArrayList<MoveInterface> playerMoveList;
	public ProgressInterface progress;
	public TextLabel turn;
	public int round;
	public int sequenceLength;
	public static boolean playerTurn;
	
	public SimonScreenZheng(int width, int height) {
		super(width, height);
		round = 0;
		sequenceLength = 2;
		
		Thread play = new Thread(this);
		play.start();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		buttonList = new ArrayList<ButtonInterface>();
		moveList = new ArrayList<MoveInterface>();
		playerMoveList = new ArrayList<MoveInterface>();
		
		ButtonInterface button1 = getAButton(10, 150, 50, 50, Color.red);
		ButtonInterface button2 = getAButton(150, 150, 50, 50, Color.blue);
		ButtonInterface button3 = getAButton(10, 300, 50, 50, Color.yellow);
		ButtonInterface button4 = getAButton(150, 300, 50, 50, Color.green);
		
		buttonList.add(button1);
		buttonList.add(button2);
		buttonList.add(button3);
		buttonList.add(button4);
		
		viewObjects.add(button1);
		viewObjects.add(button2);
		viewObjects.add(button3);
		viewObjects.add(button4);
		
		turn = new TextLabel(0, 0, getWidth(), 200, "");
		progress = getProgress();
		moveList.add(getAMove());
		moveList.add(getAMove());
		

	}

	private void getButtons() {
		ButtonInterface button1 = getAButton(10, 150, 50, 50, Color.red);
		ButtonInterface button2 = getAButton(150, 150, 50, 50, Color.blue);
		ButtonInterface button3 = getAButton(10, 300, 50, 50, Color.yellow);
		ButtonInterface button4 = getAButton(150, 300, 50, 50, Color.green);
		
		buttonList.add(button1);
		buttonList.add(button2);
		buttonList.add(button3);
		buttonList.add(button4);
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
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
		
		playerTurn = false;
		playerMoveList = new ArrayList<MoveInterface>();
		newRound();
		changeText("Simon's Turn");
		changeText("");
		moveList.add(getAMove());
		showMoves();
		
		changeText("Your Turn");
		playerTurn = true;
		
	}
	
	private void newRound()
	{
		round ++;
		sequenceLength ++;
		
		progress.setRound(round);
		progress.setSequenceLength(sequenceLength);
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
	
	public void checkMove(MoveInterface move)
	{
		if(playerTurn)
		{
			playerMoveList.add(move);
			if(playerMoveList.equals(moveList.subList(0, playerMoveList.size())))
			{
				move.getButton().blink();
				if(playerMoveList.equals(moveList))
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
		ButtonInterface button = new SimonButton(x, y, width, height, color);
		button.setAction(new Action() {
			public void act()
			{
				button.blink();
				checkMove(new Move(button));
			}
		});
		return button;
	}
	
	private MoveInterface getAMove() {
		MoveInterface move;
		
		//do
		//{
			ButtonInterface randButton = buttonList.get((int)  Math.random() * buttonList.size());
			move = new Move(randButton);
			
		//}while(moveList.get(moveList.size() - 1) != null && !moveList.get(moveList.size() - 1).equals(move));
		
		return move;
	}
	
	private ProgressInterface getProgress() {
		ProgressInterface progress = new Progress(0, 0, 300, 100, "", round, sequenceLength);
		return progress;
	}
}

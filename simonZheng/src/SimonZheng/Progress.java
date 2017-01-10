package SimonZheng;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import guiPackage.components.TextLabel;

public class Progress extends TextLabel implements ProgressInterface {
	
	private int sequenceLength;
	private int round;
	
	public Progress(int x, int y, int width, int height, String text, int round, int sequenceLength) {
		super(x, y, width, height, text);
		this.round = round;
		this.sequenceLength = sequenceLength;
		update();
	}

	@Override
	public void setRound(int round) {
		this.round = round;
		update();
	}

	@Override
	public void setSequenceLength(int sequenceLength) {
		this.sequenceLength = sequenceLength;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.setFont(new Font("Helvetica", Font.PLAIN, 20));
		g.drawRect(0, 0,  getWidth()-1,  getHeight()-1);
		g.drawString("Round : " + round, 5, 30);
		g.drawString("Sequence Length : " + sequenceLength, 5, 60);
	}

}

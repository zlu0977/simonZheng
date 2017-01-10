package SimonZheng;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import guiPackage.components.Action;
import guiPackage.components.Component;

public class Button extends Component implements ButtonInterface{
	
	private Action action;
	private Color color;
	private Color displayColor;
	
	public Button(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
		update();
	}

	@Override
	public void blink() {
		displayColor = color;
		update();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dim();

	}

	@Override
	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) 
			g.setColor(displayColor);
		else 
			g.setColor(Color.gray);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		
	}

	@Override
	public void dim() {
		displayColor = Color.gray;
		update();
		
	}

	@Override
	public void act() {
		action.act();
	}
	
	@Override
	public boolean isHovered(int x, int y) {
		// TODO Auto-generated method stub
		if(x > getX() && x < getX() + getWidth())
			if(y > getY() && y < getY() + getHeight())
				return true;
				
		return false;
	}

}

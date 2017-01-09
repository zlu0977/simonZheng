package SimonZheng;

import guiPackage.components.Action;
import guiPackage.components.Clickable;

public interface ButtonInterface extends Clickable {

	void blink();
	void setAction(Action action);
	void dim();

}

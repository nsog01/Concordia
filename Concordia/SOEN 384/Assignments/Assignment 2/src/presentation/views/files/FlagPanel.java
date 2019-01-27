package presentation.views.files;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import domain.files.Flag;
import domain.files.FlagList;
import domain.files.Household;

public class FlagPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 4789043214432L;
	ArrayList<FlagButton> buttons;
	FlagList flags;
	private boolean isSet = false;

	FlagPanel(Household house) {
		setLayout(new MigLayout());
		if (house != null) {
			flags = house.getFlags();
			buttons = new ArrayList<FlagButton>();
			flags.addObserver(this);
			for (Flag f : flags) {
				FlagButton button = new FlagButton(f);
				buttons.add(button);
				add(button);
			}
			isSet = true;
		}
	}
	
	public void setHouse(Household house){
		if (!isSet){
			flags = house.getFlags();
			buttons = new ArrayList<FlagButton>();
			flags.addObserver(this);
			for (Flag f : flags) {
				FlagButton button = new FlagButton(f);
				buttons.add(button);
				add(button);
			}
			isSet = true;
		}
		revalidate();
		repaint();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for (Flag f : flags) {
			if (!contains(f)) {
				FlagButton button = new FlagButton(f);
				buttons.add(button);
				add(button);
				revalidate();
				repaint();
			}
		}
		for (FlagButton b : buttons) {
			if (!flags.contains(b.getFlag())) {
				remove(b);
				b = null;
				revalidate();
				repaint();
			}
		}
	}

	private boolean contains(Flag f) {
		for (FlagButton b : buttons) {
			if (b.getFlag() == f) {
				return true;
			}
		}
		return false;
	}
}

package presentation.views.appointments;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentation.controllers.appointments.AppointmentWindowController;

public class AppointmentButton extends JButton{

	private static final long serialVersionUID = 43278903214L;

	ImageIcon icon;
	
	public AppointmentButton(){
		icon = new ImageIcon("images/OK.png");
		this.setAction(action());
		setMaximumSize(new Dimension(icon.getIconHeight()+5, icon.getIconWidth()+5));
	}
	
	public AbstractAction action() {
		return new AbstractAction(null, icon) {
			private static final long serialVersionUID = -43278190432342235L;

			// This method is called when the button is pressed
			public void actionPerformed(ActionEvent evt) {
				new AppointmentWindowController();
			}
		};
	}
	
}

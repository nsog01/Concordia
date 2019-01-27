package presentation.views.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import domain.files.Client;

import presentation.views.appointments.AppointmentCreationFrame;
import presentation.views.files.HouseholdPanel;
//import presentation.views.files.HouseholdWindow;

public class NewAppointmentMenuItem extends JMenuItem{

	private static final long serialVersionUID = 78943214267L;
	private String newAptText="Appointment";
	
	@SuppressWarnings("unused")
	private HouseholdPanel window;
	public NewAppointmentMenuItem(HouseholdPanel householdPanel){
		this.window = householdPanel;
		setText(newAptText);
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		addActionListener(action());
	}
	
	
	public NewAppointmentMenuItem() {
		setText(newAptText);
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		addActionListener(action());
	}


	private ActionListener action(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new AppointmentCreationFrame(new Client());
			}
			
		};
	}


	public void setWindow(HouseholdPanel householdPanel) {
		this.window = householdPanel;
	}
}

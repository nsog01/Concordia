package presentation.controllers.appointments;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import domain.Appointments.AppointmentManager;

import presentation.views.appointments.AppointmentCheckOutPanel;

public class AppointmentCheckOutPanelController {

	AppointmentCheckOutPanel view;
	AppointmentManager appointmentManager;
	
	public AppointmentCheckOutPanelController() {
		view = new AppointmentCheckOutPanel();
		appointmentManager = AppointmentManager.getUniqueInstance();
		addScanListener();
		view.getScan().requestFocus();
	}
	
	public AppointmentCheckOutPanel getView(){
		return view;
	}
	
	private void addScanListener() {
		Action someAction = new AbstractAction() {

			private static final long serialVersionUID = 432165436543L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String scanString = view.getScan().getText();
				appointmentManager.checkOut(scanString);
				view.getScan().setText(null);
			}
		};

		InputMap input = view.getScan()
				.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		input.put(KeyStroke.getKeyStroke("ENTER"), "submit");
		view.getScan().getActionMap().put("submit", someAction);

	}
	
	public JTextField getScan(){
		return view.getScan();
	}
}

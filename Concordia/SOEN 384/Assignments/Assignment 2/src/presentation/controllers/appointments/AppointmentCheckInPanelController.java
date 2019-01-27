package presentation.controllers.appointments;

import domain.Appointments.Appointment;

import presentation.views.appointments.AppointmentCheckInPanel;

public class AppointmentCheckInPanelController {

	private AppointmentCheckInPanel view;
	
	public AppointmentCheckInPanelController() {
		view = new AppointmentCheckInPanel(null);
	}

	public AppointmentCheckInPanel getView(){
		return view;
	}

	public void setAppointment(Appointment appointment) {
		view.setAppointment(appointment);
	}
}

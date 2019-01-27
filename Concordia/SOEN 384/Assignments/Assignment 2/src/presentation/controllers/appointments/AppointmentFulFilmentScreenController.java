package presentation.controllers.appointments;


import domain.Appointments.Appointment;
import presentation.views.appointments.AppointmentFulFilmentScreen;

public class AppointmentFulFilmentScreenController {

	private AppointmentFulFilmentScreen view;
	private AppointmentCheckInPanelController appointmentCheckinPanelController;
	
	public AppointmentFulFilmentScreenController(Appointment appointment){
		appointmentCheckinPanelController = new AppointmentCheckInPanelController();
		appointmentCheckinPanelController.setAppointment(appointment);
		view = new AppointmentFulFilmentScreen(appointmentCheckinPanelController.getView());
	}
	public AppointmentFulFilmentScreen getView(){
		return view;
	}
	public void set(Appointment appointment) {
		view.set(appointment);
	}
	public AppointmentCheckInPanelController getAppointmentCheckinPanelController() {
		return appointmentCheckinPanelController;
	}
	public void setAppointmentCheckinPanelController(
			AppointmentCheckInPanelController appointmentCheckinPanelController) {
		this.appointmentCheckinPanelController = appointmentCheckinPanelController;
	}


}

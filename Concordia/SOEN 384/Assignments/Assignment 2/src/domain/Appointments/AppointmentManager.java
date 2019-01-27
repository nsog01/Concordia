package domain.Appointments;

import java.util.Calendar;

import domain.events.Event;
import domain.files.Household;
import domain.mappers.files.AppointmentMapper;

public class AppointmentManager {

	private static AppointmentManager appointmentManager;
	private AppointmentRepository repository = AppointmentRepository.getUniqueInstance();
	
	private AppointmentManager(){
		
	}
	public synchronized static AppointmentManager getUniqueInstance(){
		if (appointmentManager == null){
			appointmentManager = new AppointmentManager();
		}
		return appointmentManager;
	}
	public AppointmentList getAppointmentForEvent(Household house, Event event) {
		return repository.getAppointmentForEvent(house, event);
	}
	public void setAppointmentList(AppointmentList appointmentForEvent) {
		repository.setList(appointmentForEvent);
	}
	public AppointmentList getAppointmentList(){
		return repository.getList();
	}
	public AppointmentList getAppointmentsForEvent(Event activeEvent) {
		return repository.getAppointmentForEvent(activeEvent);
	}
	public AppointmentList getArrivalsForEvent(Event activeEvent) {
		return repository.getArrivalsForEvent(activeEvent);
	}
	public AppointmentList getAbsentForEvent(Event activeEvent) {
		return repository.getAbsentForEvent(activeEvent);
	}
	public void checkOut(String text) {
		Appointment app = AppointmentMapper.readByBadge(text);
		app.setDeparture(Calendar.getInstance().getTime());
		app.commit();
	}
	
	
}

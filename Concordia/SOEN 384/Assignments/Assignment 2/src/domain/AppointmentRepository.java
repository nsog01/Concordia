package domain.Appointments;

import domain.events.Event;
import domain.files.Household;
import domain.mappers.files.AppointmentMapper;

public class AppointmentRepository {

	private static AppointmentRepository appointmentRepository;
	private AppointmentList appointmentList;
	private AppointmentMapper mapper= new AppointmentMapper();
	private AppointmentRepository(){
		
	}
	public synchronized static AppointmentRepository getUniqueInstance(){
		if (appointmentRepository == null){
			appointmentRepository = new AppointmentRepository();
		}
		return appointmentRepository;
	}
	public AppointmentList getAppointmentForEvent(Household house, Event event) {
		return AppointmentMapper.get(house, event);
	}
	public void setList(AppointmentList appointmentForEvent) {
		appointmentList = appointmentForEvent;
	}
	public AppointmentList getList(){
		return appointmentList;
	}
	public AppointmentList getAppointmentForEvent(Event activeEvent) {
		return mapper.readAllAppointments(activeEvent);
	}
	public AppointmentList getArrivalsForEvent(Event activeEvent) {
		return mapper.readAllArrivals(activeEvent);
	}
	public AppointmentList getAbsentForEvent(Event activeEvent) {
		return mapper.readAllAbsent(activeEvent);
	}
	
	
}

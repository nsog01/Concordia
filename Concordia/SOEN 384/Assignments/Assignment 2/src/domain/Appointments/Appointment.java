package domain.Appointments;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import domain.PersistentObject;
import domain.events.Event;
import domain.events.TimeSlot;
import domain.files.Client;
import domain.files.Household;
import domain.mappers.files.AppointmentMapper;

public class Appointment extends PersistentObject{

	private Client client;
	private Event event;
	private TimeSlot timeslot;
	private int occurrence;
	private Date arrival;
	private Date departure;
	private int badgeId;
	private Household household;
	private AppointmentMapper mapper = new AppointmentMapper();
	
	public Appointment(Client client, Event event, int occurrence,
			TimeSlot timeslot, Household household, Date arrival,
			Date departure, int badgeId) {
		super();
		this.client = client;
		this.event = event;
		this.timeslot = timeslot;
		this.occurrence = occurrence;
		this.household = household;
		this.arrival = arrival;
		this.departure = departure;
		this.badgeId = badgeId;
	}
	public Appointment(int id, Client client, Event event, int occurrence,
			TimeSlot timeslot, Household household, Date arrival,
			Date departure, int badgeId) {
		super(id);
		this.client = client;
		this.event = event;
		this.timeslot = timeslot;
		this.occurrence = occurrence;
		this.household = household;
		this.arrival = arrival;
		this.departure = departure;
		this.badgeId = badgeId;
	}
	public Client getClient() {
		return client;
	}
	public int getOccurrence(){
		return occurrence;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public TimeSlot getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(TimeSlot timeslot) {
		this.timeslot = timeslot;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
		setChanged();
		notifyObservers();
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
		setChanged();
		notifyObservers();
		registerDirty();
	}
	public Household getHousehold() {
		return household;
	}
	public void setHousehold(Household household) {
		this.household = household;
	}
	public int getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
		setChanged();
		notifyObservers();
	}
	@Override
	public PersistentObject insert() {
		return mapper.insert(this);
	}
	@Override
	public void update() {
		mapper.update(this);
	}
	@Override
	public void delete() {
		
	}
	@Override
	public AppointmentMapper getMapper() {
		return mapper;
	}
	public String getArrivalString() {
		if (arrival == null){
			String arrivalString="Not Arrived Yet";
			return arrivalString;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(arrival);
		SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
		return format.format(cal.getTime());
	}

	
}

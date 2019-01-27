package domain.events;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

import domain.events.Event.Period;
import domain.mappers.events.EventMapper;


public final class EventRepository extends Observable{

	private EventList events = new EventList();
	private EventTypeList eventTypes = new EventTypeList();
	private static EventRepository repository;
	private Event activeEvent;
	
	private EventRepository() {
		eventTypes.setList();
	}
	
	public synchronized static EventRepository getUniqueInstance(){
		if(repository == null){

				repository = new EventRepository();

		}
		return repository;
	}
	
	public void readAllEvents() {
		events.setEventList(new Event().getAll());
		setChanged();
		notifyObservers();
	}
	
	public EventList getEventList() {
		if (events.isEmpty()){
			getNextEvents();
		}
		return events;
	}
	
	public Event getEvent(long l) {
		if(events.isEmpty()){
			getNextEvents();
		}
		for (Event event : events){
			if (event.getId() == l){
				return event;
			}
		}
		return null;
		/*
		No good
		Event addEvent = Event.read(id);
		events.add(addEvent);
		return addEvent;
		*/
	}
	public Event getActiveEvent() {
		return activeEvent;
	}

	public void setActiveEvent(Event activeEvent) {
		this.activeEvent = activeEvent;
	}

	/*
	 * Not used
	public Event readEvent(int id) throws SQLException{
		return Event.read(id);
	}
	*/
	public void updateEvent(Event event) {
		event.update();
		setChanged();
		notifyObservers();
	}
	public void  updateEventTimeSlots(Event event, TimeSlotList add, 
			TimeSlotList remove, TimeSlotList update) {
		event.updateTimeSlots(add,remove,update);
		setChanged();
		notifyObservers();
	}
	
	public void updateAllEvents(Event event){
		if (event.getOid() == 1){
			for (Event e : events){
				if (e.getId() == event.getId()){
					e.sync(event);
				}
			}
		}
		else {
			for (Event e : events){
				if (e.getId() == event.getId() && e.getOid() >= event.getOid()){
					e.sync(event);
				}
			}
		}
		event.updateAll();
		setChanged();
		notifyObservers();
	}
	public void removeEvent(Event event)  {
		events.remove(event);
		event.delete();
		setChanged();
		notifyObservers();
	}
	
	public void removeAllEvents(Event event) {
		ArrayList<Event> eventsToRemove = new ArrayList<Event>();
		for (Event eventToRemove : events){
			if (event.getId() == eventToRemove.getId() && event.getDate().after(eventToRemove.getDate())){
				eventsToRemove.add(eventToRemove);
			}
		}
		events.remove(event);
		for (Event eventToRemove : eventsToRemove){
			events.remove(eventToRemove);
		}
		event.deleteAll();
		setChanged();
		notifyObservers();
	}
	
	public void insertEvent(Event event){
		event.insert();
		Calendar cal = Calendar.getInstance();
		event.setOid(1);
		events.add(event);
		int count = event.getRecurCount();
		for (int i = 2; i <= count; i++){
			cal.setTime(event.getDate());
			if (event.getPeriod().equals(Period.MONTH)){
				cal.add(Calendar.MONTH, +i-1);
			}
			else if (event.getPeriod().equals(Period.YEAR)){
				cal.add(Calendar.YEAR, +i-1);
			}
			else if (event.getPeriod().equals(Period.WEEK)){
				cal.add(Calendar.DAY_OF_MONTH, +(i-1)*7);
			}
			Date newDate = new Date(cal.getTime().getTime());
			events.add(new Event(event.getId(), i, event.getTitle(), event.getEventType(), event.getDescription(),
					newDate, event.getRecurCount(), event.getPeriod(), event.getTimeSlotList()));
		}
		setChanged();
		notifyObservers();
	}
	
	public boolean containsEvent(Event event)
	{
		return events.contains(event);
	}
	
	public void clearList(){
		events.clear();
	}
	public void addEvent(Event event){
		events.add(event);
	}

	public EventList getNextEvents(){
		int lastId = 0;
		if (!events.isEmpty()){
			lastId = events.getLast().getId();
		} 
		return events.addAll(Event.readNext(lastId));
	}

	public EventList getAppointments(int id) {
		return EventMapper.readAppointments(id);
	}
	public EventType getEventType(int index){
		return eventTypes.get(index);
	}
}

package domain.events;

import java.util.Date;
import java.util.List;
import java.util.Observer;

import domain.mappers.events.EventTypeMapper;



public final class EventManager {

	private EventRepository repo;
	private static EventManager eventManager = null;
	
	public synchronized static EventManager getUniqueInstance() {
		if (eventManager == null){
			eventManager = new EventManager(EventRepository.getUniqueInstance());
		}
		return eventManager;
	}
	private EventManager(){};
	
	private EventManager(EventRepository repo) {
		this.repo = repo;
	}
	
	public void insertEvent(String name, EventType type, String description, Date date, int recurCount, 
			Event.Period period, TimeSlotList list){
			repo.insertEvent(new Event(1, recurCount, name, type, description, date, recurCount, period, list));
	}
	public EventList getNextEvents(){
		return repo.getNextEvents();
	}
	public void insertEventType(String eventType){
		new EventType(eventType).insert();
	}
	
	public List<EventType> getEventTypeList() {
		return new EventTypeMapper().getArrayList();
	}
	
	public Event getEvent(int id) {
		return repo.getEvent(id);
	}
	
	public void updateEvent(Event event){
		repo.updateEvent(event);
	}
	
	public void updateEventTimeSlots(Event event, TimeSlotList add,
			TimeSlotList remove, TimeSlotList update) 
	{
		repo.updateEventTimeSlots(event,add,remove,update);
	}
	
	public void updateAllEvents(Event event) {
		repo.updateAllEvents(event);
	}
	
	public void addObserverToRepo(Observer object){
		this.repo.addObserver(object);
	}
	
	
	public void removeEvent(Event event)  {
		repo.removeEvent(event);
	}
	public void removeAllEvents(Event event) {
		repo.removeAllEvents(event);
	}
	
	public EventList getEventList() {
		return repo.getEventList();
	}
	public EventList getAppointments(int id){
		return repo.getAppointments(id);
	}
	public EventType getEventType(int int1) {
		return repo.getEventType(int1);
	}
	public Event getActiveEvent(){
		return repo.getActiveEvent();
	}
	public void setActiveEvent(Event e){
		repo.setActiveEvent(e);
	}
} 

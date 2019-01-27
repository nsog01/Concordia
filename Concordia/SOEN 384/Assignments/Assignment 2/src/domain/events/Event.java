package domain.events;

import java.util.Date;
import java.sql.SQLException;

import domain.PersistentObject;
import domain.events.TimeSlotList;
import domain.mappers.events.EventMapper;

/**
 * @author  paulsmelser
 */
public class Event extends PersistentObject{

	private int occurrenceId;
	private String name;
	private String description;
	private EventType eventType;
	private Date date;
	private int recur_count;
	public enum Period {MONTH, WEEK, YEAR, NONE};
	private Period period;
	private TimeSlotList timeSlots;
	private EventMapper model = new EventMapper();
	
	public Event(){
		timeSlots = new TimeSlotList();
	}
	public Event(String name, EventType type, String description, 
			Date date, int recur, Period period)  {
		super();
		this.name = name;
		this.description = description;
		this.eventType = type;
		this.date = date;
		this.recur_count = recur;
		this.period = period;
		this.timeSlots = new TimeSlotList();
	}
	public Event(int id, int Oid, String name, EventType type, 
			String description, Date date, int recur, Period period){
		super(id);
		this.occurrenceId = Oid;
		this.name = name;
		this.description = description;
		this.eventType = type;
		this.date = date;
		this.recur_count = recur;
		this.period = period;
		this.timeSlots = new TimeSlotList();
	}
	
	public Event(int l, int Oid, String name, EventType type, 
			String description, Date date, int recur, Period period, TimeSlotList list){
		super(l);
		this.occurrenceId = Oid;
		this.name = name;
		this.description = description;
		this.eventType = type;
		this.date = date;
		this.recur_count = recur;
		this.period = period;
		this.timeSlots = list;
	}
	// TODO FINISH TIME SLOTS IN EVENTS
	//--------------TIME SLOTS-------------
	public void addTimeSlot(TimeSlot timeSlot){
//		timeSlot = mapTimeSlotToDate(timeSlot);
		this.timeSlots.add(timeSlot);
	}

	public void removeTimeSlot(TimeSlot timeSlot){
		this.timeSlots.remove(timeSlot);
	}
	
	public void updateTimeSlot(long l,Date start, Date end, int limit)
	{
		TimeSlot temp = this.timeSlots.get(l);
		temp.setStartTime(start);
		temp.setEndTime(end);
		temp.setLimit(limit);
	}

	//------------TIME SLOTS---------------
	public int getOid(){
		return this.occurrenceId;
	}

	public void setOid(int Oid){
		this.occurrenceId = Oid;
	}
	public int getRecurCount(){
		return this.recur_count;
	}
	
	public Period getPeriod(){
		return this.period;
	}
	public String getTitle() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setCategory(EventType type) {
		this.eventType = type;
	}

	public Event insert(){
			return (EventMapper.insert(this));
	}
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return this.date;
	}
	public EventList getAll(){
		return model.readAll();
	}
	
	public TimeSlotList getTimeSlotList(){
		return this.timeSlots;
	}
	
	public void setTimeSlotList(TimeSlotList list){
		this.timeSlots = list;
	}

	public void update(){
		EventMapper.update(this);
	}
	
	
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void updateAll() {
		EventMapper.updateAll(this);
	}
	
	public void delete() {
		EventMapper.deleteSingleOccurrence(this);
	}
	public void deleteAll() {
		EventMapper.delete(this);
	}
	public void sync(Event event){
		this.date = event.date;
		this.description = event.description;
		this.eventType = event.eventType;
		this.name = event.name;
		this.period = event.period;
		this.recur_count = event.recur_count;
		this.timeSlots = event.timeSlots;
	}
	
	public void updateTimeSlots(TimeSlotList add, TimeSlotList remove, TimeSlotList update){
		EventMapper.updateTimeSlots(this,add,remove,update);
	}
	public static EventList readNext(int lastId) {
		return EventMapper.readNext(lastId);
	}
	@Override
	public EventMapper getMapper() {
		return model;
	}
	
}

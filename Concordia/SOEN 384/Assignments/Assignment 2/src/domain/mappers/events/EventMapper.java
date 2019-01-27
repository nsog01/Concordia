package domain.mappers.events;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import data.EventExceptionTDG;
import data.EventTDG;
import data.TimeSlotTDG;
import domain.PersistentObject;
import domain.events.Event;
import domain.events.EventList;
import domain.events.EventManager;
import domain.events.TimeSlot;
import domain.events.TimeSlotList;
import domain.events.Event.Period;
import domain.mappers.Mapper;

public class EventMapper implements Mapper{

	public static Event insert(Event event) {
		try {
		event.setId(EventTDG.insert(event.getTitle(), event.getDescription(),
				event.getEventType().getId(), event.getDate(),
				event.getRecurCount(), event.getPeriod()));
		// we insert the time slots here <----- THE EVENT IS ALREADY CREATED AND
		// THE ID IS SET
		
		Iterator<TimeSlot> it = event.getTimeSlotList().iterator();
		
		while(it.hasNext()) {
			TimeSlot timeSlot = it.next();
			timeSlot.setId(TimeSlotTDG.insert(timeSlot.getStartTime(), 
					timeSlot.getEndTime(), timeSlot.getLimit(), event.getId()));
		}		
		} catch(SQLException e){
			e.printStackTrace();
		}
		return event;
	}

	public static void delete(Event event)  {
		try {
		if (event.getOid() == 1){
			EventTDG.delete(event.getId());
		} else {
//			EventTDG.updateRecurCount(event.getId(), 1);
			EventExceptionTDG.insert(event.getId(), event.getOid(), 
					event.getTitle(), event.getDescription(),
					event.getEventType().getId(), event.getDate(),
					event.getPeriod(), true);
			
		}
		//delete all time slots for that event
		Iterator<TimeSlot> it = event.getTimeSlotList().iterator();
		
		while(it.hasNext()) {
			TimeSlot timeSlot = it.next();
			TimeSlotTDG.delete(timeSlot.getId());
		}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static void deleteSingleOccurrence(Event event){
		try {
			EventExceptionTDG.insert(event.getId(), event.getOid(),
					event.getTitle(), event.getDescription(), event.getEventType()
							.getId(), event.getDate(), event.getPeriod(), true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/*
	 *Mapping does not work for the Events table only the All view
	public static Event read(int id) throws SQLException {
		ResultSet set = EventTDG.read(id);
		if(set.next())
			return map(set);
		else
			return null;
	}
*/
	public static void update(Event event) {
		if (event.getOid() == 1)
			try {
				EventTDG.update(event.getId(), event.getTitle(), event
						.getEventType().getId(), event.getDescription(), event
						.getDate());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		else {
			try {
				EventExceptionTDG.insert(event.getId(), event.getOid(), event
						.getTitle(), event.getDescription(), event.getEventType()
						.getId(), event.getDate(), event.getPeriod(), false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void updateAll(Event event){
		if (event.getOid() == 1)
			try {
				EventTDG.update(event.getId(), event.getTitle(), event
						.getEventType().getId(), event.getDescription(), event
						.getDate());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			try {
			EventTDG.updateRecurCount(event.getId(), event.getOid());
			
				EventTDG.insert(event.getTitle(), event.getDescription(), event
						.getEventType().getId(), event.getDate(),
						event.getRecurCount() - event.getOid() + 1, event
								.getPeriod());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void updateTimeSlots(Event event, TimeSlotList add,
			TimeSlotList remove, TimeSlotList update){
		//Adding
		Iterator<TimeSlot> it = add.iterator();
		
		while(it.hasNext()) {
			TimeSlot timeSlot = it.next();
			try {
				timeSlot.setId(TimeSlotTDG.insert(timeSlot.getStartTime(), 
						timeSlot.getEndTime(), timeSlot.getLimit(), event.getId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//add
			event.addTimeSlot(timeSlot);
		}
		
		//Removing
		it = remove.iterator();
		
		while(it.hasNext()) {
			TimeSlot timeSlot = it.next();
			try {
				TimeSlotTDG.delete(timeSlot.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//remove
			event.removeTimeSlot(timeSlot);
		}	
		
		//Updating
		it = update.iterator();
		
		while(it.hasNext()) {
			TimeSlot timeSlot = it.next();
			try {
				TimeSlotTDG.update(timeSlot.getId(),timeSlot.getStartTime(),
						timeSlot.getEndTime(),timeSlot.getLimit(), event.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//update
			event.updateTimeSlot(timeSlot.getId(), timeSlot.getStartTime(),
					timeSlot.getEndTime(), timeSlot.getLimit());

		}	
		
	}

	public static EventList readNext(int lastId){
		EventList list = null;
		try {
		ResultSet set = EventTDG.readNext(lastId);
		list = new EventList();
		while (set.next()) {
			Event event = map(set);
			
			//Map Timeslots
			event.setTimeSlotList(TimeSlotMapper.readAll(event));

			list.add(event);

		}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	private static Event map(ResultSet set) {
		Event event = null;
		try {
			event =  new Event(set.getInt(1), set.getInt(2), set.getString(3),
					EventManager.getUniqueInstance().getEventType(set.getInt(6)), set.getString(4),
					set.getDate(7), set.getInt(5), Period.valueOf(set.getString(8)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}

	public static EventList readAppointments(int id) {
		EventList list = null;
		try{
		list = new EventList();
		Event event = null;
		ResultSet set = EventTDG.readAppointments(id);
		while(set.next()){
			event = map(set);
			//Map Timeslots
			event.setTimeSlotList(TimeSlotMapper.readAll(event));
			list.add(event);
		}
		} catch (SQLException sql){
			sql.printStackTrace();
		}
		return list;
	}

	@Override
	public Event insert(PersistentObject event){
		try {
			((Event)event).setId(EventTDG.insert(((Event)event).getTitle(), ((Event)event).getDescription(),
					((Event)event).getEventType().getId(), ((Event)event).getDate(),
					((Event)event).getRecurCount(), ((Event)event).getPeriod()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	// we insert the time slots here <----- THE EVENT IS ALREADY CREATED AND
	// THE ID IS SET
	
	Iterator<TimeSlot> it = ((Event)event).getTimeSlotList().iterator();
	
	while(it.hasNext()) {
		TimeSlot timeSlot = it.next();
		try {
			timeSlot.setId(TimeSlotTDG.insert(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getLimit(), event.getId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		

	return ((Event)event);
	}

	@Override
	public void delete(PersistentObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventList readAll(){
		EventList list =null;
		try {
		ResultSet set = EventTDG.readAll();
		list = new EventList();
		while (set.next()) {
			Event event = map(set);
			
			//Map Timeslots
			event.setTimeSlotList(TimeSlotMapper.readAll(event));
			list.add(event);
		}
		} catch (SQLException sql){
			sql.printStackTrace();
		}
		return list;
	}

	@Override
	public Event read(int l) {
		Event event = null;
		try {
		ResultSet set = EventTDG.read(l);
		
		set.next();
		event = map(set);
		event.setTimeSlotList(TimeSlotMapper.readAll(event));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public void update(PersistentObject object){
		// TODO Auto-generated method stub
		
	}

	public static EventList getEventsForToday() {
		EventList list = new EventList();
		try {
			ResultSet set= EventTDG.getEventsForToday();
			while(set.next()){
				Event event = map(set);
				
				//Map Timeslots
				event.setTimeSlotList(TimeSlotMapper.readAll(event));
				list.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}

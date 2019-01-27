package domain.events;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import domain.IList;
import domain.mappers.events.EventTypeMapper;



public class EventTypeList implements Observer, IList, Iterable<EventType>{
	private List<EventType> eventList;
	private EventTypeMapper model = new EventTypeMapper();
	
	public EventTypeList(){
		eventList = new ArrayList<EventType>();
	}
	
	public void setList(){
		eventList = model.getArrayList();
	}
	public void setEventTypes(List<EventType> list){
		eventList = list;
	}
	
	public EventType get(int id){
		for (EventType type : eventList){
			if (type.getId() == id){
				return type;
			}
		}
		return null;
	}
	public void update() {
		eventList = model.getArrayList();
	}
	public int size(){
		return eventList.size();
	}
	public boolean isEmpty(){
		return eventList.isEmpty();
	}
	@Override
	public void update(Observable o, Object arg) {
			setEventTypes(model.getArrayList());

	}

	@Override
	public void add(Object obj) {
		eventList.add((EventType) obj);
	}

	@Override
	public void clear() {
		eventList.clear();
	}

	@Override
	public void remove(Object event) {
		eventList.remove((EventType) event);
	}

	@Override
	public boolean contains(Object app) {
		return eventList.contains((EventType) app);
	}

	@Override
	public Iterator<EventType> iterator() {
		return eventList.iterator();
	}

	public EventTypeList readAll() {
		setEventTypes(model.getArrayList());
		return this;
	}

	
}

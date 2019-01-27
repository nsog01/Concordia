package domain.events;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import domain.IList;

public class EventList implements Iterable<Event>, Observer, IList {
	private List<Event> eventList;
	private EventManager eventManager;
	
	public EventList(EventManager control) {
		this.eventManager = control;
		eventList = new ArrayList<Event>();
		eventManager.getEventList().get(0);
		setEventList(this.eventManager.getEventList());
	}
	public EventList() {
		eventList = new ArrayList<Event>();
	}
	public Iterator<Event> iterator(){
		return eventList.iterator();
	}
	
	public void add(Event event){
		eventList.add(event);
	}
	public Event get(int id){
		return eventList.get(id);
	}
	public int size(){
		return getEventList().size();
	}
	public boolean isEmpty(){
		return getEventList().isEmpty();
	}
	protected List<Event> getList(){
		return this.getEventList();
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public final void setEventList(EventList eventList) {
		clear();
		for (Event e : eventList){
			add(e);
		}
	}
	
	public void clear(){
		eventList.clear();
	}
	public void remove(Event event){
		eventList.remove(event);
	}
	public Event getLast(){
		if (!eventList.isEmpty())
			return eventList.get(eventList.size()-1);
		return null;
	}
	public boolean contains(Event event){
		return eventList.contains(event);
	}
	public EventList addAll(EventList events) {
		for(Event e : events){
			eventList.add(e);
		}
		return events;
	}
	@Override
	public void update(Observable o, Object arg) {
			setEventList(EventManager.getUniqueInstance().getEventList());

	}
	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remove(Object event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(Object app) {
		// TODO Auto-generated method stub
		return false;
	}
}

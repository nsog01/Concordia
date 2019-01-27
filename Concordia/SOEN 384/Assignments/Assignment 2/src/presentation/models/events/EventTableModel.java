package presentation.models.events;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.events.Event;
import domain.events.EventList;
import domain.events.EventManager;


public class EventTableModel extends EventList implements TableModel{
	

	private String[] header = {" ", "Name", "Description", "Type", "Date", "Recurrences"};
	protected List<TableModelListener> observers = new ArrayList<TableModelListener>();
	
	public EventTableModel(EventManager manager) {
		super(manager);
		manager.addObserverToRepo(this);
		for (TableModelListener anObserver: observers)
			anObserver.tableChanged(new TableModelEvent(this));
	}
	public EventTableModel() {
		super();
	}
	
	public EventTableModel(EventList list) {
		super();
		setList(list);
		for (TableModelListener anObserver: observers)
			anObserver.tableChanged(new TableModelEvent(this));
	}
	public void addToList(EventList eventList){
		for(Event e : eventList){
			add(e);
		}
			for (TableModelListener l : observers){
				l.tableChanged(new TableModelEvent(this));
			}
	}
	public void setList(EventList list){
		clear();
		for (Event e : list){
			add(e);
		}
			for (TableModelListener l : observers){
				l.tableChanged(new TableModelEvent(this));
		}
	}
	

	@Override
	public void addTableModelListener(TableModelListener aListener) {
		observers.add(aListener);		
	}
	
	@Override
	public void removeTableModelListener(TableModelListener aListener) {
		observers.remove(aListener);
	}

	@Override
	public Class<?> getColumnClass(int c) {
		switch (c) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return Date.class;
		case 5:
			return String.class;
		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public int getRowCount() {
		return size();
	}
	
	@Override
	public String getColumnName(int col) {
		return header[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(isEmpty())
			return null;
		
		//Event event = this.get(getKey(row));
		Event event = this.getEventList().get(row);
		
		switch (col) {
		case 0:
			return row;
		case 1:
			return event.getTitle();
		case 2:
			return event.getDescription();
		case 3: 
				return event.getEventType().getName();
		case 4:
			return event.getDate();
		case 5:
			return event.getPeriod().toString();
		default:
			return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 5:
			return false;
		case 0: 
			return false;
		case 1: 
			return false;
		case 2: 
			return false;
		case 3:
			return false;
		case 4:
			return false;
		default:
			return false;
		}
	}
	public Event getSelected(int selectedRow) {
		return get(selectedRow);
	}
//	public Event get(int id){
//		return eventList.get(id);
//	}
//	public void add(Event event){
//		eventList.add(event);
//		for (TableModelListener anObserver: observers)
//			anObserver.tableChanged(new TableModelEvent(this));
//	}
//	public void clear(){
//		eventList.clear();
//	}
//	public boolean isEmpty(){
//		return eventList.isEmpty();
//	}
//	public int size(){
//		return eventList.size();
//	}
//	public EventList getEventList(){
//		return eventList;
//	}
	@Override
	public void update(Observable arg0, Object arg1) {
		setList(EventManager.getUniqueInstance().getEventList());
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


}

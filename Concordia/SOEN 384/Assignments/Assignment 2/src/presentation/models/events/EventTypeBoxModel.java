package presentation.models.events;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import domain.events.EventManager;
import domain.events.EventType;

public class EventTypeBoxModel implements ComboBoxModel {

	private ArrayList<EventType> list;
	private EventType selectedItem;
	EventManager eventManager;
	
	public EventTypeBoxModel() {
		 eventManager = EventManager.getUniqueInstance();
		list = (ArrayList<EventType>) eventManager.getEventTypeList();
		selectedItem = list.get(0);
	}
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getElementAt(int arg0) {
		return list.get(arg0).getName();
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}
	
	public EventType getSelected(){
		return selectedItem;
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem.getName();
	}

	@Override
	public void setSelectedItem(Object arg0) {
		for (EventType item : list){
			if(item.getName().equals((String) arg0)){
				this.selectedItem = item;
			}
		}
	}
	public void setSelectedType(long l){
		for (EventType item : list){
			if(item.getId() == l){
				this.selectedItem = item;
			}
		}
	}

}

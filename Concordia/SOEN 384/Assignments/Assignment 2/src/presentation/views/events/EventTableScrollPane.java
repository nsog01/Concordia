package presentation.views.events;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.models.events.EventTableModel;
import domain.events.Event;
import domain.events.EventList;

public class EventTableScrollPane extends JScrollPane{

	EventTable eventTable; 

	private static final long serialVersionUID = 784903215623L;
	private String eventTableBorderText="Events";

	private EventTableScrollPane(EventTable eventTable){
		super(eventTable);
		this.eventTable = eventTable;
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				eventTableBorderText));
	}
	public EventTableScrollPane(){
		super();
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				eventTableBorderText));
	}
	public static EventTableScrollPane getNewInstance(EventList eventList){
		EventTable eventTable = new EventTable(new EventTableModel());
		eventTable.setList(eventList);
		return new EventTableScrollPane(eventTable);
		
	}
	public EventTable getTable(){
		return eventTable;
	}
	public Event getEvent(int index) {
		return eventTable.getEvent(index);
	}
}

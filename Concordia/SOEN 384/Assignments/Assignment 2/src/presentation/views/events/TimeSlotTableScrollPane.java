package presentation.views.events;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.models.events.TimeSlotTableModel;
import domain.events.Event;
import domain.events.EventManager;
import domain.events.TimeSlot;

public class TimeSlotTableScrollPane extends JScrollPane{


	private static final long serialVersionUID = 743890543256L;
	private TimeSlotTable timeSlotTable;

	private TimeSlotTableScrollPane(TimeSlotTable timeSlotTable){
		super(timeSlotTable);
		this.timeSlotTable = timeSlotTable;
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		String timeSlotTableBorderText="Appointment Times";
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				timeSlotTableBorderText));
	}
	public static TimeSlotTableScrollPane getNewInstance(Event event){
		TimeSlotTable timeSlotTable = new TimeSlotTable(new TimeSlotTableModel(event));
		return new TimeSlotTableScrollPane(timeSlotTable);
		
	}
	public TimeSlotTable getTable(){
		return timeSlotTable;
	}


	public static void main(String[] args){
		javax.swing.JFrame frame = new javax.swing.JFrame();
		frame.add(TimeSlotTableScrollPane.getNewInstance(EventManager.getUniqueInstance().getEventList().getLast()));
		frame.pack();
		frame.setVisible(true);
	}
	public TimeSlot getTimeslot(int index) {
		return timeSlotTable.getTimeSlot(index);
	}
}

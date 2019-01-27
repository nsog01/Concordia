package presentation.controllers.appointments;

import java.awt.event.MouseListener;

import javax.swing.JScrollPane;

import domain.events.Event;
import presentation.models.events.TimeSlotTableModel;
import presentation.views.appointments.TimeSlotTablePanel;
import presentation.views.events.TimeSlotTable;

public class TimeSlotTablePanelController {

	private TimeSlotTable timeSlotTable;
	private TimeSlotTableModel timeSlotTableModel;
	private TimeSlotTablePanel view;
	private JScrollPane sPane;
	
	TimeSlotTablePanelController(){
		view = new TimeSlotTablePanel();
		timeSlotTableModel = new TimeSlotTableModel(new Event());
		timeSlotTable = new TimeSlotTable(timeSlotTableModel);
		sPane = new JScrollPane(timeSlotTable);
		sPane.setPreferredSize(view.getMaximumSize());
		view.add(sPane, "center");
	}
	
	public void set(Event event){
		timeSlotTable.setList(event);
	}
	
	public TimeSlotTablePanel getView(){
		return view;
	}

	public TimeSlotTable getTable() {
		return timeSlotTable;
	}

	public void addMouseListenerToTable(MouseListener selectTimeSlot) {
		timeSlotTable.addMouseListener(selectTimeSlot);
	}
}

package presentation.controllers.appointments;

import java.awt.event.MouseListener;

import net.miginfocom.swing.MigLayout;
import domain.files.FileManager;
import presentation.views.appointments.EventSelectionPanel;
import presentation.views.events.EventTable;

public class EventSelectionPanelController {

	private EventSelectionPanel view;
	private FileManager fileManager;
	
	public EventSelectionPanelController(){
		fileManager = FileManager.getUniqueInstance();
		view = new EventSelectionPanel();
		view.setLayout(new MigLayout( "", "[]","[][]"));
		view.set(fileManager.getEventsForToday());	
	}
	
	public EventSelectionPanel getView(){
		return view;
	}

	public EventTable getTable() {
		return view.getTable();
	}

	public void addMouseListenerToTable(MouseListener selectEvent) {
		view.getTable().addMouseListener(selectEvent);		
	}
}

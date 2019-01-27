package presentation.views.appointments;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;


import domain.events.EventList;

import presentation.models.events.EventTableModel;
import presentation.views.events.EventTable;

public class EventSelectionPanel extends JPanel{

	private static final long serialVersionUID = 7849032146L;

	private EventTable eventTable;
	private JScrollPane sPane;
	private JLabel instructions;
	
	public EventSelectionPanel(){
		setLayout(new MigLayout("", "", "[200][]"));
		String eventSelectInstructionsText="Please select an event from the list below";
		instructions = new JLabel(eventSelectInstructionsText);
		instructions.setHorizontalAlignment(JLabel.CENTER);
		instructions.setFont(new Font("", Font.BOLD, 15));
	}
	public void set(EventList eventList){
		eventTable = new EventTable(new EventTableModel(eventList));
		sPane = new JScrollPane(eventTable);
		sPane.setPreferredSize(getMaximumSize());
		add(instructions, "wrap, center");
		add(sPane);
	}
	public EventTable getTable() {
		return eventTable;
	}
}

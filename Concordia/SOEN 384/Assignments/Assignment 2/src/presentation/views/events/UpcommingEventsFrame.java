package presentation.views.events;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import domain.events.EventList;
import domain.events.EventManager;
import domain.files.Household;

public class UpcommingEventsFrame extends JFrame{

	EventManager manager;
	EventList eventList;
	EventTableScrollPane eventTableScrollPane;
	JButton appointment;
	
	private static final long serialVersionUID = 748920423156L;
	private String upcomingEventAptButtonText="Make Appointment";
	private String upcomingEventTitleText="Upcoming Events";

	public UpcommingEventsFrame() throws SQLException, IOException{
		manager = EventManager.getUniqueInstance();
		eventList = manager.getNextEvents();
		setLayout(new MigLayout());
		eventTableScrollPane = EventTableScrollPane.getNewInstance(eventList);
		appointment = new JButton(upcomingEventAptButtonText);
		setTitle(upcomingEventTitleText);
		setVisible(true);
	}

	public UpcommingEventsFrame(Household house) throws SQLException, IOException {
		manager = EventManager.getUniqueInstance();
		eventList = manager.getNextEvents();
		setLayout(new MigLayout());
		eventTableScrollPane = EventTableScrollPane.getNewInstance(eventList);
		appointment = new JButton(upcomingEventAptButtonText);
		setTitle(upcomingEventTitleText);
		setVisible(true);
	}
}

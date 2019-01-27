package presentation.controllers.appointments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JTextField;

import domain.Appointments.Appointment;
import domain.events.Event;
import domain.events.TimeSlot;
import domain.files.Client;
import domain.files.FileManager;

import presentation.views.appointments.AppointmentCreationFrame;
import presentation.views.events.EventTableScrollPane;
import presentation.views.events.TimeSlotTableScrollPane;

public class AppointmentCreationFrameController {
	private EventTableScrollPane eventTableScrollPane;
	private TimeSlotTableScrollPane timeSlotTableScrollPane;
	private JTextField selection;
	private JButton submit;
	private FileManager fileManager;
	AppointmentCreationFrame view;

	public AppointmentCreationFrameController(AppointmentCreationFrame frame) {
		view = frame;
		fileManager = FileManager.getUniqueInstance();
	}
	public void setListeners(Client client){
		view.getEventTableScrollPane().getTable().addMouseListener(selectedEvent(client));
		view.getSelection().addActionListener(selectEvent(client));
	}

	public EventTableScrollPane getEventTableScrollPane() {
		return eventTableScrollPane;
	}

	public void setEventTableScrollPane(
			EventTableScrollPane eventTableScrollPane) {
		this.eventTableScrollPane = eventTableScrollPane;
	}

	public TimeSlotTableScrollPane getTimeSlotTableScrollPane() {
		return timeSlotTableScrollPane;
	}

	public void setTimeSlotTableScrollPane(
			TimeSlotTableScrollPane timeSlotTableScrollPane) {
		this.timeSlotTableScrollPane = timeSlotTableScrollPane;
	}

	public JTextField getSelection() {
		return selection;
	}

	public void setSelection(JTextField selection) {
		this.selection = selection;
	}

	public JButton getSubmit() {
		return submit;
	}

	public void setSubmit(JButton submit) {
		this.submit = submit;
	}

	public void addSubmitAction(ActionListener action) {
		submit.addActionListener(action);
	}

	public void addSelectAction(MouseListener mouse) {
		eventTableScrollPane.getTable().addMouseListener(mouse);
	}
	
	public MouseListener selectedEvent(final Client client) {
		return new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					Event event = view
							.getEventTableScrollPane().getTable().getSelected();
					view.remove(view
							.getEventTableScrollPane());
					view
							.setTimeSlotTableScrollPane(TimeSlotTableScrollPane
									.getNewInstance(event));
					view.getTimeSlotTableScrollPane()
							.getTable()
							.addMouseListener(selectedTimeSlot(event, client));
					view.add(view
							.getTimeSlotTableScrollPane(), "w 800");
					view.getSelection().setText(null);
					view.addSubmitAction(selectTimeSlot(
							event, client));
					view.pack();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

		};
	}
	public ActionListener selectEvent(final Client client) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventTableScrollPane eventTableScrollPane = view
						.getEventTableScrollPane();
				view.remove(eventTableScrollPane);
				Event event = eventTableScrollPane.getEvent(Integer
						.parseInt((view.getSelection()
								.getText())));
				view.setTimeSlotTableScrollPane(TimeSlotTableScrollPane
								.getNewInstance(event));
				view.getTimeSlotTableScrollPane()
						.getTable()
						.addMouseListener(selectedTimeSlot(event, client));
				view.add(
						view.getTimeSlotTableScrollPane(),
						"w 800");
				view.getSelection().setText(null);
				view.addSubmitAction(selectTimeSlot(event,
						client));
				view.pack();
			}

		};
	}
	
	
	public MouseListener selectedTimeSlot(final Event event, final Client client) {
		return new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					TimeSlot timeslot = view
							.getTimeSlotTableScrollPane().getTable()
							.getSelected();
					Appointment appointment = new Appointment(client, event,
							event.getOid(), timeslot,
							fileManager.getActiveFile(), null, null, 0);

					client.addAppointment(appointment);
					appointment.commit();
					client.commit();

					view.dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

		};
	}
	public ActionListener selectTimeSlot(final Event event, final Client client) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TimeSlot timeslot = view
						.getTimeSlotTableScrollPane().getTimeslot(
								Integer.parseInt((view
										.getSelection().getText())));
				Appointment appointment = new Appointment(fileManager
						.getActiveFile().getRepresentative(), event,
						event.getOid(), timeslot, fileManager.getActiveFile(),
						null, null, 0);

				client.addAppointment(appointment);
				appointment.insert();
				fileManager.getActiveFile().update();

				view.dispose();
			}

		};
	}
	
}

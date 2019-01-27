package presentation.controllers.appointments;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import domain.Appointments.Appointment;
import domain.Appointments.AppointmentList;
import domain.events.Event;
import domain.events.TimeSlot;

import presentation.models.appointments.DetailedAppointmentTableModel;
import presentation.views.appointments.AppointmentListPanel;
import presentation.views.appointments.AppointmentWindow;

public class AppointmentWindowController {

	AppointmentWindow view;
	Event event;
	AppointmentListPanelController allAppointmentListPanelController;
	AppointmentFulFilmentScreenController appointmentFulFilmentScreenController;
	EventSelectionPanelController eventSelectionPanelController;
	TimeSlotTablePanelController timeSlotTablePanelController;
	AppointmentCheckOutPanelController appointmentCheckOutPanelController;

	String prevSearch = "";

	public AppointmentWindowController() {
		initialize();
		addListeners();

		view = new AppointmentWindow();
		view.addTab("Events", eventSelectionPanelController.getView());
		view.addTab("TimeSlots", timeSlotTablePanelController.getView());
		view.addTab("Appointments", allAppointmentListPanelController.getView());
		view.addTab("Check-In", appointmentFulFilmentScreenController.getView());
		view.addTab("Check-Out", appointmentCheckOutPanelController.getView());

	}

	private void initialize() {
		event = null;
		allAppointmentListPanelController = new AppointmentListPanelController(
				new DetailedAppointmentTableModel(new AppointmentList()));
//		appointmentCheckInPanelController = new AppointmentCheckInPanelController();
		appointmentFulFilmentScreenController = new AppointmentFulFilmentScreenController(null);
		eventSelectionPanelController = new EventSelectionPanelController();
		timeSlotTablePanelController = new TimeSlotTablePanelController();
		appointmentCheckOutPanelController = new AppointmentCheckOutPanelController();

	}

	private void addListeners() {
		allAppointmentListPanelController.getView()
				.addKeyListenerToSearchField(search(allAppointmentListPanelController.getView()));
		allAppointmentListPanelController.getView()
				.addMouseListenerToTable(selectAppointment(allAppointmentListPanelController.getView()));
		eventSelectionPanelController.addMouseListenerToTable(selectEvent());
		timeSlotTablePanelController.addMouseListenerToTable(selectTimeSlot());
	}

	public void setAppointmentFulFillmentScreen(Appointment a) {
		appointmentFulFilmentScreenController.set(a);
	}

	public AppointmentWindow getView() {
		return view;
	}

	private void addScanListener(final Appointment appointment) {
		JTextField scan = appointmentFulFilmentScreenController.
				getAppointmentCheckinPanelController().getView().
				getScanPanel().getScan();
		Action someAction = new AbstractAction() {

			private static final long serialVersionUID = 432165436543L;

			@Override
			public void actionPerformed(ActionEvent e) {
				appointment.setArrival(Calendar.getInstance().getTime());
				appointment.setBadgeId(Integer
						.parseInt(appointmentFulFilmentScreenController.
								getAppointmentCheckinPanelController().getView().getScanText()));
				appointmentFulFilmentScreenController.getAppointmentCheckinPanelController().getView()
						.setInfoText(
								"<html><center>Check-In "
										+ appointment.getArrivalString()
										+ "</center><html>");
				appointment.update();
				appointmentFulFilmentScreenController.getAppointmentCheckinPanelController().getView().getScanJTextField().setEnabled(false);
				view.getTabPane().setSelectedIndex(2);
			}
		};

		InputMap input = scan
				.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		input.put(KeyStroke.getKeyStroke("ENTER"), "submit");
		scan.getActionMap().put("submit", someAction);

	}

	private KeyListener search(final AppointmentListPanel appointmentListPanel) {
		return new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				String search = appointmentListPanel.getSearchText();
				int searchLength = search.length();
				int prevLength = prevSearch.length();
				if (searchLength < prevLength) {
						((DetailedAppointmentTableModel) appointmentListPanel
								.getTableModel()).backspaceSearch(search);
				} else {

						((DetailedAppointmentTableModel) appointmentListPanel
								.getTableModel()).search(search);
				}
				prevSearch = search;
			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

		};
	}

	private MouseListener selectAppointment(
			final AppointmentListPanel appointmentListPanel) {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Appointment appointment = appointmentListPanel.getTable().
							getSelected(appointmentListPanel.getTable().
									convertRowIndexToModel(appointmentListPanel.getTable()
											.getSelectedRow()));
					appointmentFulFilmentScreenController.getAppointmentCheckinPanelController().
						getView().getScanPanel().set(appointment);
					addScanListener(appointment);
					appointmentListPanel.getSearch().setText(null);
					view.getTabPane().setSelectedIndex(3);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		};
	}

	private MouseListener selectEvent() {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2) {
					event = eventSelectionPanelController.getTable()
							.getSelected();
					allAppointmentListPanelController.setEvent(event);
					timeSlotTablePanelController.set(event);
					view.getTabPane().setSelectedIndex(1);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		};
	}

	private MouseListener selectTimeSlot() {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2) {
					TimeSlot timeSlot = timeSlotTablePanelController.getTable()
							.getSelected();
					AppointmentList list = timeSlot.getAppointments();
					allAppointmentListPanelController.getView().set(list);
					view.getTabPane().setSelectedIndex(2);
					timeSlot = null;
					list = null;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		};
	}
}

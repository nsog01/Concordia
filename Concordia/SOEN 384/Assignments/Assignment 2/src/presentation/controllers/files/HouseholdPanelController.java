package presentation.controllers.files;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.models.appointments.AppointmentTableModel;
import presentation.views.appointments.AppointmentCreationFrame;
import presentation.views.appointments.AppointmentsTable;
import presentation.views.events.EventTable;
import presentation.views.files.DependentsPanel;
import presentation.views.files.FileListTableScrollPane;
import presentation.views.files.HouseholdInfoPanel;
import presentation.views.files.HouseholdNotesArea;
import presentation.views.files.HouseholdPanel;
import domain.Appointments.AppointmentList;
import domain.files.ClientList;
import domain.files.FileList;
import domain.files.FileManager;
import domain.files.Household;

public class HouseholdPanelController {

	private HouseholdInfoPanel householdInfoPanel;
	private DependentsPanel dependentsPanel;
	private HouseholdPanel view;
	private FileListTableScrollPane fileListTableScrollPane;
	private FileManager fileManager = FileManager.getUniqueInstance();
	private HouseholdNotesAreaController householdNotesAreaController;
	private EventTable eventTable;

	private enum Table {
		file, event, appointment
	};

	private Table table;
	private Dimension screenSize;
	private AppointmentsTable appointmentsTable;
	private JScrollPane appointmentTableScrollPane;

	public HouseholdPanelController(HouseholdPanel window) {
		this.view = window;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	}

	public HouseholdPanel getHouseholdWindow() {
		return this.view;
	}

	public Dimension getScreenSize() {
		return screenSize;
	}

	public HouseholdInfoPanel getHouseholdInfoPanel() {
		return householdInfoPanel;
	}

	public void setHouseholdInfoPanel(HouseholdInfoPanel householdPanel) {
		this.householdInfoPanel = householdPanel;
		this.householdInfoPanel.AddSearchListener(search());
	}

	public HouseholdNotesArea getHouseholdNotesArea() {
		return householdNotesAreaController.getView();
	}

	public EventTable getEventTable() {
		return eventTable;
	}

	public void setHouseholdNotesArea(Household house) {
		if (house != null)
			this.householdNotesAreaController = new HouseholdNotesAreaController(house);
		else
			this.householdNotesAreaController = new HouseholdNotesAreaController();
	}

	public DependentsPanel getDependentsPanel() {
		return dependentsPanel;
	}

	public void setDependentsPanel(ClientList clientList) {
		if (dependentsPanel == null) {
			dependentsPanel = new DependentsPanel();
		} else {
			this.dependentsPanel.set(clientList);
		}
	}

	public void setFileListTable() {
		fileListTableScrollPane = FileListTableScrollPane
				.getNewInstance(new FileList());
		fileListShowing();
	}

	public void setFileListTable(FileList fileList) {
		if (fileList == null) {
			fileListTableScrollPane.getTable().clear();
		} else {
			fileListTableScrollPane.getTable().setList(fileList);
			fileListTableScrollPane.getTable().addMouseListener(selectedFile());
		}
	}

	public void setAppointmentTable(AppointmentList list) {
		if (appointmentsTable == null) {
			appointmentsTable = new AppointmentsTable(
					new AppointmentTableModel(list));
			appointmentTableScrollPane = new JScrollPane(appointmentsTable);
			Border loweredetched = BorderFactory
					.createEtchedBorder(EtchedBorder.LOWERED);
			String appointmentsTitledBorderText="Appointments";
			appointmentTableScrollPane.setBorder(BorderFactory
					.createTitledBorder(loweredetched,appointmentsTitledBorderText ));
		} else {
			appointmentsTable.setList(list);

		}

	}

	public JScrollPane getFileTableScrollPane() {
		return this.fileListTableScrollPane;
	}

	public void fileListShowing() {
		table = Table.file;
	}

	public void EventListShowing() {
		table = Table.event;
	}

	public void appointmentListShowing() {
		table = Table.appointment;
	}

	public boolean isFileListShowing() {
		return (table == Table.file);
	}

	public boolean isEventListShowing() {
		return (table == Table.event);
	}

	public boolean isAppointmentListShowing() {
		return (table == Table.appointment);
	}

	private ActionListener search() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String parameterHouseholWarningText="Could not find a Household with your parameters!";
				String search = householdInfoPanel.getSearchField().getText();
				try {
					if (Household.isFileNumber(search)) {
						fileManager.findHouse(Integer.parseInt(search));
						if (fileManager.getActiveFile() != null) {
							householdInfoPanel.reset();
							swapToAppointmentList(fileManager.getActiveFile()
									.getAppointments());
							view.reset(fileManager.getActiveFile());
							householdInfoPanel.setWarning(null);
							householdInfoPanel.reset();
						} else {
							householdInfoPanel
									.setWarning(parameterHouseholWarningText);
						}
					} else {
						FileList fileList = fileManager
								.searchByAnything(search);
						if (fileList.isEmpty()) {
							householdInfoPanel
									.setWarning(parameterHouseholWarningText);
						} else {
							swapToFileList(fileList);
							householdInfoPanel.setWarning(null);
						}
					}
					view.getRootPane().setDefaultButton(null);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		};
	}

	private MouseListener selectedFile() {
		return new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					fileManager.setActiveFile(fileListTableScrollPane
							.getTable().getSelected());
					view.reset(fileManager.getActiveFile());
					swapToAppointmentList(fileManager.getActiveFile()
							.getAppointments());
					householdInfoPanel.setWarning(null);
					householdNotesAreaController.getView().getNewButton().setEnabled(true);
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


	public MouseListener noteListerer() {
		return new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				view.getRootPane().setDefaultButton(
						householdNotesAreaController.getSaveButton());
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

		};
	}

	private void swapToFileList(FileList fileList) {
		if (isFileListShowing()) {
			setFileListTable(fileList);
		} else {
			view.removeFromBottomPanel(appointmentTableScrollPane);
			setFileListTable(fileList);
			view.addToBottomPanel(fileListTableScrollPane, "h "
					+ screenSize.height / 2 + ", grow, cell 0 0");
		}
		fileListShowing();
	}

	public void swapToAppointmentList(AppointmentList list) {
		if (isAppointmentListShowing()) {
			setAppointmentTable(list);
		} else {
			view.removeFromBottomPanel(fileListTableScrollPane);
			setAppointmentTable(list);
			view.addToBottomPanel(appointmentTableScrollPane, "h "
					+ screenSize.height / 2 + ", grow, cell 0 0");
		}
		appointmentListShowing();

	}

	public AppointmentsTable getAppointmentTable() {
		return appointmentsTable;
	}

//	public ActionListener openAppointmentCreationFrame(final Client client) {
//		return new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				appointmentCreationFrame = new AppointmentCreationFrame(client);
//				appointmentCreationFrame.addSubmitAction(selectEvent(client));
//				appointmentCreationFrame.addSelectAction(selectedEvent(client));
//			}
//
//		};
//	}

	// ------------------

//	public ActionListener selectEvent(final Client client) {
//		return new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				EventTableScrollPane eventTableScrollPane = appointmentCreationFrame
//						.getEventTableScrollPane();
//				appointmentCreationFrame.remove(eventTableScrollPane);
//				Event event = eventTableScrollPane.getEvent(Integer
//						.parseInt((appointmentCreationFrame.getSelection()
//								.getText())));
//				appointmentCreationFrame
//						.setTimeSlotTableScrollPane(TimeSlotTableScrollPane
//								.getNewInstance(event));
//				appointmentCreationFrame.getTimeSlotTableScrollPane()
//						.getTable()
//						.addMouseListener(selectedTimeSlot(event, client));
//				appointmentCreationFrame.add(
//						appointmentCreationFrame.getTimeSlotTableScrollPane(),
//						"w 800");
//				appointmentCreationFrame.getSelection().setText(null);
//				appointmentCreationFrame.addSubmitAction(selectTimeSlot(event,
//						client));
//				appointmentCreationFrame.pack();
//			}
//
//		};
//	}

//	public ActionListener selectTimeSlot(final Event event, final Client client) {
//		return new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				TimeSlot timeslot = appointmentCreationFrame
//						.getTimeSlotTableScrollPane().getTimeslot(
//								Integer.parseInt((appointmentCreationFrame
//										.getSelection().getText())));
//				Appointment appointment = new Appointment(fileManager
//						.getActiveFile().getRepresentative(), event,
//						event.getOid(), timeslot, fileManager.getActiveFile(),
//						null, null, 0);
//
//				client.addAppointment(appointment);
//				appointment.insert();
//				fileManager.getActiveFile().update();
//
//				appointmentCreationFrame.dispose();
//			}
//
//		};
//	}

//	public MouseListener selectedEvent(final Client client) {
//		return new MouseListener() {
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				if (arg0.getClickCount() == 2) {
//					Event event = appointmentCreationFrame
//							.getEventTableScrollPane().getTable().getSelected();
//					appointmentCreationFrame.remove(appointmentCreationFrame
//							.getEventTableScrollPane());
//					appointmentCreationFrame
//							.setTimeSlotTableScrollPane(TimeSlotTableScrollPane
//									.getNewInstance(event));
//					appointmentCreationFrame.getTimeSlotTableScrollPane()
//							.getTable()
//							.addMouseListener(selectedTimeSlot(event, client));
//					appointmentCreationFrame.add(appointmentCreationFrame
//							.getTimeSlotTableScrollPane(), "w 800");
//					appointmentCreationFrame.getSelection().setText(null);
//					appointmentCreationFrame.addSubmitAction(selectTimeSlot(
//							event, client));
//					appointmentCreationFrame.pack();
//				}
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//
//			}
//
//		};
//	}

//	public MouseListener selectedTimeSlot(final Event event, final Client client) {
//		return new MouseListener() {
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				if (arg0.getClickCount() == 2) {
//					TimeSlot timeslot = appointmentCreationFrame
//							.getTimeSlotTableScrollPane().getTable()
//							.getSelected();
//					Appointment appointment = new Appointment(fileManager
//							.getActiveFile().getRepresentative(), event,
//							event.getOid(), timeslot,
//							fileManager.getActiveFile(), null, null, 0);
//
//					client.addAppointment(appointment);
//					appointment.insert();
//					fileManager.getActiveFile().update();
//
//					appointmentCreationFrame.dispose();
//				}
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//
//			}
//
//		};
//	}

	public KeyListener searchFieldListener(final JButton button) {
		return new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				view.getRootPane().setDefaultButton(button);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

		};
	}

	public HouseholdNotesAreaController getHouseholdNotesAreaController() {
		return householdNotesAreaController;
	}

}

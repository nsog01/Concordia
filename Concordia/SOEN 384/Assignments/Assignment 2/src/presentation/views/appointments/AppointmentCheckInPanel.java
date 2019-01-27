package presentation.views.appointments;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import domain.Appointments.Appointment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import presentation.views.files.ClientImagePanel;

public class AppointmentCheckInPanel extends JPanel{


	private static final long serialVersionUID = 6789023673L;
	private Appointment appointment;
	private ClientImagePanel clientImagePanel;
	private ScanPanel scan;
	private Dimension screenSize;
	private JLabel eventTitle;
	private JLabel eventDate;
	private JLabel eventStart;
	private JLabel eventEnd;
	private JLabel eventTitleLabel;
	private JLabel eventDateLabel;
	private JLabel eventStartLabel;
	private JLabel eventEndLabel;
	
	public AppointmentCheckInPanel(Appointment a){
		if (appointment != null){
			setAppointment(a);
		} else {
			setAppointment(null);
		}
		init();
		addComponents();
		}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	private void init(){
		String checkInBorderText="Check-In";
		setBorder(new TitledBorder(checkInBorderText));
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (appointment != null){
			scan = new ScanPanel(appointment);
			clientImagePanel = new ClientImagePanel(appointment.getClient());
		} else {
			scan = new ScanPanel(null);
			clientImagePanel = new ClientImagePanel();
		}
		setLayout(new MigLayout("", "40["+screenSize.width*.4+", center]40", ""));
		
		String eventTitleText="Event Title:";
		String eventDateText="Date: ";
		String eventStartText="Start time: ";
		String eventEndText="End time: ";
		
		eventTitleLabel = new JLabel(eventTitleText);
		eventDateLabel = new JLabel(eventDateText);
		eventStartLabel = new JLabel(eventStartText);
		eventEndLabel = new JLabel(eventEndText);
	}
	private void addComponents(){
		if (appointment != null){
			SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
			eventDate = new JLabel(format.format(appointment.getEvent().getDate()));
			eventTitle = new JLabel(appointment.getEvent().getTitle());
			format = new SimpleDateFormat("hh:mm");
			eventStart = new JLabel(format.format(appointment.getTimeslot().getStartTime()));
			eventEnd = new JLabel(format.format(appointment.getTimeslot().getEndTime()));
		} else {
			eventTitle = new JLabel();
			eventDate = new JLabel();
			eventStart = new JLabel();
			eventEnd = new JLabel();
		}
			add(clientImagePanel, "west, w 50:100:250, h 80:150:250");
//			add(eventTitleLabel, "split 12");
//			add(eventTitle);
//			add(new JSeparator(JSeparator.VERTICAL), "growy");
//			add(eventDateLabel);
//			add(eventDate);
//			add(new JSeparator(JSeparator.VERTICAL), "growy");
//			add(eventStartLabel);
//			add(eventStart);
//			add(new JSeparator(JSeparator.VERTICAL), "growy");
//			add(eventEndLabel);
//			add(eventEnd, "wrap");
			add(scan, "grow, east");
	}
	public String getClientName(){
		return appointment.getClient().getFullName();
	}
	public ScanPanel getScanPanel(){
		return scan;
	}
	
	public void setInfoText(String string) {
		scan.setInfoText(string);
	}
	public String getScanText() {
		return scan.getText();
	}

	public JTextField getScanJTextField() {
		return scan.getTextField();
	}
	public  void setScanListener(ActionListener a){
		scan.getTextField().addActionListener(a);
	}
	public void set(Appointment appointment2) {
		appointment = appointment2;
		SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
		eventDate.setText(format.format(appointment.getEvent().getDate()));
		eventTitle.setText(appointment.getEvent().getTitle());
		format = new SimpleDateFormat("hh:mm");
		eventStart.setText(format.format(appointment.getTimeslot().getStartTime()));
		eventEnd.setText(format.format(appointment.getTimeslot().getEndTime()));
		
		scan.set(appointment);
		clientImagePanel.reset(appointment.getClient());
	}
}

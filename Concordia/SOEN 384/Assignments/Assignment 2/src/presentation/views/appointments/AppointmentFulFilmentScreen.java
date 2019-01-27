package presentation.views.appointments;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import domain.Appointments.Appointment;

public class AppointmentFulFilmentScreen extends JPanel implements Observer{

	private static final long serialVersionUID = 748390416L;
	private AppointmentCheckInPanel appointmentCheckInPanel;
	private JPanel innerPanel;
	private Dimension screenSize;
	private JLabel title;

	public AppointmentFulFilmentScreen(AppointmentCheckInPanel appointmentCheckInPanel) {
		this.appointmentCheckInPanel = appointmentCheckInPanel;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		init();
		addComponents();
	}

	public void setCheckinPanel(AppointmentCheckInPanel appointmentCheckInPanel){
		this.appointmentCheckInPanel = appointmentCheckInPanel;
	}
	private void init() {
		this.setLayout(new MigLayout("", "[" + screenSize.width + "]", ""));
		innerPanel = new JPanel(new MigLayout("", "[" + screenSize.width * .8
				+ "]", ""));
		String appointmentCheckinText="<html><br>Appointment Check-In<br><br></html>";
		title = new JLabel(appointmentCheckinText);
		title.setForeground(new Color(0, 51, 153));
		title.setFont(new Font("", Font.BOLD, 23));
	}

	private void addComponents() {
		add(title, "align center, wrap");
		JSeparator js = new JSeparator(JSeparator.HORIZONTAL);
		js.setAlignmentX(JSeparator.BOTTOM_ALIGNMENT);
		innerPanel.add(js, "grow, wrap");
		innerPanel.add(appointmentCheckInPanel, "wrap, span, grow");
		add(innerPanel, "alignx center");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		set((Appointment) arg0);
	}

	public void set(Appointment a) {
		appointmentCheckInPanel.set(a);	
	}

	public AppointmentCheckInPanel getAppointmentCheckInPanel() {
		return appointmentCheckInPanel;
	}
}
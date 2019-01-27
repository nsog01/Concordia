package presentation.views.appointments;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Appointments.Appointment;

import net.miginfocom.swing.MigLayout;

public class ScanPanel extends JPanel {

	private static final long serialVersionUID = 43247893120L;
	private JTextField scan;
	private JLabel info;
	public Appointment appointment;
	String arrivedAtText=" <br>arrived at <br>";
	String scanInfoText="Scan or Enter Badge Number";

	public ScanPanel(Appointment a) {
		this.appointment = a;
		init();
		addComponents();
	}

	private void init() {
		scan = new JTextField();
		setLayout(new MigLayout("", "[200.00]", "[][35.00]"));
		scan.setHorizontalAlignment(JTextField.CENTER);
		if (appointment != null) {
			if (appointment.getArrival() != null) {
				
				info = new JLabel("<html><center>"
						+ appointment.getClient().getFullName()
						+ arrivedAtText
						+ appointment.getArrivalString() + "<center><html>");
				scan.setEnabled(false);
			} else {
				
				info = new JLabel(scanInfoText);
			}

		} else {
			
			info = new JLabel(scanInfoText);
			scan.setEnabled(false);
		}
		info.setForeground(new Color(0, 51, 153));
		info.setFont(new Font("", Font.BOLD, 13));
	}

	private void addComponents() {
		add(info, "wrap, alignx center");
		add(scan, "grow, span, alignx center");
	}

	public void addActionLitener(ActionListener action) {
		scan.addActionListener(action);
	}

	public JTextField getScan() {
		return scan;
	}

	public void setInfoText(String string) {
		info.setText(string);
	}

	public String getText() {
		return scan.getText();
	}

	public JTextField getTextField() {
		return scan;
	}

	public void set(Appointment appointment2) {
		appointment = appointment2;
		if (appointment.getArrival() != null) {
			info.setText("<html>"
					+ appointment.getClient().getFullName()
					+ arrivedAtText
					+ appointment.getArrivalString() + "<html>");
			
			scan.setEnabled(false);
			scan.setText(Integer.toString(appointment2.getBadgeId()));
		} else {
			info.setText(scanInfoText);
			scan.setEnabled(true);
			scan.setText(null);
		}
		
	}

}

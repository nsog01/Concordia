package presentation.views.files;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.views.appointments.AppointmentButton;
import presentation.views.events.EventButton;
import presentation.views.reports.ReportsButton;

import net.miginfocom.swing.MigLayout;

public class BannerPanel extends JPanel {

	private static final long serialVersionUID = 43257849203534L;
	private Component flagComboBox;
	private EventButton eventButton;
	private AppointmentButton appointmentButton;
	private ReportsButton reportButton;
	private AdminButton adminButton;
	private JPanel searchPanel;
	private JPanel flagPanel;
	private JLabel flagLabel;

	public BannerPanel(JTextField searchField2, JButton searchButton2) {
		init();
		addComponents();

		searchPanel.add(searchField2);
		searchPanel.add(searchButton2);

	}

	public void init() {
		setLayout(new MigLayout("", "20[]10[]10[]10[]10[][grow]", "[]"));
		flagComboBox = new FlagComboBox();
		searchPanel = new JPanel();
		searchPanel.setBackground(new Color(51, 102, 153));
		setBackground(new Color(51, 102, 153));
		eventButton = new EventButton();
		appointmentButton = new AppointmentButton();
		adminButton = new AdminButton();
		reportButton = new ReportsButton();
		flagPanel = new JPanel(new MigLayout("", "", "21[]1[]"));
		flagPanel.setBackground(new Color(51, 102, 153));
		flagPanel.add(flagComboBox, "wrap");
		flagLabel = new JLabel("Add Flag");
		flagLabel.setFont(new Font("", Font.BOLD, 9));
		flagLabel.setForeground(Color.WHITE);
		flagPanel.add(flagLabel);
	}

	private void addComponents() {
		add(new JLabel(new ImageIcon("images/logo_fr.jpg")), "west");
		add(flagPanel);
		add(eventButton);
		add(appointmentButton);
		add(reportButton);
		add(adminButton);
		add(searchPanel, "align right, spanx");
	}
}

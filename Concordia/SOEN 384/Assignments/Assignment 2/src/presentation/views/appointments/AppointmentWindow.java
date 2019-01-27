package presentation.views.appointments;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import presentation.views.files.EmptyBannerPanel;
import presentation.views.menus.GeneralMenuBar;

public class AppointmentWindow extends JFrame{


	private static final long serialVersionUID = 431253476L;
	JTabbedPane jTabbedPane;
	
	public AppointmentWindow() {
		init();
		addComponents();
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		String emptyBannerPanelText="Appointment Fulfilment";
		EmptyBannerPanel banner = new EmptyBannerPanel(emptyBannerPanelText);
		add(banner, BorderLayout.NORTH);
		add(jTabbedPane);
		pack();
		setVisible(true);
	}

	private final void init(){
		String appointmentCheckInTitleText="Appointment Check-In";
		setTitle(appointmentCheckInTitleText);
		jTabbedPane = new JTabbedPane();
	}
	
	private final void addComponents(){
		setJMenuBar(new GeneralMenuBar());

	}


	public JTabbedPane getTabPane() {
		return jTabbedPane;
	}
	public void addTab(String title, JComponent component){
		jTabbedPane.addTab(title, component);
	}
}

package presentation.views.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentation.views.files.HouseholdManageFrame;
import presentation.views.files.HouseholdPanel;
//import presentation.views.files.HouseholdWindow;

public class FileNewMenu extends JMenu implements LocaleChangeListener{

	private static final long serialVersionUID = 4795949332743434721L;
	private JMenuItem menuItem;
	private NewAppointmentMenuItem appointmentMenuItem;
	private String fileNewTitleText ="New";
	
	public FileNewMenu(){
	
		this.setText(fileNewTitleText);
		this.setMnemonic(KeyEvent.VK_N);
		String clientFileItem="Client File";
		menuItem = new JMenuItem(clientFileItem);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));
		menuItem.addActionListener(newHousehold());
		
		this.add(menuItem);
//		appointmentMenuItem = new NewAppointmentMenuItem();
//		appointmentMenuItem.setVisible(false);
//		add(appointmentMenuItem);
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
		
	}
	
	private ActionListener newHousehold(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new HouseholdManageFrame();
			}
		};
	}

	public void setAppointmentMenuItem(HouseholdPanel householdPanel) {
		if (appointmentMenuItem == null){
			appointmentMenuItem = new NewAppointmentMenuItem(householdPanel);
			add(appointmentMenuItem);
		}	else{
			appointmentMenuItem.setWindow(householdPanel);
		}
			
	}

	@Override
	public void localeChanged(LocaleChangeEvent e) {
		
		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		
		this.setText(messages.getString("fileNewTitleText"));
		
	}
}

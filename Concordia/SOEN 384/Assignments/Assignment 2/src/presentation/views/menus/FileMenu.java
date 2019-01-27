package presentation.views.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import presentation.controllers.files.AdminPageWindowController;
import presentation.controllers.reports.ManageReportsWindowController;

public class FileMenu extends JMenu implements LocaleChangeListener{

	private static final long serialVersionUID = 5887883473083170752L;
	private JMenuItem menuOpenItem;
	private JRadioButtonMenuItem rbFamMenuItem;
	private JRadioButtonMenuItem rbEvMenuItem;
	private JCheckBoxMenuItem cbSearchMenuItem;
	private JCheckBoxMenuItem cbEntryMenuItem;
	private FileNewMenu fileNew;
	private JMenuItem reportMenuItem;
	private JMenuItem adminMenuItem;
	private JMenuItem exitMenuItem;
	private String exitItem="Exit";
	private String adminSettingsItemText="Administrative Settings";
	
	private ReportMenu reportMenu;
	FileMenu(ReportMenu reportingMenu){
		this.reportMenu = reportingMenu;
	String fileItemText="File";	
	this.setText(fileItemText);
	this.setMnemonic(KeyEvent.VK_F);
	this.getAccessibleContext().setAccessibleDescription(
			"The only menu in this program that has menu items");

	// New sub menu
	fileNew = new FileNewMenu();
	this.add(fileNew);
	
	// a group of JMenuItems
	String openFileText="Open File...";
	menuOpenItem = new JMenuItem(openFileText, KeyEvent.VK_O);
	menuOpenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
			ActionEvent.ALT_MASK));
	menuOpenItem.getAccessibleContext().setAccessibleDescription(
			"This doesn't really do anything");
	this.add(menuOpenItem);
	
	// a group of radio button menu items
	this.addSeparator();
	ButtonGroup group = new ButtonGroup();
	String eventMode="Event Mode";
	rbEvMenuItem = new JRadioButtonMenuItem(eventMode);
	rbEvMenuItem.setSelected(true);
	rbEvMenuItem.setMnemonic(KeyEvent.VK_E);
	group.add(rbEvMenuItem);
	this.add(rbEvMenuItem);
	
	String familyMode="Family Mode";
	rbFamMenuItem = new JRadioButtonMenuItem(familyMode);
	rbFamMenuItem.setMnemonic(KeyEvent.VK_F);
	group.add(rbFamMenuItem);
	this.add(rbFamMenuItem);

	// a group of check box menu items
	this.addSeparator();
	String entryItem="Entry";
	cbEntryMenuItem = new JCheckBoxMenuItem(entryItem);
	cbEntryMenuItem.setMnemonic(KeyEvent.VK_N);
	this.add(cbEntryMenuItem);

	String searchItem="Search";
	cbSearchMenuItem = new JCheckBoxMenuItem(searchItem);
	cbSearchMenuItem.setMnemonic(KeyEvent.VK_S);
	this.add(cbSearchMenuItem);
	
	this.addSeparator();
	String manageReportsItem="Manage Reports...";
	reportMenuItem = new JMenuItem(manageReportsItem);
	reportMenuItem.addActionListener(manageReports());	
	this.add(reportMenuItem);
	
	this.addSeparator();
	
	adminMenuItem = new JMenuItem(adminSettingsItemText);
	adminMenuItem.addActionListener(AdminListener());	
	this.add(adminMenuItem);
	
	this.addSeparator();
	
	exitMenuItem =new JMenuItem(exitItem);
	exitMenuItem.setMnemonic(KeyEvent.VK_X);
	exitMenuItem.addActionListener(exitProgram());
	this.add(exitMenuItem);

	
	LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
	}
	
	
	private ActionListener exitProgram(){
		return new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent argo0) {
				
				System.exit(0);
			}	
		};
	}
	
	private ActionListener manageReports(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManageReportsWindowController manageReportsController = new ManageReportsWindowController();
				manageReportsController.setReportingMenu(reportMenu);
			}
		};
	}
	
	private ActionListener AdminListener(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminPageWindowController adminPageController = new AdminPageWindowController();
			}
		};
	}
	
	public FileNewMenu getFileNewMenu(){
		return fileNew;
	}


	@Override
	public void localeChanged(LocaleChangeEvent e) {
		
		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		
		exitMenuItem.setText(messages.getString("exitItem"));
		reportMenuItem.setText(messages.getString("manageReportsItem"));
		rbFamMenuItem.setText(messages.getString("familyMode"));
		rbEvMenuItem.setText(messages.getString("eventMode"));
		cbEntryMenuItem.setText(messages.getString("entryItem"));
		cbSearchMenuItem.setText(messages.getString("searchItem"));
		menuOpenItem.setText(messages.getString("openFileText"));
		setText(messages.getString("fileItemText"));
		adminMenuItem.setText(messages.getString("adminSettingsItemText"));
		
		
		
	
		
		
	}
}

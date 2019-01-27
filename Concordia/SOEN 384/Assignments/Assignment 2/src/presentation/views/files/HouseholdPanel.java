package presentation.views.files;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JPanel;


import presentation.controllers.files.HouseholdPanelController;
import net.miginfocom.swing.MigLayout;
import domain.files.ClientList;
import domain.files.FileManager;
import domain.files.Household;


public class HouseholdPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 8290213190060358286L;
	private FileManager fileManager = FileManager.getUniqueInstance();
	private HouseholdPanelController controller;
	private ApplicationWindow appWindow;
	private BannerPanel bannerPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	public HouseholdPanel(ApplicationWindow applicationWindow) {
		fileManager.addObserverToRepository(this);
		controller = new HouseholdPanelController(this);
		this.appWindow = applicationWindow;
		initializeElements();
		addComponents();
		
		this.setVisible(true);
	}
	
	public HouseholdPanelController getController()
	{
		return this.controller;
	}
	
	public Household getHousehold(){
		return fileManager.getActiveFile();
	}
	private void initializeElements() {
		
		
		this.setLayout(new MigLayout("", "[]", "[][]"));
		if ( fileManager.getActiveFile() != null){
			controller.setHouseholdInfoPanel(new HouseholdInfoPanel(this.controller));
			controller.setDependentsPanel(fileManager.getActiveFile().getAllClients());
			controller.setHouseholdNotesArea(fileManager.getActiveFile());
			controller.setAppointmentTable(fileManager.getActiveFile().getAppointments());


		} else {
			controller.setHouseholdInfoPanel(new HouseholdInfoPanel(this.controller));
			controller.setDependentsPanel(new ClientList());
			controller.setFileListTable();
			controller.setHouseholdNotesArea(null);
		}
		Dimension searchSize = new Dimension((int) (controller.getScreenSize().getWidth()/5), 30);
		controller.getHouseholdInfoPanel().getSearchField().setPreferredSize(searchSize);
		controller.getHouseholdInfoPanel().getSearchField().addKeyListener(
				controller.searchFieldListener(controller.getHouseholdInfoPanel().getSearchButton()));
		
		bannerPanel = new BannerPanel(controller.getHouseholdInfoPanel().getSearchField(), 
				controller.getHouseholdInfoPanel().getSearchButton());
		

		controller.getHouseholdNotesArea().getTextArea().addMouseListener(controller.noteListerer());

	}
	
	public void reset(Household house){
		if (house != null){
		controller.setAppointmentTable(fileManager.getActiveFile().getAppointments());
		controller.setDependentsPanel(house.getAllClients());

		} else {
			appWindow.getBar().getFileMenu().getFileNewMenu().setAppointmentMenuItem(this);
			controller.getHouseholdInfoPanel().set(null);
			controller.getDependentsPanel().set(null);
			controller.getHouseholdNotesAreaController().set(null);

		}
	}
	public void refresh(){
		controller.setAppointmentTable(fileManager.getActiveFile().getAppointments());

	}
	private void addComponents(){
		topPanel = new JPanel(new MigLayout("", "["+controller.getScreenSize().width/1.55+"]0[]0", "[]0[]"));
		bottomPanel = new JPanel(new MigLayout("", 
				"[:"+controller.getScreenSize().width/2+":][:"+controller.getScreenSize().width/2+":]", 
				""));
		topPanel.add(controller.getHouseholdInfoPanel(),
				"h "+controller.getScreenSize().height/1.5+",w "+controller.getScreenSize().width/1.55);
		topPanel.add(controller.getDependentsPanel());
		
		bottomPanel.add(controller.getFileTableScrollPane(),
				"h "+controller.getScreenSize().height/2+", grow");
		
		int h = (int) (controller.getScreenSize().height/2.5);
		int w = (int) (controller.getScreenSize().width/2);
		Dimension noteArea = new Dimension(w, h);
		controller.getHouseholdNotesArea().setMaximumSize(noteArea);
		bottomPanel.add(controller.getHouseholdNotesArea(), "cell 1 0");
		controller.getHouseholdNotesAreaController().setTextAreaSize(w, h);
		add(bannerPanel, "north");
		add(topPanel, "wrap");
		add(bottomPanel);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		reset(fileManager.getActiveFile());
	}
	public void removeFromBottomPanel(JComponent component){
		bottomPanel.remove(component);
	}
	public void addToBottomPanel(JComponent comp, String settings){
		bottomPanel.add(comp, settings);
	}
	
}

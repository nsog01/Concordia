package presentation.controllers.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JTable;



import domain.files.FileList;
import domain.files.FileManager;

import presentation.models.files.DependentListTableModel;
import presentation.models.files.FileListTableModel;
import presentation.views.files.ApplicationWindow;
import presentation.views.files.FileListTable;
import presentation.views.files.HouseholdPanel;
import presentation.views.files.SearchClientsPanel;

public class SearchClientsPanelController {
	//views
	private SearchClientsPanel searchClientsPanel;
	private FileListTable fileListTable;
	private JTable tableDependents;
	private DependentListTableModel dependentListTableModel;
	private HouseholdPanel householdPanel;
	private FileManager fileManager = FileManager.getUniqueInstance();
	
	//models
	private FileListTableModel fileListTableModel;
	
	private ApplicationWindow appWindow;
	
	public SearchClientsPanelController(HouseholdPanel myHouseholdPanel,ApplicationWindow applicationWindow)
	{
		//initialize search table & model
		fileListTableModel = new FileListTableModel();
		fileListTable = new FileListTable(fileListTableModel);
		fileListTable.addMouseListener(selectedFile());
		
		this.householdPanel = myHouseholdPanel;
		
		dependentListTableModel = new DependentListTableModel();
		tableDependents = new JTable(dependentListTableModel);		
		
		//initialize search frame
		searchClientsPanel = new SearchClientsPanel(fileListTable, tableDependents);
		searchClientsPanel.AddSearchListener(search());
		this.appWindow = applicationWindow;
		appWindow.getRootPane().setDefaultButton(searchClientsPanel.getSearchButton());
		
	}
	
	public SearchClientsPanel getPanel()
	{
		return this.searchClientsPanel;
	}
	
	private ActionListener search() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileList fileList;
					fileList = FileManager.getUniqueInstance().searchByAnything(searchClientsPanel.getSearchText());
					fileListTable.setList(fileList);
					dependentListTableModel.clearHouse();
					searchClientsPanel.repaint();
					searchClientsPanel.repaint();
									
			};
		};
	}
	
	private MouseListener selectedFile(){
		return new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				 if (arg0.getClickCount() == 2)
				 {
					    fileManager.setActiveFile(fileListTable.getSelected());
							householdPanel.reset(fileManager.getActiveFile());
						    householdPanel.getController().swapToAppointmentList(
						    		fileManager.getActiveFile().getAppointments());
						    appWindow.setSelectedTab(1);
						    

	
				 }
				 else
				 {
					fileManager.setActiveFile(fileListTable.getSelected());
					dependentListTableModel.setHouse(fileManager.getActiveFile());
					searchClientsPanel.repaint();
					searchClientsPanel.repaint();
				 }
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

}

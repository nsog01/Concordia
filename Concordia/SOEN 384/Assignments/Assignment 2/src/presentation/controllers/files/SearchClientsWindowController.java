package presentation.controllers.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



import javax.swing.JTable;



import domain.files.FileList;
import domain.files.FileManager;
import domain.files.Household;

import presentation.models.files.DependentListTableModel;
import presentation.models.files.FileListTableModel;
import presentation.views.files.FileListTable;
import presentation.views.files.SearchClientsWindow;

public class SearchClientsWindowController {
	//Manager
	FileManager fileManager = FileManager.getUniqueInstance();
	//views
	SearchClientsWindow searchFrame;
	FileListTable table;
	JTable tableDependents;
	DependentListTableModel modelDependents;
	//models
	FileListTableModel tableModel;
	
	public SearchClientsWindowController()
	{
		//initialize search table & model
		tableModel = new FileListTableModel();
		table = new FileListTable(tableModel);
		table.addMouseListener(selectedFile());
		
		
		modelDependents = new DependentListTableModel();
		tableDependents = new JTable(modelDependents);		
		
		//initialize search frame
		searchFrame = new SearchClientsWindow(table, tableDependents);
		searchFrame.AddSearchListener(search());
		
	}
	
	private ActionListener search() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileList fileList;
				fileList = FileManager.getUniqueInstance().searchByAnything(searchFrame.getSearchText());
				table.setList(fileList);
									
			};
		};
	}
	
	private MouseListener selectedFile(){
		return new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Household house = table.getSelected();
				fileManager.setActiveFile(house);
				searchFrame.repaint();
				searchFrame.repaint();
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

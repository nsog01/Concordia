package presentation.controllers.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import domain.files.Client;
import domain.files.FileManager;
import domain.files.Household;
import domain.files.Relation;
import presentation.views.files.AddDependentFrame;
import presentation.views.files.DependentsPanel;

public class DependentsPanelController {

//	private DependentsPanel dependentsPanel;
	private AddDependentFrame addDependantFrame;
	private FileManager fileManager = FileManager.getUniqueInstance();
	
	public DependentsPanelController(DependentsPanel dependentsPanel){
//		this.dependentsPanel = dependentsPanel;
	}
	
	public Household getHouse(){
		return this.fileManager.getActiveFile();
	}
	public ActionListener addDependentListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDependantFrame = new AddDependentFrame(fileManager.getActiveFile());
				addDependantFrame.addEventSubmitListener(addDependentAction());
				String addDepEventSubmitText="Add Dependent";
				addDependantFrame.setEventSubmitButtonText(addDepEventSubmitText);
				addDependantFrame.pack();
				addDependantFrame.setVisible(true);
			}
		};
	}

	private ActionListener addDependentAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Client client = addDependantFrame.getClient();
				Relation relation = addDependantFrame.getRelation();
				if (client != null && relation != null){
				fileManager.addDependent(client, relation);
				fileManager.getActiveFile().insertDependent(client);
				addDependantFrame.dispose();
				}
			}

		};
	}
}

package presentation.controllers.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import domain.files.Client;
import domain.files.FileManager;
import domain.files.Household;

import presentation.views.files.AddDependentFrame;
import presentation.views.files.ClientFormPanel;
import presentation.views.files.HouseholdManageFrame;

public class HouseholdManageFrameController {

	private HouseholdManageFrame householdManageFrame;
	private ClientFormPanel clientFormPanel;
	private FileManager fileManager;
	private AddDependentFrame dependentFrame;
	
	public HouseholdManageFrameController(
			HouseholdManageFrame householdManageFrame){
		this.householdManageFrame = householdManageFrame;
		fileManager = FileManager.getUniqueInstance();
	}
	public void setClientFormPanel(Client client){
		if (client == null){
			clientFormPanel = new ClientFormPanel();
		} else {
			clientFormPanel = new ClientFormPanel(client);
		}
		clientFormPanel.remove(clientFormPanel.getDeleteButton());
		clientFormPanel.remove(clientFormPanel.getSubmitButton());
	}
	public Household getHousehold(){
		return fileManager.getActiveFile();
	}
	public ClientFormPanel getClientFormPanel(){
		return clientFormPanel;
	}

	public ActionListener addDependent(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
					dependentFrame = new AddDependentFrame(fileManager.getActiveFile());
					dependentFrame.addEventSubmitListener(addDependentAction());
		}
	};
}	
	
	private ActionListener addDependentAction(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Client client = dependentFrame.getClient();
				if (client != null){
					fileManager.addDependent(
							dependentFrame.getClient(), dependentFrame.getRelation());
					dependentFrame.dispose();
				}
			}
			
		};
	}

	public ActionListener submit(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Household newHouse = new Household();
				newHouse.setRepresentative(clientFormPanel.retrieveClient());
				newHouse.set(householdManageFrame.getAddress(), 
						householdManageFrame.getPostalCode(),
						Calendar.getInstance().getTime(),
						householdManageFrame.getTel(), 
						newHouse.getRepresentative(), 
						clientFormPanel.getProvince(), newHouse.getDependentsList(),
						fileManager.getFlag(1));
				newHouse.commit();
				fileManager.setActiveFile(newHouse);
				householdManageFrame.dispose();

				householdManageFrame.dispose();
		}
	};
}
}

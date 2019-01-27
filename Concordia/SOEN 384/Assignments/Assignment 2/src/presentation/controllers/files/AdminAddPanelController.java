package presentation.controllers.files;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import domain.files.Client;
import domain.files.FileManager;
import domain.files.MedicareParser;
import domain.files.Client.Sex;

import presentation.views.files.AdminAddPanel;

public class AdminAddPanelController {
	
	private AdminAddPanel view;
	private Client client;
	private FileManager fileManager;
	
	public AdminAddPanelController(Client client)
	{
		fileManager = FileManager.getUniqueInstance();
		view = new AdminAddPanel();
		view.setClient(client.getFirstName(), client.getMiddleName(), client.getLastName(),
				client.getMedicare(), client.getDOB(), client.getMaritalStatus().toString(),
				client.getSex().toString());
		setClient(client);
		addListeners(medicareAction());
	}
	public AdminAddPanelController(){
		view = new AdminAddPanel();
		addListeners(medicareAction());
	}
	public void addListeners(KeyListener key){
		view.addMedicareListener(key);
	}

	
	
	
	public void setClient(Client client){
		this.client = client;
	}
	public Client getClient(){
		return this.client;
	}

	public KeyListener medicareAction() {
		return new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				String medicare = view.getMedicare();
				Date dob = null;
				Sex sex = null;
				try{
				dob = MedicareParser.getDOB(medicare);
				sex = MedicareParser.getSex(medicare);
				}catch (Exception e){		
				}
				view.setDateOfBirth(dob);
				view.setSex(sex);
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
	};
	}
	
	public void addClientAsDependent(){
		client = fileManager.addClientAsDependent(view.getFirstName(), view.getMiddleName(),
				view.getLastName(), view.getDateOfBirth(), view.getMedicare(), view.getMaritalStatus(),
				view.getGender());
	}
	
	public void addClientAsPrimary(){
		client = fileManager.addClientAsPrimary(view.getFirstName(), view.getMiddleName(),
				view.getLastName(), view.getDateOfBirth(), view.getMedicare(), view.getMaritalStatus(),
				view.getGender());
	}
	public AdminAddPanel getView() {
		return view;
	}
}

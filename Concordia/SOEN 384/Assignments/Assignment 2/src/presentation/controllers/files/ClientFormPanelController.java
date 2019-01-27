package presentation.controllers.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;


import presentation.views.files.ClientFormPanel;
import presentation.views.files.ClientImagePanel;

import domain.files.Client;
import domain.files.Client.Sex;
import domain.files.MedicareParser;
import domain.images.ImageUploader;
import domain.images.Upload;

public class ClientFormPanelController{

	private ClientFormPanel clientFormPanel;
	private Client client;
	private ClientImagePanel clientImagePanel;
	
	public ClientFormPanelController(ClientFormPanel formPanel) {
		clientFormPanel = formPanel;
	}
	public void setClient(Client client){
		this.client = client;
	}
	public Client getClient(){
		return this.client;
	}
	public ClientImagePanel getImagePanel(){
		return this.clientImagePanel;
	}
	public void setClientImagePanel(Client client)  {
		this.clientImagePanel = new ClientImagePanel(client);
	}
	public void setClientImagePanel() throws IOException{
		this.clientImagePanel = new ClientImagePanel();
	}
	
	public ActionListener uploadAction() {
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ImageUploader uploader = new ImageUploader();
				Upload upLoadHandler = new Upload(uploader, clientImagePanel);
				uploader.setBrowseListener(upLoadHandler);
				uploader.setUploadListener(upLoadHandler);
			}
		};
	}
	public KeyListener medicareAction() {
		return new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				String medicare = clientFormPanel.getMedicare();
				Date dob = null;
				Sex sex = null;
				try{
				dob = MedicareParser.getDOB(medicare);
				sex = MedicareParser.getSex(medicare);
				}catch (Exception e){
				
					//DISPLAY AN ERROR MESSAGE
				}
				clientFormPanel.setDateOfBirth(dob);
				clientFormPanel.setSex(sex);
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
	

}

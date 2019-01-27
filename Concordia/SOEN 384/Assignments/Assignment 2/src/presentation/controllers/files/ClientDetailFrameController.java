package presentation.controllers.files;


import domain.files.Client;
import domain.files.Household;

import presentation.views.files.ClientDetailFrame;
import presentation.views.files.ClientFormPanel;

public class ClientDetailFrameController{
	
	private ClientFormPanel clientFormPanel;
	private Client client;
	
	public ClientDetailFrameController(Client client, ClientDetailFrame clientDetailFrame){
		super();
		this.client = client;
	}
	
	public ClientDetailFrameController(Client client, 
			ClientDetailFrame clientDetailFrame, Household house){
		super();
		this.client = client;

	}
	public ClientDetailFrameController(ClientDetailFrame clientDetailFrame){
		super();
		this.client = null;
	}

	public Client getClient(){
		return this.client;
	}

	public void setClientFormPanel(ClientFormPanel clientFormPanel) {
		this.clientFormPanel = clientFormPanel;
	}

	public ClientFormPanel getClientFormPanel() {
		return this.clientFormPanel;
	}
	
	
}

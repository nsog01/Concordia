package presentation.controllers.files;

import java.awt.Image;

import presentation.views.files.ClientFormPanel;
import presentation.views.files.ClientManageFrame;

public class ClientManageFrameController {

	protected ClientFormPanel clientFormPanel;
	protected ClientManageFrame clientManageFrame;
	protected Image photo;
	
	public ClientManageFrameController(ClientManageFrame clientManageFrame) {
		this.clientManageFrame = clientManageFrame;
	}

	public ClientFormPanel getClientFormPanel() {
		return this.clientFormPanel;
	}

	public void setClientFormPanel(ClientFormPanel clientFormPanel) {
		this.clientFormPanel = clientFormPanel;
	}

	public ClientManageFrame getClientManageFrame() {
		return clientManageFrame;
	}

	public void setClientManageFrame(ClientManageFrame clientManageFrame) {
		this.clientManageFrame = clientManageFrame;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

}

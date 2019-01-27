package presentation.views.files;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;

import presentation.controllers.files.ClientManageFrameController;


import domain.files.Client;

import net.miginfocom.swing.MigLayout;


public class ClientManageFrame extends JFrame{


	private static final long serialVersionUID = 43728904321L;
	protected ClientManageFrameController controller;
	private Client client;

	public ClientManageFrame() {
		controller  = new ClientManageFrameController(this);
		this.setLayout(new MigLayout());
		String clientManageFrameTitleText="Mangage Client Files";
		this.setTitle(clientManageFrameTitleText);
		controller.setClientFormPanel(new ClientFormPanel(this.client));
		String clientManageFrameAddClientText="Add New Client";
		JLabel title = new JLabel(clientManageFrameAddClientText);
		title.setFont(new Font("Serif", Font.BOLD, 17));
		this.add(title, "span, wrap 20");
		this.add(controller.getClientFormPanel(), "wrap");
		this.pack();
//		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public Image getPhoto(){
		return this.controller.getPhoto();
	}
	public void setPhoto(Image photo){
		this.controller.setPhoto(photo);
	}
}

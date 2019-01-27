package presentation.views.files;

import java.awt.Image;

import javax.swing.JFrame;


import presentation.controllers.files.ClientDetailFrameController;
import presentation.views.menus.GeneralMenuBar;

import net.miginfocom.swing.MigLayout;
import domain.files.Client;
import domain.files.Household;

public class ClientDetailFrame extends JFrame{

	private static final long serialVersionUID = 6021879023048456645L;
	ClientDetailFrameController controller;
	private String clientDetailManageFileText1 ="Manage Client File - ";
	private String clientDetailManageFileText2="Manage Client File";
	
	public ClientDetailFrame(Client client) {
		controller = new ClientDetailFrameController(client, this);
		this.initializeElements();
		this.addComponents();
		pack();
	}
	
	public ClientDetailFrame(Client client, Household house){
		controller = new ClientDetailFrameController(client, this, house);
		this.initializeElements();
		this.addComponents();
		pack();
	}

	public ClientDetailFrame() {
		controller = new ClientDetailFrameController(this);
		this.initializeElements();
		this.addComponents();
		pack();
	}
	
	private void initializeElements() {
		this.setLayout(new MigLayout());
		
		GeneralMenuBar bar = new GeneralMenuBar();
		this.setJMenuBar(bar);
		if (this.controller.getClient() != null){
			this.setTitle(clientDetailManageFileText1+controller.getClient().getFullName());
			controller.setClientFormPanel(new ClientFormPanel(this.controller.getClient()));
		} else {
			this.setTitle(clientDetailManageFileText2);
			controller.setClientFormPanel(new ClientFormPanel());
		}
	}
	
	private void addComponents(){
		this.add(controller.getClientFormPanel());
		getRootPane().setDefaultButton(controller.getClientFormPanel().getSubmitButton());
		this.pack();
	}
	
	public Image getPhoto(){
		return this.controller.getClientFormPanel().getPhoto();
	}
	public ClientFormPanel getClientFormPanel(){
		return this.controller.getClientFormPanel();
	}
}

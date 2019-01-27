package presentation.controllers.files;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.views.files.ClientInfoWindow;

public class ClientInfoWindowController {

	private ClientInfoWindow view;
	private ClientPersonalInfoPanelController personalInfoPanel;
	private ClientAddressPanelController addressPanel;
	private ClientIncomePanelController clientIncomePanelController;
	
	public ClientInfoWindowController(){
		view = new ClientInfoWindow();
		personalInfoPanel = new ClientPersonalInfoPanelController();
		addressPanel = new ClientAddressPanelController();
		clientIncomePanelController =  new ClientIncomePanelController();
		
		view.setLayout(new BorderLayout());
		
		view.add(personalInfoPanel.getView(),BorderLayout.EAST);
		view.add(addressPanel.getView(),BorderLayout.CENTER);
		view.add(clientIncomePanelController.getView(),BorderLayout.WEST);
		view.add(view.getSubmitButton(), BorderLayout.SOUTH);
		view.addSubmitListener(submitListener());
		view.pack();
		view.setVisible(true);
	}
	
	private ActionListener submitListener(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				personalInfoPanel.addClientAsPrimary();
				
			}
			
		};
	}
}

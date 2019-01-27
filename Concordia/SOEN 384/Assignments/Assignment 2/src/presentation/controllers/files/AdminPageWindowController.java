package presentation.controllers.files;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.views.files.AdminAddWindow;

public class AdminPageWindowController {

	private AdminAddWindow view;
	private AdminAddPanelController adminAddPanel;

	
	public AdminPageWindowController(){
		view = new AdminAddWindow();
		adminAddPanel = new AdminAddPanelController();

		
		view.setLayout(new BorderLayout());
		
		view.add(adminAddPanel.getView(),BorderLayout.CENTER);

		view.add(view.getSubmitButton(), BorderLayout.SOUTH);
		view.addSubmitListener(submitListener());
		view.pack();
		view.setVisible(true);
	}
	
	private ActionListener submitListener(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminAddPanel.addClientAsPrimary();
				
			}
			
		};
	}

}

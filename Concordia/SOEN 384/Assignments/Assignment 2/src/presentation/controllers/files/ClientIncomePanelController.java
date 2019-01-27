package presentation.controllers.files;

import presentation.views.files.ClientIncomePanel;

public class ClientIncomePanelController {

	ClientIncomePanel view;
	
	public ClientIncomePanelController(){
		view = new ClientIncomePanel();
	}
	
	public ClientIncomePanel getView(){
		return view;
	}
}

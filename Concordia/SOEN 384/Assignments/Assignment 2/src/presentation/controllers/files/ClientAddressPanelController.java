package presentation.controllers.files;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import domain.files.FileManager;
import domain.files.Household;
import presentation.views.files.ClientAddressPanel;

public class ClientAddressPanelController {

	private ClientAddressPanel view;
	private Household	household;
	private FileManager fileManager;
	
	public ClientAddressPanelController ()
	{
		fileManager = FileManager.getUniqueInstance();
		household = fileManager.getActiveFile();
		view = new ClientAddressPanel();
		if (household != null){
		view.setHousehold(household.getStreetNumber(), 
				household.getStreetName(), household.getUnitNumber(),
				household.getPostalCode(), household.getCity(),
				household.getProvince());
		}
	}
	
	public ActionListener provinceAction(){
		return new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox)e.getSource();
				String province = (String)cb.getSelectedItem();
				//household.setMaritalStatus(maritalStatus);
			
			}
		};
	}

	public ClientAddressPanel getView() {
		return view;
	}
	
	/*public KeyListener postalCodeListener(){
		return new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
				String postalCode = clientAddressPanel.getPostalCode();
				String Province = null;
				String City = null;
				try{
				dob = MedicareParser.getDOB(medicare);
				sex = MedicareParser.getSex(medicare);
				}catch (Exception e){
				
					//DISPLAY AN ERROR MESSAGE
				}
				clientFormPanel.setDateOfBirth(dob);
				clientFormPanel.setSex(sex);
			}
		}
		}
	}*/
	
}

package presentation.controllers.files;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import domain.files.FileManager;

import presentation.views.files.ClientBriefPanel;
import presentation.views.files.ClientDetailFrame;
import presentation.views.files.ClientImagePanel;

public class ClientBriefPanelController{

	private ClientBriefPanel clientBriefPanel;
	private ClientDetailFrame clientDetailFrame;
	private ClientImagePanel clientImagePanel;
	
	public ClientBriefPanelController(ClientBriefPanel clientBriefPanel)  {
		this.clientBriefPanel = clientBriefPanel;
	}

	public ActionListener viewDetail(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clientDetailFrame  = new ClientDetailFrame(clientBriefPanel.getClient());
//				clientDetailFrame.getClientFormPanel().addSubmitListener(editClientFile());
				clientDetailFrame.setLocationRelativeTo(null);
				clientDetailFrame.setVisible(true);
			}
			
		};
	}
	
	public ClientImagePanel setClientImagePanel(ClientImagePanel panel){
		this.clientImagePanel = panel;
		return this.clientImagePanel;
	}
	public void setImage(Image photo){
		if (photo != null){
		this.clientImagePanel.setImage(new ImageIcon(photo));
		} else {
			this.clientImagePanel.setImage(new ImageIcon("images/default.png"));
		}
	}
	
//	public ActionListener editClientFile(){
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				clientDetailFrame.getClientFormPanel().getClient().set(
//						clientDetailFrame.getClientFormPanel().getFirstName(),
//						clientDetailFrame.getClientFormPanel().getMiddleName(), 
//						clientDetailFrame.getClientFormPanel().getLastName(), 
//						clientDetailFrame.getClientFormPanel().getMedicare(),
//						clientDetailFrame.getClientFormPanel().getMotherTongue(),
//						clientDetailFrame.getClientFormPanel().getCommLanguage(),
//						clientDetailFrame.getClientFormPanel().getMaritalStatus(),
//						clientDetailFrame.getClientFormPanel().getWorkStatus(),
//						clientDetailFrame.getClientFormPanel().getWorkStatusExtra(),
//						clientDetailFrame.getClientFormPanel().getCanadaStatus(),
//						clientDetailFrame.getClientFormPanel().getCanadaStatusExtra(),
//						clientDetailFrame.getClientFormPanel().getOrigon(),
//						clientDetailFrame.getClientFormPanel().getOrigonExtra(),
//						clientDetailFrame.getClientFormPanel().getRefferal(),
//						clientDetailFrame.getClientFormPanel().getRefferalExtra(),
//						clientDetailFrame.getClientFormPanel().getReason(),
//						clientDetailFrame.getClientFormPanel().getReasonExtra(),
//						clientDetailFrame.getClientFormPanel().getPhoto(), 
//						clientDetailFrame.getClientFormPanel().getThumb(),
//						clientDetailFrame.getClientFormPanel().getDateOfBirth(), 
//						clientDetailFrame.getClientFormPanel().getSex());
//				clientImagePanel.setImage(new ImageIcon(
//						clientDetailFrame.getClientFormPanel().getClient().getPhoto()));
//				clientImagePanel.repaint();
//				if (!clientDetailFrame.getClientFormPanel().getClientImagePanel().isChanged()){
//					FileManager.getUniqueInstance().saveClientWithoutPhoto(
//							clientDetailFrame.getClientFormPanel().getClient());
//				} else {
//					FileManager.getUniqueInstance().commitClient(
//							clientDetailFrame.getClientFormPanel().getClient());
//				}
//
//				clientDetailFrame.dispose();
//			}
//		};
//	}
}

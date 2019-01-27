package presentation.views.files;

import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.controllers.files.ClientBriefPanelController;

import net.miginfocom.swing.MigLayout;

import domain.files.Client;
import domain.files.FileManager;

public class ClientBriefPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 543579054325L;
	private Client client;
	private JButton viewClientDetail;
	private JLabel name;
	private JLabel medicare;
	private ClientBriefPanelController controller;
	private String clientViewDetailsText="View Details";
	private String primaryPhotoText="Primary Photo";
	private String clientMedicareLabelText="Medicare: ";
	private String clientNameLabelText="Name: ";
	
	
	public ClientBriefPanel(Client client){
		client.addObserver(this);
		this.client = client;
		controller = new ClientBriefPanelController(this);
		initialize(client);
	}
	
	ClientBriefPanel() {
		this.client = null;
		controller = new ClientBriefPanelController(this);
		initialize(null);
	}
	public Client getClient(){
		return this.client;
	}
	private void initialize(Client client) {
		this.setLayout(new MigLayout());
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				primaryPhotoText));
		if (client != null){
			this.add(this.controller.setClientImagePanel(new ClientImagePanel(client)), "wrap");
			
			this.add(new JLabel(clientNameLabelText), "split 2");
			name = new JLabel(client.getFullName());
			this.add(name, "wrap");
			
			this.add(new JLabel(clientMedicareLabelText), "split 2");
			medicare = new JLabel(client.getMedicare());
			this.add(medicare, "wrap");
			
			viewClientDetail = new JButton(clientViewDetailsText);
			viewClientDetail.addActionListener(this.controller.viewDetail());
			this.add(viewClientDetail);
		} else {
			this.add(this.controller.setClientImagePanel(new ClientImagePanel()), "wrap");
			this.add(new JLabel(clientNameLabelText), "split 2");
			name = new JLabel();
			this.add(name, "wrap");
			this.add(new JLabel(clientMedicareLabelText), "split 2");
			medicare = new JLabel();
			this.add(medicare, "wrap");
			viewClientDetail = new JButton(clientViewDetailsText);
			viewClientDetail.addActionListener(this.controller.viewDetail());
		}
	}
	
	public void set(Client client) {
		if (client != null){
		this.client = client;
		client.deleteObserver(this);
		client.addObserver(this);
		this.medicare.setText(client.getMedicare());
		this.name.setText(client.getFullName());
		Image photo = FileManager.getUniqueInstance().getPhoto(client);
		this.controller.setImage(photo);
		this.add(viewClientDetail);
		} else {
			this.client = null;
			this.medicare.setText(null);
			this.name.setText(null);
			this.controller.setImage(null);
			this.remove(viewClientDetail);
		}
		this.reset();
	}	
	
	private void reset()
	{
		this.repaint();
		this.revalidate();
	}
	
	@Override
	public void update(Observable oClient, Object arg1) {
		client = (Client) oClient;
		this.medicare.setText(client.getMedicare());
		this.name.setText(client.getFullName());
	}
}

package presentation.views.files;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentation.controllers.files.DependentButtonController;


import domain.files.Client;
import domain.files.Relation;

public class DependentButton extends JButton implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 54328590432L;
	private ImageIcon imageIcon;
	private Action action;
	private Client client;
	private Relation relation;
	private DependentButtonController controller;
	
	public DependentButton(Client client, Relation relation) {
		this.setLayout(new BoxLayout(this, LEFT));
		this.controller = new DependentButtonController(this);
		this.relation = relation;
		this.client = client;
		client.addObserver(this);
		this.imageIcon = new ImageIcon(client.getThumb());
		this.action = controller.action();
		this.setAction(action);
		this.setHorizontalAlignment(JButton.LEFT);
		setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width
				/ 3.4), 0));
	}	

	@Override
	public void update(Observable arg0, Object arg1) {
		this.imageIcon = new ImageIcon(client.getThumb());
		action = controller.action();
		this.setAction(action);
		revalidate();
		repaint();
	}
	public Client getClient(){
		return this.client;
	}
	public ImageIcon getIcon(){
		return this.imageIcon;
	}
	public String getRelation(){
		return this.relation.getRelation();
	}
}

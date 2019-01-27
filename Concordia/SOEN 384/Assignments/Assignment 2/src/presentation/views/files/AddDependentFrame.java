package presentation.views.files;

import java.awt.event.ActionListener;

import javax.swing.JFrame;


import domain.files.Client;
import domain.files.Household;
import domain.files.Relation;

import net.miginfocom.swing.MigLayout;


public class AddDependentFrame extends JFrame{

	private static final long serialVersionUID = 431278490321L;
	
	private ClientFormPanel clientPanel;
	private RelationComboBoxPanel panel;
	
	public AddDependentFrame(Household house) {
		this.setLayout(new MigLayout());
		panel = new RelationComboBoxPanel(null, null);
		panel.setLayout(new MigLayout());
		clientPanel = new ClientFormPanel();
		String addDepSubmitText="Add Dependent";
		clientPanel.setSubmitText(addDepSubmitText);
		add(clientPanel, "wrap");
		clientPanel.addToFormPanel(panel, "align center, span, grow");
		pack();
		setVisible(true);
	}
	
	public void addEventSubmitListener(ActionListener submit)
	{
		clientPanel.addSubmitListener(submit);
	}
	
	public void setEventSubmitButtonText(String text){
		clientPanel.setSubmitText(text);
	}
	public void addEventDeleteListener(ActionListener delete)
	{
		clientPanel.getDeleteButton().addActionListener(delete);
	}
	
	
	public Client getClient()
	{
		Client client = clientPanel.retrieveClient();
		if (client != null)
			return clientPanel.retrieveClient();
		else return null;
	}
	
	public Relation getRelation()
	{
		Relation relation = panel.getBox().getBoxModel().getSelected();
		if (relation != null){
			panel.setWarning(null);
			return relation;
		} else {
			String relationWarning="Please enter a relation";
			panel.setWarning(relationWarning);
			return null;
		}
	}
}


package presentation.controllers.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

import domain.files.Client;
import domain.files.FileManager;

import presentation.views.files.ClientDetailFrame;
import presentation.views.files.DependentButton;
import presentation.views.files.RelationComboBoxPanel;

public class DependentButtonController {

	private DependentButton dependentButton;
	private ClientDetailFrame clientDetailFrame;
	private FileManager fileManager = FileManager.getUniqueInstance();

	public DependentButtonController(DependentButton button) {
		this.dependentButton = button;
	}

	public AbstractAction action() {
		String depButtonNameText="<html>Name: ";
		String depButtonAgeText="<br>Age: ";
		String depButtonRelationText="<br>Relation: ";
		String depButtonEndTagText="</html>";
		
		return new AbstractAction(depButtonNameText
				+ dependentButton.getClient().getFullName() + depButtonAgeText 
				+ dependentButton.getClient().getAge() +depButtonRelationText 
				+ dependentButton.getRelation()
				+ depButtonEndTagText, dependentButton.getIcon()) {
			private static final long serialVersionUID = -43278190432342235L;

			// This method is called when the button is pressed
			public void actionPerformed(ActionEvent evt) {
				clientDetailFrame = new ClientDetailFrame(
						dependentButton.getClient(),
						fileManager.getActiveFile());
				clientDetailFrame.getClientFormPanel().getDeleteButton()
						.addActionListener(deleteDependantAction());
				clientDetailFrame.getClientFormPanel().getDeleteButton()
						.addActionListener(deleteDependantAction());
				clientDetailFrame.getClientFormPanel().addSubmitListener(
						editClientFile());
				clientDetailFrame.getClientFormPanel().addToFormPanel(
						new RelationComboBoxPanel(dependentButton.getClient(),
								dependentButton.getRelation()), "span, grow");
				clientDetailFrame.setLocationRelativeTo(null);
				clientDetailFrame.pack();
				clientDetailFrame.setVisible(true);
			}
		};
	}

	public ActionListener editClientFile() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientDetailFrame
						.getClientFormPanel()
						.getClient()
						.set(	clientDetailFrame.getClientFormPanel().getFirstName(),
								clientDetailFrame.getClientFormPanel().getMiddleName(), 
								clientDetailFrame.getClientFormPanel().getLastName(), 
								clientDetailFrame.getClientFormPanel().getMedicare(),
								clientDetailFrame.getClientFormPanel().getMotherTongue(),
								clientDetailFrame.getClientFormPanel().getCommLanguage(),
								clientDetailFrame.getClientFormPanel().getMaritalStatus(),
								clientDetailFrame.getClientFormPanel().getWorkStatus(),
								clientDetailFrame.getClientFormPanel().getWorkStatusExtra(),
								clientDetailFrame.getClientFormPanel().getCanadaStatus(),
								clientDetailFrame.getClientFormPanel().getCanadaStatusExtra(),
								clientDetailFrame.getClientFormPanel().getOrigon(),
								clientDetailFrame.getClientFormPanel().getOrigonExtra(),
								clientDetailFrame.getClientFormPanel().getRefferal(),
								clientDetailFrame.getClientFormPanel().getRefferalExtra(),
								clientDetailFrame.getClientFormPanel().getReason(),
								clientDetailFrame.getClientFormPanel().getReasonExtra(),
								clientDetailFrame.getClientFormPanel().getPhoto(), 
								clientDetailFrame.getClientFormPanel().getThumb(),
								clientDetailFrame.getClientFormPanel().getDateOfBirth(), 
								clientDetailFrame.getClientFormPanel().getSex());
				if (!clientDetailFrame.getClientFormPanel()
						.getClientImagePanel().isChanged()) {
					FileManager.getUniqueInstance().saveClientWithoutPhoto(
							clientDetailFrame.getClientFormPanel().getClient());
				} else {
					FileManager.getUniqueInstance().commitClient(
							clientDetailFrame.getClientFormPanel().getClient());
				}
				clientDetailFrame.dispose();
				dependentButton.update(null, null);
				dependentButton.revalidate();
				dependentButton.repaint();
			}
		};
	}

	private ActionListener deleteDependantAction() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Client client = clientDetailFrame.getClientFormPanel()
						.getClient();
				fileManager.removeDependent(client);
				clientDetailFrame.dispose();
				fileManager.getActiveFile().update();
			}
		};
	}

	public void addDeleteAction(ActionListener action) {
		clientDetailFrame.getClientFormPanel().getDeleteButton()
				.addActionListener(action);
	}
}

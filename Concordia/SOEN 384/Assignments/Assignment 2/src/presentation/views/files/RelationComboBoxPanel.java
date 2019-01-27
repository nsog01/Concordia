package presentation.views.files;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.models.files.RelationComboBoxModel;



import domain.files.Client;

public class RelationComboBoxPanel extends JPanel{

	private static final long serialVersionUID = 432148329104L;
	private JLabel warning;
	private RelationComboBox relationBox;
	
	public RelationComboBoxPanel(Client client, String string) {
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		String relationComboBorderText="Relation Selection";
		String relationComboAddRelText="Relation";
		setBorder(BorderFactory.createTitledBorder(loweredetched,
				relationComboBorderText));
		add(new JLabel(relationComboAddRelText));
		RelationComboBoxModel model = new RelationComboBoxModel();
		relationBox = new RelationComboBox(model);
		warning = new JLabel();
		warning.setForeground(Color.RED);
		add(warning, "wrap");
		relationBox.setSelectedItem(string);
		add(relationBox, "wrap, align center, span");
	}
	public RelationComboBox getBox(){
		return relationBox;
	}
	public void setWarning(String text){
		warning.setText(text);
	}
}

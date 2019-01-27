package presentation.views.files;

import javax.swing.JComboBox;

import domain.files.Language;
import domain.files.Province;


import presentation.models.files.ProvinceListComboBoxModel;

public class ProvinceComboBox extends JComboBox{
	
	private static final long serialVersionUID = 187025784923L;

	public ProvinceListComboBoxModel getBoxModel(){
		return (ProvinceListComboBoxModel) this.dataModel;
	}
	
	public ProvinceComboBox(ProvinceListComboBoxModel model){
		super(model);
	}

	public Province getSelected()
	{
		return this.getBoxModel().getSelected();
	}
}
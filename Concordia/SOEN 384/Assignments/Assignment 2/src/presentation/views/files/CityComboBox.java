package presentation.views.files;

import javax.swing.JComboBox;

import domain.files.City;
import domain.files.Language;


import presentation.models.files.CityListComboBoxModel;
import presentation.models.files.ProvinceListComboBoxModel;

public class CityComboBox extends JComboBox{
	
	private static final long serialVersionUID = 187025784923L;

	public CityListComboBoxModel getBoxModel(){
		return (CityListComboBoxModel) this.dataModel;
	}
	
	public CityComboBox(CityListComboBoxModel model){
		super(model);
	}
	public City getSelected()
	{
		return this.getBoxModel().getSelected();
	}

	
}
package presentation.views.files;

import javax.swing.JComboBox;

import domain.files.Language;

import presentation.models.files.LanguageListComboBoxModel;



public class LanguageComboBox extends JComboBox{


	private static final long serialVersionUID = 47839054325342L;


	public LanguageListComboBoxModel getBoxModel(){
		return (LanguageListComboBoxModel) this.dataModel;
	}
	public Language getSelected()
	{
		return this.getBoxModel().getSelected();
	}
	
	public LanguageComboBox(){
		super(new LanguageListComboBoxModel());
	}
}

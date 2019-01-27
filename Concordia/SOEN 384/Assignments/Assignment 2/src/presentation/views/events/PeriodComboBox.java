package presentation.views.events;

import javax.swing.JComboBox;

import presentation.models.events.PeriodComboBoxModel;

public class PeriodComboBox extends JComboBox {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 432679854L;

	public PeriodComboBoxModel getBoxModel(){
		return (PeriodComboBoxModel) this.dataModel;
	}
	
	public PeriodComboBox(PeriodComboBoxModel model){
		super(model);
	}
}

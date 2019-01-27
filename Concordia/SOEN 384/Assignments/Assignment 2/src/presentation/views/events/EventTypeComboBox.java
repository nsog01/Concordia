package presentation.views.events;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import presentation.models.events.EventTypeBoxModel;

public class EventTypeComboBox extends JComboBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 44326543644L;

	public EventTypeBoxModel getBoxModel(){
		return (EventTypeBoxModel) this.dataModel;
	}
	
	public EventTypeComboBox(ComboBoxModel model){
		super(model);
	}
}

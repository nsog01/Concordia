package presentation.models.events;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import domain.events.Event.Period;

public class PeriodComboBoxModel implements ComboBoxModel {

	Period[] periodList;
	Period selectedItem;
	
	public PeriodComboBoxModel(){
		this.periodList = Period.values();
		this.selectedItem = periodList[3];
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getElementAt(int arg0) {
		return this.periodList[arg0];
	}

	@Override
	public int getSize() {
		return this.periodList.length;
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		
	}

	@Override
	public Object getSelectedItem() {
		return this.selectedItem;
	}

	@Override
	public void setSelectedItem(Object arg0) {
		this.selectedItem = (Period) arg0;

	}

}

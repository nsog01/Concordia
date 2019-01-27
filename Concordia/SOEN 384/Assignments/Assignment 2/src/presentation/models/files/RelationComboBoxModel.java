package presentation.models.files;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import domain.files.FileManager;
import domain.files.Relation;
import domain.files.RelationList;


public class RelationComboBoxModel implements ComboBoxModel{

	RelationList list;
	Relation selectedItem;
	
	public RelationComboBoxModel(){
		this.list = FileManager.getUniqueInstance().getAllRelations();
		this.selectedItem = list.getRelationAt(0);
		this.setSelectedItem(list.getRelationAt(0).getRelation());
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getElementAt(int arg0) {
		if (list.getRelationAt(arg0) != null)
			return list.getRelationAt(arg0).getRelation();
		else
			return null;
	}

	@Override
	public int getSize() {
		return this.list.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		
	}
	public Relation getSelected(){
		return this.selectedItem;
	}
	@Override
	public Object getSelectedItem() {
		if (selectedItem != null)
			return this.selectedItem.getRelation();
		else 
			return null;
	}
	
	@Override
	public void setSelectedItem(Object arg0) {
		if (arg0 == null) 
			this.selectedItem = null;
		else
			this.selectedItem = list.get(arg0);

	}

}

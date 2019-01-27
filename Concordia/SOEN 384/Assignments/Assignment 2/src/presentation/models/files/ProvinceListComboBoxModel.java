package presentation.models.files;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import domain.files.FileManager;
import domain.files.Province;
import domain.files.ProvinceList;

public class ProvinceListComboBoxModel implements ComboBoxModel{
	ProvinceList list;
	Province selectedItem;
	
	public ProvinceListComboBoxModel(){
		this.list = FileManager.getUniqueInstance().getAllProvinces();
		this.selectedItem = list.getProvinceAt(0);
		this.setSelectedItem(list.getProvinceAt(0).getProvince());
	}
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getElementAt(int arg0) {
		if (list.getProvinceAt(arg0) != null)
			return list.getProvinceAt(arg0).getProvince();
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
	public Province getSelected(){
		return this.selectedItem;
	}
	@Override
	public Object getSelectedItem() {
		if (selectedItem != null)
			return this.selectedItem.getProvince();
		else 
			return null;
	}
	public String getSelectedProvince(){
		if (selectedItem != null)
			return this.selectedItem.getProvince();
		else 
			return null;
	}
	@Override
	public void setSelectedItem(Object arg0) {
		if (arg0 == null) 
			selectedItem = null;
		else
			for (Province l: list){
				if (arg0.equals(l.getProvince())){
					selectedItem = l;
					break;
				}
				selectedItem = null;
			}

	}
	
}
/*package presentation.models.files;



public class LanguageListComboBoxModel implements ComboBoxModel{
	LanguageList list;
	Language selectedItem;
	
	public LanguageListComboBoxModel() throws SQLException, IOException{
		this.list = FileManager.getUniqueInstance().getAllLanguages();
		this.selectedItem = list.getLanguageAt(0);
		this.setSelectedItem(list.getLanguageAt(0).getLanguage());
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getElementAt(int arg0) {
		if (list.getLanguageAt(arg0) != null)
			return list.getLanguageAt(arg0).getLanguage();
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
	public Language getSelected(){
		return this.selectedItem;
	}
	@Override
	public Object getSelectedItem() {
		if (selectedItem != null)
			return this.selectedItem.getLanguage();
		else 
			return null;
	}
	
	@Override
	public void setSelectedItem(Object arg0) {
		if (arg0 == null) 
			selectedItem = null;
		else
			for (Language l: list){
				if (arg0 == l.getLanguage()){
					selectedItem = l;
					break;
				}
				selectedItem = null;
			}

	}
	
}

*/
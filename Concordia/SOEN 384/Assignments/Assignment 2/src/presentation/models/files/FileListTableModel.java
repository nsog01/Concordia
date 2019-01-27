package presentation.models.files;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.files.FileList;
import domain.files.Household;

public class FileListTableModel extends FileList implements TableModel{

	private String[] header = {"Client ID", "Last Name", "First Name", "Medicare", "Telephone"};
	protected List<TableModelListener> observers = new ArrayList<TableModelListener>();
	
	public FileListTableModel(){
		super();
	}
	public FileListTableModel(ArrayList<Household> houses){
		super(houses);
	}
	public void addToList(FileList fileList){
		for(Household h : fileList){
			this.add(h);
		}
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	public void setList(FileList fileList){
		this.clear();
		for(Household h : fileList){
			this.add(h);
		}
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	@Override
	public void addTableModelListener(TableModelListener aListener) {
		observers.add(aListener);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Long.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public String getColumnName(int col) {
		return header[col];
	}

	@Override
	public int getRowCount() {
		return this.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(this.isEmpty())
			return null;
		Household house = this.houses.get(row);
		
		switch (col) {
		case 0:
			return house.getFileNumber();
		case 1:
			return house.getRepresentative().getLastName();
		case 2: 
				return house.getRepresentative().getFirstName();
		case 3:
			return house.getRepresentative().getMedicare();
		case 4:
			return house.getTelString();
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		observers.remove(arg0);		
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		return;
	}
	public Household getSelected(int row){
		return houses.get(row);
	}
}

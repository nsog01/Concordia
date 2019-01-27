package presentation.models.files;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.files.Client;
import domain.files.ClientList;
import domain.files.FileRepository;
import domain.files.Household;

public class DependentListTableModel implements TableModel, Observer{

	private String[] header = {"Relation", "Age", "Name"};
	protected List<TableModelListener> observers = new ArrayList<TableModelListener>();
	
	Household house;
	ArrayList<Client> clients;
	
	public DependentListTableModel(){
		house = null;
		clients = new ArrayList<Client>();		
	}
	public DependentListTableModel(ArrayList<Household> houses){
	}
	public void setHouse(Household myHouse)
	{
		this.house = myHouse;
		clients.clear();
		
		//populate dependents table
		ClientList dependentsList = house.getDependents();
		for (Client dep : dependentsList) {
			clients.add(dep);
			//Relation r=  house.getDependentsList().get(c);
		}
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	
	public void clearHouse()
	{
		this.clients.clear();
	}
	@Override
	public void addTableModelListener(TableModelListener aListener) {
		observers.add(aListener);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
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
		return clients.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(clients.isEmpty())
			return null;
		Client client = clients.get(row);
		
		switch (col) {
		case 0:
			return house.getDependentsList().get(client).getRelation();
		case 1:
			return client.getAge();
		case 2: 
			return client.getFullName();
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
	public Client getSelected(int row){
		return clients.get(row);
	}
	@Override
	public void update(Observable o, Object arg) {
		setHouse(((FileRepository) o).getActiveFile());
	}
}

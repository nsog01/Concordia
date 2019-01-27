package presentation.models.appointments;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Appointments.Appointment;
import domain.Appointments.AppointmentList;

public class AppointmentTableModel implements TableModel, Observer{
	
	private String[] header = {"Name", "Date", "Start Time", "End Time"};
	private AppointmentList appList = new AppointmentList();
	protected List<TableModelListener> observers = new ArrayList<TableModelListener>();
	
	
	public AppointmentTableModel(AppointmentList list) {
		super();
		list.addObserver(this);
		setList(list);
	}
	public void addToList(final AppointmentList appointmentList){
		
				for(Appointment a : appointmentList){
					appList.add(a);
				}	

		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	public void setList(final AppointmentList list){

				appList = list;	

		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}

	@Override
	public void addTableModelListener(TableModelListener aListener) {
		observers.add(aListener);		
	}
	
	@Override
	public void removeTableModelListener(TableModelListener aListener) {
		observers.remove(aListener);
	}

	@Override
	public Class<?> getColumnClass(int c) {
		switch (c) {
		case 0:
			return String.class;
		case 1:
			return Date.class;
		case 2:
			return String.class;
		case 3:
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
	public int getRowCount() {
		//return eventHash.size();
		return appList.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return header[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		//if (eventHash.isEmpty())
		//	return null;
		if(appList.isEmpty())
			return null;
		
		//Event event = this.get(getKey(row));
		Appointment app = appList.get(row);
		
		switch (col) {
		case 0:
			return app.getClient().getFullName();
		case 1:
			return app.getEvent().getDate();
		case 2:
			SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
			return f.format(app.getTimeslot().getStartTime());

		case 3:
			f = new SimpleDateFormat("HH:mm:ss");
			return f.format(app.getTimeslot().getEndTime());
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		return;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {

		return false;
	}

	public void add(Appointment a){
		appList.add(a);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	public Appointment getSelected(int selectedRow) {
		return appList.get(selectedRow);
	}


}

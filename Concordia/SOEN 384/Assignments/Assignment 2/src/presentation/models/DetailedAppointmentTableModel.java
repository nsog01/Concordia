package presentation.models.appointments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Appointments.Appointment;
import domain.Appointments.AppointmentList;

public class DetailedAppointmentTableModel implements TableModel, Observer{
	
	private String[] header = {"Photo", "Name", "Date", "Start Time", "End Time", "Check-In"};
	private AppointmentList appList = new AppointmentList();
	private AppointmentList pushList = new AppointmentList();
	protected List<TableModelListener> observers = new ArrayList<TableModelListener>();
	
	public DetailedAppointmentTableModel(){
		
	}
	public DetailedAppointmentTableModel(AppointmentList list) {
		list.addObserver(this);
		setList(list);
	}

	public void addToPush(Appointment a){
		pushList.add(a);
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
					list.addObserver(this);
					pushList.clear();
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	protected AppointmentList getList(){
		return appList;
	}
	public void setPushList(AppointmentList list){
		pushList = list;
	}
	protected AppointmentList getPushList(){
		return pushList;
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
			return ImageIcon.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
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
			return new ImageIcon(app.getClient().getThumb());
		case 1:
			return "  "+app.getClient().getFullName();
		case 2:
			return "  "+app.getEvent().getDate();
		case 3:
			SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
			return "  "+f.format(app.getTimeslot().getStartTime());

		case 4:
			f = new SimpleDateFormat("HH:mm:ss");
			return "  "+f.format(app.getTimeslot().getEndTime());
		case 5:
			return "  "+app.getArrivalString();
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
	public void search(String search){
		AppointmentList list = new AppointmentList();
		for (Appointment a : appList){
			if (!a.getClient().getFullName().toLowerCase().contains(search.toLowerCase()) &&
					!Integer.toString(a.getClient().getId()).substring(
							0, search.length()).equals(search)){
				pushList.add(a);
			}	else {
				list.add(a);
			}
		}
		appList = list;
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	public void backspaceSearch(String search) {
		AppointmentList list = new AppointmentList();
		for (Appointment a : pushList){
			if (a.getClient().getFullName().toLowerCase().contains(search.toLowerCase()) ||
					Integer.toString(a.getClient().getId()).substring(
							0, search.length()).equals(search)){
				appList.add(a);
			} else {
				list.add(a);
			}
			pushList = list;
		}
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	public Appointment getSelected(int selectedRow) {
		return appList.get(selectedRow);
	}
	public int[] getArrivedAndAbsent(){
		int[] arrivedAndAbsent = {0, 0};
		
		for (Appointment a : appList){
			if (a.getArrival() == null){
				arrivedAndAbsent[1]++;
			} else {
				arrivedAndAbsent[0]++;
			}
		}
		for (Appointment a: pushList){
			System.out.println(a.getArrival());
			if (a.getArrival() == null){
				arrivedAndAbsent[1]++;
			} else {
				arrivedAndAbsent[0]++;
			}
		}
		return arrivedAndAbsent;
	}
}

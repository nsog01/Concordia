package presentation.models.events;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.events.Event;
import domain.events.TimeSlot;
import domain.events.TimeSlotList;

public class TimeSlotTableModel implements TableModel {

	private String[] header = { "", "Event", "Date", "StartTime", "EndTime",
			"Limit", "Current" };
	private TimeSlotList timeSlotList;
	private Event event;
	protected List<TableModelListener> observers = new ArrayList<TableModelListener>();

	public TimeSlotTableModel(Event event) {
		timeSlotList = event.getTimeSlotList();
		this.event = event;
	}

	public void addToList(TimeSlotList list) {
		for (TimeSlot a : list) {
			timeSlotList.add(a);
		}
		for (TableModelListener l : observers) {
			l.tableChanged(new TableModelEvent(this));
		}
	}

	public void setList(TimeSlotList list) {
		timeSlotList.clear();
		for (TimeSlot t : list) {
			timeSlotList.add(t);
		}
		for (TableModelListener l : observers) {
			l.tableChanged(new TableModelEvent(this));
		}
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		observers.add(arg0);
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		switch (arg0) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Date.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
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
		return timeSlotList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// if (eventHash.isEmpty())
		// return null;
		if (timeSlotList.isEmpty())
			return null;

		// Event event = this.get(getKey(row));
		TimeSlot slot = event.getTimeSlotList().get(row);/*
														 * timeSlotList.get(row);
														 */
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		switch (col) {
		case 0:
			return row;
		case 1:
			return event.getTitle();
		case 2:
			return event.getDate();
		case 3:
			return f.format(slot.getStartTime());
		case 4:
			return f.format(slot.getEndTime());
		case 5:

			return slot.getLimit();

		case 6:
			return slot.getAppointmentCount();
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		observers.remove(arg0);
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void add(TimeSlot a) {
		timeSlotList.add(a);
	}

	public TimeSlot getSelected(int selectedRow) {
		return timeSlotList.get(selectedRow);
	}

	public void setList(Event event2) {
		event = event2;
		setList(event2.getTimeSlotList());
	}

}

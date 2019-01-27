package presentation.models.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.events.EventManager;
import domain.mappers.reports.ReportMapper;
import domain.reports.Report;
import domain.reports.ReportList;

public class ReportTableModel extends ReportList implements TableModel{

	private String[] header = {/*"id", */"Name", "Description", "Department", "Path"};
	protected List<TableModelListener> observers = new ArrayList<TableModelListener>();
	
	public ReportTableModel() {
		super();
	}
	
	public ReportTableModel(ReportList list) {
		super();
		setList(list);
		for (TableModelListener anObserver: observers)
			anObserver.tableChanged(new TableModelEvent(this));
	}
	
	public void setList(ReportList list){
		clear();
		for (Report r : list){
			add(r);
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
	public void removeTableModelListener(TableModelListener aListener) {
		observers.remove(aListener);
		
	}

	@Override
	public Class<?> getColumnClass(int c) {
		switch (c) {
		case 0:
//			return Integer.class;
			return String.class;
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
		return size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(isEmpty())
			return null;
		
		Report report = this.getReportAt(row);
		
		switch (col) {
//		case 0:
//			return report.getId();
		case 0:
			return report.getName();
		case 1:
			return report.getDesc();
		case 2: 
			return report.getDept();
		case 3:
			return report.getPath();
		default:
			return null;
		}
	}
	
	public Report getSelected(int selectedRow) {
		return get(selectedRow);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		setReportList(new ReportMapper().readAll());
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}

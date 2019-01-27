package presentation.models.files;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.GList;
import domain.IList;
import domain.files.Note;

public class NoteListTableModel implements TableModel, Observer{

	private String[] header = {"Date", "User", "Note"};
	private List<TableModelListener> observers = new ArrayList<TableModelListener>();
	private GList<Note> notes;
	
	public NoteListTableModel(){
		notes = new GList<Note>();
		
	}
	public NoteListTableModel(GList<Note> list) {
		list.addObserver(this);
		setList(list);
	}
	private void notifiyListeners(){
		for (TableModelListener l : observers){
			l.tableChanged(new TableModelEvent(this));
		}
	}
	@SuppressWarnings("unchecked")
	public void setList(IList addList){
		notes.clear();
		notes.addAll((GList<Note>) addList);
		notifiyListeners();
	}
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		observers.add(arg0);
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		switch(arg0){
		case 0:
			return Date.class;
		case 1: 
			return String.class;
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
	public String getColumnName(int arg0) {
		return header[arg0];
	}

	@Override
	public int getRowCount() {
		return notes.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Note note = notes.get(row);
		switch(col){
		case 0:
			return note.getDate();
		case 1:
			return note.getUser();
		case 2:
			return note.getNote();
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
	public void setValueAt(Object arg0, int arg1, int arg2) {
		//not implemented
	}
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {
			setList(((GList<Note>)arg0));
	}
	public GList<Note> clearList() {
		notes.clear();
		return notes;
	}
	public Note getSelected(int index){
		return notes.get(index);
	}
	
}

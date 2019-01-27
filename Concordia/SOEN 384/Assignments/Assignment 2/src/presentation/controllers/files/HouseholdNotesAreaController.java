package presentation.controllers.files;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import domain.files.FileManager;
import domain.files.FileRepository;
import domain.files.Household;
import domain.files.Note;

import presentation.models.files.NoteListTableModel;
import presentation.views.files.HouseholdNotesArea;

public class HouseholdNotesAreaController implements Observer{

	private HouseholdNotesArea view;
	private FileManager fileManager = FileManager.getUniqueInstance();

	public HouseholdNotesAreaController(Household house){
		fileManager.addObserverToRepository(this);
		view = new HouseholdNotesArea(house);
		view.setNoteTableListener(noteSelect());
		addUpdateListener(saveNotesListener());
		addNewListener(newNote());
	}
	
	public HouseholdNotesAreaController() {
		fileManager.addObserverToRepository(this);
		view = new HouseholdNotesArea();
		view.setNoteTableListener(noteSelect());
		addUpdateListener(saveNotesListener());
		addNewListener(newNote());
	}

	public void set(Household house) {
		if (house != null){
		getTextArea().setText(house.getNotes());
		view.setTableList(house.getNoteList());
		} else {
		getTextArea().setText(null);
		view.setTableList(((NoteListTableModel) view.getTable().getModel()).clearList());
		}
	}
	
	public void setTextAreaSize(int w, int h) {
		Dimension size = new Dimension(w, h);
		getScrollPane().setPreferredSize(size);
		view.getTablePane().setPreferredSize(size);
	}
	
	public String getNote(){
		return getTextArea().getText();
	}
	
	public JButton getSaveButton(){
		return getSaveNotesButton();
	}
	
	public JTextArea getTextArea(){
		return view.getTextArea();
	}
	
	public void setSaveNotesButton(JButton button){
		view.setSaveNotesButton(button);
	}
	
	public JButton getSaveNotesButton(){
		return view.getSaveNotesButton();
	}
	
	public void setScrollPane(JScrollPane pane){
		view.setScrollPane(pane);
	}
	public JScrollPane getScrollPane(){
		return view.getScrollPane();
	}
	public JTable getTable() {
		return view.getTable();
	}

	public HouseholdNotesArea getView() {
		return view;
	}

	public void setView(HouseholdNotesArea view) {
		this.view = view;
	}

	public void addUpdateListener(ActionListener action){
		view.getSaveNotesButton().addActionListener(action);
	}
	
	public void addNewListener(ActionListener action){
		view.getNewButton().addActionListener(action);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		set(((FileRepository) o).getActiveFile());
	}
	
	private MouseListener noteSelect(){
		return new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2){
					Note note = view.getTable().getSelected();
					setTextArea(note);
					view.switchToText();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		};
	}

	public ActionListener newNote(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Note note = new Note();
				setTextArea(note);
				view.switchToText();
			}
			
		};
	}
	public ActionListener saveNotesListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Note note = getView().getNote();
				note.setNote(getNote());
				note.setDate(Calendar.getInstance().getTime());
				note.setUser(System.getProperty("user.name"));
				fileManager.saveNote(note);
				view.switchToTable();
			}

		};
	}
	
	protected void setTextArea(Note note) {
		view.getTextArea().setText(note.getNote());
		view.setNote(note);
	}
}

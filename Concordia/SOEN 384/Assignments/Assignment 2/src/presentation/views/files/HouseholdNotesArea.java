package presentation.views.files;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.models.files.NoteListTableModel;

import net.miginfocom.swing.MigLayout;

import domain.GList;
import domain.files.Household;
import domain.files.Note;

public class HouseholdNotesArea extends JPanel{


	private JTextArea textArea;
	private JButton saveNotesButton;
	private JScrollPane scrollPane;
	private NoteListTableModel model;
	private JScrollPane tablePane;
	private NoteTable table;
	private boolean textVisible = false;
	private JButton newNote;
	private Note note;
	
	private static final long serialVersionUID = 678943259322L;

	public HouseholdNotesArea(Household house) {
		initializeElements(house);
		addComponents();
	}

	public HouseholdNotesArea() {
		initializeElements(null);
		addComponents();
	}

	private void initializeElements(Household house) {
		
		
		String hNotesBorderText="Notes";
		String hNotesNewNoteText="New Note";
		String hNotesSaveNoteText="Save Note";
		setLayout(new MigLayout());
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		setBorder(BorderFactory.createTitledBorder(loweredetched,
				hNotesBorderText));
		if (house == null) {
			setTextArea(new JTextArea());
		} else {
			setTextArea(new JTextArea(house.getNotes()));
		}
		getTextArea().setLineWrap(true);
		getTextArea().setWrapStyleWord(true);
		setSaveNotesButton(new JButton(hNotesSaveNoteText));
		setNewButton(new JButton(hNotesNewNoteText));
		getTextArea().setSize(getSize());
		setScrollPane(new JScrollPane(getTextArea()));
		
		//setup table
		table = new NoteTable();
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		tablePane = new JScrollPane(table);
	}
	
	private void setNewButton(JButton jButton) {
		newNote = jButton;
	}
	
	public JButton getNewButton(){
		return newNote;
	}
	private void setTablePane(JScrollPane jScrollPane) {
		tablePane = jScrollPane;
	}
	
	public JScrollPane getTablePane(){
		return tablePane;
	}

	private void addComponents() {
		add(getTablePane(), "center");
		add(newNote, "south");
		newNote.setEnabled(false);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getSaveNotesButton() {
		return saveNotesButton;
	}
	
	public void setSaveNotesButton(JButton saveNotesButton) {
		this.saveNotesButton = saveNotesButton;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public NoteTable getTable() {
		return table;
	}

	public void setTable() {
		this.table = new NoteTable();
		setTablePane(new JScrollPane(table));
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setTableList(GList<Note> addList) {
		table.getTableModel().setList(addList);
	}
	
	public void switchToTable(){
		if (textVisible){
				remove(scrollPane);
				remove(saveNotesButton);
				add(tablePane, "center");
				add(newNote, "south");
				textVisible = false;
				SwingUtilities.invokeLater(new Runnable(){

					@Override
					public void run() {
						repaint();
					}
					
				});
		}
	}
	public void switchToText(){
		if (!textVisible){
				remove(tablePane);
				remove(newNote);
				add(scrollPane, "center");
				add(saveNotesButton, "south");
			SwingUtilities.invokeLater(new Runnable(){

				@Override
				public void run() {
					revalidate();
					repaint();
				}
				
			});
				textVisible = true;
			}
	}

	public NoteListTableModel getTableModel() {
		return model;
	}

	public void setNoteTableListener(MouseListener noteSelect) {
		table.addMouseListener(noteSelect);
	}
	public void setNewButtonListener(ActionListener listen){
			newNote.addActionListener(listen);
	}

	public void setNote(Note note) {
		this.note = note;
	}
	public Note getNote(){
		return note;
	}
}

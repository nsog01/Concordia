package presentation.views.files;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.KeyStroke;

import domain.files.Note;

import presentation.models.files.NoteListTableModel;

public class NoteTable extends JTable{

	private static final long serialVersionUID = 8490784832423L;
	private final KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
	private final KeyStroke shiftTabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB + KeyEvent.VK_SHIFT, 0);
	
	public NoteTable(){
		setModel(new NoteListTableModel());
		getColumnModel().getColumn(0).setPreferredWidth(50);
		getColumnModel().getColumn(1).setPreferredWidth(50);
		getColumnModel().getColumn(2).setPreferredWidth(150);
	}
	
	public NoteListTableModel getTableModel(){
		return (NoteListTableModel) getModel();
	}
	
	public Note getSelected(){
		return getTableModel().getSelected(getSelectedRow());
	}
	public void changeSelection(int row, int col, boolean toggle, boolean extend) { 
	        AWTEvent currentEvent = EventQueue.getCurrentEvent(); 
	        if(currentEvent instanceof KeyEvent){ 
	            KeyEvent ke = (KeyEvent)currentEvent; 
	            if(ke.getSource()!=this) 
	                return; 
	            // focus change with keyboard 

	           if(KeyStroke.getKeyStrokeForEvent(ke).equals(tabKeyStroke)){ 

					removeRowSelectionInterval(row, row);
					removeColumnSelectionInterval(col, col);
					
					try{
						addRowSelectionInterval(row+1, row+1);
					} catch (IllegalArgumentException e){
						addRowSelectionInterval(0, 0);
					}
					addColumnSelectionInterval(0, 0);
					return;
	            }
	           else if(KeyStroke.getKeyStrokeForEvent(ke).equals(shiftTabKeyStroke)){
	        	   removeRowSelectionInterval(row, row);
					removeColumnSelectionInterval(col, col);
					
					try{
						addRowSelectionInterval(row-1, row-1);
					} catch (IllegalArgumentException e){
						super.changeSelection(row, 0, toggle, extend); 
					}
					addColumnSelectionInterval(0, 0);
					return;
	           }
	        } 
	        super.changeSelection(row, 0, toggle, extend); 
	    } 
}

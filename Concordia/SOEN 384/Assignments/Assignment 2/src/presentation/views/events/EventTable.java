package presentation.views.events;


import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import domain.events.Event;
import domain.events.EventList;

import presentation.models.events.EventTableModel;

public class EventTable extends JTable{

	private static final long serialVersionUID = 7894032146L;
	private final KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
	private final KeyStroke shiftTabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB + KeyEvent.VK_SHIFT, 0);

	public EventTable(EventTableModel model){
		super(model);
		init();
	}
	public void init(){
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.getColumnModel().getColumn(0).setPreferredWidth(10);
		this.getColumnModel().getColumn(1).setPreferredWidth(130);
		this.getColumnModel().getColumn(2).setPreferredWidth(130);
		this.getColumnModel().getColumn(3).setPreferredWidth(80);
		this.getColumnModel().getColumn(4).setPreferredWidth(60);
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	public boolean add(Event event){
		if (getModel() != null){
			((EventTableModel) getModel()).add(event);
			return true;
		} else{
			return false;
		}
	}
	public void addToList (EventList eventList){
		((EventTableModel) getModel()).addToList(eventList);
	}
	public void setList (EventList eventList){
		((EventTableModel) getModel()).setList(eventList);
	}
	public Event getSelected(){
		return ((EventTableModel) getModel()).getSelected(getSelectedRow());
	}
	public Event getEvent(int index) {
		return ((EventTableModel) getModel()).getSelected(index);
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

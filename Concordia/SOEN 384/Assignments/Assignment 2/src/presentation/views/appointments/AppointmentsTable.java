package presentation.views.appointments;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;

import domain.Appointments.Appointment;
import domain.Appointments.AppointmentList;

import presentation.models.appointments.AppointmentTableModel;

public class AppointmentsTable extends JTable{

	private static final long serialVersionUID = 7894032146L;
	private final KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0); 
	private final KeyStroke shiftTabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB + KeyEvent.VK_SHIFT, 0);

	public AppointmentsTable(TableModel detailedAppointmentTableModel){
		super(detailedAppointmentTableModel);
		init();
	}
	public void init(){
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.getColumnModel().getColumn(0).setPreferredWidth(140);
		this.getColumnModel().getColumn(1).setPreferredWidth(80);
		this.getColumnModel().getColumn(2).setPreferredWidth(80);
		this.getColumnModel().getColumn(3).setPreferredWidth(110);
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(true);

		Action action = new AbstractAction(){

			private static final long serialVersionUID = 432478392014L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System	.out.println("hello");
				int row = getSelectedRow();
				int col = getSelectedColumn();
				removeRowSelectionInterval(row, row);
				removeColumnSelectionInterval(col, col);
				addRowSelectionInterval(row+1, row+1);
				addColumnSelectionInterval(0, 0);
			}
			
		};
		ActionMap map = getActionMap();
		map.put( KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), action);
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
 
	 
	 
	public boolean add(Appointment a){
		if (getModel() != null){
			((AppointmentTableModel) getModel()).add(a);
			return true;
		} else{
			return false;
		}
	}
	public void addToList (AppointmentList eventList){
		((AppointmentTableModel) getModel()).addToList(eventList);
	}
	public void setList (AppointmentList eventList){
		((AppointmentTableModel) getModel()).setList(eventList);
		repaint();
	}
	public Appointment getSelected(){
		return ((AppointmentTableModel) getModel()).getSelected(getSelectedRow());
	}
}

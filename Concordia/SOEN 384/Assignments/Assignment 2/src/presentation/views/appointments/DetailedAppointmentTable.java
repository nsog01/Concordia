package presentation.views.appointments;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import presentation.models.appointments.DetailedAppointmentTableModel;
import domain.Appointments.Appointment;
import domain.Appointments.AppointmentList;

public class DetailedAppointmentTable extends JTable{


	private static final long serialVersionUID = 7894032146L;
	private final KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
	private final KeyStroke shiftTabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB + KeyEvent.VK_SHIFT, 0);

	public DetailedAppointmentTable(TableModel detailedAppointmentTableModel){
		super(detailedAppointmentTableModel);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);
		init();
	}
	public void init(){
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.getColumnModel().getColumn(0).setMaxWidth(45);
		this.getColumnModel().getColumn(1).setPreferredWidth(140);
		this.getColumnModel().getColumn(2).setPreferredWidth(80);
		this.getColumnModel().getColumn(3).setPreferredWidth(80);
		this.getColumnModel().getColumn(4).setPreferredWidth(110);
		setRowHeight(60);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(true);
	}
	public boolean add(final Appointment a){
		if (getModel() != null){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					((DetailedAppointmentTableModel) getModel()).add(a);
				}
			});

			return true;
		} else{
			return false;
		}
	}
	public void addToList (final AppointmentList eventList){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				((DetailedAppointmentTableModel) getModel()).addToList(eventList);	
			}
		});

	}
	public void setList (final AppointmentList eventList){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				((DetailedAppointmentTableModel) getModel()).setList(eventList);
				repaint();	
			}
		});

	}
	public Appointment getSelected(){
		return ((DetailedAppointmentTableModel) getModel()).getSelected(getSelectedRow());
	}

	public int[] getArrivedAndAbsent(){
		return ((DetailedAppointmentTableModel) getModel()).getArrivedAndAbsent();
	}
	public Appointment getSelected(int convertRowIndexToModel) {
		return ((DetailedAppointmentTableModel) getModel()).getSelected(convertRowIndexToModel);
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

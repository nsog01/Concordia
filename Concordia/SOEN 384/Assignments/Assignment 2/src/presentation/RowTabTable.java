package presentation;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;

import domain.IList;

public abstract class RowTabTable extends JTable{

	
	private static final long serialVersionUID = 78940372148903L;
	private final KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);	
	private final KeyStroke shiftTabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB + KeyEvent.VK_SHIFT, 0);
	
	public RowTabTable(TableModel model) {
		super(model);
	}
	public RowTabTable(){
		super();
	}
	public abstract TableModel getTableModel();
	
	public abstract void setList(IList list);
	
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

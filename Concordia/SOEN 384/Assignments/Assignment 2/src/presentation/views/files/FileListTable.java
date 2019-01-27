package presentation.views.files;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import presentation.models.files.FileListTableModel;

import domain.files.FileList;
import domain.files.Household;

public class FileListTable extends JTable{


	private static final long serialVersionUID = 67894456789L;
	private final KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
	private final KeyStroke shiftTabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB + KeyEvent.VK_SHIFT, 0);
	
	public FileListTable(TableModel model){
		super(model);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment( JLabel.LEFT );
		getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableColumnModel cModel = getColumnModel();
		cModel.getColumn(3).setPreferredWidth(80);
		cModel.getColumn(2).setPreferredWidth(60);
		cModel.getColumn(1).setPreferredWidth(130);
		cModel.getColumn(4).setPreferredWidth(130);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public boolean add(Household house){
		if (getModel() != null){
			((FileListTableModel) getModel()).add(house);
			return true;
		} else{
			return false;
		}
	}
	public void addToList (FileList fileList){
		((FileListTableModel) getModel()).addToList(fileList);
	}
	public void setList (FileList fileList){
		((FileListTableModel) getModel()).setList(fileList);
		repaint();
	}
	public Household getSelected(){
		return ((FileListTableModel) getModel()).getSelected(getSelectedRow());
	}

	public void clear() {
		((FileListTableModel) getModel()).clear();
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

package presentation.views.events;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class DeleteCellRenderer extends JButton implements TableCellRenderer {

	public DeleteCellRenderer() {
		setOpaque(true);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8016956982363254793L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
			    if (isSelected) {
			      setForeground(table.getSelectionForeground());
			      setBackground(table.getSelectionBackground());
			    } else {
			      setForeground(table.getForeground());
			      setBackground(UIManager.getColor("Button.background"));
			    }
			    setText((value == null) ? "" : value.toString());
			    return this;
	}

}

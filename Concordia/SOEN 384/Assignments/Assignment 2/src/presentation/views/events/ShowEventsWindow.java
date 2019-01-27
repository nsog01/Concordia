
package presentation.views.events;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import presentation.models.events.EventTableModel;
import presentation.views.files.EmptyBannerPanel;
import presentation.views.menus.GeneralMenuBar;



public class ShowEventsWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8065910045018537898L;

	private EventTableModel model;
	private JTable table;
	JScrollPane scrollPane;
	
	JButton editButton;
	JButton deleteButton;
	JButton addButton;
	JButton cancelButton;
	
	
	public ShowEventsWindow(EventTableModel tableModel)  {
		this.model = tableModel;
		
		initializeButtons();
		initializeTable();
		
		JPanel panel = new JPanel(new MigLayout("","[]","[][]"));
		// Create the scroll pane and add the table to it.
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width/6, screen.height/4);
		setPreferredSize(new Dimension(((int) (screen.width/1.5)), (int) screen.getHeight()/2));
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(getPreferredSize());
		panel.add(scrollPane,"wrap");
		
		panel.add(editButton,"flowx,split 4, span");
		panel.add(deleteButton, "");
		panel.add(addButton, "");
		panel.add(cancelButton, "align right");
		
		getContentPane().add(panel);
		String showEventsPanelText="Manage Events";
		EmptyBannerPanel banner = new EmptyBannerPanel(showEventsPanelText);
		add(banner, BorderLayout.NORTH);
		
		this.setVisible(true);
		String showEventsPanelTitleText="Event Manager";
		this.setTitle(showEventsPanelTitleText);
		
		this.pack();
		
		setJMenuBar(new GeneralMenuBar());
	}
	
	private void initializeButtons()
	{
		String editButtonText="Edit";
		String editButtonTooTip="Alt+E";
		String deleteButtonText="Delete";
		String deleteButtonToolTip="Alt+D";
		String addButtonText="New";
		String addButtonToolTip="Alt+N";
		String showEventsCancelText="Cancel";
		String showEventsCancelToolTip="Alt+C";
		
		editButton = new JButton(editButtonText);
		this.editButton.setToolTipText(editButtonTooTip);
		this.editButton.setMnemonic(KeyEvent.VK_E);
		
	    
		deleteButton = new JButton(deleteButtonText);
	    this.deleteButton.setToolTipText(deleteButtonToolTip);
		this.deleteButton.setMnemonic(KeyEvent.VK_D);
	   
	    
	    addButton = new JButton(addButtonText);
	    this.addButton.setToolTipText(addButtonToolTip);
		this.addButton.setMnemonic(KeyEvent.VK_N);
	    
	    cancelButton = new JButton(showEventsCancelText);
	    this.cancelButton.setToolTipText(showEventsCancelToolTip);
		this.cancelButton.setMnemonic(KeyEvent.VK_C);
		cancelButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	}
	
	private void initializeTable() {
		// initialize our table
		table = new JTable(model);
		model.addTableModelListener(table);
		// enable sorting
		// table.setAutoCreateRowSorter(true);

		// set dimensions
		table.setPreferredScrollableViewportSize(new Dimension(650, 200));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.setFillsViewportHeight(true);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
	}
	
	public int getTableSelectedRow()
	{
		return table.getSelectedRow();
	}
	
	 public void addEditEventListener(ActionListener edit) {
		 	editButton.addActionListener(edit);
	    }
	 
	 public void addDeleteEventListener(ActionListener delete)
	 {
		 deleteButton.addActionListener(delete);
	 } 
	 public void addAddEventListener(ActionListener add)
	 {
		 addButton.addActionListener(add);
	 } 


	
}

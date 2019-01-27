package presentation.views.reports;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import presentation.models.reports.ReportTableModel;
import presentation.views.files.EmptyBannerPanel;

public class ManageReportsWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5493051801617685000L;
	private ReportTableModel model;
	private JTable table;
	JScrollPane scrollPane;
	
	JButton editButton;
	JButton deleteButton;
	JButton addButton;
	JButton showButton;
	
	public ManageReportsWindow(ReportTableModel tableModel)
	{
this.model = tableModel;
		
		initializeButtons();
		initializeTable();
		EmptyBannerPanel banner = new EmptyBannerPanel("Manage Reports");
		add(banner, BorderLayout.NORTH);
		JPanel panel = new JPanel(new MigLayout());
		
		// Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		
		panel.add(scrollPane,"wrap");
		
		panel.add(showButton,"flowx,split 4, span");
		panel.add(editButton,"");
		panel.add(deleteButton, "");
		panel.add(addButton, "");
		
		getContentPane().add(panel);
		
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Manage Reports");
		
		this.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int) screenSize.getWidth() - this.getWidth()) / 2, 100);
	}
	
	private void initializeButtons()
	{
		editButton = new JButton("Edit");
		this.editButton.setToolTipText("Alt+E");
		this.editButton.setMnemonic(KeyEvent.VK_E);
		
	    
		deleteButton = new JButton("Delete");
	    this.deleteButton.setToolTipText("Alt+D");
		this.deleteButton.setMnemonic(KeyEvent.VK_D);
	   
	    
	    addButton = new JButton("New");
	    this.addButton.setToolTipText("Alt+N");
		this.addButton.setMnemonic(KeyEvent.VK_N);
		
		showButton = new JButton("Show");
	    this.addButton.setToolTipText("Alt+S");
		this.addButton.setMnemonic(KeyEvent.VK_S);
	    
		/*
	    cancelButton = new JButton("Cancel");
	    this.cancelButton.setToolTipText("Alt+C");
		this.cancelButton.setMnemonic(KeyEvent.VK_C);
		cancelButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	    */
	}
	
	private void initializeTable() {
		// initialize our table
		table = new JTable(model);
		model.addTableModelListener(table);
		// enable sorting
		// table.setAutoCreateRowSorter(true);

		// set dimensions
		table.setPreferredScrollableViewportSize(new Dimension(900, 200));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		//table.setFillsViewportHeight(true);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public int getTableSelectedRow()
	{
		return table.getSelectedRow();
	}
	
	 public void addEditReportListener(ActionListener edit) {
		 	editButton.addActionListener(edit);
	    }
	 
	 public void addDeleteReportListener(ActionListener delete)
	 {
		 deleteButton.addActionListener(delete);
	 } 
	 public void addAddReportListener(ActionListener add)
	 {
		 addButton.addActionListener(add);
	 } 
	 
	public void addShowReportListener(ActionListener show)
	{
		 showButton.addActionListener(show);
	} 

}

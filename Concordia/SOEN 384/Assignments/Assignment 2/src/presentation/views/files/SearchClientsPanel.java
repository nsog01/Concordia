package presentation.views.files;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class SearchClientsPanel extends JPanel {

	/**
	 * 
	 */
	FileListTable table;
	JScrollPane scrollPane;
	JScrollPane scrollPaneDependents;
	JTable tableDependents;
	
	private JTextField searchField;
	private JButton search;
	
	private static final long serialVersionUID = -5035976295351424766L;
	
	public SearchClientsPanel(FileListTable myTable, JTable myTableDependents)  {
		this.table = myTable;
		
		this.tableDependents = myTableDependents;
		
		initControls();
		
		JPanel panel = new JPanel(new MigLayout());		
		panel.add(searchField, "split 2, grow");
		panel.add(search, "wrap 20");		
		panel.add(scrollPane,"span 2, grow");	
		panel.add(scrollPaneDependents);
		//getContentPane().
		add(panel);
		
		
		//this.setVisible(true);
		//this.setLocationRelativeTo(null);
		//this.setTitle("Search Clients");
		
		//this.pack();
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(((int) screenSize.getWidth() - this.getWidth()) / 2, 100);
	}
	
	private void initControls()
	{
		// Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);	
		
		scrollPaneDependents = new JScrollPane(tableDependents);
		
		//Create the search controls
		searchField = new JTextField();
		search = new JButton();	
		search.setIcon(new ImageIcon("images/search_b.png"));
	}
	
	public void AddSearchListener(ActionListener actionListner){
		this.search.addActionListener(actionListner);
	}
	
	public String getSearchText()
	{
		return searchField.getText();
	}

	public JButton getSearchButton() {
		return search;
	}

}

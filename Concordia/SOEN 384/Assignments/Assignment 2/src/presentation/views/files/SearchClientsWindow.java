package presentation.views.files;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class SearchClientsWindow extends JFrame implements Observer{

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
	
	public SearchClientsWindow(FileListTable myTable, JTable myTableDependents)  {
		this.table = myTable;
		
		this.tableDependents = myTableDependents;
		
		initControls();
		
		JPanel panel = new JPanel(new MigLayout());		
		panel.add(searchField, "split 2, grow");
		panel.add(search, "wrap 20");		
		panel.add(scrollPane,"span 2, grow");	
		panel.add(scrollPaneDependents);
		getContentPane().add(panel);
		
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Search Clients");
		
		this.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int) screenSize.getWidth() - this.getWidth()) / 2, 100);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}

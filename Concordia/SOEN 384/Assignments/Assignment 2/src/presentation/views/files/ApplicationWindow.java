package presentation.views.files;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import presentation.controllers.files.SearchClientsPanelController;
import presentation.views.menus.GeneralMenuBar;
import presentation.views.menus.LanguageSingleton;
import presentation.views.menus.LocaleChangeEvent;
import presentation.views.menus.LocaleChangeListener;


public class ApplicationWindow extends JFrame implements LocaleChangeListener {

	private static final long serialVersionUID = -4841609473376382142L;
	GeneralMenuBar bar;
	SearchClientsPanelController searchPanelController;
	SearchClientsPanel searchPanel;
	JTabbedPane tabbedPane;
	HouseholdPanel householdPanel;
	InputMap inputMap;
	ActionMap actionMap;
	
	Action f1Action = new AbstractAction() {

		private static final long serialVersionUID = 67890432147890L;

		public void actionPerformed(ActionEvent e) {
	    	tabbedPane.setSelectedIndex(0);
	    }
	};
	
	Action f2Action = new AbstractAction() {

		private static final long serialVersionUID = 789403214231L;

		public void actionPerformed(ActionEvent e) {
	    	tabbedPane.setSelectedIndex(1);
	    }
	};
	
	public ApplicationWindow()  {

		initControls();
		bar = new GeneralMenuBar();
		this.setJMenuBar(bar);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		String applicationWindowText="Welcome Hall Mission FSTS 1.0";
		this.setTitle(applicationWindowText);
		
		this.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		this.setLocation(0, 0);
		
		
		
		inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = getRootPane().getActionMap(); 
		
		inputMap.put(KeyStroke.getKeyStroke("F1"), "f1Action");
		actionMap.put("f1Action", f1Action);
		
		inputMap.put(KeyStroke.getKeyStroke("F2"), "f2Action");
		actionMap.put("f2Action", f2Action);
		
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
	}
	
	private void initControls() 
	{
		//tabbed panel
//		tabbedPane = new JTabbedPane();		
		
		//household components
		householdPanel = new HouseholdPanel(this);
		
		//search components
//		searchPanelController = new SearchClientsPanelController(householdPanel,this);
//		searchPanel = searchPanelController.getPanel();
		
//		tabbedPane.addTab("Search (F1)", searchPanel);
//		
//		tabbedPane.addTab("Client Detail (F2)", householdPanel);
		
		this.add(householdPanel);
		
	}
	
	public GeneralMenuBar getBar()
	{
		return this.bar;
	}
	
	public void setSelectedTab(int index)
	{
		tabbedPane.setSelectedIndex(index);
	}

	@Override
	public void localeChanged(LocaleChangeEvent e) {

		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		
		this.setTitle(messages.getString("applicationWindowText"));
		
	}

}

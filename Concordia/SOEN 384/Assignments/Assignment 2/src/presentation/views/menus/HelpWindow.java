package presentation.views.menus;


//package goes here
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;

public class HelpWindow extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -794258459999321978L;
	private final int WIDTH = 600;
	private final int HEIGHT = 400;
	private JEditorPane editorpane;
	private URL helpURL;


public HelpWindow(String title, URL hlpURL) {
	super(title);
	helpURL = hlpURL;  
	editorpane = new JEditorPane();
	editorpane.setEditable(false);
	
	try {
		editorpane.setPage(helpURL);
	
	} catch (Exception ex) {
		
		ex.printStackTrace();
	}
	//anonymous inner listener
	
	editorpane.addHyperlinkListener(new HyperlinkListener() {

		
public void hyperlinkUpdate(HyperlinkEvent ev) {

	try {
		if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			editorpane.setPage(ev.getURL());
		}
	
	} catch (IOException ex) {
//put message in window
		ex.printStackTrace();
	}
	}
	});
	getContentPane().add(new JScrollPane(editorpane));
	addButtons();
// no need for listener just dispose
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
// dynamically set location
	calculateLocation();
	setVisible(true);
// end constructor
	}
/**
* An Actionlistener so must implement this method
*
*/
public void actionPerformed(ActionEvent e) {
	String strAction = e.getActionCommand();
	URL tempURL;
	try {
		String contentsHelpItem="Table of Contents";
		if (strAction == contentsHelpItem) {
			tempURL = editorpane.getPage();
			editorpane.setPage(helpURL);
		}
		String closeHelpItem="Close";
		if (strAction == closeHelpItem) {
// more portable if delegated
			processWindowEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
		}
	
	} catch (IOException ex) {
		
		ex.printStackTrace();
	}
}
/**
* add buttons at the south
*/

private void addButtons() {
	String btnContText="Table of Contents"; 
	JButton btncontents = new JButton(btnContText);
	btncontents.addActionListener(this);
	
	String btnCloseText="Close";
	JButton btnclose = new JButton(btnCloseText);
	btnclose.addActionListener(this);
	//put into JPanel
	JPanel panebuttons = new JPanel();
	panebuttons.add(btncontents);
	panebuttons.add(btnclose);
	//add panel south
	getContentPane().add(panebuttons, BorderLayout.SOUTH);
	
	}
/**
* locate in middle of screen
*/	
private void calculateLocation() {
	
	Dimension screendim = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(new Dimension(WIDTH, HEIGHT));
	int locationx = (screendim.width - WIDTH) / 2;
	int locationy = (screendim.height - HEIGHT) / 2;
	setLocation(locationx, locationy);
	
	}
}
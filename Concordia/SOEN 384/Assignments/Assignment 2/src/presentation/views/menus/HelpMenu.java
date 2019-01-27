package presentation.views.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class HelpMenu extends JMenu implements LocaleChangeListener {
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5886572535415299611L;
	private String helpMenuFileText="Help";
	private String helpItem="Help";
	private JMenuItem help;

	public HelpMenu(){
		
		
		this.setText(helpItem);
		//72 is the KeyEvent.VK value for 'H'
		int helpMnemonic=72;
		this.setMnemonic(helpMnemonic);
		
		
		
		help=new JMenuItem(helpItem);
		help.addActionListener(openHelp());
		this.add(help);
		
		
		//JMenuItem about= new JMenuItem("About");
		//about.addActionListener(arg0);
		//this.add(about);
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
		
		
		
	}
	
	private ActionListener openHelp(){
		return new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {					
					try {
													
						//File myFile= new File("C:/Users/Danny/Desktop/workspace/HelpFile/src/help.html");
					    //URL index=myFile.toURI().toURL();
						URL index=new URL("http://users.encs.concordia.ca/~d_magni/help_index.html");
						
						new HelpWindow(helpMenuFileText, index);		
						
					}
					catch(Exception ex) {
						System.out.println(ex);			
					}
			}
		};
			
		}

	@Override
	public void localeChanged(LocaleChangeEvent e) {

		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		
		this.setText(messages.getString("helpMenuFileText"));
		
		/* Change the mnemonic to the language appropriate one*/
		int mnemonic= Integer.parseInt(messages.getString("helpMnemonic"));
		this.setMnemonic(mnemonic);
		
		help.setText(messages.getString("helpItem"));
		

		
	}
	}
	

	
	
	



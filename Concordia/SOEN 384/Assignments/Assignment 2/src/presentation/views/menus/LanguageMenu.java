package presentation.views.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;



import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


public class LanguageMenu extends JMenu implements LocaleChangeListener {
	
	private static final long serialVersionUID = 197939012675833357L;
	private JMenuItem rbEngMenuItem;
	private JMenuItem rbFrMenuItem;
	private ButtonGroup languages;
	private LanguageSingleton langSing;
	
	
	
	public LanguageMenu(){
		
		langSing=LanguageSingleton.getUniqueInstance();
		
		String languageMenuItemText="Language";
		this.setText(languageMenuItemText);
		this.setMnemonic(KeyEvent.VK_L);
		
		languages= new ButtonGroup();
		
		
		String engLangText="English";
		String frLangText="French";
		
		rbEngMenuItem = new JRadioButtonMenuItem(engLangText);
		rbEngMenuItem.setSelected(true);
		rbEngMenuItem.setMnemonic(KeyEvent.VK_E);
		rbEngMenuItem.setLocale(Locale.CANADA);
		rbEngMenuItem.addActionListener(changeLanguage());
		languages.add(rbEngMenuItem);
		this.add(rbEngMenuItem);

		rbFrMenuItem = new JRadioButtonMenuItem(frLangText);
		rbFrMenuItem.setMnemonic(KeyEvent.VK_F);
		rbFrMenuItem.setLocale(Locale.CANADA_FRENCH);
		rbFrMenuItem.addActionListener(changeLanguage());
		languages.add(rbFrMenuItem);
		this.add(rbFrMenuItem);
		
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
		
	}
	
	

	
	private ActionListener changeLanguage(){
		return new ActionListener(){
				
			@Override
			public void actionPerformed(ActionEvent arg0) {					
					try {
						
						if(rbEngMenuItem.isSelected()){
							Locale locale = rbEngMenuItem.getLocale();
							LocaleChangeEvent e= new LocaleChangeEvent(this,locale);
							LanguageSingleton.getUniqueInstance().fireLocaleChangeEvent(e);
						}
						else{
							Locale locale =rbFrMenuItem.getLocale();
							LocaleChangeEvent e= new LocaleChangeEvent(this,locale);
							LanguageSingleton.getUniqueInstance().fireLocaleChangeEvent(e);
							
								
						}
							
						
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
		this.setText(messages.getString("languageMenuItemText"));
		this.rbEngMenuItem.setText(messages.getString("engLangText"));
		this.rbFrMenuItem.setText(messages.getString("frLangText"));
		
	}
	
	
	
	
}
	

	
	
	



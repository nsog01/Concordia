package presentation.views.menus;

import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;



public class GeneralMenuBar extends JMenuBar implements LocaleChangeListener {

	private static final long serialVersionUID = -3943106827652571415L;
	private FileMenu file;
	private ReportMenu report;
	private HelpMenu help;
	private LanguageMenu lang;

	/*
	 * just testing to see what can be done for now -- wee can add this stuff
	 * later.
	 */
	public GeneralMenuBar() {
		
		report = new ReportMenu();
		file = new FileMenu(report);
		help= new HelpMenu();
		lang= new LanguageMenu();
		
		this.add(file);
		
		this.add(report);
		
		this.add(lang);
		
		this.add(help);
		
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
	}
	
	public FileMenu getFileMenu(){
		return file;
	}

	@Override
	public void localeChanged(LocaleChangeEvent e) {
		
		
	}

}

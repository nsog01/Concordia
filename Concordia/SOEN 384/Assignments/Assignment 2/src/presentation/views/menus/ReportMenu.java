package presentation.views.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

import presentation.views.reports.ReportViewerFrame;

import domain.mappers.reports.ReportMapper;
import domain.reports.Report;
import domain.reports.ReportList;

public class ReportMenu extends JMenu implements Observer, LocaleChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 508361565314149982L;
	
	ReportList reportList;
	ArrayList<JMenuItem> reportMenuItemList;
	
	public ReportMenu() {
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
		String reportItemText="Reports";
		this.setText(reportItemText);
		this.setMnemonic(KeyEvent.VK_R);
		this.getAccessibleContext().setAccessibleDescription(
				"The only menu in this program that has menu items");

		//Get list of all reports from database
		reportList = new ReportMapper().readAll();
		
		//initialize list of menu items that will contain each report
		reportMenuItemList = new ArrayList<JMenuItem>();
		
		//generate list of report menu items
		JMenuItem reportMenuItem;
		for(Report report: reportList)
		{
			reportMenuItem = new JMenuItem(report.getName());
			reportMenuItem.addActionListener(openReport(report.getPath()));			
			
			this.add(reportMenuItem);
			reportMenuItemList.add(reportMenuItem);
		}
	}
	
	private ActionListener openReport(final String path){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {					
					try {
													
						//Open report.
						ReportClientDocument reportClientDoc = new ReportClientDocument();
						reportClientDoc.open(path, 0);
						//Launch JFrame that contains the report viewer.
						new ReportViewerFrame(reportClientDoc);		
						
					}
					catch(ReportSDKException ex) {	
						System.out.println(ex);
					}
					catch(Exception ex) {
						System.out.println(ex);			
					}
			}
		};
	}

	public void refresh()
	{
		//clear all lists
		reportList.clear();
		reportMenuItemList.clear();		
		this.removeAll();
		
		//Get list of all reports from database
		reportList = new ReportMapper().readAll();

		
		//generate list of report menu items
		JMenuItem reportMenuItem;
		for(Report report: reportList)
		{
			reportMenuItem = new JMenuItem(report.getName());
			reportMenuItem.addActionListener(openReport(report.getPath()));			
			
			this.add(reportMenuItem);
			reportMenuItemList.add(reportMenuItem);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		refresh();
	}

	@Override
	public void localeChanged(LocaleChangeEvent e) {
		
		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		this.setText(messages.getString("reportItemText"));
		
	}

}

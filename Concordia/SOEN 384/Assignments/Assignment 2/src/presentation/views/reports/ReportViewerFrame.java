package presentation.views.reports;

/**
 * This file is a sample for demonstrating how the Crystal Reports Java Reporting Component's thick-client 
 * ReportViewerBean can be embedded into a Swing JFrame.  
 */

//Crystal Java Reporting Component (JRC) imports.
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.*;
import com.crystaldecisions.ReportViewer.*;

//Java imports.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReportViewerFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5228381377123127596L;
	//Initial window frame properties.
	private final int XPOS = 75;
	private final int YPOS = 50;
	private final int WIDTH = 750;
	private final int HEIGHT = 600;
	
	//Crystal Report thick-client viewer object that will be embedded into the JFrame. 
	private ReportViewerBean reportViewer = new ReportViewerBean();	
	//This report will be viewed in the thick-client viewer.  
	private ReportClientDocument reportClientDoc = new ReportClientDocument();	
	
	/**
	 * Constructs and launches the new frame.
	 */
	public ReportViewerFrame(ReportClientDocument reportClientDoc) throws Exception {
				
		//Initialize frame properties.
		this.setResizable(true);
		this.setLocation(XPOS, YPOS);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Crystal Report Java Viewer");
				
		//Add GUI components to the frame including the ReportViewerBean.
		addComponents();
				
		//Add GUI listeners to the frame.
		addListeners();
		
		//Set the report that the ReportViewerBean will display.
		this.reportClientDoc = reportClientDoc;
		reportViewer.setReportSource(reportClientDoc.getReportSource());
		reportViewer.init();
		reportViewer.start();
				
		//Display the frame.
		this.setVisible(true);
		
	}
	
	/**
	 * Utility function for adding GUI components to frame.  Created to separate
	 * the constructor logic of the frame from logic for adding visual
	 * components.  This function will add the ReportViewerBean to the frame.
	 */
	private void addComponents() {   
  	
	  	//Create new panel and add the ReportViewerBean to it.
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(reportViewer);
		
	}
	
	/**
	 * Utility function for adding GUI listeners to the frame.  Created to separate 
	 * the constructor logic of the frame for adding listeners.  
	 */
	private void addListeners() {
		
		//This window listener will capture the event of the ReportViewerFrame being closed.  If this 
		//frame is closed, then it is a good practice to then close the report being displayed, which the
		//quit() method will handle.    
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
				
	}
  
	/**
	 * Close frame and report being viewed.
	 */
	public void quit() {
	 
	  	try {
	  		//Release report.
	  		reportClientDoc.close();
			//System.exit(0);
	  	}
	  	catch(ReportSDKException ex) {
	  		ex.printStackTrace();  		
	  	}
			  	
	}
    
}

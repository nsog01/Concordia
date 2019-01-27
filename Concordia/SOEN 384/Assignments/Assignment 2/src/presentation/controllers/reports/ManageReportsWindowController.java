package presentation.controllers.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

import domain.mappers.reports.ReportMapper;
import domain.reports.Report;
import domain.reports.ReportList;
import presentation.models.reports.ReportTableModel;
import presentation.views.menus.ReportMenu;
import presentation.views.reports.AddReportWindow;
import presentation.views.reports.EditReportWindow;
import presentation.views.reports.ManageReportsWindow;
import presentation.views.reports.ReportViewerFrame;

public class ManageReportsWindowController {
	//view
	ManageReportsWindow manageReportsWindow;
	AddReportWindow addReportWindow;
	EditReportWindow editReportWindow;
	
	//model
	ReportTableModel tableModel;
	
	ReportList list;
	
	ReportMenu reportMenu;
	
	Report selectedReport;
	
	public ManageReportsWindowController() {
		
		//initialize table model
		list = new ReportMapper().readAll();
		tableModel = new ReportTableModel(list);
		
		//window with table containing list of reports
		manageReportsWindow = new ManageReportsWindow(tableModel);
		
		//add listeners to showEventsWindow
		manageReportsWindow.addEditReportListener(openEditReportListener());
		manageReportsWindow.addDeleteReportListener(deleteReportListener());
		manageReportsWindow.addAddReportListener(openAddReportListener());
		manageReportsWindow.addShowReportListener(showReportListener());
		
		manageReportsWindow.setVisible(true);
	}
	
	public void setReportingMenu(ReportMenu reportingMenu)
	{
		this.reportMenu = reportingMenu;
	}
	
	private ActionListener openEditReportListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
			int row = manageReportsWindow.getTableSelectedRow();
						
				if (row != -1) {
					selectedReport = tableModel.get(row);
					editReportWindow = new EditReportWindow();
					editReportWindow.addSubmitReportListener(editReportListener());
					
					editReportWindow.setRptName(selectedReport.getName());
					editReportWindow.setDesc(selectedReport.getDesc());
					editReportWindow.setDept(selectedReport.getDept());
					editReportWindow.setPath(selectedReport.getPath());
					}				
			}
			};
		}
	
	
	private ActionListener deleteReportListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			int row = manageReportsWindow.getTableSelectedRow();
				if (row != -1) {
					selectedReport = tableModel.get(row);
					list.remove(selectedReport);
					selectedReport.delete();
					tableModel.update(null, null);
					reportMenu.update(null, null);
					}
			}
			};
		}
	
	private ActionListener openAddReportListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addReportWindow = new AddReportWindow();
				addReportWindow.addOpenFileListener(browseFilesListener());
				addReportWindow.addSubmitReportListener(addReportListener());
			}
			};
		}
	
	private ActionListener browseFilesListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = loadReport();
				addReportWindow.setPath(path);
			}
		};
	}
	
	private ActionListener addReportListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String name = addReportWindow.getName();
				String desc = addReportWindow.getDesc();
				String dept = addReportWindow.getDept();
				String path = addReportWindow.getPath();
				Report report = new Report(name, desc, dept, path);
				report.insert();
				list.add(report);
				tableModel.update(null, null);
				reportMenu.update(null, null);
				addReportWindow.dispose();
			}
		};
	}
	
	private ActionListener editReportListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String name = editReportWindow.getName();
				String desc = editReportWindow.getDesc();
				String dept = editReportWindow.getDept();
				String path = editReportWindow.getPath();
				selectedReport.setName(name);
				selectedReport.setDesc(desc);
				selectedReport.setDept(dept);
				selectedReport.setPath(path);
				selectedReport.update();
				tableModel.update(null, null);
				reportMenu.update(null, null);
				editReportWindow.dispose();				
			}
		};
	}
	
	private ActionListener showReportListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				try {
					int row = manageReportsWindow.getTableSelectedRow();
					if (row != -1) {					
						selectedReport = tableModel.get(row);
						
						//Open report.
						ReportClientDocument reportClientDoc = new ReportClientDocument();
						reportClientDoc.open(selectedReport.getPath(), 0);
						//Launch JFrame that contains the report viewer.
						new ReportViewerFrame(reportClientDoc);		
					}					
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
	
	private String loadReport()
	{
		 // Determine the report to display using a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle ("Choose a report");
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept (File f)
            {
                if (f != null) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    return f.getName ().endsWith (".rpt");
                }
                return false;
            }

            @Override
            public String getDescription ()
            {
                return "Crystal Reports (*.rpt)";
            }
        });

        int returnVal = fileChooser.showOpenDialog(addReportWindow);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();             
        }
        else return "";
	}

}

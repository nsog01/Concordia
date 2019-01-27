package presentation.views.reports;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;


import net.miginfocom.swing.MigLayout;

public class AddReportWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637812310822920907L;
	
	private JLabel nameLabel;
	private JTextField nameTxt;
	
	private JLabel descLabel;
	private JTextField descTxt;
	
	private JLabel deptLabel;
	private JTextField deptTxt;
	
	private JLabel pathLabel;
	private JTextField pathTxt;
	private JButton openFileBtn;
	
	private JButton submitBtn;
	
	public AddReportWindow()
	{
		setTitle("Add Report");	
		this.setLayout(new MigLayout());
		
		this.initializeComponents();
		this.addComponents();
		
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void initializeComponents() {		
		nameLabel = new JLabel("Name:");
		nameTxt = new JTextField();
		
		descLabel = new JLabel("Description:");
		descTxt = new JTextField();
		
		deptLabel = new JLabel("Department:");
		deptTxt = new JTextField();
		
		pathLabel = new JLabel("Choose file:");
		pathTxt = new JTextField();
		openFileBtn = new JButton("Browse");
		
		submitBtn = new JButton("Submit");		
	}
	
	
	private void addComponents() {
		this.add(nameLabel, "grow");
		this.add(nameTxt, "w 150:150:150,wrap");
		
		this.add(descLabel,"grow");
		this.add(descTxt, "w 150:150:150,wrap");
		
		this.add(deptLabel,"grow");
		this.add(deptTxt,"w 150:150:150,wrap");
		
		this.add(pathLabel,"grow");
		this.add(pathTxt, "w 150:150:150,grow");
		this.add(openFileBtn, "w 100:100:100,wrap");		
		
		this.add(submitBtn, "w 100:100:100,wrap");
	}
	
	public void addOpenFileListener(ActionListener open)
	{
		openFileBtn.addActionListener(open);
	}
	
	public void addSubmitReportListener(ActionListener submit)
	{
		submitBtn.addActionListener(submit);
	}
	
	public void setPath(String path)
	{
		pathTxt.setText(path);
	}
	
	public String getPath()
	{
		return pathTxt.getText();
	}

	public String getDesc()
	{
		return descTxt.getText();
	}
	
	public String getName()
	{
		return nameTxt.getText();
	}
	
	public String getDept()
	{
		return deptTxt.getText();
	}

}

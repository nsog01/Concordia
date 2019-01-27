package presentation.views.files;

import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presentation.controllers.files.HouseholdManageFrameController;


import net.miginfocom.swing.MigLayout;
import domain.files.Client;

public class HouseholdManageFrame extends JFrame{

	private static final long serialVersionUID = -637359912257127665L;
	private HouseholdManageFrameController controller;
	private JLabel title;
	private JLabel fileNumberLabel;
	private JTextField fileNumber;
	private JLabel addressLabel;
	private JTextField address;
	private JLabel postLabel;
	private JTextField post;
	private JLabel phoneLabel;
	private JTextField phone;
	private JButton modify;
	private JButton addDependent;
	
	public HouseholdManageFrame() {
		this.controller = new HouseholdManageFrameController(this);
		this.setLayout(new MigLayout());
		initializeElements();
		addComponents();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initializeElements() {
		title = new JLabel("Add New Client File");
		title.setFont(new Font("Serif", Font.BOLD, 17));
		addressLabel = new JLabel("Address: ");
		postLabel = new JLabel("Postal Code: ");
		phoneLabel = new JLabel("Telephone Number: ");
		if (this.controller.getHousehold() != null){
			this.fileNumberLabel = new JLabel("File Number: ");
			controller.setClientFormPanel(controller.getHousehold().getRepresentative());
			fileNumber = new JTextField(Long.toString(controller.getHousehold().getFileNumber()));
			address = new JTextField(controller.getHousehold().getStreetName());
			post = new JTextField(controller.getHousehold().getPostalCode());
			phone = new JTextField(Long.toString(controller.getHousehold().getTel()));
			modify = new JButton("Modify");
		} else {
			controller.setClientFormPanel(null);
			address = new JTextField();
			post =new JTextField();
			phone = new JTextField();
			modify = new JButton("Submit");
			addDependent = new JButton("Add Dependent");
			addDependent.addActionListener(this.controller.addDependent());
			modify.addActionListener(this.controller.submit());
		}
	}
	
	private void addComponents() {
			add(title, "span, wrap 20");
			add(controller.getClientFormPanel(), "wrap, span 2");
			if (controller.getHousehold() != null){
				add(fileNumberLabel, "grow");
				add(fileNumber, "wrap, grow, w 300");
			}
			add(addressLabel);
			add(address, "wrap, grow, w 300");
			add(postLabel);
			add(post, "wrap, grow, w 300");
			add(phoneLabel);
			add(phone, "wrap 20, grow, w 300");
			add(addDependent);
			add(modify, "span, align right");
		}
	
	public void addRepresentative(Client client){
		controller.getHousehold().setRepresentative(client);
	}
	public String getAddress(){
		return address.getText();
	}
	public String getFileNumber(){
		return fileNumber.getText();
	}
	public String getPostalCode(){
		return post.getText();
	}
	public Date getInitialVisit(){
		return Calendar.getInstance().getTime();
	}
	public String getTel(){
		return phone.getText();
	}
}

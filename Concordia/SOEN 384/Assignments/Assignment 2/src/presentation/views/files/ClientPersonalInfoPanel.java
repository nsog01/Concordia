//REMEMBER THAT THIS IS A PANEL INSIDE A FRAME, MORE THAN ONE PANEL IS GOING TO BE ADDED TO THAT FRAME
//SAME CLIENT OBJECT IS GOING TO BE PASSED TO ALL PANELS. 

package presentation.views.files;

import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import domain.files.Client.Sex;

import net.miginfocom.swing.MigLayout;

public class ClientPersonalInfoPanel extends JPanel{
	
	private static final long serialVersionUID = 423748905432L;
	
	private JLabel firstNameLabel;
	private JLabel middleNameLabel;
	private JLabel lastNameLabel;
	private JLabel medicareNumberLabel;
	private JLabel genderLabel;
	private JLabel dateOfBirthLabel;
	private JLabel maritalStatusLabel;
	
	private JTextField firstNameTextField;
	private JTextField middleNameTextField;
	private JTextField lastNameTextField;
	private JTextField medicareNumberTextField;
	private JTextField genderTextField;
	private JTextField dateOfBirthTextField;
	
	private String maritalStatus[] = {"Single","Married","Divorced","Widowed","Cohabiting","Civil Union","Domestic Partnership","Unmarried Partners"};
	private JComboBox maritalStatusComboBox;
	
	
	public ClientPersonalInfoPanel()
	{
		this.setLayout(new MigLayout());
		this.initializeElements();
		this.addComponents();
	}
	public void initializeElements()
	{
		String clientPersonalInfoFirstNameText="First Name: ";
		String clientPersonalInfoMidNameText="Middle Name: ";
		String clientPersonalInfoLastNameText="Last Name: ";
		String clientPersonalInfoMedicareText="Medicare Numebr: ";
		String clientPersonalInfoGenderText="Gender: ";
		String clientPersonalInfoDOBText="Date of Birth: ";
		String clientPersonalInfoMaritalText="Martial Status: ";
		
		
		this.firstNameLabel = new JLabel(clientPersonalInfoFirstNameText);
		this.middleNameLabel = new JLabel(clientPersonalInfoMidNameText);
		this.lastNameLabel = new JLabel(clientPersonalInfoLastNameText);
		this.medicareNumberLabel = new JLabel(clientPersonalInfoMedicareText);
		this.genderLabel = new JLabel(clientPersonalInfoGenderText);
		this.dateOfBirthLabel = new JLabel(clientPersonalInfoDOBText);
		this.maritalStatusLabel = new JLabel(clientPersonalInfoMaritalText);
	
		this.firstNameTextField = new JTextField();
		this.middleNameTextField = new JTextField();
		this.lastNameTextField = new JTextField();
		this.medicareNumberTextField = new JTextField();
		this.genderTextField = new JTextField();
		this.dateOfBirthTextField = new JTextField();
		this.maritalStatusComboBox = new JComboBox(maritalStatus);
		
		//HAVE FUN DEALING WITH THE COMBOBOX :)
	}

	public void addComponents()
	{
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		setBorder(BorderFactory.createTitledBorder(loweredetched,
		"Client Personal Info"));
		this.add(firstNameLabel);
		this.add(firstNameTextField, "grow, w 300, wrap");
		this.add(middleNameLabel);
		this.add(middleNameTextField, "grow, w 300, wrap");
		this.add(lastNameLabel);
		this.add(lastNameTextField, "grow, w 300, wrap");
		this.add(medicareNumberLabel);
		this.add(medicareNumberTextField, "grow, w 300, wrap");
		this.add(genderLabel);
		this.add(genderTextField, "grow, w 300, wrap");
		this.add(dateOfBirthLabel);
		this.add(dateOfBirthTextField, "grow, w 300, wrap");
		this.add(maritalStatusLabel);
		this.add(maritalStatusComboBox, "grow, w 300, wrap");
		
	}

	public String getMedicare(){
		return this.medicareNumberTextField.getText();
	}
	public void setDateOfBirth(Date dob){
		SimpleDateFormat format =  new SimpleDateFormat("MMMM d, yyyy");
			if(dob != null)
			this.dateOfBirthTextField.setText(format.format(dob));
	}
	public void setSex(Sex sex){
		if (sex != null)
			this.genderTextField.setText(sex.toString());
	}

	public String getFirstName() {
		return firstNameTextField.getText();
	}

	public String getLastName() {
		return lastNameTextField.getText();
	}

	public String getMiddleName() {
		return middleNameTextField.getText();
	}

	public String getDateOfBirth() {
		return dateOfBirthTextField.getText();
	}
	public void setClient(String firstName, String middleName, String lastName,
			String medicare, Date dob, String maritalStatus2, String sex) {
		this.firstNameTextField = new JTextField(firstName);
		this.middleNameTextField = new JTextField(middleName);
		this.lastNameTextField = new JTextField(lastName);
		this.medicareNumberTextField = new JTextField(medicare);
		this.genderTextField = new JTextField(sex);
		SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
		this.dateOfBirthTextField = new JTextField(format.format(dob));
		this.maritalStatusComboBox = new JComboBox(maritalStatus);
		maritalStatusComboBox.setSelectedItem(maritalStatus2);
	}

	public void addMedicareListener(KeyListener listener){
		this.medicareNumberTextField .addKeyListener(listener);
	}
	public String getMaritalStatus() {
		return (String) maritalStatusComboBox.getSelectedItem();
	}
	public String getGender() {
		return genderTextField.getText();
	}
}

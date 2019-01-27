package presentation.views.files;


import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;

public class ClientAddressPanel extends JPanel{
	
	private static final long serialVersionUID = 4748930214723L;
	
	private JLabel streetNumberLabel;
	private JLabel streetNameLabel;
	private JLabel unitNumberLabel;
	private JLabel postalCodeLabel;
	private JLabel cityLabel;
	private JLabel provinceLabel;
	
	private JTextField streetNumberTextField;
	private JTextField streetNameTextField;
	private JTextField unitNumberTextField;
	private JFormattedTextField postalCodeTextField;
	private JTextField cityTextField;
	
	private String province[] = {"QC","ON","BC","AB","MB","SK","NS","NB","NL","PE","NT","YT","NU"};
	private JComboBox provinceComboBox;
	
	public ClientAddressPanel()
	{
		this.setLayout(new MigLayout());
		this.initializeElements();
		this.addComponents();
	}
	
	public void initializeElements()
	{
		
		String clientAddressPanelStreetNumText="Street Number: ";
		String clientAddressPanelStreetNameText="Street Name: ";
		String clientAddressPanelUnitNumText="Unit Number: ";
		String clientAddressPanelPostalText="Postal Code: ";
		String clientAddressPanelCityText="City: ";
		String clientAddressPanelProvText="Province: ";
		
		this.streetNumberLabel = new JLabel(clientAddressPanelStreetNumText);
		this.streetNameLabel = new JLabel(clientAddressPanelStreetNameText);
		this.unitNumberLabel = new JLabel(clientAddressPanelUnitNumText);
		this.postalCodeLabel = new JLabel(clientAddressPanelPostalText);
		this.cityLabel = new JLabel(clientAddressPanelCityText);
		this.provinceLabel = new JLabel(clientAddressPanelProvText);
		
		this.streetNumberTextField = new JTextField();
		this.streetNameTextField = new JTextField();
		this.unitNumberTextField = new JTextField();
		this.postalCodeTextField = new JFormattedTextField(createFormatter("U#U#U#"));
		this.cityTextField = new JTextField();
		this.provinceComboBox = new JComboBox(province);
	}
	public void addComponents()
	{
		String clientAddressBorderText="Client Address Info";
		Border loweredetched2 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		setBorder(BorderFactory.createTitledBorder(loweredetched2,clientAddressBorderText));
		
		this.add(streetNumberLabel);
		this.add(streetNumberTextField, "grow, w 300, wrap");
		
		this.add(streetNameLabel);
		this.add(streetNameTextField, "grow, w 300, wrap");
		
		this.add(unitNumberLabel);
		this.add(unitNumberTextField, "grow, w 300, wrap");
		
		this.add(postalCodeLabel);
		this.add(postalCodeTextField, "grow, w 300, wrap");
		
		this.add(cityLabel);
		this.add(cityTextField, "grow, w 300, wrap");
		
		this.add(provinceLabel);
		this.add(provinceComboBox,"grow, w 300, wrap");
	}
	
	public String getStreetNumber()
	{
		return this.streetNumberTextField.getText();
	}
	public String getStreetName()
	{
		return this.streetNameTextField.getText();
	}
	public String getUnitNumber()
	{
		return this.unitNumberTextField.getText();
	}
	public String getPostalCode()
	{
		return this.postalCodeTextField.getText();
	}
	/*public String setCity()
	{
		
	}*/
	public String getCity()
	{
		return this.cityTextField.getText();
	}
	public String getProvince()
	{
		return (String) this.provinceComboBox.getSelectedItem();
	}
	

	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
				formatter = new MaskFormatter(s);
			} 
		catch (java.text.ParseException exc) {
			
			Component frame = null;
			String clientAddressPostalFormatDialog="The Postal Code Format entered is not valid";
			JOptionPane.showMessageDialog(frame,clientAddressPostalFormatDialog );
			}
			return formatter;
		}

	public void setHousehold(int streetNumber, String streetName,
			int unitNumber, String postalCode, String city, String province2) {
		this.streetNumberTextField.setText(Integer.toString(streetNumber));
		this.streetNameTextField.setText(streetName);
		this.unitNumberTextField.setText(Integer.toString(unitNumber));
		this.postalCodeTextField.setText(postalCode);
		this.cityTextField.setText(city);
		this.provinceComboBox.setSelectedItem(province2);
	}

	}

package presentation.views.files;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;
import presentation.controllers.files.HouseholdInfoPanelController;
import presentation.controllers.files.HouseholdPanelController;
import presentation.models.files.CityListComboBoxModel;
import presentation.models.files.LanguageListComboBoxModel;
import presentation.models.files.ProvinceListComboBoxModel;
import presentation.views.menus.LanguageSingleton;
import presentation.views.menus.LocaleChangeEvent;
import presentation.views.menus.LocaleChangeListener;
import data.PostalCodeTDG;
import domain.files.Client;
import domain.files.ClientList;
import domain.files.FileManager;
import domain.files.Flag;
import domain.files.Household;
import domain.files.Language;
import domain.files.MedicareParser;
import domain.files.Relation;
import domain.files.Client.CanadianStatus;
import domain.files.Client.MaritalStatus;
import domain.files.Client.Origin;
import domain.files.Client.Reason;
import domain.files.Client.Referral;
import domain.files.Client.Sex;
import domain.files.Client.WorkStatus;

public class ClientForm extends JFrame implements Observer, LocaleChangeListener{
	
	private static final long serialVersionUID = -637359912257127665L;
	@SuppressWarnings("unused")
	
	
	/* File number */
	
	private JLabel fileNumberLabel;
	private JLabel fileNumberLabel2;
	private JTextField fileNumber;
	
	/* Common client info  */
	
	private JLabel firstNameLabel;
	private JTextField firstNameTxt;
	
	private JLabel lastNameLabel;
	private JTextField lastNameTxt;
	
	private JLabel medicareLabel;
	private JTextField medicareTxt;
	
	private JLabel genderLabel;
	private JComboBox genderCBox = new JComboBox(Sex.values());
	
	
	private JLabel ageLabel;
	private JSpinner ageSpinner = new JSpinner();
	
	/* Location fields */

	private JLabel addressLabel;
	private JTextField addressTxt;
	
//	private JLabel unitNumberLabel;
//	private JTextField unitNumberTxt;	
	
	private JLabel postalCodeLabel;
	private JTextField postalCodeTxt;
	
	private JLabel cityLabel;
	//private JComboBox cityCBox = new JComboBox(new String[]{"Montreal"});
	private CityComboBox cityComboBox = new CityComboBox(new CityListComboBoxModel());
	
	private JLabel provinceLabel;
	//private JComboBox provinceCBox = new JComboBox(new String[]{"Quebec"});
	private ProvinceComboBox provinceComboBox = new ProvinceComboBox(new ProvinceListComboBoxModel());
	
	private JLabel regionLabel;
	private JComboBox regionCBox  = new JComboBox(new String[]{"North America","South America", "Europe", "Asia", "Middle East"});
	
	/*  Other status */
	
	private JLabel maritalStatusLabel;
	private JComboBox maritalStatusCBox = new JComboBox(MaritalStatus.values());
	
	private JLabel workStatusLabel;
	private JComboBox workStatusCBox = new JComboBox(WorkStatus.values());
	
	private JLabel workExtraLabel;
	private JTextField workExtraTxt;
	
	private JLabel canadianStatusLabel;
	private JComboBox canadianStatusCBox = new JComboBox(CanadianStatus.values());
	
	private JLabel canadianExtraLabel;
	private JTextField canadianExtraTxt;
	
	/* Demographic information */
	private JLabel originLabel;
	private JComboBox originCBox = new JComboBox(Origin.values());
	
	private JLabel originExtraLabel;
	private JTextField originExtraTxt;
	
	private JLabel motherTongueLabel;
	private LanguageComboBox motherTongueCBox = new LanguageComboBox();
	
	private JLabel commLanguageLabel;
	private LanguageComboBox commLanguageCBox = new LanguageComboBox();
	
	/* Registration information */
	
	private JLabel referralLabel;
	private JComboBox referralCBox = new JComboBox(Referral.values());
	
	private JLabel referralExtraLabel;
	private JTextField referralExtraTxt;
	
	private JLabel reasonLabel;
	private JComboBox reasonCBox = new JComboBox(Reason.values());
	
	private JLabel reasonExtraLabel;
	private JTextField reasonExtraTxt;
	
	private JLabel registrationDateLabel;
	private JTextField registrationDateTxt;
	

	private JLabel streetNumberLabel;
	private JTextField streetNumberTxt;
	
	private JLabel streetNameLabel;
	private JTextField streetNameTxt;
	
	private JButton submit;

	
	/*  Old fields */
	
	private JButton search;	
	private JTextField searchField;
	
	private JLabel warning;
	
	private FileManager fileManager = FileManager.getUniqueInstance();
	private Household household;
	
	//change controller back in case it doesnt work
//	public ClientForm(HouseholdPanelController panelController) {
//		
//		fileManager.addObserverToRepository(this);
//		this.controller = new HouseholdInfoPanelController(this);
//		Border loweredetched = BorderFactory
//				.createEtchedBorder(EtchedBorder.LOWERED);
//		setBorder(BorderFactory.createTitledBorder(loweredetched,
//				"Client File Details"));
//		setLayout(new MigLayout());
//		initializeElements();
//		addComponents();
//		
//	}
//	

	public ClientForm() {
		fileManager.addObserverToRepository(this);
		this.household = FileManager.getUniqueInstance().getActiveFile();
		
		setLayout(new MigLayout("", "20[][150:150:150][][150:150:150][][150:150:150][][150:150:150][][150:150:150]20", ""));
		setTitle("Edit Household Details");
		add(new EmptyBannerPanel("Edit Household Details"), "north");
		initializeElements();
		addComponents();
		pack();
		setVisible(true);
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
	}
	
	
	public void clearFields(){
		
		fileNumber.setText("");
		addressTxt.setText("");
	}
	
	
	public void reset(){
		fileNumberLabel2.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));
		this.fileNumberLabel.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));
		firstNameTxt.setText(fileManager.getActiveFile().getRepresentative().getFirstName());
		lastNameTxt.setText(fileManager.getActiveFile().getRepresentative().getLastName());
		medicareTxt.setText(fileManager.getActiveFile().getRepresentative().getMedicare());
		
		//must derive from medicare
		genderCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getSex());
		ageSpinner.setValue(fileManager.getActiveFile().getRepresentative().getAge());
		
		addressTxt.setText(fileManager.getActiveFile().getStreetName());
		
//		streetNumberTxt.setText(Integer.toString(fileManager.getActiveFile().getStreetNumber()));
//		streetNameTxt.setText(fileManager.getActiveFile().getStreetName());
		
//		unitNumberTxt.setText(Integer.toString(fileManager.getActiveFile().getUnitNumber()));
		postalCodeTxt.setText(fileManager.getActiveFile().getPostalCode());
		
		//postal code must derive automatically city and province, also suggests region
		cityComboBox.setSelectedItem(fileManager.getActiveFile().getCity());
		//provinceCBox.setSelectedIndex(0);
		provinceComboBox.setSelectedItem(fileManager.getActiveFile().getProvince());
		regionCBox.setSelectedIndex(0);
		
		maritalStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getMaritalStatus());
		workStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getWorkStatus());
		workExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getExtraWorkStatusField());
		canadianStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getCanadianStatus());
		canadianExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getExtraCanadianStatusField());	
		
		originCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getOrigin());
		originExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getExtraOriginField());
		
		
		referralCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getReferral());
		referralExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getReferralExtra());
		reasonCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getReason());
		reasonExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getReasonExtra());
		//registrationDateTxt.setText(fileManager.getActiveFile().getRepresentative().getRegistrationDate().toString());
		registrationDateTxt.setText("");
		
		
		//*********************************************************************************//
		//*********************************************************************************//
		
	
		//postal code must derive automatically city and province, also suggests region
		cityComboBox.setSelectedIndex(-1);
		//provinceCBox.setSelectedIndex(0);
		provinceComboBox.setSelectedIndex(-1);
		regionCBox.setSelectedIndex(0);
		
	}

	public JButton getSearchButton(){
		return search;
	}
	
	public Household getHousehold(){
		return fileManager.getActiveFile();
	}
	public String getFileNumber(){
		return fileNumber.getText().toString();
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		this.reset();
		this.repaint();
	}
	public void set(Household household) {
		if (household != null){
		searchField.setText(null);
		//this.rep.setText(fileManager.getActiveFile().getRepresentative().getFullName());
		this.fileNumberLabel2.setText(Long.toString(household.getFileNumber()));
		firstNameTxt.setText(fileManager.getActiveFile().getRepresentative().getFirstName());
		lastNameTxt.setText(fileManager.getActiveFile().getRepresentative().getLastName());
		medicareTxt.setText(fileManager.getActiveFile().getRepresentative().getMedicare());
		
		//must derive from medicare
		genderCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getSex());
		ageSpinner.setValue(fileManager.getActiveFile().getRepresentative().getAge());

//		streetNumberTxt.setText(Integer.toString(fileManager.getActiveFile().getStreetNumber()));
//		streetNameTxt.setText(fileManager.getActiveFile().getStreetName());
		
		addressTxt.setText(fileManager.getActiveFile().getStreetName());
		
//		unitNumberTxt.setText(Integer.toString(fileManager.getActiveFile().getUnitNumber()));
		postalCodeTxt.setText(fileManager.getActiveFile().getPostalCode());
		
		//postal code must derive automatically city and province, also suggests region
		cityComboBox.setSelectedItem(household.getCity());
		//provinceCBox.setSelectedIndex(0);
		provinceComboBox.setSelectedItem(household.getProvince());
		regionCBox.setSelectedIndex(0);
		
		maritalStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getMaritalStatus());
		workStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getWorkStatus());
		workExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getExtraWorkStatusField());
		canadianStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getCanadianStatus());
		canadianExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getExtraCanadianStatusField());
		
		this.motherTongueCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getMotherTongue());
		this.commLanguageCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getSecondLanguage());
		
		referralCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getReferral());
		referralExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getReferralExtra());
		reasonCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getReason());
		reasonExtraTxt.setText(fileManager.getActiveFile().getRepresentative().getReasonExtra());
		registrationDateTxt.setText(fileManager.getActiveFile().getRepresentative().getRegistrationDate().toString());
		
		} else {
			//this.rep.setText(null);
			this.fileNumber.setText(null);
			firstNameTxt.setText(null);
			lastNameTxt.setText(null);
			medicareTxt.setText(null);
			genderCBox.setSelectedIndex(0);
			ageSpinner.setValue(0);
			
			
			
			this.addressTxt.setText(null);
		}
	}
	
	//*************************************************************************************************
	//*************************************************************************************************
	//*************************************************************************************************
	
	public void initializeElements()
	{
		searchField = new JTextField();
		
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
		
		String clientFormFileNumberText="File Number";
		String clientFormFirstNameText="First Name";
		String clientFormLastNameText="Last Name";
		String clientFormMedicareText="Medicare";
		String clientFormGenderText="Gender";
		String clientFormAgeText="Age";
		String clientFormAddressText="Address";
		String clientFormAptText="Apt/Unit#";
		String clientFormPostalCodeText="Postal code";
		String clientFormCityText="City";
		String clientFormProvinceText="Province";
		String clientFormRegionText="Region";
		String clientFormMaritalText="Marital status";
		String clientFormWorkText="Work status";
		String clientFormExtra1Text="Extra";
		String clientFormCanadianText="Canadian status";
		String clientFormExtra2Text="Extra";
		String clientFormOriginText="Origin";
		String clientFormArrivalText="Arrival";
		String clientFormMotherText="Mother tongue";
		String clientFormCommLangText="Comm. language";
		String clientFormReferralText="Referral";
		String clientFormSpecify1Text="Specify";
		String clientFormReasonText="Reason";
		String clientFormSpecify2Text="Specify";
		String clientFormDateRegText="Date registered";
		String clientFormSubmitText="Submit Form";
		
		
		
		
		fileNumberLabel = new JLabel(clientFormFileNumberText);
		//this.fileNumberLabel.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));
		fileNumberLabel.setForeground(new Color(123, 195, 66));
		fileNumberLabel.setFont(new Font("", Font.BOLD, 14));
		
		firstNameLabel = new JLabel(clientFormFirstNameText);
		lastNameLabel = new JLabel(clientFormLastNameText);
		medicareLabel = new JLabel(clientFormMedicareText);
		genderLabel = new JLabel(clientFormGenderText);
		ageLabel = new JLabel(clientFormAgeText);
		
		
		addressLabel = new JLabel(clientFormAddressText);
//		streetNumberLabel = new JLabel("Street Number");
//		streetNameLabel = new JLabel("Street Name");
		
//		unitNumberLabel = new JLabel(clientFormAptText);
		postalCodeLabel = new JLabel(clientFormPostalCodeText);
		cityLabel = new JLabel(clientFormCityText);
		provinceLabel = new JLabel(clientFormProvinceText);
		regionLabel = new JLabel(clientFormRegionText);
		
		maritalStatusLabel = new JLabel(clientFormMaritalText);
		workStatusLabel = new JLabel(clientFormWorkText);
		workExtraLabel = new JLabel(clientFormExtra1Text);
		canadianStatusLabel = new JLabel(clientFormCanadianText);
		canadianExtraLabel = new JLabel(clientFormExtra2Text);
		
		originLabel = new JLabel(clientFormOriginText);
		originExtraLabel = new JLabel(clientFormArrivalText);
		motherTongueLabel = new JLabel(clientFormMotherText);
		commLanguageLabel = new JLabel(clientFormCommLangText);
		
		referralLabel = new JLabel(clientFormReferralText);
		referralExtraLabel = new JLabel(clientFormSpecify1Text);
		reasonLabel = new JLabel(clientFormReasonText);
		reasonExtraLabel = new JLabel(clientFormSpecify2Text);
		registrationDateLabel = new JLabel(clientFormDateRegText);
		submit = new JButton(clientFormSubmitText);
		
		
		warning = new JLabel();
		warning.setForeground(Color.RED);
		if (fileManager.getActiveFile() != null){

			//fileNumber = new JTextField(Long.toString(fileManager.getActiveFile().getFileNumber()));
			fileNumberLabel2 = new JLabel(Long.toString(fileManager.getActiveFile().getFileNumber()));
			fileNumberLabel2.setForeground(new Color(51, 102, 153));
			fileNumberLabel2.setFont(new Font("", Font.BOLD, 12));
			firstNameTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getFirstName());
			lastNameTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getLastName());
			medicareTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getMedicare());
			this.medicareTxt.addFocusListener(medicareAction());
			
			//setting the Labels
			
			//must derive from medicare
			genderCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getSex());
			ageSpinner.setValue(fileManager.getActiveFile().getRepresentative().getAge());
			
			addressTxt = new JTextField(fileManager.getActiveFile().getStreetName());
			
//			streetNumberTxt = new JTextField(Integer.toString(fileManager.getActiveFile().getStreetNumber()));
//			streetNameTxt = new JTextField(fileManager.getActiveFile().getStreetName());
			
//			unitNumberTxt = new JTextField(Integer.toString(fileManager.getActiveFile().getUnitNumber()));
			postalCodeTxt = new JTextField(fileManager.getActiveFile().getPostalCode());
			this.postalCodeTxt.addFocusListener(postalcodeAction());
			
			
			//postal code must derive automatically city and province, also suggests region
	
			regionCBox.setSelectedIndex(0);
			cityComboBox.setSelectedItem(fileManager.getActiveFile().getCity());
			//provinceCBox.setSelectedItem(fileManager.getActiveFile().getProvince());
			provinceComboBox.setSelectedItem(fileManager.getActiveFile().getProvince());
			//regionLabel2.setText(fileManager.getActiveFile().getRegion());
			
			
			maritalStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getMaritalStatus());
			workStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getWorkStatus());
			workExtraTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getExtraWorkStatusField());
			canadianStatusCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getCanadianStatus());
			canadianExtraTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getExtraCanadianStatusField());
		
			
			originCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getOrigin());
			originExtraTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getExtraOriginField());
	
			this.motherTongueCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getMotherTongue().getLanguage());
			this.commLanguageCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getSecondLanguage().getLanguage());
			
			referralCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getReferral());
			referralExtraTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getReferralExtra());
			reasonCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getReason());
			reasonExtraTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getReasonExtra());
			registrationDateTxt = new JTextField(fileManager.getActiveFile().getRepresentative().getRegistrationDate().toString());
			String clientFormSubmitFormText="Submit Form";
			submit = new JButton(clientFormSubmitFormText);
			addSubmitListener();
			
			search = new JButton();
		} else {
			//fileNumber = new JTextField();
			firstNameTxt = new JTextField();
			lastNameTxt = new JTextField();
			medicareTxt = new JTextField();
			
			//normally derived from medicare
			genderCBox.setSelectedIndex(0);
			ageSpinner.setValue(0);
			
			addressTxt = new JTextField();
//			streetNumberTxt = new JTextField();
//			streetNameTxt = new JTextField();
//			unitNumberTxt = new JTextField();
			postalCodeTxt = new JTextField();
			
			//normally derived automatically by postal code
			cityComboBox.setSelectedIndex(-1);
			//provinceCBox.setSelectedIndex(0);
			provinceComboBox.setSelectedIndex(-1);
			regionCBox.setSelectedIndex(0);
			
			maritalStatusCBox.setSelectedIndex(0);
			workStatusCBox.setSelectedIndex(0);
			workExtraTxt = new JTextField();
			canadianStatusCBox.setSelectedIndex(0);
			canadianExtraTxt = new JTextField();	
			
			originCBox.setSelectedIndex(0);
			originExtraTxt = new JTextField();
			
			
			referralCBox.setSelectedIndex(0);
			referralExtraTxt = new JTextField();
			reasonCBox.setSelectedIndex(0);
			reasonExtraTxt = new JTextField();
			registrationDateTxt = new JTextField();
			
			//this.fileNumberLabel.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));			
			String clientFormSubmitForm2Text="Sumbit Form";
			submit = new JButton(clientFormSubmitForm2Text);
			addSubmitListener();
			search = new JButton();
		}
			search.setIcon(new ImageIcon("images/search_b.png"));
	}
	
	public void addComponents()
	{
		add(fileNumberLabel, "grow");
		add(fileNumberLabel2, "wrap, grow");
		
		/* Common client fields */
		add(firstNameLabel, "grow");
		add(firstNameTxt, "grow");
		add(lastNameLabel, "grow");
		add(lastNameTxt, "grow");
		add(medicareLabel, "grow");
		add(medicareTxt, "grow");
		add(genderLabel, "grow");
		add(genderCBox, "grow");
		add(ageLabel, "grow");
		//add(ageTxt, "w 35:35:40,wrap");	
		add(ageSpinner, "w 50:50:50,wrap");		
		
		/* Location Fields */
		add(addressLabel, "grow");
		add(addressTxt,"grow");
//		add(streetNumberLabel,"grow");
//		add(streetNumberTxt,"w 150:150:150");
		
		//------------------------IF ERORRRRRRRRRRRRRRRRRRRRRRRRR----------------------
//		add(streetNameLabel, "grow");
//		add(streetNameTxt, "w 150:150:150");
		
//		add(unitNumberLabel, "grow");
//		add(unitNumberTxt, "w 100:100:100");
		add(postalCodeLabel, "grow");
		add(postalCodeTxt, "grow");
		add(cityLabel, "grow");
		//add(cityCBox, "w 100:100:100");
		add(cityComboBox,"grow, w 150:150:150");
		add(provinceLabel, "grow");
		//add(provinceCBox, "w 100:100:100");
		add(provinceComboBox, " grow");
		add(regionLabel, "grow");
		add(regionCBox, "grow, wrap");
		
		/* Other status fields */
		add(maritalStatusLabel, "grow");
		add(maritalStatusCBox,"grow");
		add(workStatusLabel, "grow");
		add(workStatusCBox, "grow, w 150:150:150");
		add(workExtraLabel,"grow");
		add(workExtraTxt,"grow");
		add(canadianStatusLabel,"grow");
		add(canadianStatusCBox, "grow, w 150:150:150");
		add(canadianExtraLabel, "grow");
		add(canadianExtraTxt, "grow, wrap");
		
		/*  Demographic fields */
		add(originLabel, "grow");
		add(originCBox, "grow, w 150:150:150");
		add(originExtraLabel, "grow");
		add(originExtraTxt, "grow");
		add(motherTongueLabel, "grow");
		add(motherTongueCBox, "grow");
		add(commLanguageLabel, "grow");
		add(commLanguageCBox, "grow, wrap");
		
		/*  Registration info fields */
		add(referralLabel, "grow");
		add(referralCBox, "grow");
		add(referralExtraLabel,"grow");
		add(referralExtraTxt,"grow");
		add(reasonLabel, "grow");
		add(reasonCBox, "grow");
		add(reasonExtraLabel, "grow");
		add(reasonExtraTxt, "grow");
		add(registrationDateLabel, "grow");
		add(registrationDateTxt, "grow, wrap");
		add(submit,"grow");
		
	}
	//*******************************************************************************************
	//*******************************************************************************************
	//*******************************************************************************************
	
	public void setWarning(String warning){
		this.warning.setText(warning);
	}
	
	
	public JTextField getSearchField(){
		return searchField;
	}
	
	public void addSubmitListener()
	{
		 submit.addActionListener(new ActionListener() {
			  
	          public void actionPerformed(ActionEvent e)
	          {
	        	  
	        	  String city = (String) cityComboBox.getSelectedItem();
	        	  String provice = (String) provinceComboBox.getSelectedItem();
	        	  
	        	  
	        	  FileManager.getUniqueInstance().getActiveFile().set2(
	        			  addressTxt.getText(), 
	        			  0,
	        			  postalCodeTxt.getText(), 
	        			  city, 
	        			  provice, 
	        			  regionCBox.getSelectedItem().toString(), 
	        			  household.getRepresentative());
	        	  
	        	  
	        	  FileManager.getUniqueInstance().getActiveFile().getRepresentative().set2(firstNameTxt.getText(), lastNameTxt.getText(),
	        			  medicareTxt.getText(), motherTongueCBox.getSelected(), commLanguageCBox.getSelected(),
	        			  maritalStatusCBox.getSelectedItem().toString(), workStatusCBox.getSelectedItem().toString(),
	        			  workExtraTxt.getText(), canadianStatusCBox.getSelectedItem().toString(), canadianExtraTxt.getText(),
	        			  originCBox.getSelectedItem().toString(), originExtraTxt.getText(), referralCBox.getSelectedItem().toString(),
	        			  referralExtraTxt.getText(), reasonCBox.getSelectedItem().toString(), reasonExtraTxt.getText());
	        	  
	        	  
	        	  

	        	  
//	        	  FileManager.getUniqueInstance().getActiveFile().set2(1,addressTxt.getText(), Integer.parseInt(unitNumberTxt.getText()),
//	        			  postalCodeTxt.getText(), "Montreal", 
//	        			  "a", "b", household.getRepresentative());
//	        	  
	        	  FileManager.getUniqueInstance().getActiveFile().commit();
	        	  //household.commit();
	        	  
	          }
	      });  
	}
	
	public FocusListener medicareAction() {
		return new FocusListener(){

			

			

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				String medicare = medicareTxt.getText();
				Date dob = null;
				Sex sex = null;
				try{
				dob = MedicareParser.getDOB(medicare);
				sex = MedicareParser.getSex(medicare);
				}catch (Exception e1){
				
					//DISPLAY AN ERROR MESSAGE
				}
				fileManager.getActiveFile().getRepresentative().setDOB(dob);
				fileManager.getActiveFile().getRepresentative().setSex(sex);
				//System.out.println("PRESSED");
				ageSpinner.setValue(fileManager.getActiveFile().getRepresentative().getAge());
				
			}

	};
	}
	
	public FocusListener postalcodeAction() {
		return new FocusListener(){

			

			

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				String postalcode = postalCodeTxt.getText();

				try {
					String province = PostalCodeTDG.getProvince(postalcode.toUpperCase());
					String city = PostalCodeTDG.getCity(postalcode.toUpperCase());
					provinceComboBox.setSelectedItem(province);
					cityComboBox.setSelectedItem(city);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				
				
			}

	};
	}


	@Override
	public void localeChanged(LocaleChangeEvent e) {
		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		this.fileNumberLabel.setText(messages.getString("clientFormFileNumberText"));
		this.firstNameLabel.setText(messages.getString("clientFormFirstNameText"));
		this.lastNameLabel.setText(messages.getString("clientFormReasonText"));
		this.medicareLabel.setText(messages.getString("clientFormMedicareText"));
		this.genderLabel.setText(messages.getString("clientFormGenderText"));
		this.ageLabel.setText(messages.getString("clientFormAgeText"));
		this.addressLabel.setText(messages.getString("clientFormAddressText"));
		this.cityLabel.setText(messages.getString("clientFormCityText"));
		this.regionLabel.setText(messages.getString("clientFormRegionText"));
		this.provinceLabel.setText(messages.getString("clientFormProvinceText"));
		this.postalCodeLabel.setText(messages.getString("clientFormPostalCodeText"));
		this.maritalStatusLabel.setText(messages.getString("clientFormMaritalText"));
		this.canadianExtraLabel.setText(messages.getString("clientFormExtra2Text"));
		this.canadianStatusLabel.setText(messages.getString("clientFormCanadianText"));
		this.workStatusLabel.setText(messages.getString("clientFormWorkText"));
		this.workExtraLabel.setText(messages.getString("clientFormExtra1Text"));
		this.originExtraLabel.setText(messages.getString("clientFormReasonText"));
		this.originLabel.setText(messages.getString("clientFormOriginText"));
		this.commLanguageLabel.setText(messages.getString("clientFormCommLangText"));
		this.motherTongueLabel.setText(messages.getString("clientFormMotherText"));
		this.registrationDateLabel.setText(messages.getString("clientFormDateRegText"));
		this.referralLabel.setText(messages.getString("clientFormReferralText"));
		this.referralExtraLabel.setText(messages.getString("clientFormSpecify1Text"));
		this.reasonExtraLabel.setText(messages.getString("clientFormSpecify2Text"));
		this.reasonLabel.setText(messages.getString("clientFormReasonText"));
		this.submit.setText(messages.getString("clientFormSubmitText"));
		
	}



	
}

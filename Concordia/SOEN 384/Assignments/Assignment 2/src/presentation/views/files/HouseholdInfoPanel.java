package presentation.views.files;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.controllers.files.HouseholdInfoPanelController;
import presentation.controllers.files.HouseholdPanelController;
import presentation.views.menus.LanguageSingleton;
import presentation.views.menus.LocaleChangeEvent;
import presentation.views.menus.LocaleChangeListener;


import net.miginfocom.swing.MigLayout;
import domain.files.Client.CanadianStatus;
import domain.files.Client.MaritalStatus;
import domain.files.Client.Origin;
import domain.files.Client.Reason;
import domain.files.Client.Referral;
import domain.files.Client.Sex;
import domain.files.Client.WorkStatus;
import domain.files.FileManager;
import domain.files.Household;

public class HouseholdInfoPanel extends JPanel implements Observer, LocaleChangeListener {

	private static final long serialVersionUID = -637359912257127665L;
	@SuppressWarnings("unused")
	private HouseholdInfoPanelController controller;
	
	private String hInfoPanelBorderText="Client File Details";
	private String hInfoPanelFileText="File Number";
	private String hInfoPanelFirstNameText="First Name";
	private String hInfoPanelLastNameText="Last Name";
	private String hInfoPanelMedicareText="Medicare";
	private String hInfoPanelGenderText="Gender";
	private String hInfoPanelAgeText="Age";
	private String hInfoPanelAddressText="Address";
	private String hInfoPanelAptText="Apt/Unit#";
	private String hInfoPanelPostalText="Postal Code";
	private String hInfoPanelCityText="City";
	private String hInfoPanelProvText="Province";
	private String hInfoPanelRegionText="Region";
	private String hInfoPanelMaritalText="Marital Status";
	private String hInfoPanelWorkStatusText="Work Status";
	private String hInfoPanelExtra1Text="Extra";
	private String hInfoPanelCanadianText="Canadian Status";
	private String hInfoPanelExtra2Text="Extra";
	private String hInfoPanelOriginText="Origin";
	private String hInfoPanelArrivalText="Arrival";
	private String hInfoPanelMotherText="Mother Tongue";
	private String hInfoPanelCommLangText="Comm. language";
	private String hInfoPanelReferralText="Referral";
	private String hInfoPanelReasonText="Reason";
	private String hInfoPanelSpecifyText="Specify";
	private String hInfoPanelDateRegText="Date registered";
	private String hInfoPanelEditBtnText="Edit Client File";
	
	
	
	/* File number */

	private JLabel fileNumberLabel;
	private JLabel fileNumberLabel2;
	private JTextField fileNumber;

	/* Common client info */

	private JLabel firstNameLabel;
	private JLabel firstNameLabel2;
	private JTextField firstNameTxt;

	private JLabel lastNameLabel;
	private JLabel lastNameLabel2;
	private JTextField lastNameTxt;

	private JLabel medicareLabel;
	private JLabel medicareLabel2;
	private JTextField medicareTxt;

	private JLabel genderLabel;
	private JLabel genderLabel2;
	private JComboBox genderCBox = new JComboBox(Sex.values());

	private JLabel ageLabel;
	private JLabel ageLabel2;
	private JSpinner ageSpinner = new JSpinner();

	/* Location fields */

	private JLabel addressLabel;
	private JLabel addressLabel2;
	private JTextField addressTxt;

	private JLabel unitNumberLabel;
	private JLabel unitNumberLabel2;
	private JTextField unitNumberTxt;

	private JLabel postalCodeLabel;
	private JLabel postalCodeLabel2;
	private JTextField postalCodeTxt;

	private JLabel cityLabel;
	private JLabel cityLabel2;
	private JComboBox cityCBox = new JComboBox(new String[] { "Montreal" });

	private JLabel provinceLabel;
	private JLabel provinceLabel2;
	private JComboBox provinceCBox = new JComboBox(new String[] { "Quebec" });

	private JLabel regionLabel;
	private JLabel regionLabel2;
	private JComboBox regionCBox = new JComboBox(new String[] { "MTL Area" });

	/* Other status */

	private JLabel maritalStatusLabel;
	private JLabel maritalStatusLabel2;
	private JComboBox maritalStatusCBox = new JComboBox(MaritalStatus.values());

	private JLabel workStatusLabel;
	private JLabel workStatusLabel2;
	private JComboBox workStatusCBox = new JComboBox(WorkStatus.values());

	private JLabel workExtraLabel;
	private JLabel workExtraLabel2;
	private JTextField workExtraTxt;

	private JLabel canadianStatusLabel;
	private JLabel canadianStatusLabel2;
	private JComboBox canadianStatusCBox = new JComboBox(
			CanadianStatus.values());

	private JLabel canadianExtraLabel;
	private JLabel canadianExtraLabel2;
	private JTextField canadianExtraTxt;

	/* Demographic information */
	private JLabel originLabel;
	private JLabel originLabel2;
	private JComboBox originCBox = new JComboBox(Origin.values());

	private JLabel originExtraLabel;
	private JLabel originExtraLabel2;
	private JTextField originExtraTxt;

	private JLabel motherTongueLabel;
	private JLabel motherTongueLabel2;
	private JComboBox motherTongueCBox = new JComboBox(
			new String[] { "English" });

	private JLabel commLanguageLabel;
	private JLabel commLanguageLabel2;
	private JComboBox commLanguageCBox = new JComboBox(
			new String[] { "English" });

	/* Registration information */

	private JLabel referralLabel;
	private JLabel referralLabel2;
	private JComboBox referralCBox = new JComboBox(Referral.values());

	private JLabel referralExtraLabel;
	private JLabel referralExtraLabel2;
	private JTextField referralExtraTxt;

	private JLabel reasonLabel;
	private JLabel reasonLabel2;
	private JComboBox reasonCBox = new JComboBox(Reason.values());

	private JLabel reasonExtraLabel;
	private JLabel reasonExtraLabel2;
	private JTextField reasonExtraTxt;

	private JLabel registrationDateLabel;
	private JLabel registrationDateLabel2;
	private JTextField registrationDateTxt;

	private JButton edit;

	/* Old fields */

	private JButton search;
	private JTextField searchField;

	private JLabel warning;

	private FileManager fileManager = FileManager.getUniqueInstance();
	private FlagPanel flagPanel;
	private Border loweredetched;

	// change controller back in case it doesn't work
	public HouseholdInfoPanel(HouseholdPanelController panelController) {
		fileManager.addObserverToRepository(this);
		this.controller = new HouseholdInfoPanelController(this);
		loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		
		setBorder(BorderFactory.createTitledBorder(loweredetched,hInfoPanelBorderText));
		setLayout(new MigLayout());
		initializeElements();
		addComponents();
		
		
		
		addEditListener(this.getHousehold());
		
		
		
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
		
	}



	private void initializeElements() {
		
		searchField = new JTextField();
		flagPanel = new FlagPanel(FileManager.getUniqueInstance().getActiveFile());
		add(flagPanel, "north");
		fileNumberLabel = new JLabel(hInfoPanelFileText);
		// this.fileNumberLabel.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));
		fileNumberLabel.setForeground(new Color(123, 195, 66));
		fileNumberLabel.setFont(new Font("", Font.BOLD, 14));
		
		firstNameLabel = new JLabel(hInfoPanelFirstNameText);
		firstNameLabel.setForeground(new Color(123, 195, 66));
		firstNameLabel.setFont(new Font("", Font.BOLD, 14));
		
		lastNameLabel = new JLabel(hInfoPanelLastNameText);
		lastNameLabel.setForeground(new Color(123, 195, 66));
		lastNameLabel.setFont(new Font("", Font.BOLD, 14));
		
		medicareLabel = new JLabel(hInfoPanelMedicareText);
		medicareLabel.setForeground(new Color(123, 195, 66));
		medicareLabel.setFont(new Font("", Font.BOLD, 14));
		
		genderLabel = new JLabel(hInfoPanelGenderText);
		genderLabel.setForeground(new Color(123, 195, 66));
		genderLabel.setFont(new Font("", Font.BOLD, 14));
		
		ageLabel = new JLabel(hInfoPanelAgeText);
		ageLabel.setForeground(new Color(123, 195, 66));
		ageLabel.setFont(new Font("", Font.BOLD, 14));

		addressLabel = new JLabel(hInfoPanelAddressText);
		addressLabel.setForeground(new Color(123, 195, 66));
		addressLabel.setFont(new Font("", Font.BOLD, 14));
		
		unitNumberLabel = new JLabel(hInfoPanelAptText);
		unitNumberLabel.setForeground(new Color(123, 195, 66));
		unitNumberLabel.setFont(new Font("", Font.BOLD, 14));
		
		postalCodeLabel = new JLabel(hInfoPanelPostalText);
		postalCodeLabel.setForeground(new Color(123, 195, 66));
		postalCodeLabel.setFont(new Font("", Font.BOLD, 14));
		
		cityLabel = new JLabel(hInfoPanelCityText);
		cityLabel.setForeground(new Color(123, 195, 66));
		cityLabel.setFont(new Font("", Font.BOLD, 14));
		
		provinceLabel = new JLabel(hInfoPanelProvText);
		provinceLabel.setForeground(new Color(123, 195, 66));
		provinceLabel.setFont(new Font("", Font.BOLD, 14));
		
		regionLabel = new JLabel(hInfoPanelRegionText);
		regionLabel.setForeground(new Color(123, 195, 66));
		regionLabel.setFont(new Font("", Font.BOLD, 14));

		maritalStatusLabel = new JLabel(hInfoPanelMaritalText);
		maritalStatusLabel.setForeground(new Color(123, 195, 66));
		maritalStatusLabel.setFont(new Font("", Font.BOLD, 14));
		
		workStatusLabel = new JLabel(hInfoPanelWorkStatusText);
		workStatusLabel.setForeground(new Color(123, 195, 66));
		workStatusLabel.setFont(new Font("", Font.BOLD, 14));
		
		workExtraLabel = new JLabel(hInfoPanelExtra1Text);
		workExtraLabel.setForeground(new Color(123, 195, 66));
		workExtraLabel.setFont(new Font("", Font.BOLD, 14));
		
		canadianStatusLabel = new JLabel(hInfoPanelCanadianText);
		canadianStatusLabel.setForeground(new Color(123, 195, 66));
		canadianStatusLabel.setFont(new Font("", Font.BOLD, 14));
		
		canadianExtraLabel = new JLabel(hInfoPanelExtra2Text);
		canadianExtraLabel.setForeground(new Color(123, 195, 66));
		canadianExtraLabel.setFont(new Font("", Font.BOLD, 14));
		

		originLabel = new JLabel(hInfoPanelOriginText);
		originLabel.setForeground(new Color(123, 195, 66));
		originLabel.setFont(new Font("", Font.BOLD, 14));
		
		originExtraLabel = new JLabel(hInfoPanelArrivalText);
		originExtraLabel.setForeground(new Color(123, 195, 66));
		originExtraLabel.setFont(new Font("", Font.BOLD, 14));
		
		motherTongueLabel = new JLabel(hInfoPanelMotherText);
		motherTongueLabel.setForeground(new Color(123, 195, 66));
		motherTongueLabel.setFont(new Font("", Font.BOLD, 14));
		
		commLanguageLabel = new JLabel(hInfoPanelCommLangText);
		commLanguageLabel.setForeground(new Color(123, 195, 66));
		commLanguageLabel.setFont(new Font("", Font.BOLD, 14));

		referralLabel = new JLabel(hInfoPanelReferralText);
		referralLabel.setForeground(new Color(123, 195, 66));
		referralLabel.setFont(new Font("", Font.BOLD, 14));
		
		referralExtraLabel = new JLabel(hInfoPanelSpecifyText);
		referralExtraLabel.setForeground(new Color(123, 195, 66));
		referralExtraLabel.setFont(new Font("", Font.BOLD, 14));
		
		reasonLabel = new JLabel(hInfoPanelReasonText);
		reasonLabel.setForeground(new Color(123, 195, 66));
		reasonLabel.setFont(new Font("", Font.BOLD, 14));
		
		reasonExtraLabel = new JLabel(hInfoPanelSpecifyText);
		reasonExtraLabel.setForeground(new Color(123, 195, 66));
		reasonExtraLabel.setFont(new Font("", Font.BOLD, 14));
		
		registrationDateLabel = new JLabel(hInfoPanelDateRegText);
		registrationDateLabel.setForeground(new Color(123, 195, 66));
		registrationDateLabel.setFont(new Font("", Font.BOLD, 14));
		
		
		edit = new JButton(hInfoPanelEditBtnText);

		warning = new JLabel();
		warning.setForeground(Color.RED);
		if (fileManager.getActiveFile() != null) {

			// fileNumber = new
			// JTextField(Long.toString(fileManager.getActiveFile().getFileNumber()));
			// firstNameTxt = new
			// JTextField(fileManager.getActiveFile().getRepresentative().getFirstName());
			// lastNameTxt = new
			// JTextField(fileManager.getActiveFile().getRepresentative().getLastName());
			// medicareTxt = new
			// JTextField(fileManager.getActiveFile().getRepresentative().getMedicare());

			// setting the Labels
			this.fileNumberLabel2 = new JLabel(Long.toString(fileManager
					.getActiveFile().getFileNumber()));
			fileNumberLabel2.setForeground(new Color(51, 102, 153));
			fileNumberLabel2.setFont(new Font("", Font.BOLD, 12));
			
			firstNameLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getFirstName());
			firstNameLabel2.setForeground(new Color(51, 102, 153));
			firstNameLabel2.setFont(new Font("", Font.BOLD, 12));
			
			lastNameLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getLastName());
			lastNameLabel2.setForeground(new Color(51, 102, 153));
			lastNameLabel2.setFont(new Font("", Font.BOLD, 12));
			
			medicareLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getMedicare());
			medicareLabel2.setForeground(new Color(51, 102, 153));
			medicareLabel2.setFont(new Font("", Font.BOLD, 12));

			// must derive from medicare
			// genderCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getSex());
			// ageSpinner.setValue(fileManager.getActiveFile().getRepresentative().getAge());

			this.genderLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getSex().toString());
			genderLabel2.setForeground(new Color(51, 102, 153));
			genderLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.ageLabel2 = new JLabel(Integer.toString(fileManager
					.getActiveFile().getRepresentative().getAge()));
			ageLabel2.setForeground(new Color(51, 102, 153));
			ageLabel2.setFont(new Font("", Font.BOLD, 12));

			// addressTxt = new
			// JTextField(Integer.toString(fileManager.getActiveFile().getStreetNumber())
			// + fileManager.getActiveFile().getStreetName());
			// unitNumberTxt = new
			// JTextField(Integer.toString(fileManager.getActiveFile().getUnitNumber()));
			// postalCodeTxt = new
			// JTextField(fileManager.getActiveFile().getPostalCode());

			this.addressLabel2 = new JLabel(fileManager.getActiveFile().getStreetName());
			addressLabel2.setForeground(new Color(51, 102, 153));
			addressLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.unitNumberLabel2 = new JLabel(Integer.toString(fileManager
					.getActiveFile().getUnitNumber()));
			unitNumberLabel2.setForeground(new Color(51, 102, 153));
			unitNumberLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.postalCodeLabel2 = new JLabel(fileManager.getActiveFile()
					.getPostalCode());
			postalCodeLabel2.setForeground(new Color(51, 102, 153));
			postalCodeLabel2.setFont(new Font("", Font.BOLD, 12));


			regionLabel2.setText(fileManager.getActiveFile().getRegion());
			regionLabel2.setForeground(new Color(51, 102, 153));
			regionLabel2.setFont(new Font("", Font.BOLD, 12));
			
			cityLabel2.setText(fileManager.getActiveFile().getCity());
			cityLabel2.setForeground(new Color(51, 102, 153));
			cityLabel2.setFont(new Font("", Font.BOLD, 12));
			
			provinceLabel2.setText(fileManager.getActiveFile().getProvince());
			provinceLabel2.setForeground(new Color(51, 102, 153));
			provinceLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.maritalStatusLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getMaritalStatus().toString());
			maritalStatusLabel2.setForeground(new Color(51, 102, 153));
			maritalStatusLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.workStatusLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getWorkStatus().toString());
			workStatusLabel2.setForeground(new Color(51, 102, 153));
			workStatusLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.workExtraLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getExtraWorkStatusField());
			workExtraLabel2.setForeground(new Color(51, 102, 153));
			workExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.canadianStatusLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getCanadianStatus().toString());
			canadianStatusLabel2.setForeground(new Color(51, 102, 153));
			canadianStatusLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.canadianExtraLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getExtraCanadianStatusField());
			canadianExtraLabel2.setForeground(new Color(51, 102, 153));
			canadianExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.originLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getOrigin().toString());
			originLabel2.setForeground(new Color(51, 102, 153));
			originLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.originExtraLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getExtraOriginField());
			originExtraLabel2.setForeground(new Color(51, 102, 153));
			originExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.motherTongueLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getMotherTongueString());
			motherTongueLabel2.setForeground(new Color(51, 102, 153));
			motherTongueLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.commLanguageLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getSecondLangString());
			commLanguageLabel2.setForeground(new Color(51, 102, 153));
			commLanguageLabel2.setFont(new Font("", Font.BOLD, 12));

			this.referralLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getReferral().toString());
			referralLabel2.setForeground(new Color(51, 102, 153));
			referralLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.referralExtraLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getReferralExtra());
			referralExtraLabel2.setForeground(new Color(51, 102, 153));
			referralExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.reasonLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getReason().toString());
			reasonLabel2.setForeground(new Color(51, 102, 153));
			reasonLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.reasonExtraLabel2 = new JLabel(fileManager.getActiveFile()
					.getRepresentative().getReasonExtra());
			reasonExtraLabel2.setForeground(new Color(51, 102, 153));
			reasonExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			this.registrationDateLabel2 = new JLabel(fileManager
					.getActiveFile().getRepresentative().getRegistrationDate()
					.toString());
			registrationDateLabel2.setForeground(new Color(51, 102, 153));
			registrationDateLabel2.setFont(new Font("", Font.BOLD, 12));

			search = new JButton();
		} else {
			
			fileNumberLabel2 = new JLabel();
			// this.fileNumberLabel.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));
			fileNumberLabel2.setForeground(new Color(51, 102, 153));
			fileNumberLabel2.setFont(new Font("", Font.BOLD, 12));

			firstNameLabel2 = new JLabel();
			firstNameLabel2.setForeground(new Color(51, 102, 153));
			firstNameLabel2.setFont(new Font("", Font.BOLD, 12));
			
			lastNameLabel2 = new JLabel();
			lastNameLabel2.setForeground(new Color(51, 102, 153));
			lastNameLabel2.setFont(new Font("", Font.BOLD, 12));
			
			medicareLabel2 = new JLabel();
			medicareLabel2.setForeground(new Color(51, 102, 153));
			medicareLabel2.setFont(new Font("", Font.BOLD, 12));
			
			genderLabel2 = new JLabel();
			genderLabel2.setForeground(new Color(51, 102, 153));
			genderLabel2.setFont(new Font("", Font.BOLD, 12));
			
			ageLabel2 = new JLabel();
			ageLabel2.setForeground(new Color(51, 102, 153));
			ageLabel2.setFont(new Font("", Font.BOLD, 12));
			

			addressLabel2 = new JLabel();
			addressLabel2.setForeground(new Color(51, 102, 153));
			addressLabel2.setFont(new Font("", Font.BOLD, 12));
			
			unitNumberLabel2 = new JLabel();
			unitNumberLabel2.setForeground(new Color(51, 102, 153));
			unitNumberLabel2.setFont(new Font("", Font.BOLD, 12));
			
			postalCodeLabel2 = new JLabel();
			postalCodeLabel2.setForeground(new Color(51, 102, 153));
			postalCodeLabel2.setFont(new Font("", Font.BOLD, 12));
			
			cityLabel2 = new JLabel();
			cityLabel2.setForeground(new Color(51, 102, 153));
			cityLabel2.setFont(new Font("", Font.BOLD, 12));
			
			provinceLabel2 = new JLabel();
			provinceLabel2.setForeground(new Color(51, 102, 153));
			provinceLabel2.setFont(new Font("", Font.BOLD, 12));
			
			regionLabel2 = new JLabel();
			regionLabel2.setForeground(new Color(51, 102, 153));
			regionLabel2.setFont(new Font("", Font.BOLD, 12));

			maritalStatusLabel2 = new JLabel();
			maritalStatusLabel2.setForeground(new Color(51, 102, 153));
			maritalStatusLabel2.setFont(new Font("", Font.BOLD, 12));
			
			workStatusLabel2 = new JLabel();
			workStatusLabel2.setForeground(new Color(51, 102, 153));
			workStatusLabel2.setFont(new Font("", Font.BOLD, 12));
			
			workExtraLabel2 = new JLabel();
			workExtraLabel2.setForeground(new Color(51, 102, 153));
			workExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			canadianStatusLabel2 = new JLabel();
			canadianStatusLabel2.setForeground(new Color(51, 102, 153));
			canadianStatusLabel2.setFont(new Font("", Font.BOLD, 12));
			
			canadianExtraLabel2 = new JLabel();
			canadianExtraLabel2.setForeground(new Color(51, 102, 153));
			canadianExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			

			originLabel2 = new JLabel();
			originLabel2.setForeground(new Color(51, 102, 153));
			originLabel2.setFont(new Font("", Font.BOLD, 12));
			
			originExtraLabel2 = new JLabel();
			originExtraLabel2.setForeground(new Color(51, 102, 153));
			originExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			motherTongueLabel2 = new JLabel();
			motherTongueLabel2.setForeground(new Color(51, 102, 153));
			motherTongueLabel2.setFont(new Font("", Font.BOLD, 12));
			
			commLanguageLabel2 = new JLabel();
			commLanguageLabel2.setForeground(new Color(51, 102, 153));
			commLanguageLabel2.setFont(new Font("", Font.BOLD, 12));
			

			referralLabel2 = new JLabel();
			referralLabel2.setForeground(new Color(51, 102, 153));
			referralLabel2.setFont(new Font("", Font.BOLD, 12));
			
			referralExtraLabel2 = new JLabel();
			referralExtraLabel2.setForeground(new Color(51, 102, 153));
			referralExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			reasonLabel2 = new JLabel();
			reasonLabel2.setForeground(new Color(51, 102, 153));
			reasonLabel2.setFont(new Font("", Font.BOLD, 12));
			
			reasonExtraLabel2 = new JLabel();
			reasonExtraLabel2.setForeground(new Color(51, 102, 153));
			reasonExtraLabel2.setFont(new Font("", Font.BOLD, 12));
			
			registrationDateLabel2 = new JLabel();
			registrationDateLabel2.setForeground(new Color(51, 102, 153));
			registrationDateLabel2.setFont(new Font("", Font.BOLD, 12));
			

			search = new JButton();
		}
		search.setIcon(new ImageIcon("images/search_b.png"));
		
		
	}

	public void clearFields() {

		fileNumber.setText("");
		addressTxt.setText("");
	}

	public void reset() {
		flagPanel.setHouse(FileManager.getUniqueInstance().getActiveFile());
	
		fileNumberLabel2.setText(Long.toString(fileManager.getActiveFile()
				.getFileNumber()));
		// this.fileNumberLabel.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));
		firstNameLabel2.setText(fileManager.getActiveFile().getRepresentative()
				.getFirstName());
		lastNameLabel2.setText(fileManager.getActiveFile().getRepresentative()
				.getLastName());
		medicareLabel2.setText(fileManager.getActiveFile().getRepresentative()
				.getMedicare());

		// must derive from medicare

		this.genderLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getSex().toString());
		this.ageLabel2.setText(Integer.toString(fileManager.getActiveFile()
				.getRepresentative().getAge()));

		this.addressLabel2.setText(fileManager.getActiveFile().getStreetName());
		this.unitNumberLabel2.setText(Integer.toString(fileManager
				.getActiveFile().getUnitNumber()));
		this.postalCodeLabel2.setText(fileManager.getActiveFile()
				.getPostalCode());
		regionLabel2.setText(fileManager.getActiveFile().getRegion());
		provinceLabel2.setText(fileManager.getActiveFile().getProvince());
		cityLabel2.setText(fileManager.getActiveFile().getCity());
		// postal code must derive automatically city and province, also
		// suggests region
		// cityCBox.setSelectedIndex(0);
		// provinceCBox.setSelectedIndex(0);
		// regionCBox.setSelectedIndex(0);

		this.maritalStatusLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getMaritalStatus().toString());
		this.workStatusLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getWorkStatus().toString());
		this.workExtraLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getExtraWorkStatusField());
		this.canadianStatusLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getCanadianStatus().toString());
		this.canadianExtraLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getExtraCanadianStatusField());

		this.originLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getOrigin().toString());
		this.originExtraLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getExtraOriginField());
		this.motherTongueLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getMotherTongueString());
		this.commLanguageLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getSecondLangString());

		this.referralLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getReferral().toString());
		this.referralExtraLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getReferralExtra());
		this.reasonLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getReason().toString());
		this.reasonExtraLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getReasonExtra());
		// registrationDateTxt.setText(fileManager.getActiveFile().getRepresentative().getRegistrationDate().toString());
		this.registrationDateLabel2.setText(fileManager.getActiveFile()
				.getRepresentative().getRegistrationDate().toString());

	}

	private void addComponents() {


		add(fileNumberLabel, "grow");
		add(fileNumberLabel2, "wrap, w 150:150:200");

		/* Common client fields */
		add(firstNameLabel, "grow");
		add(this.firstNameLabel2, "w 150:150:200");

		add(lastNameLabel, "grow");
		add(this.lastNameLabel2, "w 150:150:200, wrap");

		add(medicareLabel, "grow");
		add(this.medicareLabel2, "w 120:120:150");

		add(genderLabel, "grow");
		add(this.genderLabel2, "w 100:100:100");

		add(ageLabel, "grow");
		// add(ageTxt, "w 35:35:40,wrap");
		add(this.ageLabel2, "w 50:50:50,wrap");

		/* Location Fields */
		add(addressLabel, "grow");
		add(this.addressLabel2, "w 150:150:150");

		add(unitNumberLabel, "grow");
		add(this.unitNumberLabel2, "w 100:100:100");

		add(postalCodeLabel, "grow");
		add(this.postalCodeLabel2, "w 120:120:120,wrap");

		add(cityLabel, "grow");
		add(this.cityLabel2, "w 100:100:100");

		add(provinceLabel, "grow");
		add(this.provinceLabel2, "w 100:100:100");

		add(regionLabel, "grow");
		add(this.regionLabel2, "w 100:100:100, wrap");

		/* Other status fields */
		add(maritalStatusLabel, "grow");
		add(this.maritalStatusLabel2, "w 150:150:150");

		add(workStatusLabel, "grow");
		add(this.workStatusLabel2, "w 150:150:150");

		add(workExtraLabel, "grow");
		add(this.workExtraLabel2, "w 120:120:120,wrap");

		add(canadianStatusLabel, "grow");
		add(this.canadianStatusLabel2, "w 150:150:150");

		add(canadianExtraLabel, "grow");
		add(this.canadianExtraLabel2, "w 120:120:120, wrap");

		/* Demographic fields */
		add(originLabel, "grow");
		add(this.originLabel2, "w 150:150:150");

		add(originExtraLabel, "grow");
		add(this.originExtraLabel2, "w 150:150:150,wrap");

		add(motherTongueLabel, "grow");
		add(this.motherTongueLabel2, "w 120:120:120");

		add(commLanguageLabel, "grow");
		add(this.commLanguageLabel2, "w 120:120:120, wrap");

		/* Registration info fields */
		add(referralLabel, "grow");
		add(this.referralLabel2, "w 150:150:150");

		add(referralExtraLabel, "grow");
		add(this.referralExtraLabel2, "w 150:150:150,wrap");

		add(reasonLabel, "grow");
		add(this.reasonLabel2, "w 120:120:120");

		add(reasonExtraLabel, "grow");
		add(this.reasonExtraLabel2, "w 120:120:120");

		add(registrationDateLabel, "grow");
		add(this.registrationDateLabel2, "w 120:120:120, wrap");

		add(edit, "grow");
		/*
		 * add(repLabel, "grow"); add(rep, "wrap, grow"); add(addressLabel);
		 * add(address, "wrap, grow"); add(postLabel); add(post, "wrap, grow");
		 * add(phoneLabel); add(phone, "wrap, grow"); add(warning, "span");
		 */
		
	}

	public JButton getSearchButton() {
		return search;
	}

	public Household getHousehold() {
		return fileManager.getActiveFile();
	}

	public String getFileNumber() {
		return fileNumber.getText().toString();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.reset();
		this.repaint();
	}

	public void set(Household household) {
		if (household != null) {
			if (flagPanel == null){
				flagPanel = new FlagPanel(household);
				add(flagPanel, "north");
			}
			searchField.setText(null);

			// this.rep.setText(fileManager.getActiveFile().getRepresentative().getFullName());
			this.fileNumber.setText(Long.toString(household.getFileNumber()));
			firstNameTxt.setText(fileManager.getActiveFile()
					.getRepresentative().getFirstName());
			lastNameTxt.setText(fileManager.getActiveFile().getRepresentative()
					.getLastName());
			medicareTxt.setText(fileManager.getActiveFile().getRepresentative()
					.getMedicare());

			// must derive from medicare
			genderCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getSex());
			ageSpinner.setValue(fileManager.getActiveFile().getRepresentative()
					.getAge());

			addressTxt.setText(fileManager.getActiveFile().getStreetName());
			unitNumberTxt.setText(Integer.toString(fileManager.getActiveFile()
					.getUnitNumber()));
			postalCodeTxt.setText(fileManager.getActiveFile().getPostalCode());
			regionLabel2.setText(fileManager.getActiveFile().getRegion());
			provinceLabel2.setText(fileManager.getActiveFile().getProvince());
			cityLabel2.setText(fileManager.getActiveFile().getCity());
			// postal code must derive automatically city and province, also
			// suggests region
			cityCBox.setSelectedIndex(0);
			provinceCBox.setSelectedIndex(0);
			regionCBox.setSelectedIndex(0);

			maritalStatusCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getMaritalStatus());
			workStatusCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getWorkStatus());
			workExtraTxt.setText(fileManager.getActiveFile()
					.getRepresentative().getExtraWorkStatusField());
			canadianStatusCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getCanadianStatus());
			canadianExtraTxt.setText(fileManager.getActiveFile()
					.getRepresentative().getExtraCanadianStatusField());

			referralCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getReferral());
			referralExtraTxt.setText(fileManager.getActiveFile()
					.getRepresentative().getReferralExtra());
			reasonCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getReason());
			reasonExtraTxt.setText(fileManager.getActiveFile()
					.getRepresentative().getReasonExtra());
			registrationDateTxt.setText(fileManager.getActiveFile()
					.getRepresentative().getRegistrationDate().toString());

		} else {
			// this.rep.setText(null);
			this.fileNumber.setText(null);
			firstNameTxt.setText(null);
			lastNameTxt.setText(null);
			medicareTxt.setText(null);
			genderCBox.setSelectedIndex(0);
			ageSpinner.setValue(0);

			this.addressTxt.setText(null);
		}
	}

	// *************************************************************************************************
	// *************************************************************************************************
	// *************************************************************************************************

	public void initializeElements2() {
		searchField = new JTextField();

		fileNumberLabel = new JLabel(hInfoPanelFileText);
		// this.fileNumberLabel.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));

		firstNameLabel = new JLabel(hInfoPanelFirstNameText);
		lastNameLabel = new JLabel(hInfoPanelLastNameText);
		medicareLabel = new JLabel(hInfoPanelMedicareText);
		genderLabel = new JLabel(hInfoPanelGenderText);
		ageLabel = new JLabel(hInfoPanelAgeText);

		addressLabel = new JLabel(hInfoPanelAgeText);
		unitNumberLabel = new JLabel(hInfoPanelAptText);
		postalCodeLabel = new JLabel(hInfoPanelPostalText);
		cityLabel = new JLabel(hInfoPanelCityText);
		provinceLabel = new JLabel(hInfoPanelProvText);
		regionLabel = new JLabel(hInfoPanelRegionText);

		maritalStatusLabel = new JLabel(hInfoPanelMaritalText);
		workStatusLabel = new JLabel(hInfoPanelWorkStatusText);
		workExtraLabel = new JLabel(hInfoPanelExtra1Text);
		canadianStatusLabel = new JLabel(hInfoPanelCanadianText);
		canadianExtraLabel = new JLabel(hInfoPanelExtra2Text);

		originLabel = new JLabel(hInfoPanelOriginText);
		originExtraLabel = new JLabel(hInfoPanelArrivalText);
		motherTongueLabel = new JLabel(hInfoPanelMotherText);
		commLanguageLabel = new JLabel(hInfoPanelCommLangText);

		referralLabel = new JLabel(hInfoPanelReferralText);
		referralExtraLabel = new JLabel(hInfoPanelSpecifyText);
		reasonLabel = new JLabel(hInfoPanelReasonText);
		reasonExtraLabel = new JLabel(hInfoPanelSpecifyText);
		registrationDateLabel = new JLabel("hInfoPanelDateRegText");
		edit = new JButton("Edit Client File");

		warning = new JLabel();
		warning.setForeground(Color.RED);
		if (fileManager.getActiveFile() != null) {

			fileNumber = new JTextField(Long.toString(fileManager
					.getActiveFile().getFileNumber()));
			firstNameTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getFirstName());
			lastNameTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getLastName());
			medicareTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getMedicare());

			// setting the Labels

			// must derive from medicare
			genderCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getSex());
			ageSpinner.setValue(fileManager.getActiveFile().getRepresentative()
					.getAge());

			addressTxt = new JTextField(fileManager.getActiveFile().getStreetName());
			unitNumberTxt = new JTextField(Integer.toString(fileManager
					.getActiveFile().getUnitNumber()));
			postalCodeTxt = new JTextField(fileManager.getActiveFile()
					.getPostalCode());

			// postal code must derive automatically city and province, also
			// suggests region
			cityCBox.setSelectedIndex(0);
			provinceCBox.setSelectedIndex(0);
			regionCBox.setSelectedIndex(0);

			maritalStatusCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getMaritalStatus());
			workStatusCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getWorkStatus());
			workExtraTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getExtraWorkStatusField());
			canadianStatusCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getCanadianStatus());
			canadianExtraTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getExtraCanadianStatusField());

			originCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getOrigin());
			originExtraTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getExtraOriginField());
			motherTongueCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getMotherTongue());
			commLanguageCBox.setSelectedItem(fileManager.getActiveFile().getRepresentative().getSecondLanguage());

			referralCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getReferral());
			referralExtraTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getReferralExtra());
			reasonCBox.setSelectedItem(fileManager.getActiveFile()
					.getRepresentative().getReason());
			reasonExtraTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getReasonExtra());
			registrationDateTxt = new JTextField(fileManager.getActiveFile()
					.getRepresentative().getRegistrationDate().toString());

			search = new JButton();
		} else {
			fileNumber = new JTextField();
			firstNameTxt = new JTextField();
			lastNameTxt = new JTextField();
			medicareTxt = new JTextField();

			// normally derived from medicare
			genderCBox.setSelectedIndex(0);
			ageSpinner.setValue(0);

			addressTxt = new JTextField();
			unitNumberTxt = new JTextField();
			postalCodeTxt = new JTextField();

			// normally derived automatically by postal code
			cityCBox.setSelectedIndex(0);
			provinceCBox.setSelectedIndex(0);
			regionCBox.setSelectedIndex(0);

			maritalStatusCBox.setSelectedIndex(0);
			workStatusCBox.setSelectedIndex(0);
			workExtraTxt = new JTextField();
			canadianStatusCBox.setSelectedIndex(0);
			canadianExtraTxt = new JTextField();

			originCBox.setSelectedIndex(0);
			originExtraTxt = new JTextField();
			motherTongueCBox.setSelectedIndex(0);
			commLanguageCBox.setSelectedIndex(0);

			referralCBox.setSelectedIndex(0);
			referralExtraTxt = new JTextField();
			reasonCBox.setSelectedIndex(0);
			reasonExtraTxt = new JTextField();
			registrationDateTxt = new JTextField();

			// this.fileNumberLabel.setText(Long.toString(fileManager.getActiveFile().getFileNumber()));

			search = new JButton();
		}
		search.setIcon(new ImageIcon("images/search_b.png"));
	}

	public void addComponents2() {
		add(fileNumberLabel, "grow");
		add(fileNumber, "wrap, w 150:150:200");

		/* Common client fields */
		add(firstNameLabel, "grow");
		add(firstNameTxt, "w 150:150:200");
		add(lastNameLabel, "grow");
		add(lastNameTxt, "w 150:150:200");
		add(medicareLabel, "grow");
		add(medicareTxt, "w 120:120:150");
		add(genderLabel, "grow");
		add(genderCBox, "w 100:100:100");
		add(ageLabel, "grow");
		// add(ageTxt, "w 35:35:40,wrap");
		add(ageSpinner, "w 50:50:50,wrap");

		/* Location Fields */
		add(addressLabel, "grow");
		add(addressTxt, "w 150:150:150");
		add(unitNumberLabel, "grow");
		add(unitNumberTxt, "w 100:100:100");
		add(postalCodeLabel, "grow");
		add(postalCodeTxt, "w 120:120:120");
		add(cityLabel, "grow");
		add(cityCBox, "w 100:100:100");
		add(provinceLabel, "grow");
		add(provinceCBox, "w 100:100:100");
		add(regionLabel, "grow");
		add(regionCBox, "w 100:100:100, wrap");

		/* Other status fields */
		add(maritalStatusLabel, "grow");
		add(maritalStatusCBox, "w 150:150:150");
		add(workStatusLabel, "grow");
		add(workStatusCBox, "w 150:150:150");
		add(workExtraLabel, "grow");
		add(workExtraTxt, "w 120:120:120");
		add(canadianStatusLabel, "grow");
		add(canadianStatusCBox, "w 150:150:150");
		add(canadianExtraLabel, "grow");
		add(canadianExtraTxt, "w 120:120:120, wrap");

		/* Demographic fields */
		add(originLabel, "grow");
		add(originCBox, "w 150:150:150");
		add(originExtraLabel, "grow");
		add(originExtraTxt, "w 150:150:150");
		add(motherTongueLabel, "grow");
		add(motherTongueCBox, "w 120:120:120");
		add(commLanguageLabel, "grow");
		add(commLanguageCBox, "w 120:120:120, wrap");

		/* Registration info fields */
		add(referralLabel, "grow");
		add(referralCBox, "w 150:150:150");
		add(referralExtraLabel, "grow");
		add(referralExtraTxt, "w 150:150:150");
		add(reasonLabel, "grow");
		add(reasonCBox, "w 120:120:120");
		add(reasonExtraLabel, "grow");
		add(reasonExtraTxt, "w 120:120:120");
		add(registrationDateLabel, "grow");
		add(registrationDateTxt, "w 120:120:120, wrap");

	}

	// *******************************************************************************************
	// *******************************************************************************************
	// *******************************************************************************************

	public void setWarning(String warning) {
		this.warning.setText(warning);
	}

	public void AddSearchListener(ActionListener actionListner) {
		this.search.addActionListener(actionListner);
	}

	public void addEditListener(final Household household) {
		edit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
					ClientForm cForm = new ClientForm();
				
				
				



			}
		});
	}

	public JTextField getSearchField() {
		return searchField;
	}

	@Override
	public void localeChanged(LocaleChangeEvent e) {
		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		
		this.fileNumberLabel.setText(messages.getString("hInfoPanelFileText"));
		this.firstNameLabel.setText(messages.getString("hInfoPanelFirstNameText"));
		this.lastNameLabel.setText(messages.getString("hInfoPanelLastNameText"));
		this.medicareLabel.setText(messages.getString("hInfoPanelMedicareText"));
		this.ageLabel.setText(messages.getString("hInfoPanelAgeText"));
		this.addressLabel.setText(messages.getString("hInfoPanelAddressText"));
		this.cityLabel.setText(messages.getString("hInfoPanelCityText"));
		this.provinceLabel.setText(messages.getString("hInfoPanelProvinceText"));
		this.regionLabel.setText(messages.getString("hInfoPanelRegionText"));
		this.postalCodeLabel.setText(messages.getString("hInfoPanelPostalText"));
		this.reasonLabel.setText(messages.getString("hInfoPanelReasonText"));
		this.genderLabel.setText(messages.getString("hInfoGenderText"));
		this.unitNumberLabel.setText(messages.getString("hInfoPanelAptText"));
		this.maritalStatusLabel.setText(messages.getString("hInfoPanelMaritalText"));
		this.workStatusLabel.setText(messages.getString("hInfoPanelWorkStatusText"));
		this.workExtraLabel.setText(messages.getString("hInfoPanelExtra1Text"));
		this.canadianStatusLabel.setText(messages.getString("hInfoPanelCanadianText"));
		this.canadianExtraLabel.setText(messages.getString("hInfoPanelExtra2Text"));
		this.originLabel.setText(messages.getString("hInfoPanelOriginText"));
		this.originExtraLabel.setText(messages.getString("hInfoPanelArrivalText"));
		this.referralLabel.setText(messages.getString("hInfoPanelReferralText"));
		this.referralExtraLabel.setText(messages.getString("hInfoPanelSpecifyText"));
		this.reasonExtraLabel.setText(messages.getString("hInfoPanelSpecifyText"));
		this.registrationDateLabel.setText(messages.getString("hInfoPanelDateRegText"));
		this.edit.setText(messages.getString("hInfoPanelEditBtnText"));
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,messages.getString("hInfoPanelBorderText")));
		
		
		
		
	}
}

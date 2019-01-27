package presentation.views.files;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

public class ClientIncomePanel extends JPanel{
	
	private static final long serialVersionUID = 3454365436436L;
	
	private JLabel workStatusComboBoxLabel;
	private JComboBox workStatusComboBox;
	private String workStatus[] = {"Part Time","Full Time","Welfare","Worker Compensation"};
	
	private JLabel welfareTextFieldLabel;
	private JTextField welfareTextField;
	
	private JLabel canadianStatusComboBoxLabel;
	private JComboBox canadianStatusComboBox;
	private String canadianStatus[] = {"Canadian Citizen","Permanent Resident","Immigrant","Refugee","Temporary Student"};
	
	private JLabel canadianStatusTextFieldLabel;
	private JLabel canadianImmigrationNumberLabel;
	private JTextField canadianImmigrationNumberTextField;
	
	private JLabel countryOfOriginLabel;
	private JLabel dateofArrivalLabel;
	
	private JTable incomeTable;
	private String[] columnNames = 	{"Income Source","Income Amount"};
	//added in a popup menu
	private JLabel incomeAmountLabel;
	private JLabel incomeSourceLabel;
	
	private String countries[] = {"Afghanistan", "Albania","Algeria","Andorra","Angola,Antigua & Deps","Argentina","Armenia","Australia",
			"Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bhutan",
			"Bolivia","Bosnia Herzegovina","Botswana","Brazil","Brunei","Bulgaria","Burkina","Burundi","Cambodia","Cameroon","Canada",
			"Cape Verde","Central African Rep","Chad,Chile","China","Colombia","Comoros","Congo","Congo {Democratic Rep}","Costa Rica",
			"Croatia","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","East Timor","Ecuador","Egypt",
			"El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Fiji","Finland","France","Gabon","Gambia","Georgia","Germany",
			"Ghana","Greece","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana,Haiti","Honduras","Hungary","Iceland","India","Indonesia",
			"Iran","Iraq","Ireland {Republic}","Israel","Italy","Ivory Coast","Jamaica","Japan","Jordan","Kazakhstan","Kenya","Kiribati","Korea North",
			"Korea South","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania",
			"Luxembourg","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius",
			"Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar {Burma}","Namibia","Nauru","Nepal",
			"Netherlands","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay",
			"Peru","Philippines","Poland","Portugal","Qatar","Romania","Russian Federation", "Rwanda","St Kitts & Nevis","St Lucia","Saint Vincent & the Grenadines",
			"Samoa","San Marino","Sao Tome & Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands",
			"Somalia","South Africa","South Sudan","Spain","Sri Lanka","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand",
			"Togo","Tonga","Trinidad & Tobago","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States",
			"Uruguay","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam","Yemen","Zambia","Zimbabwe"};
	private JComboBox countryOfOriginComboBox;
	
	private JLabel dateOfArrivalLabel;
	private JTextField dateOfArrivalTextField;
	
	public ClientIncomePanel()
	{
		this.setLayout(new MigLayout());
		this.initializeElements();
		this.addComponents();
	}
	
	public void initializeElements()
	{
		String clientIncomePanelWorkStatusText="Work Status: ";
		String clientIncomePanelWelfareText="Welfare: ";
		String clientIncomePanelCanStatusText="Canadian Status: ";
		String clientIncomePanelCanImmText="Canadian Immigration Number: ";
		String clientIncomePanelOriginText="Country of Origin: ";
		String clientIncomePanelArrivalDateText="Date of Arrival: ";
		String clientIncomePanelIncomeAmtText="Income Amount: ";
		String clientIncomePanelIncomeSrcText="Income Source: ";

		
		this.workStatusComboBoxLabel = new JLabel(clientIncomePanelWorkStatusText);
		this.welfareTextFieldLabel = new JLabel(clientIncomePanelWelfareText);
		this.canadianStatusComboBoxLabel = new JLabel(clientIncomePanelCanStatusText);
		this.canadianImmigrationNumberLabel = new JLabel(clientIncomePanelCanImmText);
		this.countryOfOriginLabel = new JLabel(clientIncomePanelOriginText);
		this.dateOfArrivalLabel = new JLabel(clientIncomePanelArrivalDateText);
		this.incomeAmountLabel = new JLabel(clientIncomePanelIncomeAmtText);
		this.incomeSourceLabel = new JLabel(clientIncomePanelIncomeSrcText);
	
		this.workStatusComboBox = new JComboBox (workStatus);
		this.canadianStatusComboBox = new JComboBox(canadianStatus);
		this.countryOfOriginComboBox = new JComboBox(countries);

		this.canadianImmigrationNumberTextField = new JTextField();
		this.dateOfArrivalTextField = new JTextField();
		this.welfareTextField = new JTextField();
		
		
		//HAVE FUN DEALING WITH THE COMBOBOX :)
	}
	public void addComponents()
	{
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		setBorder(BorderFactory.createTitledBorder(loweredetched,
		"Client Income Info"));
		add(workStatusComboBoxLabel, "grow");
		add(workStatusComboBox, "grow, wrap");
		add(welfareTextFieldLabel, "grow");
		add(welfareTextField, "grow, wrap");
		add(canadianStatusComboBoxLabel, "grow");
		add(canadianStatusComboBox, "grow, wrap");
		add(canadianImmigrationNumberLabel, "grow");
		add(canadianImmigrationNumberTextField, "grow, wrap");
		add(dateOfArrivalLabel, "grow");
		add(dateOfArrivalTextField, "grow, wrap");
		add(incomeAmountLabel, "grow, wrap");
		add(incomeSourceLabel, "grow, wrap");
//		this.add(firstNameLabel);
//		this.add(firstNameTextField, "grow, w 300, wrap");
//		this.add(middleNameLabel);
//		this.add(middleNameTextField, "grow, w 300, wrap");
//		this.add(lastNameLabel);
//		this.add(lastNameTextField, "grow, w 300, wrap");
//		this.add(medicareNumberLabel);
//		this.add(medicareNumberTextField, "grow, w 300, wrap");
//		this.add(genderLabel);
//		this.add(genderTextField, "grow, w 300, wrap");
//		this.add(dateOfBirthLabel);
//		this.add(dateOfBirthTextField, "grow, w 300, wrap");
//		this.add(maritalStatusLabel);
//		this.add(maritalStatusComboBox, "grow, w 300, wrap");
		
	}
	
}

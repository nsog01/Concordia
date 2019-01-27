//REMEMBER THAT THIS IS A PANEL INSIDE A FRAME, MORE THAN ONE PANEL IS GOING TO BE ADDED TO THAT FRAME
//SAME CLIENT OBJECT IS GOING TO BE PASSED TO ALL PANELS. 

package presentation.views.files;

import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import domain.events.Event;
import domain.events.EventList;
import domain.events.EventType;
import domain.files.City;
import domain.files.CityList;
import domain.files.Client.Sex;
import domain.files.FileRepository;
import domain.files.Language;
import domain.files.LanguageList;
import domain.files.Province;
import domain.files.ProvinceList;
import domain.files.Relation;
import domain.files.RelationList;
import domain.mappers.events.EventMapper;
import domain.mappers.events.EventTypeMapper;
import domain.mappers.files.CityMapper;
import domain.mappers.files.LanguageMapper;
import domain.mappers.files.ProvinceMapper;
import domain.mappers.files.RelationMapper;

import net.miginfocom.swing.MigLayout;

public class AdminAddPanel extends JPanel{
	
	private static final long serialVersionUID = 423748905432L;
	
	private JLabel AddLanguageLabel;
	private JLabel AddRelationLabel;
	private JLabel AddEventTypeLabel;
	private JLabel AddCityLabel;
	private JLabel AddProvinceLabel;
	private JLabel dateOfBirthLabel;
	private JLabel maritalStatusLabel;
	
	private JTextField addLanguageTextField;
	private JTextField addRelationTextField;
	private JTextField addEventTypeTextField;
	private JTextField addCityTextField;
	private JTextField addProvinceTextField;
	private JTextField dateOfBirthTextField;
	
	private JButton AddLanguageButton;
	private JButton AddRelationButton;
	private JButton AddEventTypeButton;
	private JButton AddCityLabelButton;
	private JButton AddProvinceButton;
	
	private JButton DeleteLanguageButton;
	private JButton DeleteRelationButton;
	private JButton DeleteEventTypeButton;
	private JButton DeleteCityLabelButton;
	private JButton DeleteProvinceButton;
	
	
	private String maritalStatus[] = {"Single","Married","Divorced","Widowed","Cohabiting","Civil Union","Domestic Partnership","Unmarried Partners"};
	
	private JComboBox AddLanguageComboBox;
	private JComboBox AddRelationComboBox;
	private JComboBox AddEventTypeComboBox;
	private JComboBox AddCityComboBox;
	private JComboBox AddProvinceComboBox;
	
	
	
	private JComboBox maritalStatusComboBox;
	
	
	public AdminAddPanel()
	{
		this.setLayout(new MigLayout());
		this.initializeElements();
		this.addComponents();
	}
	public void initializeElements()
	{
		this.AddLanguageLabel = new JLabel("Add Languages: ");
		this.AddRelationLabel = new JLabel("Add Relation: ");
		this.AddEventTypeLabel = new JLabel("Add Event Type: ");
		this.AddCityLabel = new JLabel("Add City: ");
		this.AddProvinceLabel = new JLabel("Add Province: ");
		this.dateOfBirthLabel = new JLabel("Date of Birth: ");
		this.maritalStatusLabel = new JLabel("Martial Status: ");
	
		this.addLanguageTextField = new JTextField();
		this.addRelationTextField = new JTextField();
		this.addEventTypeTextField = new JTextField();
		this.addCityTextField = new JTextField();
		this.addProvinceTextField = new JTextField();
		this.dateOfBirthTextField = new JTextField();
		
		
		LanguageMapper languageMapper = new LanguageMapper();
		Language language;
		LanguageList languagelist = new LanguageList();
		ArrayList<String>  arrLanguageList= new ArrayList<String>();
		
		languagelist = languageMapper.readAll();

		for(int i = 0; i<languagelist.size(); i++)
		{
			language = languagelist.get(i);
			String tempStr;
			tempStr = language.getLanguage();
			 arrLanguageList.add(tempStr);
		}
		String[] strListLanguage = new String[arrLanguageList.size()];
		strListLanguage = arrLanguageList.toArray(strListLanguage);
		
		
		
		this.AddLanguageComboBox = new JComboBox(strListLanguage);
		
		
		RelationMapper relationMapper = new RelationMapper();
		Relation relation;
		RelationList relationlist = new RelationList();
		ArrayList<String>  arrRelationList= new ArrayList<String>();
		
		relationlist = relationMapper.readAll();

		for(int i = 0; i<relationlist.size(); i++)
		{
			relation = relationlist.getRelationAt(i);
			String tempStr;
			tempStr = relation.getRelation();
			 arrRelationList.add(tempStr);
		}
		String[] strListRelation = new String[arrRelationList.size()];
		strListRelation = arrRelationList.toArray(strListRelation);
		
		
		
		this.AddRelationComboBox = new JComboBox(strListRelation);
		
		
		EventMapper eventMapper = new EventMapper();
		Event event;
		EventList eventlist = new EventList();
		ArrayList<String>  arrEventList= new ArrayList<String>();
		
		eventlist = eventMapper.readAll();

		for(int i = 0; i<eventlist.size(); i++)
		{
			event = eventlist.get(i);
			String tempStr;
			tempStr = event.getTitle();
			 arrEventList.add(tempStr);
		}
		String[] strListEvent = new String[arrEventList.size()];
		strListEvent = arrEventList.toArray(strListEvent);
		
		
		
		
		
		this.AddEventTypeComboBox = new JComboBox(strListEvent);

		

		CityMapper cityMapper = new CityMapper();
		City unclist;
		CityList clist = new CityList();
		ArrayList<String>  strCityList= new ArrayList<String>();
		
		clist = cityMapper.readAll();

		for(int i = 0; i<clist.size(); i++)
		{
			unclist = clist.get(i);
			String tempStr;
			tempStr = unclist.getCity();
			strCityList.add(tempStr);
		}
		String[] stockArrCity = new String[strCityList.size()];
		stockArrCity = strCityList.toArray(stockArrCity);
		
		
		
		
		this.AddCityComboBox = new JComboBox(stockArrCity);
		
		ProvinceMapper provinceMapper = new ProvinceMapper();
		Province province;
		ProvinceList provincelist = new ProvinceList();
		ArrayList<String>  arrProvinceList= new ArrayList<String>();
		
		provincelist = provinceMapper.readAll();

		for(int i = 0; i<provincelist.size(); i++)
		{
			province = provincelist.get(i);
			String tempStr;
			tempStr = province.getProvince();
			arrProvinceList.add(tempStr);
		}
		String[] strListProvince = new String[arrProvinceList.size()];
		strListProvince = arrProvinceList.toArray(strListProvince);
		
		
		this.AddProvinceComboBox = new JComboBox(strListProvince);

		
		this.maritalStatusComboBox = new JComboBox(maritalStatus);
		
		this.AddLanguageButton = new JButton("Add");
		this.DeleteLanguageButton = new JButton("Delete");
		
		AddLanguageButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	LanguageMapper tempLanguageMapper = new LanguageMapper();
            	
            	Language tempLanguage = new Language(addLanguageTextField.getText());
            	tempLanguageMapper.insert(tempLanguage);
            	FileRepository.getUniqueInstance().addToLanguageList(tempLanguage);
            	AddLanguageComboBox.addItem(addLanguageTextField.getText());
                
            	System.out.println("You clicked the button");
            }
        }); 
	       
		DeleteLanguageButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
            	AddLanguageComboBox.removeItem(AddLanguageComboBox.getSelectedItem());
            	
                System.out.println("You clicked the button");
            }
        });
		
		this.AddRelationButton = new JButton("Add");
		this.DeleteRelationButton = new JButton("Delete");
		
		AddRelationButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	RelationMapper tempRelationMapper = new RelationMapper();
            	
            	Relation tempRelation = new Relation(addRelationTextField.getText());
            	tempRelationMapper.insert(tempRelation);
            	AddRelationComboBox.addItem(addRelationTextField.getText());
                System.out.println("You clicked the button");
            }
        });
		
		DeleteRelationButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
            	AddRelationComboBox.removeItem(AddRelationComboBox.getSelectedItem());
                System.out.println("You clicked the button");
            }
        });
		
		
		this.AddEventTypeButton = new JButton("Add");
		this.DeleteEventTypeButton = new JButton("Delete");
		
		
		AddEventTypeButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	EventTypeMapper tempEventMapper = new EventTypeMapper();
            	
            	EventType tempEvent = new EventType(addEventTypeTextField.getText());
            	tempEventMapper.insert(tempEvent);
            	AddEventTypeComboBox.addItem(addEventTypeTextField.getText());
                System.out.println("You clicked the button");
            }
        });
		
		DeleteEventTypeButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
            	AddEventTypeComboBox.removeItem(AddEventTypeComboBox.getSelectedItem());
                System.out.println("You clicked the button");
            }
        });
		
		this.AddCityLabelButton = new JButton("Add");
		this.DeleteCityLabelButton = new JButton("Delete");
		
		AddCityLabelButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	CityMapper tempCityMapper = new CityMapper();
            	
            	City tempCity = new City(addCityTextField.getText());
            	tempCityMapper.insert(tempCity);
            	AddCityComboBox.addItem(addCityTextField.getText());
                System.out.println("You clicked the button");
            }
        });
		
		DeleteCityLabelButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
            	AddCityComboBox.removeItem(AddCityComboBox.getSelectedItem());
                System.out.println("You clicked the button");
            }
        });
		
		
		this.AddProvinceButton = new JButton("Add");
		this.DeleteProvinceButton = new JButton("Delete");
		
		AddProvinceButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	ProvinceMapper tempProvinceMapper = new ProvinceMapper();
            	
            	Province tempProvince = new Province(addProvinceTextField.getText());
            	tempProvinceMapper.insert(tempProvince);
            	AddProvinceComboBox.addItem(addProvinceTextField.getText());
                System.out.println("You clicked the button");
            }
        });
		
		DeleteProvinceButton.addActionListener(new ActionListener() {
	    	   
            public void actionPerformed(ActionEvent e)
            {
            	AddProvinceComboBox.removeItem(AddProvinceComboBox.getSelectedItem());
                System.out.println("You clicked the button");
            }
        });
		
		//HAVE FUN DEALING WITH THE COMBOBOX :)
	}

	public void addComponents()
	{
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		setBorder(BorderFactory.createTitledBorder(loweredetched,
		"Administration"));
		add(new EmptyBannerPanel("Administration Options"), "north");
		this.add(AddLanguageLabel);
		this.add(addLanguageTextField, "w 300");
		this.add(AddLanguageComboBox, "w 300");
		this.add(AddLanguageButton, "w 50");
		this.add(DeleteLanguageButton, "grow, w 50, wrap");
		this.add(AddRelationLabel);
		this.add(addRelationTextField, "w 300");
		this.add(AddRelationComboBox, "w 300");
		this.add(AddRelationButton, "w 50");
		this.add(DeleteRelationButton, "grow, w 50, wrap");
		this.add(AddEventTypeLabel);
		this.add(addEventTypeTextField, "w 300");
		this.add(AddEventTypeComboBox, "w 300");
		this.add(AddEventTypeButton, "w 50");
		this.add(DeleteEventTypeButton, "grow, w 50, wrap");
		this.add(AddCityLabel);
		this.add(addCityTextField, "w 300");
		this.add(AddCityComboBox, "w 300");
		this.add(AddCityLabelButton, "w 50");
		this.add(DeleteCityLabelButton, "grow, w 50, wrap");
		this.add(AddProvinceLabel);
		this.add(addProvinceTextField, "w 300");
		this.add(AddProvinceComboBox, "w 300");
		this.add(AddProvinceButton, "w 50");
		this.add(DeleteProvinceButton, "grow, w 50, wrap");
		/*this.add(dateOfBirthLabel);
		this.add(dateOfBirthTextField, "grow, w 300, wrap");
		*/
		//this.add(maritalStatusLabel);
		//this.add(maritalStatusComboBox, "grow, w 300, wrap");
		
	}

	public String getMedicare(){
		return this.addCityTextField.getText();
	}
	public void setDateOfBirth(Date dob){
		SimpleDateFormat format =  new SimpleDateFormat("MMMM d, yyyy");
			if(dob != null)
			this.dateOfBirthTextField.setText(format.format(dob));
	}
	public void setSex(Sex sex){
		if (sex != null)
			this.addProvinceTextField.setText(sex.toString());
	}

	public String getFirstName() {
		return addLanguageTextField.getText();
	}

	public String getLastName() {
		return addEventTypeTextField.getText();
	}

	public String getMiddleName() {
		return addRelationTextField.getText();
	}

	public String getDateOfBirth() {
		return dateOfBirthTextField.getText();
	}
	public void setClient(String firstName, String middleName, String lastName,
			String medicare, Date dob, String maritalStatus2, String sex) {
		this.addLanguageTextField = new JTextField(firstName);
		this.addRelationTextField = new JTextField(middleName);
		this.addEventTypeTextField = new JTextField(lastName);
		this.addCityTextField = new JTextField(medicare);
		this.addProvinceTextField = new JTextField(sex);
		SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
		this.dateOfBirthTextField = new JTextField(format.format(dob));
		this.maritalStatusComboBox = new JComboBox(maritalStatus);
		maritalStatusComboBox.setSelectedItem(maritalStatus2);
	}

	public void addMedicareListener(KeyListener listener){
		this.addCityTextField .addKeyListener(listener);
	}
	public String getMaritalStatus() {
		return (String) maritalStatusComboBox.getSelectedItem();
	}
	public String getGender() {
		return addProvinceTextField.getText();
	}
}

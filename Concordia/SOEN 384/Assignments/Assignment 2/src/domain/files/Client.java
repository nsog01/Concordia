package domain.files;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import domain.PersistentObject;
import domain.Appointments.Appointment;
import domain.Appointments.AppointmentList;
import domain.mappers.files.ClientMapper;
import domain.states.CleanState;
import domain.states.DirtyPhotoState;

public class Client extends PersistentObject{

	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateOfBirth;
	private int income;
	public enum IncomeSource{Employment, Worker_Compensation, Welfare, Student_Loans};
	private IncomeSource incomeSource;
	
	public enum Sex{Male, Female};
	private Sex sex;
	
	public enum MaritalStatus {Single,Married,Divorced,Widowed,Cohabiting,Civil_Union,Domestic,Partnership,Unmarried_Partners};
	private MaritalStatus maritalStatus;
	
	public enum WorkStatus {Part_Time,Full_Time,Welfare,Worker_Compensation, Unemployed};
	private WorkStatus workStatus;
	private String workExtra;
	
	public enum CanadianStatus {Canadian_Citizen,Permanent_Resident,Immigrant,Refugee,Temporary_Student};
	private CanadianStatus canadianStatus;
	private String canadianExtra;
	
	public enum Origin {Afghanistan,Albania,Algeria,Andorra,Angola,Antigua_Deps,Argentina,Armenia,Australia,Austria,Azerbaijan,Bahamas,Bahrain,Bangladesh
		,Barbados,Belarus,Belgium,Belize,Benin,Bhutan,Bolivia,Bosnia_Herzegovina,Botswana,Brazil,Brunei,Bulgaria,Burkina,Burundi,Cambodia
		,Cameroon,Canada,Cape_Verde,Central_African_Rep,Chad,Chile,China,Colombia,Comoros,Congo,Congo_Democratic_Rep,Costa_Rica,Croatia
		,Cuba,Cyprus,Czech_Republic,Denmark,Djibouti,Dominica,Dominican_Republic,East_Timor,Ecuador,Egypt,El_Salvador,Equatorial_Guinea,Eritrea
		,Estonia,Ethiopia,Fiji,Finland,France,Gabon,Gambia,Georgia,Germany,Ghana,Greece,Grenada,Guatemala,Guinea,Guinea_Bissau,Guyana,Haiti
		,Honduras,Hungary,Iceland,India,Indonesia,Iran,Iraq,Ireland_Republic,Israel,Italy,Ivory_Coast,Jamaica,Japan,Jordan,Kazakhstan,Kenya
		,Kiribati,Korea_North,Korea_South,Kosovo,Kuwait,Kyrgyzstan,Laos,Latvia,Lebanon,Lesotho,Liberia,Libya,Liechtenstein,Lithuania,Luxembourg
		,Macedonia,Madagascar,Malawi,Malaysia,Maldives,Mali,Malta,Marshall_Islands,Mauritania,Mauritius,Mexico,Micronesia,Moldova,Monaco
		,Mongolia,Montenegro,Morocco,Mozambique,Myanmar_Burma,Namibia,Nauru,Nepal,Netherlands,New_Zealand,Nicaragua,Niger,Nigeria,Norway
		,Oman,Pakistan,Palau,Palestine, Panama,Papua_NewGuinea,Paraguay,Peru,Philippines,Poland,Portugal,Qatar,Romania,Russian_Federation,Rwanda
		,St_Kitts_Nevis,St_Lucia,Saint_Vincent_the_Grenadines,Samoa,San_Marino,Sao_Tome_Principe,Saudi_Arabia,Senegal,Serbia,Seychelles
		,Sierra_Leone,Singapore,Slovakia,Slovenia,Solomon_Islands,Somalia,South_Africa,South_Sudan,Spain,Sri_Lanka,Sudan,Suriname,Swaziland
		,Sweden,Switzerland,Syria,Taiwan,Tajikistan,Tanzania,Thailand,Togo,Tonga,Trinidad_Tobago,Tunisia,Turkey,Turkmenistan,Tuvalu,Uganda
		,Ukraine,United_Arab_Emirates,United_Kingdom,United_States,Uruguay,Uzbekistan,Vanuatu,Vatican_City,Venezuela,Vietnam,Yemen
		,Zambia,Zimbabwe,USA};
		
	private Origin origin;
	private String originExtra;
	
	public enum Referral {Friend, Event, Magazine, Website};
	private Referral referral;
	private String referralExtra;
	
	public enum Reason {Low_Income, Other};
	private Reason reason;
	private String reasonExtra;
	
	private Date registrationDate;
	
	
	//-------NOTE SECOND LANGUAGE IS COMMUNICATION LANGUAGE------
	private BufferedImage photo;
	private BufferedImage thumb;
	private String medicare;
	private Language motherTongue = null;
	private Language secondLanguage = null;
	private Relation relation;
	private ClientMapper model = new ClientMapper();
	private AppointmentList aList = new AppointmentList();
	
	public Client(){
		model = new ClientMapper();
	}
	//add martial Status
	public Client(String firstName, String middleName, String lastName, String photo, 
			String thumb, Date dob, Sex sex,String medicare, String motherTongue, String secondLanguage){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		try {
			this.photo = ImageIO.read(new File(photo));
			this.thumb = ImageIO.read(new File(thumb));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.sex = sex;
		this.dateOfBirth = dob;
		this.setMedicare(medicare);
		this.motherTongue = new Language(motherTongue);
		this.secondLanguage = new Language(secondLanguage);
		//this.maritalStatus =  mStatus;
	}
	//add martial Status
	Client(int id, String firstName,String middleName, String lastName,String photo, 
			String thumb, Date dob, Sex sex, String medicare, Relation relation){
		super(id);
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		//this.maritalStatus = mStatus;
		try {
			this.photo = ImageIO.read(new File(photo));
			this.thumb = ImageIO.read(new File(thumb));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.sex = sex;
		this.dateOfBirth = dob;
		this.setMedicare(medicare);
		this.relation = relation;
		setState(new CleanState());
	}
	//add martial Status
	public Client(String firstName, String lastName, String middleName,
			Image photo, Image thumb,String dateOfBirth, String sex, String medicare,
			Language motherTongue, Language secondLanguage, Relation relation) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		//this.maritalStatus = mStatus;
		this.photo = (BufferedImage) photo;
		this.sex = Sex.valueOf(sex);
		this.setDOB(dateOfBirth);
		this.setMedicare(medicare);
		this.thumb = (BufferedImage) thumb;
		this.motherTongue = motherTongue;
		this.secondLanguage = secondLanguage;
		this.relation = relation;
	}
	//add martial Status
	public Client(int id, String firstName, String middleName, String lastName,
			Image photo, Image thumb, Date dateOfBirth, String sex, String medicare,
			Language motherTongue, Language secondLanguage, Relation relation, 
			String marital, String work, String workStatusExtra, 
			String canadaStatus, String canadaExtra, 
			String myOrigin, String myOriginExtra, 
			String myReferral, String myReferralExtra,
			String myReason, String myReasonExtra, Date myRegisteredDate
			) {
		super(id);
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.photo = (BufferedImage) photo;
		this.thumb = (BufferedImage) thumb;
		this.sex = Sex.valueOf(sex);
		this.dateOfBirth = dateOfBirth;
		this.setMedicare(medicare);
		this.motherTongue = motherTongue;
		this.secondLanguage = secondLanguage;
		this.relation = relation;
		this.maritalStatus = MaritalStatus.valueOf(marital);
		this.workStatus = WorkStatus.valueOf(work);
		this.workExtra = workStatusExtra;
		this.canadianStatus = CanadianStatus.valueOf(canadaStatus);
		this.canadianExtra = canadaExtra;
		this.origin = Origin.valueOf(myOrigin);
		this.originExtra = myOriginExtra;
		this.referral = Referral.valueOf(myReferral);
		this.referralExtra = myReferralExtra;
		this.reason = Reason.valueOf(myReason);
		this.reasonExtra = myReasonExtra;
		this.registrationDate = myRegisteredDate;
	}
	
	public Client(String firstName, String middleName, String lastName, 
			String medicare,
			Language motherTongue, Language secondLanguage, 
			String marital, String work, String workStatusExtra, 
			String canadaStatus, String canadaExtra, 
			String myOrigin, String myOriginExtra, 
			String myReferral, String myReferralExtra,
			String myReason, String myReasonExtra
			) {
		
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		
		this.setMedicare(medicare);
		this.motherTongue = motherTongue;
		this.secondLanguage = secondLanguage;
		
		this.maritalStatus = MaritalStatus.valueOf(marital);
		this.workStatus = WorkStatus.valueOf(work);
		this.workExtra = workStatusExtra;
		this.canadianStatus = CanadianStatus.valueOf(canadaStatus);
		this.canadianExtra = canadaExtra;
		this.origin = Origin.valueOf(myOrigin);
		this.originExtra = myOriginExtra;
		this.referral = Referral.valueOf(myReferral);
		this.referralExtra = myReferralExtra;
		this.reason = Reason.valueOf(myReason);
		this.reasonExtra = myReasonExtra;

	}
	//add martial Status
	public void set(String firstName, String lastName, String middleName, 
			Image photo, Image thumb, String dob, String sex, String medicare,
			Language motherTongue, Language second, Relation relation){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.setPhoto(photo);
		this.setThumb(thumb);
		this.setDOB(dob);
		this.setSex(sex);
		this.medicare = medicare;
		this.motherTongue = motherTongue;
		this.secondLanguage = second;
		//this.maritalStatus = ms;
		setChanged();
		notifyObservers();
		FileRepository.getUniqueInstance().set();
		this.relation = relation;
		registerDirty();
	}
	public void set(String firstName, 
			String middleName, 
			String lastName, 
			String medicare,
			Language motherTongue, 
			Language secondLanguage, 
			String marital, 
			String work, 
			String workStatusExtra, 
			String canadaStatus, 
			String canadaExtra, 
			String myOrigin, 
			String myOriginExtra, 
			String myReferral, 
			String myReferralExtra,
			String myReason, 
			String myReasonExtra,
			Image photo,
			Image thumb,
			String dob,
			String sex
			){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		
		this.setMedicare(medicare);
		this.motherTongue = motherTongue;
		this.secondLanguage = secondLanguage;
		
		this.maritalStatus = MaritalStatus.valueOf(marital);
		this.workStatus = WorkStatus.valueOf(work);
		this.workExtra = workStatusExtra;
		this.canadianStatus = CanadianStatus.valueOf(canadaStatus);
		this.canadianExtra = canadaExtra;
		this.origin = Origin.valueOf(myOrigin);
		this.originExtra = myOriginExtra;
		this.referral = Referral.valueOf(myReferral);
		this.referralExtra = myReferralExtra;
		this.reason = Reason.valueOf(myReason);
		this.reasonExtra = myReasonExtra;
		this.photo = (BufferedImage) photo;
		this.thumb = (BufferedImage) thumb;
		this.setDOB(dob);
		this.setSex(sex);
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public void set2(String firstName, String lastName,
			String medicare,
			Language motherTongue, Language secondLanguage, 
			String marital, String work, String workStatusExtra, 
			String canadaStatus, String canadaExtra, 
			String myOrigin, String myOriginExtra, 
			String myReferral, String myReferralExtra,
			String myReason, String myReasonExtra){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		
		this.setMedicare(medicare);
		this.motherTongue = motherTongue;
		this.secondLanguage = secondLanguage;
		
		this.maritalStatus = MaritalStatus.valueOf(marital);
		this.workStatus = WorkStatus.valueOf(work);
		this.workExtra = workStatusExtra;
		this.canadianStatus = CanadianStatus.valueOf(canadaStatus);
		this.canadianExtra = canadaExtra;
		this.origin = Origin.valueOf(myOrigin);
		this.originExtra = myOriginExtra;
		this.referral = Referral.valueOf(myReferral);
		this.referralExtra = myReferralExtra;
		this.reason = Reason.valueOf(myReason);
		this.reasonExtra = myReasonExtra;
		//this.maritalStatus = ms;
		setChanged();
		notifyObservers();
		FileRepository.getUniqueInstance().set();
		
	}
	public void setThumb(Image photo) {
		this.thumb = (BufferedImage) photo;
		registerDirtyPhoto();
		setChanged();
		notifyObservers();
	}
	private void registerDirtyPhoto() {
		setState(new DirtyPhotoState());
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public void setMaritalStatus(MaritalStatus ms)
	{
		this.maritalStatus = ms;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public MaritalStatus getMaritalStatus()
	{
		return maritalStatus;
	}
	public String getMaritalStatusString(){
		if (maritalStatus == null){
			return null;
		}
		return maritalStatus.toString();
	}
	public void setWorkStatus(WorkStatus ws)
	{
		this.workStatus = ws;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public WorkStatus getWorkStatus()
	{
		return workStatus;
	}
	public String getWorkStatusString(){
		if (workStatus == null){
			return null;
		}
		return workStatus.toString();
	}
	public void setExtraWorkStatusField(String extra)
	{
		this.workExtra = extra;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public String getExtraWorkStatusField()
	{
		return workExtra;
	}
	public void setCanadianStatus(CanadianStatus cs)
	{
		this.canadianStatus = cs;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public CanadianStatus getCanadianStatus()
	{
		return canadianStatus;
	}
	
	public String getCanadianStatusString()
	{
		if (canadianStatus == null){
			return null;
		}
		return canadianStatus.toString();
	}
	public void setExtraCanadianStatusField(String extra)
	{
		this.canadianExtra = extra;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public String getExtraCanadianStatusField()
	{
		return canadianExtra;
	}
	
	public void setOrigin(Origin o)
	{
		origin = o;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public Origin getOrigin()
	{
		return origin;
	}
	
	public String getOriginString()
	{
		if (origin == null){
			return null;
		}
		return origin.toString();
	}
	
	public void setExtraOriginField(String extra)
	{
		this.originExtra = extra;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public String getExtraOriginField()
	{
		return originExtra;
	}
	
	public void setReferral(Referral rf)
	{
		referral = rf;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public Referral getReferral()
	{
		return referral;
	}
	
	public String getReferralString()
	{
		if (referral == null){
			return null;
		}
		return referral.toString();
	}
	
	public void setReferralExtra(String extra)
	{
		referralExtra = extra;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public String getReferralExtra()
	{
		return referralExtra;
	}
	public void setReason(Reason ra)
	{
		reason = ra;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public Reason getReason()
	{
		return reason;
	}
	public String getReasonString()
	{
		if (reason == null){
			return null;
		}
		return reason.toString();
	}
	public void setReasonExtra(String extra)
	{
		reasonExtra = extra;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public String getReasonExtra()
	{
		return reasonExtra;
	}
	
	public void setRegistrationDate(Date regDate)
	{
		registrationDate = regDate;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	
	public Date getRegistrationDate()
	{
		return registrationDate;
	}
	
	
	public Date getDOB() {
		return dateOfBirth;
	}
	public final void setDOB(Date dOB) {
		dateOfBirth = dOB;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public void setDOB(String dob){
		SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
		try {
			dateOfBirth = format.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public void setSex(String sex){
		this.sex = Sex.valueOf(sex);
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public BufferedImage getPhoto() {
		BufferedImage p = null;
		if (photo != null) {
			return photo;
		} else {
				p = null;

		}
		return p;
	}
	public BufferedImage getThumb() {
		BufferedImage p = null;
		if (thumb != null) {
			return thumb;
		} else {
			try {
				p = ImageIO.read(new File("images/defaultThumb.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}
	public void setPhoto(Image photo) {
		this.photo = (BufferedImage) photo;
		registerDirtyPhoto();
		setChanged();
		notifyObservers();
	}
	public void setPhoto(String photo){
		try {
			this.photo = ImageIO.read(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		registerDirtyPhoto();
		setChanged();
		notifyObservers();
	}
	public String getMedicare() {
		return medicare;
	}
	public final void setMedicare(String medicare) {
		this.medicare = medicare;
		registerDirty();
		setChanged();
		notifyObservers();
	}
	public int getAge() {
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		int yearNow =cal.get(Calendar.YEAR);
		cal.setTime(getDOB());
		int bYear = cal.get(Calendar.YEAR);
		int age = yearNow - bYear;
		//We need to check to make sure we have passed the persons birthday -
		//if we haven't we need to decrease the age by one year.
		cal.set(Calendar.YEAR, yearNow);
		Date nextBDay = cal.getTime();
		if (now.before(nextBDay)){
			age--;
		}
		return age;
}
	public String getFullName(){
		if (this.middleName != null) {
			return this.firstName+" "+this.middleName+" "+this.lastName;
		} else {
			return this.firstName+" "+this.lastName;
		}
	}
	@Override
	public Client insert() {
		return model.insert(this);
	}

	@Override
	public void update() {
		model.update(this);
	}

	@Override
	public void delete() {
		model.delete(this);
		
	}
	public void setThumb(String thumb)  {
		try {
			this.thumb = ImageIO.read(new File(thumb));
		} catch (IOException e) {
			e.printStackTrace();
		}
		registerDirtyPhoto();
		setChanged();
		notifyObservers();
	}
	public String getMotherTongueString (){
		if (motherTongue == null){
			return null;
		}
		return motherTongue.getLanguage();
	}
	public String getSecondLangString(){
		if (secondLanguage == null){
			return null;
		}
		return secondLanguage.getLanguage();
	}
	public Language getMotherTongue(){

		return motherTongue;
	}
	public Language getSecondLanguage(){
		return secondLanguage;
	}
	public int getMotherTongueId() {
		if (motherTongue == null){
			return 1;
		} else {
			return motherTongue.getId();
		}
	}
	public int getSecondLanguageId() {
		if (secondLanguage == null){
			return 1;
		} else {
			return secondLanguage.getId();
		}
	}
	@Override
	public ClientMapper getMapper() {
		return model;
	}
	
	public Relation getRelation(){
		return relation;
	}
	public void updateWithoutPhoto(Client client) {
		model.updateWithoutPhoto(client);
	}
	public void updateWithPhoto(Client obj) {
		model.update(obj);
	}
	public void setAppointments(AppointmentList forClient) {
		aList = forClient;
	}
	public AppointmentList getAppointments() {
		return aList;
	}
	public void addAppointment(Appointment appointment) {
		aList.add(appointment);
	}
	public void setRelation(Relation relation2) {
		this.relation = relation2;
	}

}

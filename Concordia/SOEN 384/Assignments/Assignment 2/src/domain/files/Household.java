package domain.files;

import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.GList;
import domain.PersistentObject;
import domain.Appointments.AppointmentList;
import domain.mappers.files.HouseholdMapper;

public class Household extends PersistentObject implements Iterable<Client> {

	private int streetNumber;
	private String streetName;
	private int unitNumber;
	private String city;
	private String province;
	private String postalCode;
	private Date initialVisit;
	private long tel = 0;
	private Client representative;
	private ClientList dependents;
	private String region;
	private GList<Note> noteList;
	private String notes;
	private FlagList flag = new FlagList();
	private boolean updateDependants = false;
	private boolean updateRep = false;
	private boolean updateApp = false;
	private HouseholdMapper model = new HouseholdMapper();

	public Household(String streetName, String postalCode, Date initialVisit,
			long tel, String notes, Client representative, String province,
			Flag flag,  int streetNumber,  int unitNumber, String city) {
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.unitNumber = unitNumber;
		this.postalCode = postalCode;
		this.initialVisit = initialVisit;
		this.tel = tel;
		this.city = city;
		this.representative = representative;
		this.province = province;
		this.region = "North America";
		dependents = new ClientList();
		noteList = new GList<Note>();
	}

	public Household(int id, String streetName,  String postalCode,
			Date initialVisit, long tel, String notes, Client representative,
			ClientList dependents, Flag flag,  String province,  int unitNumber, String city) {
		super(id);
		this.notes = notes;
		this.city = city;
		this.streetName = streetName;
		this.unitNumber = unitNumber;
		this.postalCode = postalCode;
		this.initialVisit = initialVisit;
		this.tel = tel;
		this.representative = representative;
		this.dependents = dependents;
		this.province = province;
		this.region = "North America";
		noteList = new GList<Note>();
	}

	public void set(String streetName, String postalCode, Date initialVisit,
			String tel, Client rep, String prov, ClientList deps, 
			Flag flag,  int streetNumber,  int unitNumber, String city) {
		this.streetName = streetName;
		this.postalCode = postalCode;
		this.city = city;
		this.streetNumber = streetNumber;
		this.unitNumber = unitNumber;
		this.initialVisit = initialVisit;
		this.tel = Long.parseLong(tel);
		this.representative = rep;
		this.province = prov;
		this.dependents = deps;
	}
	
	public String getRegion()
	{
		return this.region;
	}
	public void set2(String newStreetName, int newUnitNumber, String newPostalCode,
			String newCity, String newProvince, String newRegion, Client newClient)
	{
		//this.streetNumber = streetNumber;
		this.streetName = newStreetName;
		this.unitNumber = newUnitNumber;
		this.postalCode = newPostalCode;
		this.city = newCity;
		this.province = newProvince;
		this.region = newRegion;
		this.representative = newClient;
		setChanged();
		notifyObservers();
		this.registerDirty();
	}
	

	public Household() {
		dependents = new ClientList();
	}

	public void setProvince(String prov) {
		this.province = prov;
	}

	public String getProvince() {
		return province;
	}

	public void addDependent(Client dependent) {
		this.dependents.add(dependent);
		updateDependants = true;
	}

	public void removeDependent(Client dependent) {
		this.dependents.remove(dependent);
		model.removeClient(dependent);
		updateDependants = true;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Date getInitialVisit() {
		return initialVisit;
	}

	public long getTel() {
		return tel;
	}

	public Client getRepresentative() {
		return representative;
	}

	public int getFileNumber() {
		return representative.getId();
	}

	public void setRepresentative(Client rep) {
		this.representative = rep;
		updateRep = true;
	}

	public ClientList getDependents() {
		return this.dependents;
	}

	public ClientList getAllClients() {
		ClientList list = new ClientList();
		if (this.dependents.isEmpty()){
			list.add(representative);
		}
		else {
			list.addAll(dependents);
			list.add(representative);
		}
		return list;
	}

	public ClientList getDependentsList() {
		return this.dependents;
	}

	public void setAddress(String address) {
		this.streetName = address;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setInitialVisit(Date initialVisit) {
		this.initialVisit = initialVisit;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public String getTelString() {
		if (tel != 0) {
			String telString = Long.toString(tel);
			if (telString.length() == 10) {
				return "(" + telString.substring(0, 3) + ") "
						+ telString.substring(3, 6) + "-"
						+ telString.substring(6, 10);
			} else if (telString.length() == 7) {
				return telString.substring(0, 3) + "-"
						+ telString.substring(3, 7);
			} else if (telString.length() == 11) {
				return "1 (" + telString.substring(1, 4) + ") "
						+ telString.substring(4, 7) + "-"
						+ telString.substring(7, 11);
			} else {
				return telString;
			}
		} else {
			String notFound="No number on file";
			return (notFound);
		}
		
		
	}

	public String getNotes() {
		return notes;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public AppointmentList getAppointments() {
		AppointmentList list = new AppointmentList();
		list.addAll(representative.getAppointments());
		for (Client c : dependents){
			list.addAll(c.getAppointments());
		}
		return list;
	}

//	public EventList getAppointmentEventList() {
//		EventList list = new EventList();
//		for (Appointment a : appointments) {
//			list.add(a.getEvent());
//		}
//		return list;
//	}
//
//	public void setAppointments(AppointmentList appointments) {
//		this.appointments = appointments;
//		updateApp = true;
//	}
//
//	public void addAppointment(Appointment a) {
//		a.getTimeslot().incrAppointmentCount();
//		appointments.add(a);
//		updateApp = true;
//	}

	public void setDependents(ClientList dependents) {
		this.dependents = dependents;
	}

	public void insertDependent(Client client) {
		HouseholdMapper.insertDependent(this, client);
	}

	@Override
	public PersistentObject insert() {
		return model.insert(this);
	}

	@Override
	public void update() {
		model.update(this);
	}

	@Override
	public void delete() {

	}

	@Override
	public Iterator<Client> iterator() {
		return this.getAllClients().iterator();
	}

	public void setNote(String note) {
		notes = note;
	}

	public void saveNote() {
		HouseholdMapper.saveNote(this);
	}

	public FlagList getFlags() {
		return flag;
	}

	public void setFlags(FlagList flag) {
		this.flag = flag;
	}

	public void set(String address2, String postalCode2, Date initialVisit2,
			String tel2, Client representative2, Object prov,
			ClientList dependentsList, Flag flag2) {

	}

	public boolean updateDependents() {
		return updateDependants;
	}

	public boolean updateAppointments() {
		return updateApp;
	}

	public boolean updateRep() {
		return updateRep;
	}

	public HouseholdMapper getMapper() {
		return model;
	}
	
	public static boolean isFileNumber(String search) {
		Pattern integerPattern = Pattern.compile("^[0-9]{8}$");
		Matcher matchesInteger = integerPattern.matcher(search);
		return matchesInteger.matches();
	}

	public Client getClient(int int1) {
		if (representative.getId() == int1){
			return representative;
		}
		for (Client c : dependents){
			if (c.getId() == int1){
				return c;
			}
		}
		return null;
	}

	public GList<Note> getNoteList() {
		return noteList;
	}

	public void setNoteList(GList<Note> noteList) {
		this.noteList = noteList;
	}

	public void addFlag(Flag flag2) {
		flag.add(flag2);
		registerDirty();
	}

}

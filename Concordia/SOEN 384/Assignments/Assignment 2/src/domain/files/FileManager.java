package domain.files;

import java.awt.Image;
import java.util.Calendar;
import java.util.Observer;


import domain.events.EventList;
import domain.events.EventTypeList;


public final class FileManager{

	private FileRepository repository;
	private static FileManager manager;

	
	private FileManager(){
		this.repository = FileRepository.getUniqueInstance();
	}
	public void uploadClientThumb(String file, Client client) {
		client.setThumb(file);
	}
	public synchronized static FileManager getUniqueInstance(){
		if ( manager == null){
			manager = new FileManager();
		}
		return manager;
	}
	public ClientList getDependants(int house) {
		return repository.getDependants(house);
	}
	public Household get(int index){
		return repository.get(index);
	}
	public void addObserverToRepository(Observer object) {
		this.repository.addObserver(object);		
	}
	public void commitClient(Client client) {
		repository.commitClient(client);
	}
	public void save(){
		repository.save();
	}
	public void removeClient(Client client) {
		repository.removeClient(client);
	}
	public Household findHouse(int i) {
		return setActiveFile(repository.findHouse(i));
	}
	public Client readClient(int id) {
		return this.repository.readClient(id);
	}
	public RelationList getAllRelations() {
		return this.repository.getAllRelations();
	}
	public void insertHousehold() {
		this.repository.insertHousehold();
	}
	public FileList getFileList(){
		return this.repository.getBrowseFileList();
	}
	public FileList searchByName(String representative){
		String firstName = null;
		String middleName = null;
		String lastName = null;
		String[] names = representative.split(" ");
		if (names.length == 3){

			firstName = names[0];
			middleName = names[1];
			lastName = names[2];
		}
		else if (names.length == 2){

			firstName = names[0];
			middleName = names[0];
			lastName = names[1];
		}
		else if (names.length == 1){
			firstName = names[0];
			middleName = names[0];
			lastName = names[0];
		}
		return repository.findByName(firstName, middleName, lastName);
	}
	
	public FileList searchByAnything(String anything) {
		return repository.findByAnything(anything);
	}
	
	public void saveNote(Household household, String noteString){
		Note note = new Note(System.getProperty("user.name"), Calendar.getInstance().getTime(), noteString);
		household.getNoteList().add(note);
		note.commit();
	}
	public void saveNote(Note note) {
		repository.saveNote(note);
	}
	public LanguageList getAllLanguages()  {
		return repository.getAllLanguages();
	}
	public ProvinceList getAllProvinces() {
		return repository.getAllProvinces();
	}
	public CityList getAllCities() {
		return repository.getAllCities();
	}
	public void saveClientWithoutPhoto(Client client) {
		repository.saveClientWithoutPhoto(client);
	}
	public Flag getFlag(long id) {
		return repository.getFlag(id);
	}
	public Household getActiveFile() {
		return repository.getActiveFile();
	}
	public Household setActiveFile(Household house) {
		return repository.setActiveFile(house);
	}
	public EventTypeList getEventTypeListFromRepo() {
		return repository.getEventTypelist();
	}
	public void removeDependent(Client client){
		repository.removeDependent(client);
	}
	public void addDependent(Client client, Relation relation) {
		repository.addDependent(client, relation);
	}
	public Client addClientAsDependent(String firstName, String middleName,
			String lastName, String dateOfBirth, String medicare,
			Object maritalStatus, String gender) {
		Client client = new Client(firstName, lastName, middleName, null, null, dateOfBirth,
				gender, medicare, null, null, null);
		repository.addDependent(client, client.getRelation());
		return client;
	}
	public Client addClientAsPrimary(String firstName, String middleName,
			String lastName, String dateOfBirth, String medicare,
			String maritalStatus, String gender) {
		Client client = new Client(firstName, lastName, middleName, null, null, dateOfBirth,
				gender, medicare, null, null, null);
		repository.addPrimary(client);
		return client;
	}
	public EventList getEventsForToday() {
		return repository.getEventsForToday();
	}
	public Image getPhoto(Client client) {
		return repository.getPhoto(client);
	}
	public Household getHousehold(int int1) {
		return repository.getHousehold(int1);
	}
	public FlagList getAllFlags() {
		return repository.getAllFlags();
	}
}

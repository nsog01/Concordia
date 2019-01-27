package domain.files;

import java.awt.Image;
import java.util.Observable;

import domain.events.EventList;
import domain.events.EventTypeList;
import domain.mappers.events.EventMapper;
import domain.mappers.files.CityMapper;
import domain.mappers.files.ClientMapper;
import domain.mappers.files.FlagMapper;
import domain.mappers.files.HouseholdMapper;
import domain.mappers.files.LanguageMapper;
import domain.mappers.files.ProvinceMapper;
import domain.mappers.files.RelationMapper;

public final class FileRepository extends Observable{
	
	private FileList browsableFiles;
	private Household activeFile;
	private RelationList relations;
	private LanguageList languages;
	private ProvinceList provinces;
	private CityList cities;
	private EventTypeList eventTypeList;
	private FlagList flags;
	private static FileRepository repositoryInstance;
	
	
	private FileRepository(){
		readAllFiles();
		browsableFiles = new FileList();
		
	}
	public Flag getFlag(long id) {
		if (flags == null){
			readAllFiles();
		}
		for (Flag f : flags){
			if (f.getId() == id){
				return f;
			}
		}
		return null;
	}
	public synchronized static FileRepository getUniqueInstance() {
		if (repositoryInstance == null){
			repositoryInstance = new FileRepository();
		}
		return repositoryInstance;
	}
	
	public void readAllFiles() {
		getAllRelations();
		getAllProvinces();
		getAllLanguages();
		getAllEventTypes();
		getAllFlags();
	}
	
	FlagList getAllFlags(){
		if (flags == null){
			flags =  new FlagMapper().readAll();
		}
		return flags;
	}
	
	public Client readClient(int id){
		return new ClientMapper().read(id);
	}
	protected Household get(int index){
		return browsableFiles.get(index);
	}
	public ClientList getDependants(int house){
		return browsableFiles.getDependents(house);
	}
	public void set(){
		setChanged();
		notifyObservers();
	}

	public void commitClient(Client client) {
			client.commit();
	}
	
	public void removeClient(Client client) {
		client.delete();
		setChanged();
		notifyObservers();
	}

	public Household findHouse(int i) {
		activeFile = HouseholdMapper.findByRep(i);
		return activeFile;
	}

	public RelationList getAllRelations()  {
		if (relations == null){
			relations = new RelationMapper().readAll();
		} 
		return relations;
		
	}
	public LanguageList getAllLanguages()  {
		if (languages == null){
			languages = new LanguageMapper().readAll();
		}
		return languages;
	}
	public ProvinceList getAllProvinces() {
		if (provinces == null){
			provinces = new ProvinceMapper().readAll();
		}
		return provinces;
	}
	
	public CityList getAllCities() {
		if (cities == null){
			cities = new CityMapper().readAll();
		}
		return cities;
	}
	public EventTypeList getAllEventTypes(){
		if (eventTypeList == null){
			eventTypeList = new EventTypeList().readAll();
		}
		return eventTypeList;
	}
	public void insertHousehold() {
		activeFile.insert();
		browsableFiles.add(activeFile);
	}

	public FileList getBrowseFileList() {
		return this.browsableFiles;
	}

	public FileList findByName(String firstName, 
			String middleName, String lastName) {
		return HouseholdMapper.findByName(firstName, middleName, lastName);
	}
	
	public FileList findByAnything(String anything) {
		return HouseholdMapper.findByAnything(anything);
	}

	public void saveClientWithoutPhoto(Client client) {
		new ClientMapper().updateWithoutPhoto(client);
	}
	public Household setActiveFile(Household file){
		activeFile = file;
		setChanged();
		notifyObservers();
		return activeFile;
	}
	public Household getActiveFile(){
		return activeFile;
	}
	public EventTypeList getEventTypelist() {
		return eventTypeList;
	}
	public void removeDependent(Client client) {
		activeFile.removeDependent(client);
		setChanged();
		notifyObservers();
	}
	public void addDependent(Client client, Relation relation){
		client.setRelation(relation);
		activeFile.addDependent(client);
		setChanged();
		notifyObservers();
	}
	public void save() {
		activeFile.update();
	}
	public void addPrimary(Client client) {
		activeFile.setRepresentative(client);
	}
	public EventList getEventsForToday() {
		return EventMapper.getEventsForToday();
	}
	public Image getPhoto(Client client) {
		return ClientMapper.getPhoto(client);
	}
	public Household getHousehold(int int1) {
		if (activeFile != null && activeFile.getId() == int1){
			return activeFile;
		}
		for (Household h : browsableFiles){
			if (h.getId() == int1){
				return h;
			}
		}
		Household house = HouseholdMapper.findByRep(int1);
		browsableFiles.add(house);
		return house;
	}
	public void saveNote(Note note) {
		getActiveFile().getNoteList().add(note);
		note.commit();
		setChanged();
		notifyObservers();
	}
	public void addToLanguageList(Language language){
		languages.add(language);
	}
}

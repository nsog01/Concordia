package domain.events;

import domain.PersistentObject;
import domain.mappers.Mapper;
import domain.mappers.events.EventTypeMapper;

/**
 * @author paulsmelser
 */
public class EventType extends PersistentObject{

	private EventTypeList eventTypes;
	private String name;
	private EventTypeMapper model = new EventTypeMapper();
	
	/**
	 * @return
	 * @uml.property name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @uml.property name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	public EventType(String eventType){
		super();
		this.name = eventType;
	}
	public EventType(int id, String eventType) {
		super(id);
		this.name = eventType;
	}
	public EventType insert()  {
		EventTypeMapper.insert(this);
		return null;
	}

	public EventTypeList readAll() {
		return model.readAll();
	}

	public String[] readNames()  {
		return model.readAllNames();
	}

	public EventTypeList getAll() {
		return eventTypes;
	}

	@Override
	public void update()  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mapper getMapper() {
		return model;
	}
}

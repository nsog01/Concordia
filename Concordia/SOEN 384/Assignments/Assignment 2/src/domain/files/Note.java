package domain.files;

import java.util.Date;

import domain.PersistentObject;
import domain.mappers.Mapper;
import domain.mappers.files.NoteMapper;

public class Note extends PersistentObject{

	private String user;
	private Date date;
	private String note;
	private NoteMapper map;
	
	public Note(String user, Date date, String note){
		super();
		this.date = date;
		this.note = note;
		this.user = user;
		this.map = new NoteMapper();
	}
	public Note(int id, String user, Date date, String note){
		super(id);
		this.date = date;
		this.note = note;
		this.user = user;
		this.map = new NoteMapper();
	}
	
	public Note() {
		this.map = new NoteMapper();
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}



	@Override
	public PersistentObject insert() {
		return map.insert(this);
	}

	@Override
	public void update() {
		map.update(this);
	}

	@Override
	public void delete() {
		map.delete(this);
	}

	@Override
	public Mapper getMapper() {
		return map;
	}
}

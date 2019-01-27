package domain.files;

import domain.PersistentObject;
import domain.mappers.files.FlagMapper;

public class Flag extends PersistentObject{

	private String flag = null;
	private String note;
	private int flagType;
	private FlagMapper mapper = new FlagMapper();
	
	
	public Flag(String flag, String note, int flagType){
		this.flag = flag;
		this.note = note;
		this.flagType = flagType;
	}
	public Flag(int id, String flag, String note, int flagType){
		super(id);
		this.flag = flag;
		this.note = note;
		this.flagType = flagType;
	}
	public Flag(int id, String flag){
		super(id);
		this.flag = flag;
	}
	public String getFlag(){
		return this.flag;
	}
	
	public void setFlag(String flag){
		this.flag = flag;
		registerDirty();
	}

	@Override
	public PersistentObject insert() {
		return mapper.insert(this);
	}

	@Override
	public void update() {
		mapper.update(this);
	}

	@Override
	public void delete()  {
		mapper.delete(this);
	}
	@Override
	public FlagMapper getMapper() {
		return mapper;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
		registerDirty();
	}
	public int getFlagType() {
		return flagType;
	}
	public void setFlagType(int flagType) {
		this.flagType = flagType;
	}
}

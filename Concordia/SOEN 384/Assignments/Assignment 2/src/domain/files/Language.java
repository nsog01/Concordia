package domain.files;

import domain.PersistentObject;
import domain.mappers.files.LanguageMapper;

public class Language extends PersistentObject{

	private String language;
	private LanguageMapper model =new LanguageMapper();
	public Language(String lang){
		language = lang;
	}

	public Language(int id, String lang) {
		super(id);
		language = lang;
	}

	@Override
	public PersistentObject insert(){
		return LanguageMapper.insert(this);
	}

	@Override
	public void update()  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public String getLanguage() {
		return language;
	}

	@Override
	public LanguageMapper getMapper() {
		return model;
	}
	
}

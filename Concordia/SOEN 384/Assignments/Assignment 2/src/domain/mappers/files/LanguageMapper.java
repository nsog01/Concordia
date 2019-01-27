package domain.mappers.files;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ClientTDG;
import data.LanguageTDG;
import domain.PersistentObject;
import domain.files.Language;
import domain.files.LanguageList;
import domain.mappers.Mapper;

public class LanguageMapper implements Mapper{

	public static Language insert(Language language)  {
		try {
			language.setId(LanguageTDG.insert(language.getLanguage()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}
	public static Language map(ResultSet set){
		Language l = null;
			try {
				l = new Language(set.getInt(1), set.getString(2));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return l;
	}

	@Override
	public PersistentObject insert(PersistentObject object){
		return null;
	}

	@Override
	public void delete(PersistentObject Language){
		/*
		try {
			LanguageTDG.delete(language.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

	@Override
	public LanguageList readAll() {
		LanguageList list = new LanguageList();

		try {
			ResultSet set = LanguageTDG.readAll();
			while (set.next()){
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PersistentObject read(int id) {
		return null;
	}

	@Override
	public void update(PersistentObject object){
		
	}

}

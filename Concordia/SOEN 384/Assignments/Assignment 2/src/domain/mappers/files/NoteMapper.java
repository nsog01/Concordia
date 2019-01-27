package domain.mappers.files;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.NoteTDG;
import domain.GList;
import domain.IList;
import domain.PersistentObject;
import domain.files.FileManager;
import domain.files.Note;
import domain.mappers.Mapper;

public class NoteMapper implements Mapper{

	@Override
	public Note insert(PersistentObject object) {
		Note note = (Note) object;
		try {
			note.setId(NoteTDG.insert(FileManager.getUniqueInstance().getActiveFile().getId(),
					note.getDate(), note.getUser(), note.getNote()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (Note) note;

	}

	@Override
	public void delete(PersistentObject object) {
		Note note = (Note) object;
		try {
			NoteTDG.delete(note.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IList readAll() {
		IList list = new GList<Note>();
		try {
			ResultSet set = NoteTDG.readAll();
			while (set.next()){
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}


	@Override
	public void update(PersistentObject object) {
		Note note = (Note) object;
		try {
			NoteTDG.update(note.getId(), note.getDate(), note.getUser(), note.getNote());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public PersistentObject read(int id) {
		ResultSet set;
		Note note = null;
		try {
			set = NoteTDG.read(id);
			set.next();
			note = map(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return note;
	}

	public static GList<Note> readByHouse(int id){
		GList<Note> list = new GList<Note>();
		try {
			ResultSet set = NoteTDG.readByHousehold(id);
			while (set.next()){
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private static Note map(ResultSet set) throws SQLException {
		return new Note(set.getInt(1), set.getString(4), set.getDate(3), set.getString(5));
	}
}

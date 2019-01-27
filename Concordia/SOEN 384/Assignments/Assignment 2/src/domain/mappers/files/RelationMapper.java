package domain.mappers.files;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.RelationTDG;
import domain.PersistentObject;
import domain.files.Relation;
import domain.files.RelationList;
import domain.mappers.Mapper;

public class RelationMapper implements Mapper {

	@Override
	public RelationList readAll() {
		RelationList list = new RelationList();

		try {
			ResultSet set = RelationTDG.readAll();
			list.add(new Relation(null));
			while (set.next()) {
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static Relation map(ResultSet set) {
		Relation r = null;
		try {
			r = new Relation(set.getInt(1), set.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public PersistentObject insert(PersistentObject object) {
		return null;
	}

	@Override
	public void delete(PersistentObject object) {

	}

	@Override
	public Relation read(int id) {
		Relation r = null;
		try {
			ResultSet set = RelationTDG.read(id);
			if (set.next()) {
				r = map(set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void update(PersistentObject object) {

	}

	public ArrayList<Relation> readAllToArrayList() {
		ArrayList<Relation> list = new ArrayList<Relation>();

		try {
			ResultSet set = RelationTDG.readAll();
			set.next(); // skip representative
			list.add(new Relation(null));
			while (set.next()) {
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

package domain.mappers.files;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import data.FlagTDG;
import domain.PersistentObject;
import domain.files.FileManager;
import domain.files.Flag;
import domain.files.FlagList;
import domain.files.Household;
import domain.mappers.Mapper;

public class FlagMapper implements Mapper {

	
	private static Flag map(ResultSet set) {
		Flag f = null;
		try {
			ResultSetMetaData rsmd = set.getMetaData();
			if (rsmd.getColumnCount() > 2){
				f = new Flag(set.getInt(3), set.getString(2), set.getString(6), set.getInt(5));
			} else {
				f = new Flag(set.getInt(1), set.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static Flag get(int id) {
		ResultSet set = null;
		Flag flag = null;
		try {
			set = FlagTDG.read(id);
			if (set.next()){
				flag = map(set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public PersistentObject insert(PersistentObject object) {
		Flag flag = (Flag) object;
		try {
		if (flag.getNote() == null){
			flag.setId(FlagTDG.insert(flag.getFlag()));
		} else {
			flag.setId(FlagTDG.insertFlag(
					FileManager.getUniqueInstance().getActiveFile().getId(),
					flag.getFlagType(),
					flag.getNote()));
		}
		} catch(SQLException sql){
			sql.printStackTrace();
		}
		return flag;
	}

	@Override
	public void delete(PersistentObject object) {
		Flag flag = (Flag) object;
		try {
			if (flag.getNote() == null){
				FlagTDG.delete(flag.getFlagType());
			} else {
				FlagTDG.deleteFlagInstance(flag.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FlagList readAll() {
		FlagList flags = new FlagList();
		try {
			ResultSet set = FlagTDG.readAll();

			while (set.next()) {
				flags.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flags;
	}

	@Override
	public PersistentObject read(int id) {
		return null;
	}

	@Override
	public void update(PersistentObject object) {
		Flag flag = (Flag) object;
		try {
		if (flag.getNote() == null){
			FlagTDG.update(flag.getFlagType(), flag.getFlag());
		} else {
			FlagTDG.updateFlag(
					flag.getId(),
					FileManager.getUniqueInstance().getActiveFile().getId(),
					flag.getFlagType(),
					flag.getNote());
		}
		} catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	
	public static void addToHousehold(Household house){
		ResultSet set = null;
		
		 try {
			set = FlagTDG.readForHousehold(house.getId());
			while(set.next()){
				Flag flag = map(set);
				house.getFlags().add(flag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

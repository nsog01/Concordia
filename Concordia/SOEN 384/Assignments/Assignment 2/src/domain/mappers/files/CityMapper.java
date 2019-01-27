package domain.mappers.files;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.CityTDG;
import data.ProvinceTDG;
import domain.PersistentObject;
import domain.files.City;
import domain.files.CityList;
import domain.files.Province;
import domain.files.ProvinceList;
import domain.mappers.Mapper;

public class CityMapper implements Mapper{

	public CityList readAll()  {
		CityList list = new CityList();

		try {
			ResultSet set = CityTDG.readAll();
			while (set.next()){
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static City map(ResultSet set) {
		City p = null;
			try {
				p = new City(set.getInt(1), set.getString(2));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return p;
	}

	@Override
	public City insert(PersistentObject city)
{
			try {
				((City)city).setId(CityTDG.insert(((City)city).getCity()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ((City)city);
	}

	@Override
	public void delete(PersistentObject object) {
		
	}

	@Override
	public PersistentObject read(int id) {
		return null;
	}

	@Override
	public void update(PersistentObject object) {
		
	}

}

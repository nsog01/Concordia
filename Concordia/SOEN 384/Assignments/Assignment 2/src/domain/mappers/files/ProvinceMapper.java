package domain.mappers.files;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ProvinceTDG;
import domain.PersistentObject;
import domain.files.Province;
import domain.files.ProvinceList;
import domain.mappers.Mapper;

public class ProvinceMapper implements Mapper{

	public ProvinceList readAll()  {
		ProvinceList list = new ProvinceList();

		try {
			ResultSet set = ProvinceTDG.readAll();
			while (set.next()){
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Province map(ResultSet set) {
		Province p = null;
			try {
				p = new Province(set.getInt(1), set.getString(2));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return p;
	}

	@Override
	public Province insert(PersistentObject province)
{
			try {
				((Province)province).setId(ProvinceTDG.insert(((Province)province).getProvince()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ((Province)province);
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

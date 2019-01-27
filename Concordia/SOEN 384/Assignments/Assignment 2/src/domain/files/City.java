package domain.files;


import domain.PersistentObject;
import domain.mappers.files.CityMapper;
import domain.mappers.files.ProvinceMapper;

public class City extends PersistentObject{
	private String city;
	private CityMapper model = new CityMapper();
	public City(String prov){
		city = prov;
	}
	
		public City(int id, String cit) {
			super(id);
			city = cit;
		}

		@Override
		public PersistentObject insert()  {
			return model.insert(this);
		}

		@Override
		public void update()  {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete()  {
			// TODO Auto-generated method stub
			
		}

		public String getCity() {
			return city;
		}

		@Override
		public CityMapper getMapper() {
			return model;
		}
		

}

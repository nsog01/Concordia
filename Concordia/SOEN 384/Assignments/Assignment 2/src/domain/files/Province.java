package domain.files;


import domain.PersistentObject;
import domain.mappers.files.ProvinceMapper;

public class Province extends PersistentObject{
	private String province;
	private ProvinceMapper model = new ProvinceMapper();
	public Province(String prov){
		province = prov;
	}
	
		public Province(int id, String prov) {
			super(id);
			province = prov;
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

		public String getProvince() {
			return province;
		}

		@Override
		public ProvinceMapper getMapper() {
			return model;
		}
		

}

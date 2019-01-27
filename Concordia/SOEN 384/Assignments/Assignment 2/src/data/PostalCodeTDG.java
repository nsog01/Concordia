package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostalCodeTDG extends TableDataGateway {
	
	public static String getProvince(String postalCode) throws SQLException {
		set("select provinceCode from postal_codes where postal_codes.postalCode = ? limit 1");
		s.setString(1, postalCode);
		
		ResultSet set = s.executeQuery();
		set.next();
		return set.getString(1);
	}
	
	public static String getCity(String postalCode) throws SQLException {
		set("select city from postal_codes where postal_codes.postalCode = ? limit 1");
		
		s.setString(1, postalCode);
		ResultSet set = s.executeQuery();
		set.next();
		
		return set.getString(1);
	}

}

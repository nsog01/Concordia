package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityTDG extends TableDataGateway{
	public static int insert(String city) throws SQLException {
		set("insert into city (city) values (?)");
		s.setString(1, city);
		s.executeUpdate();
		resultSet = s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
}

public static ResultSet readAll() throws SQLException {
	set("select * from city");
	return s.executeQuery();
}

}

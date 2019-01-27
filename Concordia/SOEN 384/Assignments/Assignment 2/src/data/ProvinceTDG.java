package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceTDG extends TableDataGateway{
	public static int insert(String province) throws SQLException {
		set("insert into province (province) values (?)");
		s.setString(1, province);
		s.executeUpdate();
		resultSet = s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
}

public static ResultSet readAll() throws SQLException {
	set("select * from province");
	return s.executeQuery();
}

}

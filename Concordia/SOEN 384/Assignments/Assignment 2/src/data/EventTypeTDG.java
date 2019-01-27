package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventTypeTDG extends TableDataGateway{

	public static int insert(String name) throws SQLException{
		set("insert into event_types (name) values (?)");
		s.setString(1, name);
		s.executeUpdate();
		resultSet=s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}
	
	public static ResultSet readAll() throws SQLException{
		set("select * from event_types");
		return s.executeQuery();
	}
	
	public static ResultSet read(int id) throws SQLException{
		set("select * from event_Types where id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}
	
}

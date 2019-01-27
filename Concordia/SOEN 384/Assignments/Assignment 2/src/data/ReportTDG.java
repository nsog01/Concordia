package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportTDG extends TableDataGateway{

	public static ResultSet readAll() throws SQLException{
		set("select * from reports");
		return s.executeQuery();
	}

	public static ResultSet read(int id) throws SQLException {
		set("select * from reports where id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}
	
	public static int insert(String name, String desc, String dept, String path) throws SQLException {
		set("insert into reports (name, description, department, path) values (?, ?, ?, ?)");
		s.setString(1, name);
		s.setString(2, desc);
		s.setString(3, dept);
		s.setString(4, path);
		s.executeUpdate();
		resultSet = s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
}
	public static void delete(int id) throws SQLException{
		set("delete from reports where id = ?");	
		s.setInt(1, id);		
		s.executeUpdate();
	}	
	
	public static void update(int id, String name, String desc, String dept, String path) throws SQLException {
		set("update reports set name=?, description=?, department=?, path=? where id = ?");
		s.setString(1, name);
		s.setString(2, desc);
		s.setString(3, dept);
		s.setString(4, path);
		s.setInt(5, id);
		s.executeUpdate();
	}
	
}

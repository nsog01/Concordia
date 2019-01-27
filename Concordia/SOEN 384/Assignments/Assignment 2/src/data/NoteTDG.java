package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class NoteTDG extends TableDataGateway{

	public static int insert(int id, Date date, String user, String note) throws SQLException {
		set("insert into notes (household_id, date, user, note) values (?, ?, ?, ?)");
		s.setInt(1, id);
		s.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
		s.setString(3, user);
		s.setString(4, note);
		s.executeUpdate();
		resultSet = s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}

	public static void delete(int id) throws SQLException {
		set("delete from notes where id = ?");
		s.setInt(1, id);
		s.executeUpdate();
	}

	public static ResultSet readAll() throws SQLException {
		set("select * from notes");
		return s.executeQuery();
	}

	public static void update(int id, Date date, String user, String note) throws SQLException {
		set("update notes set date=?, user=?, note=? where id=?");
		s.setTimestamp(1, new java.sql.Timestamp(date.getTime()));
		s.setString(2, user);
		s.setString(3, note);
		s.setInt(4, id);
		s.executeUpdate();
	}

	public static ResultSet read(int id) throws SQLException {
		set("select * from notes where id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}

	public static ResultSet readByHousehold(int id) throws SQLException {
		set("select * from notes where household_id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}

}

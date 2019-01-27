package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlagTDG extends TableDataGateway{

	public static ResultSet readAll() throws SQLException{
		set("select * from flags");
		return s.executeQuery();
	}

	public static ResultSet read(int id) throws SQLException {
		set("select * from flags join households_flags on flags.id = households_flags.flag_id where flags.id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}

	public static ResultSet readForHousehold(int id) throws SQLException {
		set("select * from flags join households_flags on flags.id = households_flags.flag_id where household_id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}

	public static int insertFlag(int id, int flagType, String note) throws SQLException {
		set("insert into households_flags (household_id, flag_id, note) values (?, ?, ?)");
		s.setInt(1, id);
		s.setInt(2, flagType);
		s.setString(3, note);
		s.executeUpdate();
		resultSet = s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}

	public static int insert(String flag) throws SQLException {
		set("insert into flags (flag) values (?)");
		s.setString(1, flag);
		s.executeUpdate();
		resultSet = s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}

	public static void update(int flagType, String flag) throws SQLException {
		set("update flags set flag = ? where id = ?");
		s.setString(1, flag);
		s.setInt(2, flagType);
		s.executeUpdate();
	}

	public static void updateFlag(int id, int household_id, int flagType, String note) throws SQLException {
		set("update households_flags set household_id =?, flag_id =?, note =? where id =?");
		s.setInt(1, household_id);
		s.setInt(2, flagType);
		s.setString(3, note);
		s.setInt(4, id);
		s.executeUpdate();
	}

	public static void delete(int flagType) {
		// TODO Auto-generated method stub
	}

	public static void deleteFlagInstance(int id) throws SQLException {
		set("delete from households_flags where id = ?");
		s.setInt(1, id);
		s.executeUpdate();
	}
	
}

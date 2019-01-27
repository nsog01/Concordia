package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationTDG extends TableDataGateway{

	public static ResultSet readAll() throws SQLException{
		set("select * from relations");
		return s.executeQuery();
	}

	public static ResultSet read(int int1) throws SQLException {
		set("Select * from relations where id = ?");
		s.setInt(1, int1);
		return s.executeQuery();
	}
}

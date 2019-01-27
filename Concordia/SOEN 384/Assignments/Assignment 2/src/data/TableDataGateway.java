package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TableDataGateway {

	protected static Connection con;
	protected static ResultSet resultSet;
	protected static PreparedStatement s;

	public static void connect() throws SQLException {
		con = DBRegistry.getUniqueInstance().getDBConnection();
	}

	public static void close() throws SQLException {
		if (con != null && !con.isClosed()) {
			con.close();
		}
	}

	protected static void set(String sql) throws SQLException {
		connect();
		s = con.prepareStatement(sql);
	}
}
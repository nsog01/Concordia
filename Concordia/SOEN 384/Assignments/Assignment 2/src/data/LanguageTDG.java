package data;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LanguageTDG extends TableDataGateway{

	public static int insert(String language) throws SQLException {
			set("insert into languages (language) values (?)");
			s.setString(1, language);
			s.executeUpdate();
			resultSet = s.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(1);
	}

	public static ResultSet readAll() throws SQLException {
		set("select * from languages");
		return s.executeQuery();
	}

	public static ResultSet readMother(int id) throws SQLException {
		set("select languages.* from languages, clients " +
				"where clients.mother_tongue_id = languages.id and clients.id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}

	public static ResultSet readSecond(int id) throws SQLException {
		set("select languages.* from languages, clients " +
				"where clients.second_lang_id = languages.id and clients.id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}

	
}

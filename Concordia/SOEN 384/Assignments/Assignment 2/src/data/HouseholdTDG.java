package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class HouseholdTDG extends TableDataGateway{

	public static int insert(String address, String postalCode, Date initialVisit,
			long telNumber, int flag_id, int streetNumber, int unitNumber, String city) throws SQLException{
		set("insert into households (address, postal_code, first_visit, tel, flag_id, street_number, unit_number, city) " +
				"values (?, ?, now(), ?, ?, ?, ?, ?)");
		s.setString(1, address);
		s.setString(2, postalCode);
		s.setLong(3, telNumber);
		s.setInt(4, flag_id);
		s.setInt(5, streetNumber);
		s.setInt(6, unitNumber);
		s.setString(7, city);
		s.executeUpdate();
		resultSet=s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}
	
	
	public static ResultSet readAll() throws SQLException{
		set("select * from households");
		return s.executeQuery();
	}
	
	public static ResultSet searchByRepresentative(String first_name, String middle_name, String last_name) throws SQLException{
		set("Select households.* from (households inner join  clients on households.id = clients.household_id) " +
				"where (clients.first_name like ? or clients.middle_name like ? or clients.last_name like ?) " +
				"and relation_id = 1");
		s.setString(1, first_name+"%");
		s.setString(2, middle_name+"%");
		s.setString(3, last_name+"%");
		return s.executeQuery();
	}
	
	public static ResultSet searchByAnything(String anything) throws SQLException{
		set("select * from households "					+
						"where households.id in "					+
						"( "											+
						"select household_id from clients where "	+
						"	id 	    like ? "							+
						"or	first_name  like ? "						+
						"or	middle_name like ? "						+
						"or	last_name   like ? "						+
						"or	dob 	    like ? "						+
						"or medicare    like ? "						+
						") " 											+
						"or postal_code 	 like ? " 						+
						"or tel 			 like ? " 					+
						"or address 		 like ?");
		s.setString(1, anything+"%");
		s.setString(2, anything+"%");
		s.setString(3, anything+"%");
		s.setString(4, anything+"%");
		s.setString(5, anything+"%");
		s.setString(6, anything+"%");
		s.setString(7, anything+"%");
		s.setString(8, anything+"%");
		s.setString(9, anything+"%");
		return s.executeQuery();
	}


	public static void saveNote(int l, String note) throws SQLException {
		set("UPDATE households SET notes = ? WHERE id = ?");
		s.setInt(2, l);
		s.setString(1, note);
		s.executeUpdate();
		close();
	}


	public static void update(int id, String address, String postalCode,
			Date initialVisit, int fileNumber, String notes, int flag,long tel,
			String province, int streetNumber, int unitNumber, String city) throws SQLException {
		set("UPDATE households SET address =?, postal_code=?, first_visit=?, file_number=?, " +
				"tel=?, notes=?, flag_id=?, province=?, street_number=?, unit_number=?, city=? WHERE id = ?");
		s.setString(1, address);
		s.setString(2, postalCode);
		s.setDate(3, new java.sql.Date(initialVisit.getTime()));
		s.setString(4, Long.toString(fileNumber));
		s.setLong(5, tel);
		s.setString(6, notes);
		s.setInt(7, flag);
		s.setString(8, province);
		s.setInt(9, streetNumber);
		s.setInt(10, unitNumber);
		s.setString(11, city);
		s.setInt(12, id);
		s.executeUpdate();
	}


	public static ResultSet readByRep(int i) throws SQLException {
		set("select * from households where file_number = ?");
		s.setLong(1, i);
		return s.executeQuery();
	}


	public static ResultSet read(int file) throws SQLException {
		set("select * from households where id = ?");
		s.setLong(1, file);
		return s.executeQuery();
	}

}

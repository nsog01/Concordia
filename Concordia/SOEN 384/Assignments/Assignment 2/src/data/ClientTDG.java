package data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.imageio.ImageIO;

public class ClientTDG extends TableDataGateway{

	public static int insert(String first_name, String middle_name, String last_name,
			Date dob, String sex, BufferedImage photo, BufferedImage thumb, String medicare,
			int motherTongue, int secondLanguage,
			String maritalStatus, String workStatus, String workStatusExtra,
			String canadaStatus, String canadaStatusExtra,
			String origin, String originExtra, String referral, String referralExtra,
			String reason, String reasonExtra, Date registered) throws SQLException, IOException{
		set("insert into clients (first_name, middle_name, last_name," +
				" dob, sex, photo, thumb, medicare, mother_tongue_id, second_lang_id" +
				" marital_status, work_status, work_status_extra, canada_status, canada_status_extra" +
				" origin, origin_extra, referral, referral_extra, reason, reason_extra, rowcreateddt"+
				" )" +
				"values (?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		s.setString(1, first_name);
		s.setString(2, middle_name);
		s.setString(3, last_name);
		s.setTimestamp(4, new java.sql.Timestamp(dob.getTime()));
		s.setString(5, sex);
		// Images --------------
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(photo, "jpg", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		s.setBytes(6, imageInByte);
		baos = new ByteArrayOutputStream();
		ImageIO.write(thumb, "jpg", baos);
		baos.flush();
		imageInByte = baos.toByteArray();
		s.setBytes(7, imageInByte);
		// Images ==============
		s.setString(8, medicare);
		s.setInt(9, motherTongue);
		s.setInt(10, secondLanguage);
		
		s.setString(11, maritalStatus);
		s.setString(12, workStatus);
		s.setString(13, workStatusExtra);
		s.setString(14, canadaStatus);
		s.setString(15, canadaStatusExtra);
		
		s.setString(16, origin);
		s.setString(17, originExtra);
		s.setString(18, referral);
		s.setString(19, referralExtra);
		
		s.setString(20, reason);
		s.setString(21, reasonExtra);
		s.setTimestamp(22, new java.sql.Timestamp(registered.getTime()));
		
		
		s.executeUpdate();
		resultSet=s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}
	
	public static int insert(String first_name, String middle_name, String last_name,
			Date dob, String sex, BufferedImage photo, BufferedImage thumb, String medicare,
			int house, int rel, int motherTongue, int secondLanguage,
			String maritalStatus, String workStatus, String workStatusExtra,
			String canadaStatus, String canadaStatusExtra,
			String origin, String originExtra, String referral, String referralExtra,
			String reason, String reasonExtra, Date registered) throws SQLException, IOException{
		set("insert into clients (first_name, middle_name, last_name," +
				" dob, sex, photo, thumb, medicare, household_id, relation_id, mother_tongue_id, second_lang_id" +
				" marital_status, work_status, work_status_extra, canada_status, canada_status_extra" +
				" origin, origin_extra, referral, referral_extra, reason, reason_extra, rowcreateddt"+
				" )" +
				"values (?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		s.setString(1, first_name);
		s.setString(2, middle_name);
		s.setString(3, last_name);
		s.setDate(4, new java.sql.Date(dob.getTime()));
		s.setString(5, sex);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(photo, "jpg", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		s.setBytes(6, imageInByte);
		ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		ImageIO.write(thumb, "jpg", baos2);
		baos2.flush();
		imageInByte = baos2.toByteArray();
		s.setBytes(7, imageInByte);
		s.setString(8, medicare);
		s.setInt(9, house);
		s.setInt(10, rel);
		s.setInt(11, motherTongue);
		s.setInt(12, secondLanguage);
		
		s.setString(13, maritalStatus);
		s.setString(14, workStatus);
		s.setString(15, workStatusExtra);
		s.setString(16, canadaStatus);
		s.setString(17, canadaStatusExtra);
		
		s.setString(18, origin);
		s.setString(19, originExtra);
		s.setString(20, referral);
		s.setString(21, referralExtra);
		
		s.setString(22, reason);
		s.setString(23, reasonExtra);
		s.setTimestamp(24, new java.sql.Timestamp(registered.getTime()));
		
		s.executeUpdate();
		resultSet=s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}
	
	public static ResultSet read(int id) throws SQLException{
		set("select * from clients where id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}
	public static ResultSet readAllDependents(int houseId) throws SQLException{
		set("select * from clients, relations where household_id = ? and relation_id <> 1 and " +
				"relations.id = clients.relation_id");
		s.setInt(1, houseId);
		return s.executeQuery();
	}
	public static ResultSet readRepresentative(int houseId) throws SQLException{
		set("select * from clients where household_id = ? and relation_id = 1");
		s.setInt(1, houseId);
		return s.executeQuery();
	}

	public static void update(int clientId, String firstName, String middleName,
			String lastName, Date dob, String string, String medicare, int motherTongue,
			int secondLanguage, BufferedImage photo, BufferedImage thumb,
			String maritalStatus, String workStatus, String workStatusExtra,
			String canadaStatus, String canadaStatusExtra,
			String origin, String originExtra, String referral, String referralExtra,
			String reason, String reasonExtra, Date registered) throws SQLException, IOException {
		set("update clients set first_name =?, last_name =?, middle_name=?, " +
				"dob =?, sex=?, medicare=?, mother_tongue_id=?, second_lang_id=?, photo=?, thumb=?, " +
				"marital_status=?, work_status=?, work_status_extra=?, canada_status=?, " +
				"canada_status_extra=?, origin=?, origin_extra=?, referral=?, referral_extra=?, " +
				"reason=?, reason_extra=?, rowcreateddt=? " +
				"where id = ?");
		s.setString(1, firstName);
		s.setString(2, lastName);
		s.setString(3, middleName);
		s.setTimestamp(4, new java.sql.Timestamp(dob.getTime()));
		s.setString(5, string);
		s.setString(6, medicare);
		s.setInt(7, motherTongue);
		s.setInt(8, secondLanguage);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( photo, "jpg", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		s.setBytes(9, imageInByte);
		baos = new ByteArrayOutputStream();
		ImageIO.write( thumb, "jpg", baos );
		baos.flush();
		imageInByte = baos.toByteArray();
		s.setBytes(10, imageInByte);

		
		s.setString(11, maritalStatus);
		s.setString(12, workStatus);
		s.setString(13, workStatusExtra);
		s.setString(14, canadaStatus);
		s.setString(15, canadaStatusExtra);
		
		s.setString(16, origin);
		s.setString(17, originExtra);
		s.setString(18, referral);
		s.setString(19, referralExtra);
		
		s.setString(20, reason);
		s.setString(21, reasonExtra);
		s.setTimestamp(22, new java.sql.Timestamp(registered.getTime()));
		
		s.setInt(23, clientId);
		
		s.executeUpdate();
		close();
	}
	public static void delete(int clientId) throws SQLException{
		set("delete from clients where id = ?");
		s.setInt(1, clientId);		
		s.executeUpdate();
		close();
	}

	public static void updateWithoutPhoto(int clientId, String firstName, String middleName,
			String lastName, Date dob, String string, String medicare, int motherTongue, int secondLanguage,
			String maritalStatus, String workStatus, String workStatusExtra,
			String canadaStatus, String canadaStatusExtra,
			String origin, String originExtra, String referral, String referralExtra,
			String reason, String reasonExtra, Date registered) throws SQLException {
		System.out.println(canadaStatusExtra);
		set("update clients set first_name =?, last_name =?, middle_name=?, " +
				"dob =?, sex=?, medicare=?, mother_tongue_id=?, second_lang_id=?, " +
				"marital_status=?, work_status=?, work_status_extra=?, canada_status=?, " +
				"canada_status_extra=?, origin=?, origin_extra=?, referral=?, referral_extra=?, " +
				"reason=?, reason_extra=?, rowcreateddt=? " +
				"where id = ?");
		s.setString(1, firstName);
		s.setString(2, lastName);
		s.setString(3, middleName);
		s.setTimestamp(4, new java.sql.Timestamp(dob.getTime()));
		s.setString(5, string);
		s.setString(6, medicare);
		s.setInt(7, motherTongue);
		s.setInt(8, secondLanguage);
		
		s.setString(9, maritalStatus);
		s.setString(10, workStatus);
		s.setString(11, workStatusExtra);
		s.setString(12, canadaStatus);
		s.setString(13, canadaStatusExtra);
	
		s.setString(14, origin);
		s.setString(15, originExtra);
		s.setString(16, referral);
		s.setString(17, referralExtra);
	
		s.setString(18, reason);
		s.setString(19, reasonExtra);
		s.setTimestamp(20, new java.sql.Timestamp(registered.getTime()));
		
		
		s.setInt(21, clientId);
		s.executeUpdate();
		close();
	}

	public static void setHouseholdToNull(int client) throws SQLException {
		set("update clients set household_id = null where id = ?");
		s.setInt(1, client);
		s.executeUpdate();
		
	}

	public static ResultSet getPhoto(int id) throws SQLException {
		set("select photo from clients where id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}
	
}

package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TimeSlotTDG extends TableDataGateway{

	public static int insert(Date startTime, Date endTime, int limit, int l) throws SQLException{
		set("insert into timeslots (`start`, `end`, `limit`, `eventid`) values (?, ?, ?, ?)");
		s.setTimestamp(1, new java.sql.Timestamp(startTime.getTime()));
		s.setTimestamp(2, new java.sql.Timestamp(endTime.getTime()));
		s.setInt(3, limit);
		s.setInt(4, l);
		s.executeUpdate();
		resultSet=s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}
	
	public static ResultSet read(int id) throws SQLException{
		set("select * from timeslots where id = ? order by id");
		s.setInt(1, id);
		return s.executeQuery();
	}
	
	public static ResultSet readAll(int l) throws SQLException{
		set("select * from timeslots where `eventid` = ? order by id");
		s.setInt(1, l);
		return s.executeQuery();
	}
	
	public static void update(int l, Date startTime, Date endTime, int limit, int m) throws SQLException{
		set("update timeslots set start =?, end =? limit =? eventid =? where id =?");
		s.setDate(1, new java.sql.Date(startTime.getTime()));
		s.setDate(2, new java.sql.Date(endTime.getTime()));
		s.setInt(3, limit);
		s.setInt(4, m);
		s.setInt(5, l);
		s.executeUpdate();
		close();
	}
	
	public static void delete(int l) throws SQLException{
		set("delete from timeslots where id = ?");	
		s.setInt(1, l);		
		s.executeUpdate();
		close();
	}
	
	public static void deleteFromEvent(int id) throws SQLException{
		set("delete from timeslots where `eventid` = ?");	
		s.setInt(1, id);		
		s.executeUpdate();
		close();
	}

}

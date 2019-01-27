package data;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.events.Event.Period;


public class EventTDG extends TableDataGateway{

	public static int insert(String name, String description, int l, Date date, int recur, Period period) throws SQLException{
		set("insert into events (name, description, event_type_id, date, " +
				"recur_count, period_freq) values (?, ?, ?, ?, ?, ?)");
		s.setString(1, name);
		s.setString(2, description);
		s.setInt(3, l);
		s.setDate(4, new java.sql.Date(date.getTime()));
		s.setInt(5, recur);
		s.setString(6, period.toString());
		s.executeUpdate();
		resultSet=s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}
	
	public static ResultSet read(int id) throws SQLException{
		set("select * from all_events_ordered where id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}
	
	public static void update(int l, String name, int m, String description, Date date) throws SQLException{
		set("update events set name=? description=?, event_type_id=?, date=? where id = ?");
		s.setString(1, name);
		s.setString(2, description);
		s.setInt(3, m);
		s.setDate(4, new java.sql.Date(date.getTime()));
		s.setInt(5, l);
		s.executeUpdate();
		close();
	}
	
	public static void delete(int l) throws SQLException{
		set("delete from events where id = ?");	
		s.setInt(1, l);		
		s.executeUpdate();
		close();
	}
	
	public static ResultSet readAll() throws SQLException{
		set("select * from `all_events_ordered` order by date");
		return s.executeQuery();
	}
	
	public static void updateRecurCount(int l, int count) throws SQLException{
		set("update events set recur_count = ? where id = ?");
		s.setInt(1, count-1);
		s.setLong(2, l);
		s.executeUpdate();
		close();
	}

	public static ResultSet readNext(int lastId) throws SQLException {
		set("select * from `all_events_ordered` where id > ? and date >= DATE(now()) order by date limit 10");
		s.setInt(1, lastId);
		return s.executeQuery();
	}
	public static ResultSet readAppointments(int id) throws SQLException{
		set("select * from (all_events_ordered inner join appointments on " +
				"all_events_ordered.id = appointments.event_id) " +
				"where appointments.client_id = ? order by all_events_ordered.date limit 20");
		s.setInt(1, id);
		return s.executeQuery();
	}

	public static ResultSet getEventsForToday() throws SQLException {
		set("select * from all_events_ordered where date = DATE(NOW())");
		return s.executeQuery();
	}
	
}

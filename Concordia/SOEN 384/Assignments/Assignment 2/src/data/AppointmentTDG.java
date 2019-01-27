package data;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class AppointmentTDG extends TableDataGateway{

	public static ResultSet read(long id) throws SQLException{
		connect();
		String sql = "select appointments.*, clients.id, all_events.id, timeslots.id " +
				"from ((appointments join clients on appointments.client_id = clients.id) " +
				"join all_events on all_events.id = appointments.event_id) " +
				"join timeslots on appointments.timeSlotId = timeslots.id " +
				"where clients.id = ? And all_events.occurrence = appointments.event_occurrence_id";
		set(sql);
		s.setLong(1, id);
		resultSet = s.executeQuery();
		return resultSet;
	}

	public static void update(int id, int eventId, int occurrence, int clientId,
			int timeSlotId, int householdId, Date arrival, Date departure, int badge) throws SQLException {
		connect();
		String sql = "update appointments set event_id=?, event_occurrence_id=?," +
				"client_id=?, timeSlotId=?, household_id=?, arrival=?, departure=?, badge=? where id = ?";
		set(sql);
		s.setInt(1, eventId);
		s.setInt(2, occurrence);
		s.setInt(3, clientId);
		s.setInt(4, timeSlotId);
		s.setInt(5, householdId);
		s.setInt(6, badge);
		if (arrival != null){
			s.setTimestamp(6, new java.sql.Timestamp(arrival.getTime()));
		}
		if (departure != null){
			s.setTimestamp(7, new java.sql.Timestamp(departure.getTime()));
		} else {
			s.setTimestamp(7, null);
		}
		s.setInt(8, badge);
		s.setInt(9, id);
		s.executeUpdate();
	}

	public static int insert(long id, long eventId, int occurrence, long clientId,
			long timeSlotId, long householdId) throws SQLException {
		connect();
		String sql = "insert into appointments (event_id, event_occurrence_id, client_id, timeSlotId, household_id)" +
				"values (?,?,?,?,?)";
		s = con.prepareStatement(sql);
		s.setLong(1, eventId);
		s.setLong(2, occurrence);
		s.setLong(3, clientId);
		s.setLong(4, timeSlotId);
		s.setLong(5, householdId);
		s.executeUpdate();
		resultSet=s.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt(1);
	}

	public static ResultSet get(int household, int event) throws SQLException {
		set("select * from appointments where client_id = ? and event_id = ?");
		s.setInt(1, household);
		s.setInt(2, event);
		return s.executeQuery();
	}

	public static ResultSet readAllForEvent(int id) throws SQLException {
		set("select * from appointments where event_id = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}

	public static ResultSet readArrivedForEvent(int id) throws SQLException {
		set("select * from appointments where arrival is not null");
		return s.executeQuery();
	}

	public static ResultSet readAbsentForEvent(int id) throws SQLException {
		set("select * from appointments where arrival is null");
		return s.executeQuery();
	}

	public static ResultSet getForTimeSlot(int id) throws SQLException {
		set("select * from appointments where timeSlotId = ?");
		s.setInt(1, id);
		return s.executeQuery();
	}

	public static ResultSet readByBadge(String text) throws SQLException {
		set("select * from appointments where badge = ? and Date(arrival) = Date(now())");
		s.setInt(1, Integer.parseInt(text));
		return s.executeQuery();

	}

}

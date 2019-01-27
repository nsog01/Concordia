package data;

import java.util.Date;
import java.sql.SQLException;

import domain.events.Event.Period;

public class EventExceptionTDG extends TableDataGateway{

	public static void insert(int l, int recur, String name,
			String description, int m, Date date,
			Period period_freq, boolean isCancelled) throws SQLException {
		set("call insertEventException(?, ?, ?, ?, ?, ?, ?, ?)");
		s.setInt(1, l);
		s.setInt(2, recur);
		s.setString(3, name);
		s.setString(4, description);
		s.setInt(5, m);
		s.setDate(6, new java.sql.Date(date.getTime()));
		s.setString(7, period_freq.toString());
		s.setBoolean(8, isCancelled);
		s.executeUpdate();
	}
}

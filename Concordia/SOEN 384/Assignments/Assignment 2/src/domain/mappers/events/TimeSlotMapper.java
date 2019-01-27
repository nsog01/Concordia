package domain.mappers.events;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.TimeSlotTDG;
import domain.IList;
import domain.PersistentObject;
import domain.events.Event;
import domain.events.TimeSlot;
import domain.events.TimeSlotList;
import domain.mappers.Mapper;

public class TimeSlotMapper implements Mapper {

	public TimeSlot read(int id) {
		TimeSlot ts = null;
		try {
			ResultSet set = TimeSlotTDG.read(id);
			set.next();
			ts = map(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ts;
	}

	public static TimeSlotList readAll(Event event) {
		TimeSlotList list = new TimeSlotList();

		try {
			ResultSet set = TimeSlotTDG.readAll(event.getId());
			while (set.next()) {
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static TimeSlot map(ResultSet set) {
		TimeSlot ts = null;
		try {
			ts = new TimeSlot(set.getInt(1), new java.util.Date(set
					.getTimestamp(2).getTime()), new java.util.Date(set
					.getTimestamp(3).getTime()), set.getInt(4), set.getInt(5));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ts;
	}

	@Override
	public PersistentObject insert(PersistentObject object) {
		return null;
	}

	@Override
	public void delete(PersistentObject object) {

	}

	@Override
	public IList readAll() {
		return null;
	}

	@Override
	public void update(PersistentObject object) {

	}

}

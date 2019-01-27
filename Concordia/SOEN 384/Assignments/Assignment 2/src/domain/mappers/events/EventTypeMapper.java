package domain.mappers.events;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.EventTypeTDG;
import domain.PersistentObject;
import domain.events.EventType;
import domain.events.EventTypeList;
import domain.mappers.Mapper;

public class EventTypeMapper implements Mapper {

	public static void insert(EventType eventType) {
		try {
			eventType.setId(EventTypeTDG.insert(eventType.getName()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public EventTypeList readAll() {
		EventTypeList list = null;
		try {
			ResultSet set = EventTypeTDG.readAll();
			list = new EventTypeList();
			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				list.add(new EventType(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<EventType> getArrayList() {
		ArrayList<EventType> list = null;
		try {
			ResultSet set = EventTypeTDG.readAll();
			list = new ArrayList<EventType>();
			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				list.add(new EventType(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String[] readAllNames() {
		EventTypeList list = readAll();
		String[] stringList = new String[list.size()];
		for (int i = 0; i != list.size(); i++) {
			stringList[i] = list.get(i).getName();
		}
		return stringList;
	}

	@Override
	public EventType insert(PersistentObject object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PersistentObject object) {
		// TODO Auto-generated method stub

	}

	@Override
	public EventType read(int id) {
		return null;
	}

	@Override
	public void update(PersistentObject object) {

	}

}

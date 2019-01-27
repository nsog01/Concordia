package domain.mappers.files;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import data.AppointmentTDG;
import domain.IList;
import domain.PersistentObject;
import domain.Appointments.Appointment;
import domain.Appointments.AppointmentList;
import domain.events.Event;
import domain.events.TimeSlot;
import domain.files.Client;
import domain.files.FileManager;
import domain.files.Household;
import domain.mappers.Mapper;
import domain.mappers.events.EventMapper;
import domain.mappers.events.TimeSlotMapper;

public class AppointmentMapper implements Mapper {
	
	private static FileManager fileManager = FileManager.getUniqueInstance();

	public static AppointmentList getForClient(Client client, Household house) {
		AppointmentList list = new AppointmentList();

		try {
			ResultSet set = AppointmentTDG.read(client.getId());
			while (set.next()) {
				list.add(map(set, client, house));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	private static Appointment map(ResultSet set, Client client, Household house) {
		Appointment a = null;
		try {
			Timestamp arrivalTimeStamp = set.getTimestamp(7);
			Timestamp departureTimeStamp = set.getTimestamp(8);
			Date arrival = null;
			if (arrivalTimeStamp != null){
				arrival = new java.util.Date(set.getTimestamp(7).getTime());
			}
			Date departure = null;
			if (departureTimeStamp != null){
				departure = new java.util.Date(set.getTimestamp(8).getTime());
			}
			a = new Appointment(set.getInt(1), client, new EventMapper().read(set.getInt(2)),
					set.getInt(3), new TimeSlotMapper().read(set.getInt(5)),
					house, arrival,
					departure, set.getInt(9));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Appointment insert(PersistentObject a) {
		Appointment ap = null;
		if (a instanceof Appointment){
			ap = (Appointment) a;
		}
		try {
			ap.setId(AppointmentTDG.insert(a.getId(),
					ap.getEvent().getId(),
					ap.getOccurrence(),
					ap.getClient().getId(),
					ap.getTimeslot().getId(),
					ap.getHousehold().getId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ap;
	}

	@Override
	public void delete(PersistentObject object) {
		// TODO Auto-generated method stub

	}

	@Override
	public IList readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PersistentObject a) {
		try {
			AppointmentTDG.update(a.getId(), ((Appointment) a).getEvent()
					.getId(), ((Appointment) a).getOccurrence(),
					((Appointment) a).getClient().getId(), ((Appointment) a)
							.getTimeslot().getId(), ((Appointment) a)
							.getHousehold().getId(), ((Appointment) a).getArrival(),
							((Appointment) a).getDeparture(), ((Appointment) a).getBadgeId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Event read(int l) {
		// TODO Auto-generated method stub
		return null;
	}

	public static AppointmentList get(Household house, Event event) {
		AppointmentList list = new AppointmentList();
		try {
			ResultSet set = AppointmentTDG.get(house.getId(), event.getId());
			while (set.next()) {
				Client client = searchForClient(set, house);
				list.add(map(set, client, house));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	private static Client searchForClient(ResultSet set, Household house) throws SQLException{
		Client client = house.getClient(set.getInt(4));
		if (client == null) {
			client = new ClientMapper().read(set.getInt(4));	
		} 
		return client;
	}
	private static Household searchForHousehold(ResultSet set) throws SQLException{
			Household house = fileManager.getHousehold(set.getInt(6));
			if (house == null){
				house = new HouseholdMapper().read(set.getInt(6));
			}
			return house;
	}
	public AppointmentList readAllAppointments(Event event){
		AppointmentList list = new AppointmentList();
		try {
			ResultSet set = AppointmentTDG.readAllForEvent(event.getId());
			
			while(set.next()){
				Household house = searchForHousehold(set);
				Client client = searchForClient(set, house);
				list.add(map(set, client, house ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public AppointmentList readAllArrivals(Event event){
		AppointmentList list = new AppointmentList();
		try {
			ResultSet set = AppointmentTDG.readArrivedForEvent(event.getId());
			while(set.next()){
				Household house = searchForHousehold(set);
				Client client = searchForClient(set, house);
				list.add(map(set, client, house));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public AppointmentList readAllAbsent(Event event) {
		AppointmentList list = new AppointmentList();
		try {
			ResultSet set = AppointmentTDG.readAbsentForEvent(event.getId());
			
			while(set.next()){
				Household house = searchForHousehold(set);
				Client client = searchForClient(set, house);
				list.add(map(set, client, house));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static AppointmentList getListForTimeSlot(TimeSlot timeSlot) {
		AppointmentList list = new AppointmentList();
		try {
			ResultSet set = AppointmentTDG.getForTimeSlot(timeSlot.getId());
			
			while(set.next()){
				Household house = searchForHousehold(set);
				Client client = searchForClient(set, house);
				list.add(map(set, client, house));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Appointment readByBadge(String text) {
		ResultSet set = null;
		Appointment app = null;
		try {
		set = AppointmentTDG.readByBadge(text);
		set.next();
		
		Household house = searchForHousehold(set);
		Client client = searchForClient(set, house);
		
		app = map(set, client, house);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return app;
	}
}

package domain.mappers.files;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.AppointmentTDG;
import data.ClientTDG;
import data.FlagTDG;
import data.HouseholdTDG;
import domain.GList;
import domain.PersistentObject;
import domain.Appointments.Appointment;
import domain.files.Client;
import domain.files.ClientList;
import domain.files.FileList;
import domain.files.Flag;
import domain.files.Household;
import domain.files.Note;
import domain.mappers.Mapper;

public class HouseholdMapper implements Mapper {

	public static void insertDependent(Household house, Client dep) {
		try {
			dep.setId(ClientTDG.insert(dep.getFirstName(), 
					dep.getMiddleName(),
					dep.getLastName(), 
					dep.getDOB(), 
					dep.getSex().toString(),
					dep.getPhoto(), 
					dep.getThumb(), 
					dep.getMedicare(),
					house.getId(), 
					house.getDependentsList().get(dep).getId(),
					dep.getMotherTongueId(), 
					dep.getSecondLanguageId(),
					dep.getMaritalStatus().toString(),
					dep.getWorkStatus().toString(),
					dep.getExtraWorkStatusField(),
					dep.getCanadianStatus().toString(),
					dep.getExtraCanadianStatusField(),
					dep.getOrigin().toString(),
					dep.getExtraOriginField(),
					dep.getReferral().toString(),
					dep.getReferralExtra(),
					dep.getReason().toString(),
					dep.getReasonExtra(),
					dep.getRegistrationDate()	
					));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Household map(ResultSet set) {
		Household h = null;
		try {
			Client representative = ClientMapper.readRepresentative(set
					.getInt(1));
			ClientList dependents = ClientMapper.readAllDependents(set
					.getInt(1));

			h = new Household(
					set.getInt(1), // id
					set.getString(2), set.getString(3), new java.util.Date(set
							.getTimestamp(4).getTime()), set.getLong(6),
					set.getString(7), representative, dependents,
					FlagMapper.get(set.getInt(8)), set.getString(9), set.getInt(11), set.getString(12));
			
			GList<Note> notes = NoteMapper.readByHouse(h.getId());
			h.getNoteList().addAll(notes);
			FlagMapper.addToHousehold(h);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return h;
	}

	public static FileList findByName(String firstName, String middleName,
			String lastName) {
		FileList list = new FileList();

		try {
			ResultSet set = HouseholdTDG.searchByRepresentative(firstName,
					middleName, lastName);
			while (set.next()) {
				Household house = map(set);

				setAppointments(house);

				list.add(house);
				AppointmentTDG.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static FileList findByAnything(String anything) {
		FileList list = new FileList();

		try {
			ResultSet set = HouseholdTDG.searchByAnything(anything);
			while (set.next()) {
				Household h = map(set);
				setAppointments(h);
				list.add(h);
			}
			AppointmentTDG.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void saveNote(Household household) {
		try {
			HouseholdTDG.saveNote(household.getId(), household.getNotes());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Household insert(PersistentObject house) {
		// insert house itself
		try {
			if (house instanceof Household) {
				house.setId(HouseholdTDG.insert(
						((Household) house).getStreetName(),
						((Household) house).getPostalCode(),
						((Household) house).getInitialVisit(),
						((Household) house).getTel(), 
						1,
						((Household) house).getStreetNumber(),
						((Household) house).getUnitNumber(),
						((Household) house).getCity()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// insert the representative
		Client rep = ((Household) house).getRepresentative();
		if (rep != null) {
			try {
				rep.setId(ClientTDG.insert(rep.getFirstName(),
						rep.getMiddleName(), 
						rep.getLastName(), 
						rep.getDOB(),
						rep.getSex().toString(), 
						rep.getPhoto(),
						rep.getThumb(), 
						rep.getMedicare(), 
						((Household) house).getId(), 
						1,
						rep.getMotherTongueId(), 
						rep.getSecondLanguageId(),
						rep.getMaritalStatus().toString(),
						rep.getWorkStatus().toString(),
						rep.getExtraWorkStatusField(),
						rep.getCanadianStatus().toString(),
						rep.getExtraCanadianStatusField(),
						rep.getOrigin().toString(),
						rep.getExtraOriginField(),
						rep.getReferral().toString(),
						rep.getReferralExtra(),
						rep.getReason().toString(),
						rep.getReasonExtra(),
						rep.getRegistrationDate()							
						));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// insert all dependents
		ClientList dependentsList = ((Household) house)
				.getDependents();
		for (Client dep : dependentsList) {
			try {
				dep.setId(ClientTDG.insert(dep.getFirstName(), 
						dep.getMiddleName(), 
						dep.getLastName(), 
						dep.getDOB(), 
						dep.getSex().toString(), 
						dep.getPhoto(), 
						dep.getThumb(),
						dep.getMedicare(), 
						house.getId(), 
						((Household) house).getDependentsList().get(dep).getId(),
						dep.getMotherTongue().getId(), 
						dep.getSecondLanguage().getId(),
						dep.getMaritalStatus().toString(),
						dep.getWorkStatus().toString(),
						dep.getExtraWorkStatusField(),
						dep.getCanadianStatus().toString(),
						dep.getExtraCanadianStatusField(),
						dep.getOrigin().toString(),
						dep.getExtraOriginField(),
						dep.getReferral().toString(),
						dep.getReferralExtra(),
						dep.getReason().toString(),
						dep.getReasonExtra(),
						dep.getRegistrationDate()		
						));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (Flag flag : ((Household)(house)).getFlags()){
			flag.commit();
		}
		return (Household) house;
	}

	@Override
	public void delete(PersistentObject object) {

	}

	@Override
	public FileList readAll() {
		FileList list = new FileList();

		try {
			ResultSet set = HouseholdTDG.readAll();
			while (set.next()) {
				Household house = map(set);

				setAppointments(house);

				list.add(house);
			}
			AppointmentTDG.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Household read(int file) {
		Household h = null;
		try {
			ResultSet set = HouseholdTDG.read(file);
			if (set.next()) {
				h = map(set);
				setAppointments(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return h;
	}

	@Override
	public void update(PersistentObject house) {
		try {
			if (house instanceof Household) {
				HouseholdTDG.update(house.getId(), ((Household) house)
						.getStreetName(), ((Household) house).getPostalCode(),
						((Household) house).getInitialVisit(),
						((Household) house).getFileNumber(),
						((Household) house).getNotes(), ((Household) house)
								.getFlags().get(0).getId(), ((Household) house)
								.getTel(), ((Household) house).getProvince(),
								((Household) house).getStreetNumber(),
								((Household) house).getUnitNumber(),
								((Household) house).getCity());
					for (Client c : ((Household) house).getDependentsList()) {
						c.commit();
					}

					((Household) house).getRepresentative().commit();
				}
					for (Appointment a : ((Household) house).getAppointments()) {
						a.commit();
					}
					for (Flag f : ((Household) house).getFlags()){
						f.commit();
					}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeClient(Client client) {
		try {
			ClientTDG.setHouseholdToNull(client.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Household findByRep(int i) {
		Household h = null;
		try {
			ResultSet set = HouseholdTDG.readByRep(i);
			if (set.next()) {
				h = map(set);
				setAppointments(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return h;
	}
	
	private static void setAppointments(Household house){
		house.getRepresentative().setAppointments(AppointmentMapper.getForClient(
				house.getRepresentative(), house));
		for (Client c : house.getDependentsList()){
			c.setAppointments(AppointmentMapper.getForClient(c, house));
		}
	}
}

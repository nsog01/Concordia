package domain.Appointments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import domain.IList;
import domain.files.ClientList;

public class AppointmentList extends Observable implements Iterable<Appointment>, IList, Observer{
	
	private ArrayList<Appointment> appointments;

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public AppointmentList(){
		appointments = new ArrayList<Appointment>();
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		for (Appointment a : appointments){
			a.addObserver(this);
		}
		this.appointments = appointments;
		setChanged();
		notifyObservers();
	}

	@Override
	public Iterator<Appointment> iterator() {
		return appointments.iterator();
	}
	@Override
	public void add(Object app){
		((Appointment)app).addObserver(this);
		appointments.add((Appointment)app);
		setChanged();
		notifyObservers();
	}
	@Override
	public Appointment get(int id){
		if (!appointments.isEmpty()){
			return getAppointmentList().get(id);
		}
		return null;
	}
	@Override
	public int size(){
		return getAppointmentList().size();
	}
	@Override
	public boolean isEmpty(){
		return getAppointmentList().isEmpty();
	}
	protected ArrayList<Appointment> getList(){
		return this.getAppointmentList();
	}

	public ArrayList<Appointment> getAppointmentList() {
		return appointments;
	}

	@Override
	public void clear(){
		appointments.clear();
	}
	@Override
	public void remove(Object event){
		appointments.remove((Appointment)event);
	}
	public Appointment last(){
		return appointments.get(appointments.size()-1);
	}
	@Override
	public boolean contains(Object app){
		return appointments.contains((Appointment)app);
	}
	public ArrayList<Appointment> addAll(AppointmentList app) {
		for(Appointment a : app){
			a.addObserver(this);
			appointments.add(a);
		}
		setChanged();
		notifyObservers();
		return appointments;
	}
	public ClientList getClients(){
		ClientList list = new ClientList();
		for (Appointment a : appointments){
			list.add(a.getClient());
		}
		return list;
	}

	public static AppointmentList removeArrived(AppointmentList allList) {
		AppointmentList list = new AppointmentList();
		for(Appointment a : allList){
			if (a.getArrival() == null){
				list.add(a);
			}
		}
		return list;
	}
	public static AppointmentList removeAbsent(AppointmentList allList){
		AppointmentList list = new AppointmentList();
		for(Appointment a : allList){
			if (a.getArrival() != null){
				list.add(a);
			}
		}
		return list;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}

}

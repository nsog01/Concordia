package domain.events;

import java.util.Date;

import domain.PersistentObject;
import domain.Appointments.AppointmentList;
import domain.mappers.Mapper;
import domain.mappers.events.TimeSlotMapper;
import domain.mappers.files.AppointmentMapper;

public class TimeSlot extends PersistentObject{

	private Date startTime;
	private Date endTime;
	private int limit;	
	private int appointmentCount;
	private TimeSlotMapper model;
	
	public TimeSlot(Date start, Date end, int limit){
		super();
		this.startTime = start;
		this.endTime = end;
		this.limit = limit;
		appointmentCount = 0;
	}
	public TimeSlot(int id, Date start, Date end, int limit, int appointmentCount){
		super(id);
		this.startTime = start;
		this.endTime = end;
		this.limit = limit;
		this.appointmentCount = appointmentCount;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) { 
		this.limit = limit;
	}
	public int getAppointmentCount(){
		return appointmentCount;
	}
	public void incrAppointmentCount(){
		appointmentCount++;
	}
	public void decAppointmentCount(){
		appointmentCount--;
	}
	@Override
	public TimeSlot insert() {
		return null;
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Mapper getMapper() {
		return model;
	}
	public AppointmentList getAppointments() {
		return AppointmentMapper.getListForTimeSlot(this);
	}
	
}

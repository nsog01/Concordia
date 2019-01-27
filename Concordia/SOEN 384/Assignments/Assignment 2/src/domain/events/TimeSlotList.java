package domain.events;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import domain.IList;

public class TimeSlotList implements Iterable<TimeSlot>, IList{
	
	private List<TimeSlot> timeSlotList;
	
	public TimeSlotList(){
		timeSlotList = new ArrayList<TimeSlot>();
	}
	
	public TimeSlotList(TimeSlotList tsl) {
		this.timeSlotList = tsl.getList();
	}
	
	public void setTimeSlots (List<TimeSlot> lst){
		this.timeSlotList = lst;
	}
	public TimeSlot get(long l){
		for (TimeSlot timeSlot : this.timeSlotList){
			if (timeSlot.getId() == l){
				return timeSlot;
			}
		}
		return null;
//		return timeSlotList.get((int) l);
	}
	
	public List<TimeSlot> getList(){
		return this.timeSlotList;
	}
	
	public Iterator<TimeSlot> iterator(){
		return this.timeSlotList.iterator();
	}
	@Override
	public int size(){
		return timeSlotList.size();
	}
	
	public boolean isEmpty(){
		return timeSlotList.isEmpty();
	}
	@Override
	public void clear(){
		timeSlotList.clear();
	}

	@Override
	public void add(Object timeSlot) {
		if(timeSlotList==null)
			System.out.println("Time slot list is null");
		this.timeSlotList.add((TimeSlot)timeSlot);
	}

	@Override
	public void remove(Object timeSlot) {
		this.timeSlotList.remove((TimeSlot)timeSlot);
	
	}

	@Override
	public boolean contains(Object app) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TimeSlot get(int index) {
		return timeSlotList.get(index);
	}

	public void setDate(Date date) {
		for (TimeSlot timeSlot : timeSlotList){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			//get the fields we need
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			 
			//set Start date
			cal.setTime(timeSlot.getStartTime());
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.YEAR, year);
			timeSlot.setStartTime(cal.getTime());
			
			//Set end date
			cal.setTime(timeSlot.getEndTime());
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.YEAR, year);
			timeSlot.setEndTime(cal.getTime());
			cal.setTime(timeSlot.getEndTime());
		}
	}
}

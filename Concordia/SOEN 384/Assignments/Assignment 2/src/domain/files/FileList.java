package domain.files;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import domain.IList;

public class FileList implements Iterable<Household>, IList{

	protected List<Household> houses;

	public FileList(){
		this.houses = new ArrayList<Household>();
	}
	
	public FileList(List<Household> houses){
		this.houses = houses;
	}
	@Override
	public Iterator<Household> iterator() {
		// TODO Auto-generated method stub
		return houses.iterator();
	}
	
	public ClientList getDependents(int house){
		for (Household household : houses){
			if (house == household.getId()){
				return household.getDependents();
			}
		}
		return null;
	}
	@Override
	public Household get(int house){
		for (Household household : houses){
			if (house == household.getId()){
				return household;
			}
		}
		return null;
	}
	@Override
	public void add(Object house){
		houses.add((Household) house);
	}
	@Override
	public int size(){
		return houses.size();
	}
	@Override
	public boolean isEmpty(){
		return houses.isEmpty();
	}
	@Override
	public void clear(){
		houses.clear();
	}

	@Override
	public void remove(Object obj) {
		houses.remove((Household)obj);
	}

	@Override
	public boolean contains(Object app) {
		// TODO Auto-generated method stub
		return false;
	}
}

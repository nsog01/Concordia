
package domain.files;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import domain.IList;

public class FlagList extends Observable implements IList, Iterable<Flag>{

	List<Flag> list;
	
	public FlagList(){
		list = new ArrayList<Flag>();
	}
	public void add(Flag lang){
		list.add(lang);
		setChanged();
		notifyObservers();
	}
	public Flag getFlagAt(int index){
		return list.get(index);
	}
	public int size(){
		return list.size();
	}
	@Override
	public Flag get(int name){
		return list.get(name);
	}
	@Override
	public Iterator<Flag> iterator() {
		return list.iterator();
	}
	@Override
	public void add(Object lang) {
		list.add((Flag)lang);
		setChanged();
		notifyObservers();
	}
	@Override
	public void clear() {
		list.clear();
		setChanged();
		notifyObservers();
	}
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	@Override
	public void remove(Object arg) {
		list.remove((Flag) arg);
		setChanged();
		notifyObservers();
	}
	@Override
	public boolean contains(Object arg) {
		return list.contains((Flag) arg);
		
	}

}

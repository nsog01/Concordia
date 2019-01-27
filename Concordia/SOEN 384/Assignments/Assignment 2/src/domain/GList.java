package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GList<Item extends PersistentObject> extends Observable implements IList, Iterable<Item>, Observer {

	List<Item> list;
	
	public GList(){
		list = new ArrayList<Item>();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void add(Object arg) {
		list.add((Item) arg);
		((PersistentObject)arg).addObserver(this);
		setChanged();
		notifyObservers();
	}

	@Override
	public int size() {
		return list.size();
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
		list.remove(arg);
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean contains(Object arg) {
		return list.contains(arg);
	}

	@Override
	public Item get(int arg) {
		return list.get(arg);
	}

	public void addAll(GList<Item> addList) {
		for (Item item : addList){
			list.add(item);
			((PersistentObject)item).addObserver(this);
		}
		setChanged();
		notifyObservers();
	}
	@Override
	public Iterator<Item> iterator() {
		return list.iterator();
	}
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}


}

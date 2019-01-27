package domain.files;

import java.util.ArrayList;
import java.util.Iterator;

import domain.IList;

public class CityList implements Iterable<City>, IList {

	ArrayList<City> list;

	public CityList() {
		list = new ArrayList<City>();
	}

	public City getCityAt(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public City get(int index) {
		return list.get(index);
	}

	@Override
	public Iterator<City> iterator() {
		return list.iterator();
	}

	@Override
	public void add(Object city) {
		list.add((City) city);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void remove(Object arg) {
		list.remove((Province) arg);
	}

	@Override
	public boolean contains(Object arg) {
		return list.contains((Province) arg);
	}

}

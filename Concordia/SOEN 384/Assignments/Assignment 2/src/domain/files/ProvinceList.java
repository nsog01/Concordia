package domain.files;

import java.util.ArrayList;
import java.util.Iterator;

import domain.IList;

public class ProvinceList implements Iterable<Province>, IList {

	ArrayList<Province> list;

	public ProvinceList() {
		list = new ArrayList<Province>();
	}

	public Province getProvinceAt(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Province get(int index) {
		return list.get(index);
	}

	@Override
	public Iterator<Province> iterator() {
		return list.iterator();
	}

	@Override
	public void add(Object province) {
		list.add((Province) province);
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

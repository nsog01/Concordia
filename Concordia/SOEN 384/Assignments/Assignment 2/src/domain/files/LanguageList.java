package domain.files;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.IList;

public class LanguageList implements Iterable<Language>, IList{

	private List<Language> list;
	
	public LanguageList(){
		list = new ArrayList<Language>();
	}
	public Language getLanguageAt(int index){
		return list.get(index);
	}
	@Override
	public int size(){
		return list.size();
	}
	@Override
	public Language get(int name){
		return list.get(name);
	}
	@Override
	public Iterator<Language> iterator() {
		return list.iterator();
	}
	@Override
	public void add(Object lang) {
		list.add((Language) lang);
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
		list.remove((Language)arg);
	}
	@Override
	public boolean contains(Object arg) {
		return list.contains((Language)arg);
	}
}

package domain;

public interface IList {
	
	void add(Object arg);
	int size();
	void clear();
	boolean isEmpty();
	void remove(Object arg);
	boolean contains(Object arg);
	Object get(int arg);
}

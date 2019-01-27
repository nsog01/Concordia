package domain.files;

import java.util.ArrayList;
import java.util.List;

import domain.IList;

public class RelationList implements IList {

	List<Relation> list;

	public RelationList() {
		list = new ArrayList<Relation>();
	}

	public void add(Relation r) {
		list.add(r);
	}

	public Relation getRelationAt(int index) {
		return list.get(index);
	}

	public int size() {
		return list.size();
	}

	public Relation get(Object name) {
		for (Relation r : list) {
			if (name.equals(r.getRelation())){
				return r;
			}
		}
		return null;
	}

	@Override
	public void add(Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object arg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int arg) {
		// TODO Auto-generated method stub
		return null;
	}
}

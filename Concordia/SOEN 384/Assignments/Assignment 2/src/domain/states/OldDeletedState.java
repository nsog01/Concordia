package domain.states;

import domain.PersistentObject;

public class OldDeletedState implements State {


	@Override
	public void commit(PersistentObject obj) {
		obj.delete();
	}

	@Override
	public void registerDirty(PersistentObject persistentObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(PersistentObject obj) {
		// TODO Auto-generated method stub

	}

}

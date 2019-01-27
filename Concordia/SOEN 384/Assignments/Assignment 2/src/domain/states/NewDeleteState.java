package domain.states;

import domain.PersistentObject;

public class NewDeleteState implements State {

	@Override
	public void commit(PersistentObject obj) {
		obj.delete();
	}

	@Override
	public void registerDirty(PersistentObject persistentObject) {
		
	}

	@Override
	public void remove(PersistentObject obj) {
		
	}

}

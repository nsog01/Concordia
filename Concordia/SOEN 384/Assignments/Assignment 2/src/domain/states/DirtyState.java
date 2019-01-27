package domain.states;

import domain.PersistentObject;

public class DirtyState implements State{


	@Override
	public void commit(PersistentObject obj) {
		obj.update();
		obj.setState(new CleanState());
	}

	@Override
	public void registerDirty(PersistentObject persistentObject) {
		//nothing to do
	}

	@Override
	public void remove(PersistentObject obj) {
		obj.delete();
		obj.setState(new OldDeletedState());
	}

	
}

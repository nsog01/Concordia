package domain.states;

import domain.PersistentObject;

public class CleanState implements State{

	@Override
	public void commit(PersistentObject obj) {
		//obj.update();
	}


	@Override
	public void registerDirty(PersistentObject obj) {
		obj.setState(new DirtyState());
	}


	@Override
	public void remove(PersistentObject obj) {
		obj.setState(new NewDeleteState());
	}

}

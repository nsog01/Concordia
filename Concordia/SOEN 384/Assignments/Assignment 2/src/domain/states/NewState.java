package domain.states;

import domain.PersistentObject;

public class NewState implements State{


	@Override
	public void commit(PersistentObject obj) {
		obj.insert();
		obj.setState(new CleanState());
	}

	@Override
	public void registerDirty(PersistentObject obj) {
		//nothing to do
	}

	@Override
	public void remove(PersistentObject obj) {
		obj.setState(new NewDeleteState());
	}

}

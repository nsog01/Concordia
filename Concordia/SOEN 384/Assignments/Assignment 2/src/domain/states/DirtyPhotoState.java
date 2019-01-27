package domain.states;

import domain.PersistentObject;
import domain.files.Client;

public class DirtyPhotoState implements State{

	@Override
	public void commit(PersistentObject obj) {
		((Client) obj).updateWithPhoto((Client) obj);
	}

	@Override
	public void registerDirty(PersistentObject persistentObject) {
		//nothing to do
	}

	@Override
	public void remove(PersistentObject obj) {
		obj.setState(new OldDeletedState());
	}



}

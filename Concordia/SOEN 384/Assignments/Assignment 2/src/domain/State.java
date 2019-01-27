package domain.states;

import domain.PersistentObject;

public interface State {

	void commit(PersistentObject obj);

	void registerDirty(PersistentObject persistentObject);

	void remove(PersistentObject obj);
}

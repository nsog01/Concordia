package domain.mappers;

import domain.IList;
import domain.PersistentObject;

public interface Mapper {

	PersistentObject insert(PersistentObject object);

	void delete(PersistentObject object);

	IList readAll();

	void update(PersistentObject object);

	PersistentObject read(int id);
	}

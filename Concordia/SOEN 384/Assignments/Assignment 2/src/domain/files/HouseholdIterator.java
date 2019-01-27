package domain.files;

import java.util.Iterator;

public class HouseholdIterator implements Iterator<Client> {

	private FileList houses;
	private Client currentClient;
	private Household currentHouse;
	private Iterator<Household> houseIt;
	private Iterator<Client> clientIt;

	HouseholdIterator(FileList houses) {
		this.houses = houses;
		houseIt = this.houses.iterator();
		if (houseIt.hasNext()) {
			currentHouse = houseIt.next();
			clientIt = currentHouse.iterator();
			
			if (clientIt.hasNext()) {
				currentClient = clientIt.next();
			}
		}
	}

	@Override
	public boolean hasNext() {
		return (clientIt.hasNext() || houseIt.hasNext());
	}

	@Override
	public Client next() {
		if (!this.hasNext()){
			return null;
		}
		if (clientIt.hasNext()){
			currentClient = clientIt.next();
		} else{
				currentHouse = houseIt.next();
				clientIt = currentHouse.iterator();
				if (clientIt.hasNext()){
					currentClient = clientIt.next();
				} else {
					this.next();
				}
		}
		return currentClient;
	}

	@Override
	public void remove() {
		// TODO not implemented
	}

}

package domain.files;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import domain.IList;

public class ClientList extends Observable implements Iterable<Client>, IList, Observer{
	private List <Client> clients;
	public ClientList(){
		clients = new ArrayList<Client>();
	}
	public void add(Client client){
		client.addObserver(this);
		clients.add(client);
	}
	
	public void remove(Client client)
	{
		clients.remove(client);
	}
	
	public boolean isEmpty(){
		return clients.isEmpty();
	}

	@Override
	public Iterator<Client> iterator() {
		return clients.iterator();
	}
	@Override
	public void add(Object arg) {
		((Client)arg).addObserver(this);
		clients.add((Client) arg);
	}
	@Override
	public int size() {
		return clients.size();
	}
	@Override
	public void clear() {
		clients.clear();
	}
	@Override
	public void remove(Object arg) {
		clients.remove(arg);
	}
	@Override
	public boolean contains(Object arg) {
		return clients.contains(arg);
	}
	@Override
	public Object get(int arg) {
		return clients.get(arg);
	}
	public void addAll(ClientList clients) {
		for (Client c : clients){
			c.addObserver(this);
			this.clients.add(c);
		}
	}
	public Relation get(Client c) {
		Relation r = null;
		for (Client c1 : clients){
			if (c1.equals(c)){
				r = c1.getRelation();
			}
		}
		return r;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}
	public void setList(ClientList list) {
		clients.clear();
		for (Client c : list){
			c.addObserver(this);
			clients.add(c);
		}
		
	}
}

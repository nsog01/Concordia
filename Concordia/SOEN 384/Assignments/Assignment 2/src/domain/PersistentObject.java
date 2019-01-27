package domain;

import java.util.Observable;

import domain.mappers.Mapper;
import domain.states.CleanState;
import domain.states.NewState;
import domain.states.State;

public abstract class PersistentObject extends Observable{

	private int id;
	private State state;
	
	
	public PersistentObject(){
		state = new NewState();
	}
	public PersistentObject(int id){
		this.id = id;
		state = new CleanState();
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int l){
		this.id = l;
	}
	
	public abstract PersistentObject insert() ;
	
	public abstract void update() ;
	
	public abstract void delete();
	
	public abstract Mapper getMapper();
	
	public void registerDirty(){
		state.registerDirty(this);
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public void commit(){
		state.commit(this);
	}
	public void remove() {
		state.remove(this);
	}
}

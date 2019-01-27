package domain.files;

import domain.PersistentObject;
import domain.mappers.Mapper;
import domain.mappers.files.RelationMapper;

public class Relation extends PersistentObject {

	private String relation;
	private RelationMapper model = new RelationMapper();

	public Relation(String relation) {
		this.relation = relation;
	}

	public Relation(int id, String relation) {
		super(id);
		this.relation = relation;
	}

	public String getRelation() {
		return this.relation;
	}

	@Override
	public PersistentObject insert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public Mapper getMapper() {
		return model;
	}

}

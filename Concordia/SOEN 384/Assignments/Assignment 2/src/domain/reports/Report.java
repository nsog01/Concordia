package domain.reports;

import domain.PersistentObject;
import domain.mappers.Mapper;
import domain.mappers.reports.ReportMapper;

public class Report extends PersistentObject{
	
	private String name;
	private String desc;
	private String dept;
	private String path;

	private ReportMapper model = new ReportMapper();
	
	public Report(int rptId, String rptName, String rptDesc, String rptDept, String rptPath)
	{
		super(rptId);
		this.name = rptName;
		this.desc = rptDesc;
		this.dept = rptDept;
		this.path = rptPath;
		
	}
	
	public Report(String rptName, String rptDesc, String rptDept, String rptPath)
	{
		
		this.name = rptName;
		this.desc = rptDesc;
		this.dept = rptDept;
		this.path = rptPath;
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String rptName)
	{
		name = rptName;
	}
	
	
	
	public String getDesc()
	{
		return desc;
	}
	
	public void setDesc(String rptDesc)
	{
		desc = rptDesc;
	}
	
	public String getDept()
	{
		return dept;
	}
	
	public void setDept(String rptDept)
	{
		dept = rptDept;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String rptPath)
	{
		path = rptPath;
	}
	
	@Override
	public PersistentObject insert() {
		return model.insert(this);
	}

	@Override
	public void update() {
		model.update(this);
		
	}

	@Override
	public void delete() {
		model.delete(this);
		
	}

	@Override
	public Mapper getMapper() {
		return model;
	}

}

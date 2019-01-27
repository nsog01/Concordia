package domain.mappers.reports;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ReportTDG;
import domain.PersistentObject;
import domain.mappers.Mapper;
import domain.reports.Report;
import domain.reports.ReportList;

public class ReportMapper implements Mapper {
	
	@Override
	public ReportList readAll()  {
		ReportList list = new ReportList();

		try {
			ResultSet set = ReportTDG.readAll();
			while (set.next()){
				list.add(map(set));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Report map(ResultSet set) {
		Report report = null;
			try {
				report = new Report(set.getInt(1), set.getString(2),set.getString(3),set.getString(4),set.getString(5));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return report;
	}

	@Override
	public Report insert(PersistentObject rpt)
{
		Report report = (Report)rpt;
			try {
				report.setId(ReportTDG.insert(report.getName(), report.getDesc(), report.getDept(), report.getPath()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return report;
	}

	@Override
	public void delete(PersistentObject rpt) {
		
		try {
			ReportTDG.delete(rpt.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public PersistentObject read(int id) {
		Report report = null;
		ResultSet set;
		
		try {
			set = ReportTDG.read(id);
			set.next();
			report = map(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return report;
	}

	@Override
	public void update(PersistentObject rpt) {
		Report report = (Report)rpt;
		
		try {
			ReportTDG.update(report.getId(),report.getName(), report.getDesc(), report.getDept(), report.getPath());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

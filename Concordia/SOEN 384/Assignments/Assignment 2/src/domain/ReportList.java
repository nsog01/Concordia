package domain.reports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import domain.IList;
import domain.mappers.reports.ReportMapper;

public class ReportList implements Iterable<Report>, Observer, IList {

	ArrayList<Report> list;

	public ReportList() {
		list = new ArrayList<Report>();
	}

	public Report getReportAt(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Report get(int index) {
		return list.get(index);
	}

	@Override
	public Iterator<Report> iterator() {
		return list.iterator();
	}

	@Override
	public void add(Object report) {
		list.add((Report) report);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void remove(Object arg) {
		list.remove((Report) arg);
	}

	@Override
	public boolean contains(Object arg) {
		return list.contains((Report) arg);
	}
	
	public final void setReportList(ReportList reportList) {
		clear();
		for (Report r : reportList){
			add(r);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setReportList(new ReportMapper().readAll());
		
	}

}
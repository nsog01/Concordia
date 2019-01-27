package presentation.views.events;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

public class TimeSlotSelector {
	
	private JSpinner timeSpinner;
	JComponent editor;
	JFormattedTextField ftf;
	SpinnerModel model;
	
	public TimeSlotSelector()
	{
		model = new SpinnerDateModel();		
		timeSpinner = new JSpinner( model);
		editor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor (editor);			
		ftf =  ((JSpinner.DefaultEditor)editor).getTextField();
		
		//we set the date to 1970/1/1 instead of current date to be able to do comparisons later on
		GregorianCalendar cal = new GregorianCalendar(1970, 0, 1, 0, 0);
		ftf.setValue(cal.getTime());
		
		//timeSpinner.setValue(null);
	}
	
	public void setTime(Date timeToSet)
	{				
		ftf.setValue(timeToSet);
	}	
	
	public Date getTime()
	{				
		return (Date) ftf.getValue();
	}
	
	public JSpinner getTimeSpinner()
	{
		return timeSpinner;
	}

	
}


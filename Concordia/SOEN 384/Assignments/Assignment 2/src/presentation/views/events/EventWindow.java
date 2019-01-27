package presentation.views.events;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import domain.events.Event;
import domain.events.EventType;


public class EventWindow extends JFrame implements Observer {
	// TODO Must fix observer operation for adding a new event
	/**
	 * 
	 */
	private static final long serialVersionUID = 7894302462L;
	private JLabel warning;
	private JButton eventSubmit;
	private JButton cancelButton;
	private Event event;
	private JPanel buttonPanel;
	private EventPanel eventPanel;
	private OccurrencesPanel occurrencesPanel;
	private TimeSlotPanel timeSlotPanel;

	/**
	 * @throws SQLException 
	 * @wbp.parser.constructor
	 */
	public EventWindow() {
		String eventSumbitText="Add Event";
		setTitle(eventSumbitText);	
		this.event = null;
		this.setLayout(new MigLayout());

		this.addComponents();
		
		
		String eventSubmitToolTip="Alt+A";
		String cancelButtonText="Cancel";
		String cancelButoonToolTip="Alt+C";
		
		this.eventSubmit.setText(eventSumbitText);
		this.eventSubmit.setToolTipText(eventSubmitToolTip);
		this.eventSubmit.setMnemonic(KeyEvent.VK_A);
		
		this.cancelButton.setText(cancelButtonText);
		this.cancelButton.setToolTipText(cancelButoonToolTip);
		this.cancelButton.setMnemonic(KeyEvent.VK_C);
		
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public EventWindow(Event event)  {
		
		String eventEditTitle="Edit Event";
		String eventSubmitDone="Done";
		String eventEditToolTip="Alt+M";
		String eventEditCancel="Cancel";
		String eventEditCancelToolTip="Alt+C";
		
		setTitle(eventEditTitle);	
		this.event = event;
		this.setLayout(new MigLayout());
		this.addComponents();

		this.eventSubmit.setText(eventSubmitDone);
		this.eventSubmit.setToolTipText(eventEditToolTip);
		this.eventSubmit.setMnemonic(KeyEvent.VK_M);
		
		this.cancelButton.setText(eventEditCancel);
		this.cancelButton.setToolTipText(eventEditCancelToolTip);
		this.cancelButton.setMnemonic(KeyEvent.VK_C);
		
		this.eventPanel.setFields(event);
		this.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int) screenSize.getWidth() - this.getWidth()) / 2,
				100);
		this.setLocationRelativeTo(null); // added to make the window appear in
											// the middle
		this.setVisible(true);
	}
	
	public void addEventSubmitListener(ActionListener submit)
	{
		eventSubmit.addActionListener(submit);
	}
	
	public void addUpdateEventListener(ActionListener update)
	{
		eventSubmit.addActionListener(update);
	}

	private void addComponents() {
		if (this.event == null)
		{
			this.eventPanel = new EventPanel();
			timeSlotPanel = new TimeSlotPanel();
			this.occurrencesPanel = new OccurrencesPanel();
			this.add(this.eventPanel, "grow, wrap");
			this.add(this.occurrencesPanel, "grow, wrap");
		}
		else 
		{
			this.eventPanel = new EventPanel(this.event);
			timeSlotPanel = new TimeSlotPanel(this.event.getTimeSlotList());
			this.add(this.eventPanel, "grow, wrap");
		}

		warning = new JLabel();

		eventSubmit = new JButton();
		cancelButton= new JButton();
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		//------------TIMESLOTS--------------		
		this.add(timeSlotPanel,"grow, wrap, span");
		//------------TIMESLOTS--------------
		
		buttonPanel=new JPanel(new MigLayout());
		
		buttonPanel.add(eventSubmit,"split ,width 100!,alignx right");
		buttonPanel.add(cancelButton,"width 75!");
		this.add(buttonPanel,"spanx,right,wrap");
		
		
		this.add(warning, "span, wrap 15, align center");
		

		getRootPane().setDefaultButton(eventSubmit);
	}


	public OccurrencesPanel getOccurrencesPanel(){
		return this.occurrencesPanel;
	}
	public Event getEvent()
	{
		return this.event;
	}
	
	public void setWarning(String warning)
	{
		this.warning.setText(warning);
	}
	

	public TimeSlotPanel getTimeSlotPanel(){
		return this.timeSlotPanel;
	}
	public boolean checkFields() {
		return this.eventPanel.checkFields();
	}
	public String getEventName(){
		return this.eventPanel.getEventName();
	}
	public String getEventDescription(){
		return this.eventPanel.getDescription();
	}
	public EventType getSelectedEventType(){
		return this.eventPanel.getEventType();
	}
	public Date getDatePicked(){
		return this.eventPanel.getDatePicked();
	}

	@Override
	public void update(Observable o, Object arg) {
	}
}

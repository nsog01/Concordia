package presentation.controllers.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;

import presentation.models.events.EventTableModel;
import presentation.views.events.DeleteRecurringEventAlertFrame;
import presentation.views.events.EditRecurringEventAlertFrame;
import presentation.views.events.EventWindow;
import presentation.views.events.ShowEventsWindow;

import domain.events.Event;
import domain.events.EventManager;
import domain.events.EventType;
import domain.events.TimeSlotList;

public class EventWindowController {

	//views
	ShowEventsWindow showEventWindow ;
	EventWindow eventWindow;
	
	//models
	EventTableModel tableModel;
	
	//managers
	EventManager manager;
	
	public EventWindowController()
	{

		//create manager
		manager = EventManager.getUniqueInstance();	

		//attach repository to Table Model
		tableModel = new EventTableModel(manager);  	
		
		//Window with table containing a list of events and UI
		showEventWindow = new ShowEventsWindow(tableModel);		
		
		//add listeners to showEventsWindow
		showEventWindow.addEditEventListener(openEditEventListener());
		showEventWindow.addDeleteEventListener(deleteEventListener());
		showEventWindow.addAddEventListener(openAddEventListener());
		
		showEventWindow.setVisible(true);
	}
	
	
	private ActionListener openEditEventListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
			int row = showEventWindow.getTableSelectedRow();
						
				if (row != -1) {
					Event selectedEvent = tableModel.get(row);
							eventWindow = new EventWindow(selectedEvent);

						manager.addObserverToRepo(eventWindow);						
						
						eventWindow.addUpdateEventListener(updateEventListener(eventWindow));
						eventWindow.setVisible(true);	
					}
				
			}
			};
		}
	
	
	private ActionListener deleteEventListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			int row = showEventWindow.getTableSelectedRow();
				if (row != -1) {
					Event selectedEvent = tableModel.get(row);
					new DeleteRecurringEventAlertFrame(manager, selectedEvent);		
					}
			}
			};
		}
	
	private ActionListener openAddEventListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventWindow = new EventWindow();
				manager.addObserverToRepo(eventWindow);			
				
				eventWindow.addEventSubmitListener(addEventListener(eventWindow));
				eventWindow.setVisible(true);	
			}
			};
		}

	
	private ActionListener addEventListener(final JFrame frame) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (eventWindow.checkFields()) {
					String eventName = eventWindow.getEventName();
					EventType eventType = eventWindow.getSelectedEventType();
					String eventDescription = eventWindow.getEventDescription();
					Date eventDate = eventWindow.getDatePicked();
					
					//----------SETTING OCCURRENCES

					int recurCount = eventWindow.getOccurrencesPanel().getOccurrenceCount();
					Event.Period period = eventWindow.getOccurrencesPanel().getPeriod();
					
					//----------SETTING OCCURRENCES
					
					//--------TIME SLOTS TO ADD
					TimeSlotList timeSlotList = eventWindow.getTimeSlotPanel().getAddQueue();
					timeSlotList.setDate(eventDate);
					//--------TIME  SLOTS
					try {
						manager.insertEvent(eventName, eventType,
								eventDescription, eventDate, recurCount, period, timeSlotList);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					frame.dispose();
				} else {
					String eventWindowWarning1="Please enter all the required information.";
					eventWindow.setWarning(eventWindowWarning1);
				}
			}
		};
	}
	

	private ActionListener updateEventListener(final JFrame frame) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (eventWindow.checkFields()) {
					Event event = eventWindow.getEvent();
					
					event.setName(eventWindow.getEventName());
					event.setCategory(eventWindow.getSelectedEventType());
					event.setDescription(eventWindow.getEventDescription());
					event.setDate(eventWindow.getDatePicked());					
					
					new EditRecurringEventAlertFrame(manager, eventWindow);

				} else {
					String eventWindowWarning2="Please enter all details";
					eventWindow.setWarning(eventWindowWarning2);
				}
			}
		};
	}

}

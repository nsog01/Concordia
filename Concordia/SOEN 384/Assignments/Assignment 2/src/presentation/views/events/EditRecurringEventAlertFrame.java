package presentation.views.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import presentation.views.files.EmptyBannerPanel;


import net.miginfocom.swing.MigLayout;

import domain.events.Event;
import domain.events.EventManager;
import domain.events.TimeSlotList;

import java.awt.BorderLayout;
import java.awt.Font;


public class EditRecurringEventAlertFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4321578902L;
	private JTextArea detail;
	private JLabel title;
	private JButton cancel;
	private JButton allFutureEvents;
	private JButton onlyThisEvent;
	private Event event;
	private EventManager manager;
	private EventWindow eventWindow;
	
	
	public EditRecurringEventAlertFrame(EventManager manager, EventWindow eventWindow){
		this.event = eventWindow.getEvent();
		this.manager = manager;
		this.eventWindow = eventWindow;
		this.initializeComponenets();
		this.addComponents();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}
	public void initializeComponenets(){
		String editRecurringEventTitleText="Changing a repeat event";
		String editRecurringEventDialogText="Dialog";
		String editRecurringEventDetailText="You are changing a repeating event. Would you like to change only this occurrence of this event or all future occurences";
		String editRecurringEventCancelText="Cancel";
		String editRecurringEventFutureText="All Future Events";
		String editRecurringEventOnlyText="Only This Event";
		
		this.setLayout(new MigLayout());
		this.title = new JLabel(editRecurringEventTitleText);
		this.title.setFont(new Font(editRecurringEventDialogText, Font.PLAIN, 20));
		this.detail = new JTextArea(editRecurringEventDetailText);
		detail.setRows(4);
		detail.setColumns(40);
		this.detail.setLineWrap(true);
		detail.setEditable(false);
		this.detail.setWrapStyleWord(true);
		this.cancel = new JButton(editRecurringEventCancelText);
		this.allFutureEvents = new JButton(editRecurringEventFutureText);
		this.onlyThisEvent = new JButton(editRecurringEventOnlyText);
	}
	public void addComponents(){
		EmptyBannerPanel banner = new EmptyBannerPanel("Changing a repeat event");
		banner.removeHomeButton();
		add(banner, BorderLayout.NORTH);
		this.detail.setBackground(getContentPane().getForeground());
		add(this.detail, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel(new MigLayout());
		buttonPanel.add(this.cancel);
		this.cancel.addActionListener(cancelListener(this));
		buttonPanel.add(this.allFutureEvents);
		this.allFutureEvents.addActionListener(futureListener(this));
		buttonPanel.add(this.onlyThisEvent);
		this.onlyThisEvent.addActionListener(oneListener(this));
		add(buttonPanel, BorderLayout.SOUTH);
	}
	private ActionListener cancelListener(final EditRecurringEventAlertFrame frame){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						frame.dispose();	
			}
			
		};
	}
	private ActionListener futureListener(final EditRecurringEventAlertFrame frame){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						frame.manager.updateAllEvents(event);						
						
						TimeSlotList addList = eventWindow.getTimeSlotPanel().getAddQueue();
						TimeSlotList removeList = eventWindow.getTimeSlotPanel().getRemoveQueue();
						TimeSlotList updateList = eventWindow.getTimeSlotPanel().getUpdatedQueue();
						//not yet implemented for occurences
						frame.manager.updateEventTimeSlots(event, addList, removeList, updateList);					
						
						frame.dispose();
						eventWindow.dispose();	
			}
			
		};
	}
	private ActionListener oneListener(final EditRecurringEventAlertFrame frame){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						frame.manager.updateEvent(event);
						TimeSlotList addList = eventWindow.getTimeSlotPanel().getAddQueue();
						TimeSlotList removeList = eventWindow.getTimeSlotPanel().getRemoveQueue();
						TimeSlotList updateList = eventWindow.getTimeSlotPanel().getUpdatedQueue();
						frame.manager.updateEventTimeSlots(event, addList, removeList, updateList);	
						
						frame.dispose();
						eventWindow.dispose();	
			}
			
		};
	}
}

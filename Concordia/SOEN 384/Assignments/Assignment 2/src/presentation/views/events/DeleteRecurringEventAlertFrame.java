package presentation.views.events;

import java.awt.BorderLayout;
import java.awt.Font;
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


public class DeleteRecurringEventAlertFrame extends JFrame {

	private static final long serialVersionUID = 4321578902L;
	private JTextArea detail;
	private JLabel title;
	private JButton cancel;
	private JButton allFutureEvents;
	private JButton onlyThisEvent;
	private Event event;
	private EventManager manager;
	
	
	public DeleteRecurringEventAlertFrame(EventManager manager, Event event){
		this.event = event;
		this.manager = manager;
		this.initializeComponenets();
		this.addComponents();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}
	public void initializeComponenets(){
		
		String deleteRepeatEventTitleText="Deleting a repeat event";
		String deleteRepeatEventDialogText="Dialog";
		String deleteRepeatEventDetailText="You are deleting a repeating event. Would you like to delete only this occurrence of this event or all future occurences";
		String deleteRepeatEventCancelText="Cancel";
		String deleteRepeatEventFutureText="All Future Events";
		String deleteRepeatEventOnlyText="Only This Event";
		
		this.setLayout(new MigLayout());
		this.title = new JLabel(deleteRepeatEventTitleText);
		this.title.setFont(new Font(deleteRepeatEventDialogText, Font.PLAIN, 20));
		this.detail = new JTextArea(deleteRepeatEventDetailText);
		detail.setRows(4);
		detail.setColumns(40);
		this.detail.setLineWrap(true);
		detail.setEditable(false);
		this.detail.setWrapStyleWord(true);
		this.cancel = new JButton(deleteRepeatEventCancelText);
		this.allFutureEvents = new JButton(deleteRepeatEventFutureText);
		this.onlyThisEvent = new JButton(deleteRepeatEventOnlyText);
	}
	public void addComponents(){
		
		String emptyBannerPanelText="Deleting a repeat event";
		EmptyBannerPanel banner = new EmptyBannerPanel(emptyBannerPanelText);
		banner.removeHomeButton();
		add(banner, BorderLayout.NORTH);
		this.detail.setBackground(getContentPane().getForeground());
		add(this.detail);
		JPanel buttonPanel = new JPanel(new MigLayout());
		buttonPanel.add(this.cancel);
		this.cancel.addActionListener(cancelListener(this));
		buttonPanel.add(this.allFutureEvents);
		this.allFutureEvents.addActionListener(futureListener(this));
		buttonPanel.add(this.onlyThisEvent);
		this.onlyThisEvent.addActionListener(oneListener(this));
		add(buttonPanel, BorderLayout.SOUTH);
	}
	private ActionListener cancelListener(final DeleteRecurringEventAlertFrame frame){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						frame.dispose();	
			}
			
		};
	}
	private ActionListener futureListener(final DeleteRecurringEventAlertFrame frame){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						frame.manager.removeAllEvents(event);						
						
//							TimeSlotList addList = eventWindow.getTimeSlotListPanel().getAddQueue();
//							TimeSlotList removeList = eventWindow.getTimeSlotListPanel().getRemoveQueue();
//							TimeSlotList updateList = eventWindow.getTimeSlotListPanel().getUpdatedQueue();
						//not yet implemented for occurences
//							frame.manager.updateEventTimeSlots(event, addList, removeList, updateList);					
						
						frame.dispose();	
			}
			
		};
	}
	private ActionListener oneListener(final DeleteRecurringEventAlertFrame frame){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						frame.manager.removeEvent(event);
//							TimeSlotList addList = eventWindow.getTimeSlotListPanel().getAddQueue();
//							TimeSlotList removeList = eventWindow.getTimeSlotListPanel().getRemoveQueue();
//							TimeSlotList updateList = eventWindow.getTimeSlotListPanel().getUpdatedQueue();
//							frame.manager.updateEventTimeSlots(event, addList, removeList, updateList);	
						
						frame.dispose();	
			}
			
		};
	}
}

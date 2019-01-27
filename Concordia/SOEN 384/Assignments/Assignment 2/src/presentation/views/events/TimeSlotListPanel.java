package presentation.views.events;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import domain.events.TimeSlot;
import domain.events.TimeSlotList;
import net.miginfocom.swing.MigLayout;

public class TimeSlotListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7149004981489182670L;
	private JScrollPane scrollPane;
//	private JButton addButton;

//	private ArrayList<JButton> deleteButtons;
	private ArrayList<TimeSlotPanel> allTimeSlotPanels;

	private JPanel panel;
	@SuppressWarnings("unused")
	private TimeSlotList timeSlotList;

	private ArrayList<TimeSlotPanel> addQueue;
	private ArrayList<TimeSlotPanel> removeQueue;
	@SuppressWarnings("unused")
	private ArrayList<TimeSlotPanel> updateQueue;

	public TimeSlotListPanel() {
		initializeLayout();

		addQueue = new ArrayList<TimeSlotPanel>();
		removeQueue = new ArrayList<TimeSlotPanel>();
		updateQueue = new ArrayList<TimeSlotPanel>();

		/* adding initial timeslot */
		TimeSlotPanel timeSlotPanel = new TimeSlotPanel();
//		JButton deleteButton = new JButton("Delete");

		allTimeSlotPanels.add(timeSlotPanel);
//		deleteButtons.add(deleteButton);

//		deleteButton.addActionListener(deleteEventListener(this, timeSlotPanel,
//				deleteButton));
		addQueue.add(timeSlotPanel);

		panel.add(timeSlotPanel);
//		panel.add(deleteButton, "wrap");

		// we only need 400 width to compensate for the scrollbars
		this.add(scrollPane);
	}

	public TimeSlotListPanel(TimeSlotList list) {
		this.timeSlotList = list;

		initializeLayout();

		addQueue = new ArrayList<TimeSlotPanel>();
		removeQueue = new ArrayList<TimeSlotPanel>();
		updateQueue = new ArrayList<TimeSlotPanel>();

		/* add timeslots in list */
		Iterator<TimeSlot> it = list.iterator();

		TimeSlotPanel timeSlotPanel;
//		JButton deleteButton;
		if (list.isEmpty()) {
			timeSlotPanel = new TimeSlotPanel();
//			deleteButton = new JButton("Delete");
			
			allTimeSlotPanels.add(timeSlotPanel);
////			deleteButtons.add(deleteButton);
//			deleteButton.addActionListener(deleteEventListener(this,
//					timeSlotPanel, deleteButton));
			
			panel.add(timeSlotPanel);
//			panel.add(deleteButton, "wrap");
		} else {
			while (it.hasNext()) {
//				timeSlotPanel = new TimeSlotPanel(it.next());
//				deleteButton = new JButton("Delete");

//				allTimeSlotPanels.add(timeSlotPanel);
//				deleteButtons.add(deleteButton);

//				deleteButton.addActionListener(deleteEventListener(this,
//						timeSlotPanel, deleteButton));

//				panel.add(timeSlotPanel);
//				panel.add(deleteButton, "wrap");
			}
		}

		this.add(scrollPane);
	}

	private void initializeLayout() {
		this.setLayout(new BorderLayout());
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				"Time Slot Settings"));
		panel = new JPanel(new MigLayout());
		scrollPane = new JScrollPane(panel);
		allTimeSlotPanels = new ArrayList<TimeSlotPanel>();
//		deleteButtons = new ArrayList<JButton>();
//		addButton = new JButton("New");
//		addButton.addActionListener(addEventListener(this));
		String timeSlotlistPanelAddText="Time slots";
		panel.add(new JLabel(timeSlotlistPanelAddText), "wrap");
//		panel.add(addButton, "wrap, span 2");
	}

//	private ActionListener addEventListener(final JPanel frame) {
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//
//				TimeSlotPanel timeSlotPanel = new TimeSlotPanel();
//				JButton deleteButton = new JButton("Delete");
//
//				allTimeSlotPanels.add(timeSlotPanel);
////				deleteButtons.add(deleteButton);
//				deleteButton.addActionListener(deleteEventListener(frame,
//						timeSlotPanel, deleteButton));
//				addQueue.add(timeSlotPanel);
//
//				panel.add(timeSlotPanel);
//				panel.add(deleteButton, "wrap");
//
//				panel.repaint();
//				panel.revalidate();
//				scrollPane.revalidate();
//				frame.revalidate();
//
//			}
//		};
//	}

	private ActionListener deleteEventListener(final JPanel frame,
			final TimeSlotPanel timeSlotPanel, final JButton deletebutton) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panel.remove(timeSlotPanel);
				panel.remove(deletebutton);

				if (addQueue.contains(timeSlotPanel))
					addQueue.remove(timeSlotPanel);
				else
					removeQueue.add(timeSlotPanel);

				allTimeSlotPanels.remove(timeSlotPanel);
//				deleteButtons.remove(deletebutton);

				panel.repaint();
				panel.revalidate();
				scrollPane.revalidate();
				frame.revalidate();
			}
		};
	}

	/**
	 * Returns a list of time slots that need to be added to the database
	 * 
	 * @return TimeSlotList addQueue
	 */
//	public TimeSlotList getAddQueue() {
//		TimeSlotList list = new TimeSlotList();
//
//		for (TimeSlotPanel timeSlotPanel : addQueue) {
//			list.add(timeSlotPanel.getTimeSlot());
//		}
//
//		return list;
//	}

	/**
	 * Returns a list of time slots that need to be removed from the database
	 * 
	 * @return TimeSlotList removeQueue
	 */
//	public TimeSlotList getRemoveQueue() {
//		TimeSlotList list = new TimeSlotList();
//
//		for (TimeSlotPanel timeSlotPanel : removeQueue) {
//			list.add(timeSlotPanel.getTimeSlot());
//		}
//
//		return list;
//	}

	/**
	 * Returns a list of time slots that need to be updated in the database
	 * 
	 * @return TimeSlotList updateQueue
	 */
	public TimeSlotList getUpdatedQueue() {
		TimeSlotList list = new TimeSlotList();

		for (TimeSlotPanel timeSlotPanel : allTimeSlotPanels) {

			// make sure it is not in the add or remove queue
			if (!(addQueue.contains(timeSlotPanel) || removeQueue
					.contains(timeSlotPanel))) {
				// make sure it needs to be updated
//				if (!timeSlotPanel.isTimeSlotUpdated()) {
//					// get updated
//					list.add(timeSlotPanel.getTimeSlot());
//				}
			}
		}

		return list;
	}

	/*
	 * public TimeSlotList getTimeSlotList(){ TimeSlotList list = new
	 * TimeSlotList(); for (TimeSlotPanel timeSlotPanel: timeSlotPanels){
	 * list.add(timeSlotPanel.getTimeSlot()); } return list; } public void
	 * setTimeSlotList(TimeSlotList list){ Iterator<TimeSlot> i =
	 * list.iterator(); while(i.hasNext()){ this.timeSlotPanels.add(new
	 * TimeSlotPanel(i.next())); } }
	 */
}

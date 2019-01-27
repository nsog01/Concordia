package presentation.views.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import domain.events.TimeSlot;
import domain.events.TimeSlotList;
import net.miginfocom.swing.MigLayout;


/**
 * 
 * This panel adds/deletes time slots
 *
 */
public class TimeSlotPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5494273590656061707L;
	
	private DateFormat dateFormat;
	private TimeSlotSelector startTime;
	private TimeSlotSelector endTime;
	private JFormattedTextField txtLimit;
	private JButton addButton;
	private JPanel panel;
//	private TimeSlot timeSlot;
	private TimeSlotList timeSlotList;
	private DefaultTableModel model;
	
	//KEEPS TRACK OF MODEL ROWS THAT REPRESENT TIMESLOTS
	List <TimeSlot> modelData;
	
	private JTable timeSlotsTable;
	private DeleteCellRenderer deleteCellRenderer;
	private ButtonEditor buttonEditor;
	private TimeSlotList addQueue;
	private TimeSlotList removeQueue;
	private TimeSlotList updateQueue;
	
	/* Copy of the time slot kept to be able to track changes */
	private TimeSlotList oldTimeSlotList;
	
	public TimeSlotPanel() {
		this.timeSlotList = new TimeSlotList();
		this.oldTimeSlotList = timeSlotList;

		addQueue = new TimeSlotList();
		removeQueue = new TimeSlotList();
		updateQueue = new TimeSlotList();
		this.addComponents();
		

	}
	
	
	public TimeSlotList getTimeSlotList() {
		return timeSlotList;
	}


	public void setTimeSlotList(TimeSlotList timeSlotList) {
		this.timeSlotList = timeSlotList;
	}


	public TimeSlotList getOldTimeSlotList() {
		return oldTimeSlotList;
	}


	public void setOldTimeSlotList(TimeSlotList oldTimeSlotList) {
		this.oldTimeSlotList = oldTimeSlotList;
	}


	public TimeSlotPanel(TimeSlotList timeSlotList){	
		
		this.timeSlotList = timeSlotList;
		this.oldTimeSlotList = new TimeSlotList(timeSlotList);
		
		addQueue = new TimeSlotList();
		removeQueue = new TimeSlotList();
		updateQueue = new TimeSlotList();
		this.addComponents();

	}
	private void addComponents() {

		dateFormat = new SimpleDateFormat("KK:mm");
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		panel = new JPanel(new MigLayout());
		
		String timeSlotPanelStartText="Start";
		JLabel lblStartTime = new JLabel(timeSlotPanelStartText);
		startTime = new TimeSlotSelector();

		String timeSlotPanelEndText="End";
		JLabel lblEndTime = new JLabel (timeSlotPanelEndText);
		endTime = new TimeSlotSelector();
		
		String timeSlotPanelLimitText="Limit";
		JLabel lblLimit = new JLabel(timeSlotPanelLimitText);	
		
		txtLimit = new JFormattedTextField();
        txtLimit.setValue(new Integer(0));
        txtLimit.setColumns(10);
        
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {

		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TimeSlot timeSlot = new TimeSlot(startTime.getTime(), endTime.getTime(), Integer.parseInt(txtLimit.getText()));
				String start = dateFormat.format(timeSlot.getStartTime());
				String end = dateFormat.format(timeSlot.getEndTime());
				Vector row = new Vector();
				row.add(start);
				row.add(end);
				row.add(timeSlot.getLimit());
				row.add("Delete");
				row.add(timeSlot.getId());
				model.addRow(row);
				modelData.add(timeSlot);
				//if(addQueue==null)
				//	System.out.println("add queue is null");
				addQueue.add(timeSlot);
			}
        });
		
		panel.add(lblStartTime, "split 7");
		panel.add(startTime.getTimeSpinner());	
		panel.add(lblEndTime);
		panel.add(endTime.getTimeSpinner());
		panel.add(lblLimit);
		panel.add(txtLimit,"width 50:50:100");
		panel.add(addButton);
		
		this.add(panel);
		
		model = new DefaultTableModel();
		model.addColumn("Start");
		model.addColumn("End");
		model.addColumn("Limit");
		model.addColumn("");
		
		modelData = new ArrayList<TimeSlot>();
		Vector row2;
		/*
		 * Populate model with current time slots
		 * 
		 */
		for(TimeSlot timeslot:timeSlotList.getList())
		{
			row2 = new Vector();
			row2.add(dateFormat.format(timeslot.getStartTime()));
			row2.add(dateFormat.format(timeslot.getEndTime()));
			row2.add(timeslot.getLimit());
			row2.add("Delete");
			model.addRow(row2);
			modelData.add(timeslot);
		}
		
		timeSlotsTable = new JTable(model);// {
		deleteCellRenderer = new DeleteCellRenderer();
		timeSlotsTable.getColumn("").setCellRenderer(deleteCellRenderer);
		buttonEditor = new ButtonEditor(new JCheckBox());
		timeSlotsTable.getColumn("").setCellEditor(buttonEditor);
		
		buttonEditor.button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final int rowIndex = timeSlotsTable.getSelectedRow();
				TimeSlot timeSlot = null;
				Date start, end;
				int limit;
				/*
				try {
					start = dateFormat.parse(model.getValueAt(rowIndex, 0).toString());
					end = dateFormat.parse(model.getValueAt(rowIndex, 1).toString());
					limit = Integer.parseInt(model.getValueAt(rowIndex, 2).toString());
				
					timeSlot = new TimeSlot(start, end, limit);
				} catch (NumberFormatException e) {
					System.out.println("The limit retrieved from the table is not an interger.");
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("The time retrieved from the table is not in a proper format");
					e.printStackTrace();
				}

				removeQueue.add(timeSlot);*/
				TimeSlot t = modelData.get(rowIndex);
				if( t != null)
				{
					//DOES IT ALREADY EXIST IN DATABASE?
					if(t.getId()!=0)
						removeQueue.add(t);
				}
					
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						model.removeRow(rowIndex);
						modelData.remove(rowIndex);
					}
				});


			}
		});
		
		JScrollPane scrollPane = new JScrollPane(timeSlotsTable);
		timeSlotsTable.setFillsViewportHeight(true);
		timeSlotsTable.setRowHeight(40);
		this.add(scrollPane);
	}
	
	public Date getStartTime()
	{	
		return startTime.getTime();
	}
	
//	public TimeSlot getTimeSlot(){
//		//update time slot first
//			timeSlot.setStartTime(this.getStartTime());
//			timeSlot.setEndTime(this.getEndTime());
//			timeSlot.setLimit(this.getLimit());
//			
//			
//			return timeSlot;
//	}
	
	/**
	 * Returns a list of time slots that need to be added to the database
	 * 
	 * @return TimeSlotList addQueue
	 */
	public TimeSlotList getAddQueue() {
		return addQueue;
	}

	/**
	 * Returns a list of time slots that need to be removed from the database
	 * 
	 * @return TimeSlotList removeQueue
	 */
	public TimeSlotList getRemoveQueue() {
		return removeQueue;
	}

	/**
	 * Returns a list of time slots that need to be updated in the database
	 * 
	 * @return TimeSlotList updateQueue
	 */
	public TimeSlotList getUpdatedQueue() {
		updateQueue = new TimeSlotList();
		/*
		int i = 0;
		for (TimeSlot timeSlot : timeSlotList) {

			// make sure it is not in the add or remove queue
			if (!(addQueue.contains(timeSlot) || removeQueue.contains(timeSlot))) {
				// make sure it needs to be updated
				if (!this.isTimeSlotUpdated(timeSlot, i)) {
					// get updated
					updateQueue.add(timeSlot);
				}
			}
		}*/

		return updateQueue;
	}
	
	/**
	 * Compares two instances of the same timeslot, decides whether it needs to be updated
	 * @param TimeSlot timeslot1
	 * @param TimeSlot timeslot2
	 * @return Boolean
	 */
	public boolean isTimeSlotUpdated(TimeSlot ts, int index) {
		//update time slot first
		ts.setStartTime(this.getStartTime());
		ts.setEndTime(this.getEndTime());
		ts.setLimit(this.getLimit());
		
		if ((ts.getStartTime()).equals(oldTimeSlotList.get(index).getStartTime())
				&& (ts.getEndTime()).equals(oldTimeSlotList.get(index).getEndTime())
				&& (ts.getLimit() == oldTimeSlotList.get(index).getLimit()))
			return true;
		else
			return false;
	}
	
	public Date getEndTime() {	
		return endTime.getTime();
	}
	
	public void setStartTime(Date start) {	
		startTime.setTime(start);
	}
	
	public void setEndTime(Date end) {	
		endTime.setTime(end);
	}	
	
	public int getLimit() {
		return ((Number)txtLimit.getValue()).intValue();
	}

	public void setLimit(int limit) {
		txtLimit.setValue(limit);
	}
}
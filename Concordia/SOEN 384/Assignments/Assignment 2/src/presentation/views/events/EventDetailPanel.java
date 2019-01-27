package presentation.views.events;

import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

import domain.events.Event;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

public class EventDetailPanel extends JPanel{
	private JLabel lbl_Title;
	private JLabel title;
	private JLabel lblDate;
	private JLabel date;
	private JLabel lblEventType;
	private JLabel eventType;
	private JLabel lblDescription;
	private static final long serialVersionUID = 47389043214231L;
	private JTextField textField;
	
	public EventDetailPanel() {
		EtchedBorder border = new EtchedBorder(EtchedBorder.LOWERED, null, null);
		String eventDetailBorderText= "Event Details";
		setBorder(BorderFactory.createTitledBorder(border,eventDetailBorderText ));
		setLayout(new MigLayout("", "[143.00,grow][143.00]", "[][][][][]"));
		
		String eventDetailTitleText="Title";
		lbl_Title = new JLabel(eventDetailTitleText);
		add(lbl_Title, "cell 0 0");
		
		title = new JLabel();
		add(title, "cell 1 0");
		
		String eventDetailDateText="Date";
		lblDate = new JLabel(eventDetailDateText);
		add(lblDate, "cell 0 1");
		
		date = new JLabel();
		add(date, "cell 1 1");
		
		String eventDetailEventTypeText="Event Type";
		lblEventType = new JLabel(eventDetailEventTypeText);
		add(lblEventType, "cell 0 2");
		
		eventType = new JLabel();
		add(eventType, "cell 1 2");
		
		String eventDetailDescText="Description";
		lblDescription = new JLabel(eventDetailDescText);
		add(lblDescription, "cell 0 3");
		
		textField = new JTextField();
		textField.setBackground(UIManager.getColor("control"));
		textField.setBorder(null);
		textField.setEditable(false);
		add(textField, "cell 0 4 2 2,growx, growy, spany");
		textField.setColumns(10);
		
	}



	public void set(Event event) {
		title.setText(event.getTitle());
		SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yy");
		date.setText(format.format(event.getDate()));
		eventType.setText(event.getEventType().getName());
		textField.setText(event.getDescription());
	}

	public void setFields(Event event) {
		// TODO Auto-generated method stub
		
	}

}

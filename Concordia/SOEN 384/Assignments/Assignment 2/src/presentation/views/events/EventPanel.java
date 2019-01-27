package presentation.views.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.DatePicker;
import presentation.models.events.EventTypeBoxModel;

import domain.events.Event;
import domain.events.EventType;
import net.miginfocom.swing.MigLayout;

public class EventPanel extends JPanel {

	private static final long serialVersionUID = 7894302462L;
	private JTextField name;
	private EventTypeComboBox typeBox;
	private JTextField description;
	private JTextField date;
	private Date datePicked;
	String eventPanelBorderText="Event Details";
	
	public EventPanel() {
		this.setLayout(new MigLayout());
		this.addComponents();
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				eventPanelBorderText));
	}

	public EventPanel(Event event) {
		this.datePicked = event.getDate();
		this.setLayout(new MigLayout());
		this.addComponents();
		setFields(event);
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				eventPanelBorderText));
		
	}

	private void addComponents(){
		String eventPanelNameText="Event Name";
		JLabel lblEventName = new JLabel(eventPanelNameText);
		name = new JTextField();

		String eventPanelEventTypeText="Event Type";
		JLabel lblEventType = new JLabel(eventPanelEventTypeText);
		typeBox = new EventTypeComboBox(new EventTypeBoxModel());

		String eventPanelDescText="Description";
		JLabel lblDescription = new JLabel(eventPanelDescText);
		description = new JTextField();
		this.add(lblEventName, "grow");
		this.add(name, "wrap, grow, span");
		this.add(lblEventType, "grow");
		this.add(typeBox, "wrap, grow, span");
		this.add(lblDescription, "grow");
		this.add(description, "wrap, grow, span");

		String eventPanelSelDate="Selected Date:";
		JLabel label = new JLabel(eventPanelSelDate);
		date = new JTextField();
		date.setEditable(false);
		String eventPanelSelDateButtonText="Select Date";
		JButton setDate = new JButton(eventPanelSelDateButtonText);
		setDate.setToolTipText("Alt+S");
		setDate.setMnemonic(KeyEvent.VK_S);
		setDate.addActionListener(dateEventListener());
		this.add(label);
		this.add(date, "grow, span, wrap");
		this.add(setDate);
	}

	public void setFields(Event event) {
		if (event != null) {
			this.description.setText(event.getDescription());
			this.name.setText(event.getTitle());
			this.typeBox.getBoxModel().setSelectedType(
					event.getEventType().getId());
			this.date.setText(event.getDate().toString());
		} else {
			((JFrame) this.getParent().getParent()).dispose();
		}
	}

	public void clearFields() {
		this.name.setText(null);
		this.typeBox.setSelectedIndex(0);
		this.description.setText(null);
	}

	public Date getDatePicked() {
			return datePicked;

	}

	public void setDate(String date) {
		this.date.setText(date);
	}

	public String getEventName(){
		return this.name.getText();
	}
	public EventType getEventType() {
		return typeBox.getBoxModel().getSelected();
	}

	public String getDescription() {
		return this.description.getText();
	}

	private ActionListener dateEventListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatePicker dp = new presentation.DatePicker(new JFrame());
				date.setText(dp.setPickedDate());
				datePicked = dp.getPickedDate();
			}
		};
	}

	public boolean checkFields() {
		return (this.name.getText().length() > 0 && this.description.getText()
				.length() > 0);
	}
}
package presentation.views.appointments;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presentation.controllers.appointments.AppointmentCreationFrameController;
import presentation.views.events.EventTableScrollPane;
import presentation.views.events.TimeSlotTableScrollPane;
import domain.events.EventManager;
import domain.files.Client;

import net.miginfocom.swing.MigLayout;

public class AppointmentCreationFrame extends JFrame{

	private static final long serialVersionUID = 5487905732489054L;
	private AppointmentCreationFrameController controller;
	
	public AppointmentCreationFrame(Client client){
		controller = new AppointmentCreationFrameController(this);
		init();
		addComponents();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		controller.setListeners(client);
	}
	private void addComponents() {
		add(controller.getSelection(), "w 60, split 2");
		add(controller.getSubmit(), "wrap");
		add(controller.getEventTableScrollPane(), "w 800");
	}
	private void init(){
		String makeAptTitleText="Make Appointment";
		JLabel title = new JLabel(makeAptTitleText);
		title.setFont(new Font("", Font.BOLD, 22));
		setLayout(new MigLayout());
		add(title, "wrap 10, span, align center");
		String makeAptInstructionsText="Please select an event from the list below.";
		JLabel instructions = new JLabel(makeAptInstructionsText);
		title.setFont(new Font("", Font.BOLD, 20));
		add(instructions, "wrap 10, span, align center");
		controller.setEventTableScrollPane(EventTableScrollPane.getNewInstance(
				EventManager.getUniqueInstance().getEventList()));
		controller.setSelection(new JTextField());
		controller.setSubmit(new JButton("select"));
		getRootPane().setDefaultButton(controller.getSubmit());
	}
	public EventTableScrollPane getEventTableScrollPane() {
		return controller.getEventTableScrollPane();
	}
	public JTextField getSelection(){
		return controller.getSelection();
	}
	public void setTimeSlotTableScrollPane(TimeSlotTableScrollPane timeSlotTableScrollPane) {
		controller.setTimeSlotTableScrollPane(timeSlotTableScrollPane);
	}
	public TimeSlotTableScrollPane getTimeSlotTableScrollPane() {
		return controller.getTimeSlotTableScrollPane();
	}
	public void addSelectAction(MouseListener actionListener){
		controller.addSelectAction(actionListener);
	}
	public void addSubmitAction(ActionListener action){
		controller.addSubmitAction(action);
	}
}

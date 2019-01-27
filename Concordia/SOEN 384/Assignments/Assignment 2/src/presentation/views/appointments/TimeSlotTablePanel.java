package presentation.views.appointments;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class TimeSlotTablePanel extends JPanel{
	
	private static final long serialVersionUID = 748392043267L;
	private JLabel instructions;

	
	public TimeSlotTablePanel(){
		setLayout(new MigLayout("", "", "[200][]"));
		String appointmentTimeInstructionsText="Please select the appointment time";
		instructions = new JLabel(appointmentTimeInstructionsText);
		instructions.setHorizontalAlignment(JLabel.CENTER);
		instructions.setFont(new Font("", Font.BOLD, 15));
		add(instructions, "center, wrap");
	}


	
}

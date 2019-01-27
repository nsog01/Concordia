package presentation.views.appointments;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import net.miginfocom.swing.MigLayout;

public class AppointmentCheckOutPanel extends JPanel{


	private static final long serialVersionUID = 7483901243214789L;
	private JTextField scan;
	private JLabel label;
	
	public AppointmentCheckOutPanel(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(screen);
		setLayout(new MigLayout("", "["+screen.width+"]", ""));
		JPanel inner = new JPanel(new MigLayout());
		String checkoutBorderText="Check-Out";
		inner.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),checkoutBorderText ));
		scan = new JTextField();
		scan.setHorizontalAlignment(JTextField.CENTER);
		String scanBadgeText="Scan Badge to Check-Out";
		label = new JLabel(scanBadgeText);
		label.setFont(new Font("", Font.BOLD, 15));
		inner.add(label, "align center, wrap");
		inner.add(scan, "align center, w 400, span");
		add(inner, "alignx center, spanx");
	}
	public JTextField getScan(){
		return scan;
	}
	
}

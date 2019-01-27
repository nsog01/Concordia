package presentation.views.files;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;


public class AddFlagAlert extends JFrame {

	private static final long serialVersionUID = 4321578902L;
	private JTextArea detail;
	private JButton ok;
	private ActionListener okAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	public AddFlagAlert(){
		this.initializeComponenets();
		this.addComponents();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}
	public void initializeComponenets(){
		String flagAlertDetailText="Sorry, this action cannot be completed at this time. Please open a household file before adding a flag.";
		String flagAlertOkText="OK";
		
		
		this.setLayout(new BorderLayout()); 
		this.detail = new JTextArea(flagAlertDetailText);
		detail.setRows(4);
		this.detail.setLineWrap(true);
		detail.setEditable(false);
		this.detail.setWrapStyleWord(true);
		
		this.ok = new JButton(flagAlertOkText);
		getRootPane().setDefaultButton(ok);
	}
	public void addComponents(){
		String addFlagEmptyPanelText="Adding a flag without selecting a file";
		add(new EmptyBannerPanel(addFlagEmptyPanelText), BorderLayout.NORTH);

		this.detail.setBackground(getContentPane().getForeground());
		add(this.detail, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel(new MigLayout("", "[right, grow]", ""));
		buttonPanel.add(this.ok, "align right");
		this.ok.addActionListener(okAction);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
}

package presentation.views.files;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

import domain.files.FileManager;
import domain.files.Flag;
import domain.files.FlagImageMap;
import domain.files.Household;

public class AddFlagFrame extends JFrame{

	private static final long serialVersionUID = 43243216525L;
	private Flag flag;
	private JLabel title;
	private ImageIcon icon;
	private JTextArea note;
	private JButton submit;

	public AddFlagFrame(Flag flag) {
		submit = new JButton();
		submit.setAction(action);
		setLayout(new MigLayout("", "10[][500]10", "[][][200][]10"));
		add(new EmptyBannerPanel(flag.getFlag()), "north");
		this.flag = new Flag(flag.getFlag(), null, flag.getId());
		icon = FlagImageMap.getFlagImage(this.flag);
		String addFlagCommentsText="Enter Comments:";
		title = new JLabel(addFlagCommentsText);		
		note = new JTextArea();
		add(new JLabel(icon), "wrap");
		add(title, "wrap");
		add(note, "growx, growy, span, wrap");
		add(submit);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		getRootPane().setDefaultButton(submit);
	}
	
	private String addFlagSubmitActionButtonText="Submit";
	private AbstractAction action = new AbstractAction(addFlagSubmitActionButtonText) {

		private static final long serialVersionUID = 433214321L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			flag.setNote(note.getText());
			Household house = FileManager.getUniqueInstance().getActiveFile();
			house.addFlag(flag);
			house.commit();
			dispose();
		}
	};
}

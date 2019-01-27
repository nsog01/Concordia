package presentation.views.files;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import domain.files.FileManager;
import domain.files.Flag;

public class EditFlagFrame extends JFrame{
	
	private static final long serialVersionUID = 478904321L;
	private Flag flag;
	private EmptyBannerPanel banner;
	private JPanel buttons;
	private JButton delete;
	private JButton submit;
	private JTextArea note;
	private String editFlagFrameSubmitText="Submit";
	private AbstractAction submitAction = new AbstractAction(editFlagFrameSubmitText) {

		private static final long serialVersionUID = 4378940321423L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			flag.setNote(note.getText());
			flag.commit();
			dispose();
		}
	};
	private String editFlagFrameDeleteText="Delete";
	private AbstractAction deleteAction = new AbstractAction(editFlagFrameDeleteText) {

		private static final long serialVersionUID = 7489034321432L;

		@Override
		public void actionPerformed(ActionEvent e) {
			FileManager.getUniqueInstance().getActiveFile().getFlags().remove(flag);
			flag.remove();
			flag.commit();
			dispose();
		}
	};
	
	
	public EditFlagFrame(Flag addFlag){
		this.flag = addFlag;
		init();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width/3, screen.height/4);
		addComponents();
		pack();
		setVisible(true);
		getRootPane().setDefaultButton(submit);
	}
	
	private void init(){
		banner = new EmptyBannerPanel(flag.getFlag());
		note = new JTextArea(flag.getNote());
		note.setRows(4);
		note.setColumns(40);
		note.setWrapStyleWord(true);
		submit = new JButton();
		submit.setAction(submitAction);
		delete = new JButton();
		delete.setAction(deleteAction);
		buttons = new JPanel();
	}
	
	private void addComponents(){
		add(banner, BorderLayout.NORTH);
		add(note, BorderLayout.CENTER);
		buttons.add(submit, BorderLayout.EAST);
		buttons.add(delete, BorderLayout.WEST);
		add(buttons, BorderLayout.SOUTH);
	}
	

}

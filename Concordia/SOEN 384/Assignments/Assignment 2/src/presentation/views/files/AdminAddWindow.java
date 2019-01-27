package presentation.views.files;


import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AdminAddWindow extends JFrame{

	private static final long serialVersionUID = 78493201432L;
	private JButton submit;
	public AdminAddWindow()
	{
		submit = new JButton("");
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/6, 
				(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3);
		this.setTitle("Administration Page");
	}
		
	public void addSubmitListener(ActionListener action){
		submit.addActionListener(action);
	}

	public JButton getSubmitButton(){
		return submit;
	}


}

package presentation.views.files;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ClientInfoWindow extends JFrame{

	private static final long serialVersionUID = 78493201432L;
	private JButton submit;
	public ClientInfoWindow()
	{
		String clientInfoWindowSubmitText="Submit";
		submit = new JButton(clientInfoWindowSubmitText);
		this.setLocationRelativeTo(null);
		String clientInfoWindowTitleText="Client Form Page";
		this.setTitle(clientInfoWindowTitleText);
	}
		
	public void addSubmitListener(ActionListener action){
		submit.addActionListener(action);
	}

	public JButton getSubmitButton(){
		return submit;
	}


}

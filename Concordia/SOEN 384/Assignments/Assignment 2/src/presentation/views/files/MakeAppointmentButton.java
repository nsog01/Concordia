package presentation.views.files;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import presentation.views.appointments.AppointmentCreationFrame;

import domain.files.Client;

public class MakeAppointmentButton extends JButton{


	private static final long serialVersionUID = 78490324231L;

	public MakeAppointmentButton(Client client) {
		this.setAction(submit(client));
	}
	
	AbstractAction submit(final Client client){
		String makeAptButtonTitleText="Make Appointment";
		return new AbstractAction(makeAptButtonTitleText){

			private static final long serialVersionUID = 4378940321432L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AppointmentCreationFrame(client);
			}
		
	};
	}

}

package presentation.controllers.appointments;

import javax.swing.table.TableModel;

import domain.events.Event;

import presentation.views.appointments.AppointmentListPanel;

public class AppointmentListPanelController {

	private AppointmentListPanel view;
	
	
	public AppointmentListPanelController(TableModel model){
		view = new AppointmentListPanel(model);
		
//		this.appointmentWindowController = appointmentWindowController;
//		view.addKeyListenerToSearchField(search());
//		view.addMouseListenerToTable(selectAppointment());
	}
	
//	public KeyListener search(){
//		return new KeyListener(){
//
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//
//			}
//
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				String search = view.getSearchText();
//				if (search.length() < prevSearch.length()){
//					((DetailedAppointmentTableModel)view.getTableModel()).backspaceSearch(search);
//				} else {
//					((DetailedAppointmentTableModel)view.getTableModel()).search(search);
//				}
//				prevSearch = search;
//			}
//
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//
//			}
//			
//		};
//	}
//	private MouseListener selectAppointment(){
//		return new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				if (e.getClickCount() == 2){
//					Appointment appointment = view.getTable().getSelected();
//					appointmentWindowController.setAppointmentFulFillmentScreen(appointment);
//					appointmentWindowController.getView().getTabPane().setSelectedIndex(3);
//				}
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//	}
	public AppointmentListPanel getView(){
		return view;
	}

	public void setEvent(Event event) {
		view.setEventPanel(event);
	}
}

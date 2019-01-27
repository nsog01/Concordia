package presentation.views.appointments;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import presentation.views.events.EventDetailPanel;

import domain.Appointments.AppointmentList;
import domain.events.Event;

import net.miginfocom.swing.MigLayout;

public class AppointmentListPanel extends JPanel implements TableModelListener{

	private static final long serialVersionUID = 78904327890L;
	private DetailedAppointmentTable  table;
	private TableModel model;
	private JScrollPane sPane;
	private JTextField search;
	private JLabel arrivedLabel;
	private JLabel colon;
	private JLabel absentLabel;
	private EventDetailPanel eventPanel;
	private JPanel arrivedAbsentPanel;
	
	public AppointmentListPanel(TableModel model){
		this.model = model;
		init();
		addComponents();
		setVisible(true);
	}
	
	private void init(){
		search = new JTextField();
		table = new DetailedAppointmentTable(model);
		model.addTableModelListener(this);
		
		
		
		arrivedAbsentPanel = new JPanel();
		EmptyBorder border = new EmptyBorder(0, 0, 0, 0);
		String attendanceText="Attendance";
		arrivedAbsentPanel.setBorder(BorderFactory.createTitledBorder(border,attendanceText ));
		int[] arrivedAndAbsent = table.getArrivedAndAbsent();
		colon = new JLabel(":");
		colon.setFont(new Font("serif", Font.BOLD, 60));
		arrivedLabel = new JLabel(Integer.toString(arrivedAndAbsent[0]));
		arrivedLabel.setForeground(Color.GREEN);
		arrivedLabel.setFont(new Font("", Font.BOLD, 60));
		absentLabel = new JLabel(Integer.toString(arrivedAndAbsent[1]));
		absentLabel.setForeground(Color.RED);
		absentLabel.setFont(new Font("", Font.BOLD, 60));
		arrivedAbsentPanel.add(arrivedLabel);
		arrivedAbsentPanel.add(colon);
		arrivedAbsentPanel.add(absentLabel);		
		
		sPane = new JScrollPane(table);
		eventPanel = new EventDetailPanel();
		setLayout(new MigLayout("", "[:"+Toolkit.getDefaultToolkit().getScreenSize().width+":]",
				"[][][:"+Toolkit.getDefaultToolkit().getScreenSize().height*.7+"]:"));
	}
	
	private void addComponents(){
		add(eventPanel, "align center, wrap");
		eventPanel.add(arrivedAbsentPanel, "cell 2 0 1 4");
		add(search, "split 2, w 400, h 30, wrap");
		add(sPane, "span, growx, growy");
	}
	public String getSearchText(){
		return search.getText();
	}
	public TableModel getTableModel(){
		return model;
	}
	public DetailedAppointmentTable getTable() {
		return table;
	}

	public void setTable(DetailedAppointmentTable table) {
		this.table = table;
	}

	public JTextField getSearch() {
		return search;
	}

	public void addKeyListenerToSearchField(KeyListener listener){
		search.addKeyListener(listener);
	}
	public void addMouseListenerToTable(MouseListener listener){
		table.addMouseListener(listener);
	}
	@Override
	public void tableChanged(TableModelEvent arg0) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				int[] arrivedAndAbsent = table.getArrivedAndAbsent();
				arrivedLabel.setText(Integer.toString(arrivedAndAbsent[0]));
				absentLabel.setText(Integer.toString(arrivedAndAbsent[1]));
			}
		});

	}

	public void set(AppointmentList list) {
		eventPanel.set(list.get(0).getEvent());
		table.setList(list);
	}

	public void setEventPanel(Event event) {
		eventPanel.setFields(event);
	}
}

package presentation.views.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import domain.events.Event;
import domain.events.Event.Period;

import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;
import javax.swing.BoxLayout;

public class OccurrencesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 54327895432L;
	private JRadioButton noneButton;
	@SuppressWarnings("unused")
	private JRadioButton dailyButton;
	private JRadioButton weeklyButton;
	private JRadioButton monthlyButton;
	private JRadioButton yearlyButton;
	private JPanel radioPanel;
	private Period period;
	private JLabel occurrenceCountLabel;
	private JLabel occurrenceCountLabelTrailer;
	private JTextField occurrenceCount;
	private ButtonGroup group;
	private JSeparator separator;
	private String recurrenceBorderText="Recurrence Pattern";
	
	public OccurrencesPanel(){
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,recurrenceBorderText ));
		this.initializeComponents();
		this.setEnabled();
	}
	
	public OccurrencesPanel(Event event){
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(loweredetched, recurrenceBorderText));
		this.initializeComponents();
		this.setSelected();
		this.setOccurrenceCount(event);
		this.setEnabled();
	}
	private void initializeComponents(){
		radioPanel = new JPanel();
		radioPanel.setLayout(new MigLayout());
//		this.dailyButton = new JRadioButton("Daily");
//		this.dailyButton.addActionListener(this.dailyListener(this));
//		this.add(dailyButton, "wrap");
		String noneButtonText="None";
		this.noneButton = new JRadioButton(noneButtonText);
		this.noneButton.addActionListener(this.noneListener(this));
		radioPanel.add(noneButton, "wrap");
		String weeklyButtonText="Weekly";
		this.weeklyButton = new JRadioButton(weeklyButtonText);
		this.weeklyButton.addActionListener(this.weeklyListener(this));
		radioPanel.add(weeklyButton, "wrap");
		String monthlyButtonText="Monthly";
		this.monthlyButton = new JRadioButton(monthlyButtonText);
		this.monthlyButton.addActionListener(this.monthlyListener(this));
		radioPanel.add(monthlyButton, "wrap");
		String yearlyButtonText="Yearly";
		this.yearlyButton = new JRadioButton(yearlyButtonText);
		this.yearlyButton.addActionListener(this.yearlyListener(this));
		radioPanel.add(yearlyButton, "wrap");
		this.group = new ButtonGroup();
//		this.group.add(dailyButton);
		this.group.add(noneButton);
		this.group.add(monthlyButton);
		this.group.add(weeklyButton);
		this.group.add(yearlyButton);
		this.group.setSelected(noneButton.getModel(), true);
		this.period = Period.NONE;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(radioPanel);
		JPanel limitPanel = new JPanel();
		limitPanel.setLayout(new MigLayout());
		String occurrenceCountLabelText="End after";
		this.occurrenceCountLabel = new JLabel(occurrenceCountLabelText);
		this.occurrenceCount = new JTextField();
		String occurrenceCountLabelTrailerText="occurrences";
		this.occurrenceCountLabelTrailer = new JLabel(occurrenceCountLabelTrailerText);
		
		separator = new JSeparator(JSeparator.VERTICAL);
		add(separator);
		
		limitPanel.add(this.occurrenceCountLabel);
		limitPanel.add(this.occurrenceCount, "w 40!");
		limitPanel.add(this.occurrenceCountLabelTrailer);
		
		this.add(limitPanel);
	}
	
	private void setSelected(){
		if (period == Period.WEEK){
			this.group.setSelected(weeklyButton.getModel(), true);
		}
		else if (period == Period.MONTH){
			this.group.setSelected(monthlyButton.getModel(), true);
		}
		else if (period == Period.YEAR){
			this.group.setSelected(yearlyButton.getModel(), true);
		}
		else {
			this.group.setSelected(noneButton.getModel(), true);
		}
	}
	void setPeriod(Period period){
		this.period = period;
		this.setSelected();
		this.setEnabled();
	}
	private void setEnabled(){
		if (this.getPeriod() == Period.NONE){
			this.occurrenceCount.setEnabled(false);
			this.occurrenceCountLabel.setEnabled(false);
		}
		else {
			this.occurrenceCount.setEnabled(true);
			this.occurrenceCountLabel.setEnabled(true);
		}
	}
	private void setOccurrenceCount(Event event){
		this.occurrenceCount.setText(Integer.toString(event.getOid()));
	}
	public Period getPeriod(){
		return this.period;
	}
	public int getOccurrenceCount(){
		if (this.period != Period.NONE)
			return Integer.parseInt(this.occurrenceCount.getText());
		else return 1;
	}
	
	private ActionListener noneListener(final OccurrencesPanel panel){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				period = Period.NONE;
				panel.setEnabled();
			}
			
		};
	}
	@SuppressWarnings("unused")
	private ActionListener dailyListener(final OccurrencesPanel panel){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				period = Period.NONE;
				panel.setEnabled();
			}
			
		};
	}
	private ActionListener weeklyListener(final OccurrencesPanel panel){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				period = Period.WEEK;
				panel.setEnabled();
			}
			
		};
	}
	private ActionListener monthlyListener(final OccurrencesPanel panel){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				period = Period.MONTH;
				panel.setEnabled();
			}
			
		};
	}
	private ActionListener yearlyListener(final OccurrencesPanel panel){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				period = Period.YEAR;
				panel.setEnabled();
			}
			
		};
	}

}
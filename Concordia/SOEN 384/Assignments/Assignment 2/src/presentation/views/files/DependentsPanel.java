package presentation.views.files;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import presentation.controllers.files.DependentsPanelController;
import presentation.views.menus.LanguageSingleton;
import presentation.views.menus.LocaleChangeEvent;
import presentation.views.menus.LocaleChangeListener;

import net.miginfocom.swing.MigLayout;

import domain.files.Client;
import domain.files.ClientList;

public class DependentsPanel extends JPanel implements Observer, LocaleChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4783902432L;
	private JPanel inner;
	private JButton addDependent;
	private DependentsPanelController controller;
	private boolean addedToObserver = false;
	private ClientList clientList;
	private List<DependentButton> buttons;
	private JScrollPane scrollPane;
	private Border loweredetched; 

	public DependentsPanel() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension((int)(screenSize.width/3), (int) (screenSize.height/1.5)));
		buttons = new ArrayList<DependentButton>();
		clientList = new ClientList();
		clientList.addObserver(this);
		addedToObserver = true;
		controller = new DependentsPanelController(this);
		setLayout(new MigLayout());
		loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		String depPanelBorderText="Household Members";
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				depPanelBorderText));
		
		LanguageSingleton.getUniqueInstance().addLocaleChangeListener(this);
	}

	public void set(ClientList list) {
		if (!addedToObserver) {	
			list.addObserver(this);
		}
		clientList.setList(list);
		if (clientList != null) {
			if (inner == null){
			inner = new JPanel(new MigLayout("", "[center, grow]", ""));
			}
			for (Client c : clientList) {
				if (!contains(c)){
					DependentButton b = new DependentButton(c, c.getRelation());
					buttons.add(b);
				inner.add(
						b,
						"wrap");
				}

			}
			for (DependentButton button : buttons){
				if (!clientList.contains(button.getClient())){
					inner.remove(button);
					button = null;
				}
			}

			if (addDependent == null){
				scrollPane = new JScrollPane(inner);
				scrollPane.setPreferredSize(getPreferredSize());
				scrollPane.setHorizontalScrollBar(null);
				scrollPane.setBorder(null);

			add(scrollPane, "wrap");
			String depPanelAddButtonText="Add Household Member";
			addDependent = new JButton(depPanelAddButtonText);
			if (addDependent.getActionListeners().length == 0){
			addDependent.addActionListener(this.controller
					.addDependentListener());
			}
			this.add(addDependent, "south");
			}
		}
		this.revalidate();
		this.repaint();
	}
	private void reset() {
		for (Client c : clientList) {
			if (!contains(c)){
				DependentButton b = new DependentButton(c, c.getRelation());
				buttons.add(b);
			inner.add(
					b,
					"wrap");
			}

		}
		for (DependentButton button : buttons){
			if (!clientList.contains(button.getClient())){
				inner.remove(button);
				button = null;
			}
		}
		this.revalidate();
		this.repaint();
	}

	public boolean contains(Client client){
		if (buttons.isEmpty()){
			return false;
		}
		for (DependentButton button : buttons){
			if (button.getClient().equals(client)){
				return true;
			}
		}
		return false;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		reset();
		revalidate();
		repaint();
	}



	public JButton getAddButton() {
		return addDependent;
	}

	public void removeAddButton() {
		remove(addDependent);
	}

	@Override
	public void localeChanged(LocaleChangeEvent e) {
		
		ResourceBundle messages= ResourceBundle.getBundle("MessagesBundle",e.getLocale());
		
		this.setBorder(BorderFactory.createTitledBorder(loweredetched,
				messages.getString("depPanelBorderText")));
		
		
		
	}
}

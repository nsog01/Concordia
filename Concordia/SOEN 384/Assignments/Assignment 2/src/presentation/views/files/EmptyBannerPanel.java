package presentation.views.files;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

public class EmptyBannerPanel extends JPanel {

	private static final long serialVersionUID = 43257849203534L;
	private JLabel label;
	private JButton home;
	private ImageIcon icon;
	
	public EmptyBannerPanel() {
		init();
		addComponents();
	}
	public EmptyBannerPanel(String title){
		init();
		addComponents();
		label.setText(title);
	}

	public void init() {
		icon = new ImageIcon("images/Cancel.png");
		setLayout(new MigLayout("", (icon.getIconWidth())+"[center, grow][]25", "[75]"));
		setBackground(new Color(51, 102, 153));
		label = new JLabel();
		label.setForeground(new Color(123, 195, 66));
		label.setFont(new Font("", Font.BOLD, 25));
		home = new JButton();
		home.setMaximumSize(new Dimension(icon.getIconHeight()+5, icon.getIconWidth()+5));
		home.setAction(home());
	}

	private void addComponents() {
		add(new JLabel(new ImageIcon("images/logo_fr.jpg")), "west");
		add(label, "align center");
		add(home, "east");

	}
	
	public void removeHomeButton(){
		remove(home);
	}
	public void setTitle(String title){
		label.setText(title);
	}
	
	private AbstractAction home(){
		return new AbstractAction(null, icon){

			private static final long serialVersionUID = 4572384903243L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, home);
				frame.dispose();
				frame = null;
			}
			
		};
	}
}


package presentation.views.files;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import domain.files.Flag;
import domain.files.FlagImageMap;

public class FlagButton extends JButton{

	private static final long serialVersionUID = 4789430214L;
	private Flag flag;
	private Icon icon;
	
	public FlagButton(Flag flag){
		this.flag = flag;
		icon = FlagImageMap.getFlagImage(flag);
		setAction(action());
		UIDefaults def = new UIDefaults();
		def.put("Button[Default].backgroundPainter", null);
		putClientProperty("Nimbus.Overrides", def);
		setBorderPainted(false);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		SwingUtilities.updateComponentTreeUI(this);
		EmptyBorder margin = new EmptyBorder(0, 0, 0, 0);
		setBorder(margin);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		}
	}
	
	public Flag getFlag(){
		return flag;
	}
	AbstractAction action(){
		String flagString = flag.getFlag();
		String[] flagSplit = flagString.split(" ");
		String flagLabel = "<HTML>"+flagSplit[0];
		for (int i = 1; i != flagSplit.length; i++){
			flagLabel += "<BR>"+flagSplit[i];
		}
		flagLabel += "<HTML>";
		return new AbstractAction(flagLabel, icon){

			private static final long serialVersionUID = 4839054523L;

			@Override
			public void actionPerformed(ActionEvent e) {
				new EditFlagFrame(flag);
			}
			
		};
	}
}

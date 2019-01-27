package driver;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.MatteBorder;

import presentation.views.files.ApplicationWindow;

public class WelcomeHall {

	public static void main(String[] args){
	    
		UIManager.put("nimbusBase", new Color(34,104,204));
		UIManager.put("Table.alternateRowColor", Color.WHITE);
		UIManager.put("Table.focusCellHighlightBorder", false);
		UIManager.put("Table.cellNoFocusBorder", new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		UIManager.put("nimbusSelectionBackground", new Color(0,104,204));
		UIManager.put("nimbusBlueGrey", new Color(242,242,242));
		UIManager.put("control", new Color(247,248,250));   
	    
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    
		}
		JFrame.setDefaultLookAndFeelDecorated(true);

			
			new ApplicationWindow();

	}
}

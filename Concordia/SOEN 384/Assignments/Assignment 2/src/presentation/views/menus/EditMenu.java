package presentation.views.menus;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

public class EditMenu extends JMenu implements LocaleChangeListener{
	
	private static final long serialVersionUID = -4523649162614243592L;

	
	EditMenu(){
		this.setText("Edit");
		this.setMnemonic(KeyEvent.VK_E);
		this.getAccessibleContext().setAccessibleDescription(
				"This menu does nothing");
		
	}


	@Override
	public void localeChanged(LocaleChangeEvent e) {
		
		
		System.out.println("ad");
		
	}

	
}

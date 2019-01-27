package presentation.views.menus;

import java.util.EventObject;
import java.util.Locale;

public class LocaleChangeEvent extends EventObject {
	
	
	private static final long serialVersionUID = -9133746805291053683L;
	private Locale locale;

	public LocaleChangeEvent(Object source){
		
		super(source);
	}
	
	public LocaleChangeEvent(Object source, Locale locale){
		
		super(source);
		this.locale=locale;
	}
	
	public Locale getLocale(){
		
		return locale;
	}
	

}

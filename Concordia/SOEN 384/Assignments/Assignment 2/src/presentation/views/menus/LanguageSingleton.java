package presentation.views.menus;

import java.util.ArrayList;
import java.util.Iterator;

public class LanguageSingleton {
	
	
	private static LanguageSingleton uniqueInstance=null;
	private ArrayList<LocaleChangeListener> observers=new ArrayList<LocaleChangeListener>();
	
	
	private LanguageSingleton(){}
	
	
	
	public synchronized static LanguageSingleton getUniqueInstance(){
	if(uniqueInstance==null){
		uniqueInstance= new LanguageSingleton();
		
	}
	return uniqueInstance;
}
	
	
public synchronized void addLocaleChangeListener(LocaleChangeListener listener){
		
		observers.add(listener);
		
	}
	
	public synchronized void removeLocaleChangeListener(LocaleChangeListener listener){
		
		observers.remove(listener);
	}
	
	public void fireLocaleChangeEvent(LocaleChangeEvent e){
		
		Iterator<LocaleChangeListener> i= observers.iterator();
		
		while(i.hasNext()){
			
			LocaleChangeListener listener= i.next();
			listener.localeChanged(e);
		}	
		
	}

}

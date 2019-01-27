package technical;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.PropertyResourceBundle;

public class PropertiesBundle extends PropertyResourceBundle {

	private Hashtable<String, String> properties;
	public PropertiesBundle(InputStream arg0) throws IOException {
		super(arg0);
		
	}
	public String getProperty(String key){
		return properties.get(key);
	}
	

}

package technical;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

/**
 * @author paulsmelser
 */
public class Registry {
	/**
	 * @uml.property name="instance"
	 * @uml.associationEnd
	 */
	private static Registry instance = null;
	private static final String fileName = "welcomeHall";
	private Hashtable<String, String> properties;

	public synchronized static Registry getUniqueInstance() throws IOException {
		if (instance == null)
			instance = new Registry();
		return instance;
	}

	public String getProperty(String key) {
		return properties.get(key);
	}

	protected Registry() throws IOException {
		properties = new Hashtable<String, String>();
		ResourceBundle properties = PropertiesBundle.getBundle(fileName);
		Enumeration<String> bundleKeys = properties.getKeys();
		while (bundleKeys.hasMoreElements()) {
			String key = (String) bundleKeys.nextElement();
			String value = properties.getString(key);
			this.properties.put(key, value);
		}
	}

}

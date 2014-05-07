package shootout.dm.dashboard;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;


public class ConfigCreator {
	
	private volatile ConfigurationAdmin configAdmin;
	
	public void start() throws IOException {
		Configuration config = configAdmin.getConfiguration("dm.dashboard");
		Dictionary<String, Object> props = new Hashtable<>();
		props.put("refreshinterval", 20000);
		config.update(props);
	}

}

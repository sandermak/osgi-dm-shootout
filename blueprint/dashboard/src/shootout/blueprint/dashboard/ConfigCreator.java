package shootout.blueprint.dashboard;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigCreator {
	
	private ConfigurationAdmin configAdmin;
	
	public void activate() throws IOException {
		Configuration config = configAdmin.getConfiguration("blueprint.dashboard");
		Dictionary<String, Object> props = new Hashtable<>();
		props.put("refreshinterval", 20000);
		config.update(props);
		System.out.println("Config updated!");
	}

	public synchronized void setConfigAdmin(ConfigurationAdmin configAdmin) {
		this.configAdmin = configAdmin;
	}
}

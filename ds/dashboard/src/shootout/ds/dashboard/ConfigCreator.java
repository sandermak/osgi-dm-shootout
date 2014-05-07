package shootout.ds.dashboard;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;


public class ConfigCreator {
	
	private ConfigurationAdmin configAdmin;
	
	protected void activate(ComponentContext context) throws IOException {
		Configuration config = configAdmin.getConfiguration("shootout.dashboard");
		Dictionary<String, Object> props = new Hashtable<>();
		props.put("refreshinterval", 20000);
		config.update(props);
	}

	public synchronized void setConfigAdmin(ConfigurationAdmin configAdmin) {
		this.configAdmin = configAdmin;
	}
}

package shootout.ipojo.dashboard;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

@Component
@Instantiate
public class ConfigCreator {
	
	@Requires
	private volatile ConfigurationAdmin configAdmin;
	
	@Validate
	public void start() throws IOException {
		System.out.println("ConfigCreator started");
		Configuration config = configAdmin.getConfiguration("ipojo.dashboard");
		Dictionary<String, Object> props = new Hashtable<>();
		props.put("refreshinterval", 20000);
		config.update(props);
	}

}

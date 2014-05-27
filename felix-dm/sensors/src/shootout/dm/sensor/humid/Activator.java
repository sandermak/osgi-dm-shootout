package shootout.dm.sensor.humid;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import shootout.dm.sensor.api.Sensor;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		Dictionary<String, String> props = new Hashtable<String, String>();
		props.put("sensor.type", "humidity");
		
		manager.add(createComponent()
				.setInterface(Sensor.class.getName(), props)
				.setImplementation(HumiditySensor.class)
		);
		
	}
	
	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
		
	}


}

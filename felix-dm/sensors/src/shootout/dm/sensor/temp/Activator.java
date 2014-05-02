package shootout.dm.sensor.temp;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import shootout.dm.sensor.api.Sensor;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		manager.add(createComponent()
				.setInterface(Sensor.class.getName(), null)
				.setImplementation(TemperatureSensor.class)
		);
		
	}
	
	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
		
	}


}

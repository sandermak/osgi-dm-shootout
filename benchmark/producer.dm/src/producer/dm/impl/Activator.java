package producer.dm.impl;

import java.util.Dictionary;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import sensors.api.Sensor;
import sensors.base.PostalCodes;
import sensors.base.SensorImpl;

public class Activator extends DependencyActivatorBase {
	
	@Override
	public void destroy(BundleContext ctx, DependencyManager mgr)
			throws Exception {
		
	}

	@Override
	public void init(BundleContext ctx, final DependencyManager mgr)
			throws Exception {

		PostalCodes.forEach(new PostalCodes.PostalCodeHandler() {
			@Override
			public void doWithPostalCode(Dictionary<String, Object> properties) {
				SensorImpl sensor = new SensorImpl();
				mgr.add(createComponent().setInterface(Sensor.class.getName(), properties)
						.setImplementation(sensor));
			}
		});
		
	}

	

}

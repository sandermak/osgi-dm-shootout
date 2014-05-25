package consumer.dm.impl;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import consumer.dm.Client;
import sensors.api.Sensor;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		
		System.out.println("init...");
		
		manager.add(createComponent().setImplementation(ClientImpl.class).setInterface(Client.class.getName(), null)
				.add(createServiceDependency()
						.setService(Sensor.class, "(&(province=Noord-Holland)(municipality=Amsterdam))")
						.setCallbacks("addedSensor", "removedSensor")));

	}

}

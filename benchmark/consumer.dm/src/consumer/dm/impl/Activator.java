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
		
		String filter = "(&(province=Noord-Holland)(municipality=Amsterdam))";
//		String filter = "(&(province=Noord-Holland)(municipality=Amsterdam)(city=Amsterdam Zuidoost)(postalcode=1101AM))";
		manager.add(createComponent().setImplementation(ClientImpl.class).setInterface(Client.class.getName(), null)
				.add(createServiceDependency()
						.setService(Sensor.class, filter)
						.setCallbacks("addedSensor", "removedSensor")));

	}

}

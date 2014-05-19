package dm.client.impl;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import sensors.Sensor;
import dm.client.Client;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		
		manager.add(createComponent().setImplementation(ClientImpl.class).setInterface(Client.class.getName(), null)
				.add(createServiceDependency()
						.setService(Sensor.class, "(&(province=Noord-Holland)(municipality=Amsterdam))")
						.setCallbacks("addedSensor", "removedSensor")));

	}

}

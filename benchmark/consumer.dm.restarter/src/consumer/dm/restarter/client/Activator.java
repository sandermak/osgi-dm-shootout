package consumer.dm.restarter.client;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

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
		
		System.out.println("init client...");
		
		String filter = "(id=0)";
		manager.add(createComponent().setImplementation(ClientImpl.class).setInterface(Client.class.getName(), null)
				.add(createServiceDependency()
						.setService(Sensor.class, filter)
						.setCallbacks("addedSensor", "removedSensor")));

	}

}


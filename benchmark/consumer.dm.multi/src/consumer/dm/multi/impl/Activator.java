package consumer.dm.multi.impl;

import java.util.Dictionary;

import monitor.Monitor;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import sensors.api.Sensor;
import sensors.base.PostalCodes;
import consumer.dm.multi.Client;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(BundleContext context, final DependencyManager manager)
			throws Exception {
		
		System.out.println("init...");
		
		int count = 0;
		for (Dictionary<String, String> properties : PostalCodes.get()) {
			if (properties.get("municipality").equals("Ouderkerk")) { // Deventer
				String city = properties.get("city");
				String postalCode = properties.get("postalcode");
				manager.add(createComponent().setImplementation(ClientImpl.class).setInterface(Client.class.getName(), null)
						.setCallbacks(null, null, null, null)
						.add(createServiceDependency()
								.setService(Sensor.class, "(&(postalcode=" + postalCode + ")(city=" + city + "))")
								.setCallbacks("addedSensor", "removedSensor")));
				count ++;
			}			
		}
		Monitor.event("Registered " + count + " consumers...");
	}

}

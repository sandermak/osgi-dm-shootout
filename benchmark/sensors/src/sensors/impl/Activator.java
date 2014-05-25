package sensors.impl;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import sensors.api.Sensor;
import sensors.base.PostalCodes;
import sensors.base.SensorImpl;

public class Activator implements BundleActivator {
	
	private List<ServiceRegistration> registrations = new ArrayList<ServiceRegistration>();

	@Override
	public void start(final BundleContext context) throws Exception {
		PostalCodes.forEach(new PostalCodes.PostalCodeHandler() {
			@Override
			public void doWithPostalCode(Dictionary<String, String> properties) {
				SensorImpl sensor = new SensorImpl();
				ServiceRegistration registration = context.registerService(Sensor.class.getName(), sensor, properties);
				registrations.add(registration);
			}
		});
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping sensors bundle.");
		for (ServiceRegistration registration : registrations) {
			registration.unregister();
		}
		System.out.println("Unregistered " + registrations.size() + " services.");
		registrations.clear();
	}

	

}

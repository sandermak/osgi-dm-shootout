package sensors.impl;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import monitor.Monitor;

import org.apache.commons.lang3.time.StopWatch;
import org.codehaus.jackson.map.ObjectMapper;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import sensors.api.Sensor;
import sensors.api.model.City;
import sensors.api.model.Municipality;
import sensors.api.model.Province;

public class Activator implements BundleActivator {
	
	private List<ServiceRegistration> registrations = new ArrayList<ServiceRegistration>();

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Reading provinces...");
		ObjectMapper mapper = new ObjectMapper();
		Province[] provinces = mapper.readValue(this.getClass().getResourceAsStream("postalcodes.json"), Province[].class);
		System.out.println("Read " + provinces.length + " provinces.");

		Monitor.event("Start register sensors");
		StopWatch sw = new StopWatch();
		sw.start();
		int count = 0;
		int max = 5000;
		for (Province province : provinces) {
			for (Municipality municipality : province.getMunicipalities()) {
				for (City city : municipality.getCities()) {
					for (String postalCode : city.getPostalCodes()) {
						SensorImpl sensor = new SensorImpl();
						Dictionary<String, String> properties = new Hashtable<>();
						properties.put("province", province.getName());
						properties.put("municipality", municipality.getName());
						properties.put("city", city.getName());
						properties.put("postalcode", postalCode);
						// LIMIT THE AMOUNT OF SERVICES FOR NOW
						if (municipality.getName().equals("Amsterdam")) {
							if (count < max) {
								ServiceRegistration registration = context.registerService(Sensor.class.getName(), sensor, properties);
								registrations.add(registration);
								count ++;
								if (registrations.size() % 10000 == 0) {
									Monitor.event("registered", registrations.size());
								}
							}
						}
					}
				}
			}
		}
		sw.stop();
		System.out.println("Registered " + registrations.size() + " services in " + sw.getTime() + " ms.");
		Monitor.event("End register sensors");
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

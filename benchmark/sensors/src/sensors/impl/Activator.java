package sensors.impl;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.codehaus.jackson.map.ObjectMapper;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import sensors.Sensor;
import sensors.model.City;
import sensors.model.Municipality;
import sensors.model.Province;

public class Activator implements BundleActivator {
	
	private List<ServiceRegistration> registrations = new ArrayList<ServiceRegistration>();

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Reading provinces...");
		ObjectMapper mapper = new ObjectMapper();
		Province[] provinces = mapper.readValue(this.getClass().getResourceAsStream("postalcodes.json"), Province[].class);
		System.out.println("Read " + provinces.length + " provinces.");
		
		StopWatch sw = new StopWatch();
		sw.start();
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
						ServiceRegistration registration = context.registerService(Sensor.class.getName(), sensor, properties);
						registrations.add(registration);
						if (registrations.size() % 50000 == 0) {
							System.out.println("Registered " + registrations.size() + " services.");
						}
					}
				}
			}
		}
		sw.stop();
		System.out.println("Registered " + registrations.size() + " services in " + sw.getTime() + " ms.");
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

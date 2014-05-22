package dm.sensors.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import monitor.Monitor;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.osgi.framework.BundleContext;

import sensors.api.Sensor;
import sensors.api.model.City;
import sensors.api.model.Municipality;
import sensors.api.model.Province;

public class Activator extends DependencyActivatorBase {
	
	@Override
	public void destroy(BundleContext ctx, DependencyManager mgr)
			throws Exception {
		
	}

	@Override
	public void init(BundleContext ctx, DependencyManager mgr)
			throws Exception {
		System.out.println("Reading provinces...");
		ObjectMapper mapper = new ObjectMapper();
		Province[] provinces = mapper.readValue(this.getClass().getResourceAsStream("postalcodes.json"), Province[].class);
		System.out.println("Read " + provinces.length + " provinces.");

		Monitor.event("Start register sensors");
		StopWatch sw = new StopWatch();
		sw.start();
		int count = 0;
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
//						if (municipality.getName().equals("Amsterdam")) {
							mgr.add(createComponent().setInterface(Sensor.class.getName(), properties)
									.setImplementation(sensor));
							count ++;
							if (count % 10000 == 0) {
								Monitor.event("registered", count);
							}
//						}
					}
				}
			}
		}
		sw.stop();
		System.out.println("Registered " + count + " services in " + sw.getTime() + " ms.");
		Monitor.event("End register sensors");		
	}

	

}

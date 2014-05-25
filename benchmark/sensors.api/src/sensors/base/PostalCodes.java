package sensors.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import monitor.Monitor;

import org.codehaus.jackson.map.ObjectMapper;
import org.osgi.framework.BundleContext;

import sensors.api.model.City;
import sensors.api.model.Municipality;
import sensors.api.model.Province;

public class PostalCodes {
	
	private static List<Dictionary<String, String>> postalCodes = new ArrayList<>();
	private static BundleContext context;

	static {
		// read postalcodes into Properties objects
		ObjectMapper mapper = new ObjectMapper();
		Province[] provinces;
		try {
			provinces = mapper.readValue(PostalCodes.class.getResourceAsStream("postalcodes.json"), Province[].class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (Province province : provinces) {
			for (Municipality municipality : province.getMunicipalities()) {
				for (City city : municipality.getCities()) {
					for (String postalCode : city.getPostalCodes()) {
						Dictionary<String, String> properties = new Hashtable<>();
						properties.put("province", province.getName());
						properties.put("municipality", municipality.getName());
						properties.put("city", city.getName());
						properties.put("postalcode", postalCode);
						postalCodes.add(properties);
					}
				}
			}
		}
		System.out.println("Read " + provinces.length + " provinces.");
		
	}
	
	public static Iterable<Dictionary<String, String>> get() {
		return postalCodes;
	}
	
	public static void forEach(PostalCodeHandler handler) {
		Monitor.event("Start registering Sensor services");
		int count = 0;
		for (Dictionary<String, String> properties : get()) {
			handler.doWithPostalCode(properties);
			count ++;
			if (count % 10000 == 0) {
				Monitor.event("Registered services.", count);
			}
		}
		Monitor.event("Finished registering Sensor services");
	}
	
	public interface PostalCodeHandler {
		void doWithPostalCode(Dictionary<String, String> properties);
	}
	
	public static void setBundleContext(BundleContext context) {
		PostalCodes.context = context;
	}

	public static BundleContext getBundleContext() {
		return PostalCodes.context;
	}
	
	public static int getExpectedServiceCount() {
		return 19021;
	}
}

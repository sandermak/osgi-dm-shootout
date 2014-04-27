package shootout.ipojo.impl.sensor;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.ServiceProperty;

import shootout.ipojo.Sensor;

@Component
@Provides
@Instantiate
public class HumiditySensor implements Sensor {

	@ServiceProperty(name="sensor.type", value="humidity")
	private final String type = "humidity";
	
	public HumiditySensor() {
		System.out.println("[HumiditySensor] constructor");
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getValue() {
		return null;
	}
}

package shootout.ipojo.impl.sensor;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.ServiceProperty;
import org.apache.felix.ipojo.annotations.Validate;

import shootout.ipojo.Sensor;

@Component
@Provides
@Instantiate
public class AmbientTemperatureSensor implements Sensor {
	
	@ServiceProperty(name="sensor.type", value="temperature")
	private String type;

	public AmbientTemperatureSensor() {
		System.out.println("[AmbientTemperatureSensor] constructor");
	}
	
	@Validate
	void start() {
		System.out.println("[AmbientTemperatureSensor] validate");
	}
	
	@Invalidate
	void stop() {
		System.out.println("[AmbientTemperatureSensor] Invalidate");
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

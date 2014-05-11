package shootout.ipojo.sensor.temp;

import java.util.Random;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.StaticServiceProperty;
import org.apache.felix.ipojo.annotations.Validate;

import shootout.ipojo.sensor.api.Sensor;

@Component
@Provides(properties={
		@StaticServiceProperty(name="sensor.type", type="java.lang.String", value="humidity")})
@Instantiate
public class TemperatureSensor implements Sensor {
	
	public TemperatureSensor() {
		System.out.println("TemperatureSensor instantiated");
	}

	@Validate
	protected void start() {
		System.out.println("TemperatureSensor activated");
	}

	public String getValue() {
		return String.valueOf(new Random().nextInt(30));
	}

	public String getType() {
		return "temperature";
	}
	
}

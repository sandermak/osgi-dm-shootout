package shootout.ds.sensor.temp;

import java.util.Map;
import java.util.Random;

import shootout.ds.sensor.api.Sensor;

public class TemperatureSensor implements Sensor {

	public TemperatureSensor() {
		System.out.println("TemperatureSensor instantiated");
	}

	protected void activate(Map<String, Object> properties) {
		System.out.println("TemperatureSensor activated");
	}

	public String getValue() {
		return String.valueOf(new Random().nextInt(30));
	}

	public String getType() {
		return "temperature";
	}

}

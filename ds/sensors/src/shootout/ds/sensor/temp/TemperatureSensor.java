package shootout.ds.sensor.temp;

import java.util.Random;

import org.osgi.service.component.ComponentContext;

import shootout.ds.sensor.api.Sensor;

public class TemperatureSensor implements Sensor {

	public TemperatureSensor() {
		System.out.println("TemperatureSensor instantiated");
	}

	protected void activate(ComponentContext context) {
		System.out.println("TemperatureSensor activated");
	}

	public String getValue() {
		return String.valueOf(new Random().nextInt(30));
	}

	public String getType() {
		return "temperature";
	}

}

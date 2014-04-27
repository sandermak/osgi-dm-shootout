package shootout.sensor.temp;

import java.util.Random;

import org.osgi.service.component.ComponentContext;

import shootout.sensor.api.Sensor;

public class TemperatureSensor implements Sensor {
	
	public TemperatureSensor() {
		System.out.println("TemperatureSensor instantiated");
	}

	protected void activate(ComponentContext context) {
		System.out.println("TemperatureSensor activated");
	}

	public String getReading() {
		return String.valueOf(new Random().nextInt(30));
	}
	
}

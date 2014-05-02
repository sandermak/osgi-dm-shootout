package shootout.dm.sensor.temp;

import java.util.Random;

import shootout.dm.sensor.api.Sensor;

public class TemperatureSensor implements Sensor {
	
	public TemperatureSensor() {
		System.out.println("TemperatureSensor instantiated");
	}

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

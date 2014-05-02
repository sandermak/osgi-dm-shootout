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

	public String getReading() {
		return String.valueOf(new Random().nextInt(30));
	}
	
}

package shootout.sensor.temp;

import java.util.Random;

import shootout.sensor.api.Sensor;

public class TemperatureSensor implements Sensor {
	
	public TemperatureSensor() {
		System.out.println("TemperatureSensor instantiated");
	}

	public String getReading() {
		return String.valueOf(new Random().nextInt(30));
	}
	
}

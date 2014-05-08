package shootout.blueprint.sensor.temp;

import java.util.Random;

import shootout.blueprint.sensor.api.Sensor;

public class TemperatureSensor implements Sensor {
	
	public TemperatureSensor() {
		System.out.println("TemperatureSensor instantiated");
	}

	public String getValue() {
		return String.valueOf(new Random().nextInt(30));
	}

	public String getType() {
		return "temperature";
	}
	
}

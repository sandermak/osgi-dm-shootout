package shootout.dm.sensor.humid;

import java.util.Random;

import shootout.dm.sensor.api.Sensor;

public class HumiditySensor implements Sensor {
	
	public HumiditySensor() {
		System.out.println("HumiditySensor instantiated");
	}

	public String getType() {
		return "humidity";
	}

	public String getValue() {
		return String.valueOf(new Random().nextInt(100));
	}

}

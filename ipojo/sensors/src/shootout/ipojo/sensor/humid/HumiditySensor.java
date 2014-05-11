package shootout.ipojo.sensor.humid;

import java.util.Random;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.StaticServiceProperty;

import shootout.ipojo.sensor.api.Sensor;

@Component
@Provides(properties={
		@StaticServiceProperty(name="sensor.type", type="java.lang.String", value="humidity")})
@Instantiate
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

package sensors.base;

import sensors.api.Sensor;

public class SensorImpl implements Sensor {

	@Override
	public String[] getValues() {
		return new String[] { "" + System.currentTimeMillis() };
	}
	
}

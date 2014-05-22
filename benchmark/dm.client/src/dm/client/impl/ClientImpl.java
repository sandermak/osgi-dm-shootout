package dm.client.impl;

import monitor.Constants;
import monitor.Monitor;
import sensors.api.Sensor;
import dm.client.Client;

public class ClientImpl implements Client {
	
	int count;

	void addedSensor(Sensor sensor) {
		if (count == 0) {
			Monitor.event("Added first sensor");
		}
		count ++;
//		System.out.println("added " + sensor + " #" + count);
		if (count == Constants.EXPECTED_SERVICE_COUNT) {
			System.out.println("Added all " + count + " sensors...");
			Monitor.event("Added sensors");
		}
	}
	
	void removedSensor(Sensor sensor) {
		
	}
	
	public void start() {
		System.out.println("Activate... ");
	}
	
	public void deactivate() {
		System.out.println("Deactivate...");
	}
}

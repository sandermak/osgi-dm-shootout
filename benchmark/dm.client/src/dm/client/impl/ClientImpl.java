package dm.client.impl;

import monitor.Monitor;
import sensors.Sensor;
import dm.client.Client;

public class ClientImpl implements Client {
	
	int count;

	void addedSensor(Sensor sensor) {
		if (count == 0) {
			Monitor.event("Added first sensor");
		}
		count ++;
//		System.out.println("added " + sensor + " #" + count);
		if (count == 19021) {
			System.out.println("Added all sensors...");
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

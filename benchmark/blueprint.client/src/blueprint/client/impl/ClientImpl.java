package blueprint.client.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import monitor.Constants;
import monitor.Monitor;
import sensors.api.Sensor;
import blueprint.client.Client;

public class ClientImpl implements Client {
	private List<Sensor> sensors;
	
	AtomicInteger count = new AtomicInteger(0);

	public void addedSensor(Sensor sensor) {
		int prevCount = count.getAndIncrement();
		if (prevCount == 0) {
			Monitor.event("Added first sensor");
		}

//		System.out.println("added " + sensor + " #" + count);
		if (prevCount + 1 == Constants.EXPECTED_SERVICE_COUNT) {
			System.out.println("Added all " + count + " sensors...");
			Monitor.event("Added sensors");
		}
	}
	
	public void removedSensor(Sensor sensor) {
		
	}
	
	public void start() {
		System.out.println("Activate... ");
		System.out.println("All sensors available: " + sensors.size());
	}
	
	public void deactivate() {
		System.out.println("Deactivate...");
	}
	
	public synchronized void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
}


package consumer.blueprint.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import consumer.blueprint.Client;
import monitor.Constants;
import monitor.Monitor;
import sensors.api.Sensor;

public class ClientImpl implements Client {
	private List<Sensor> sensors;
	
	AtomicInteger count = new AtomicInteger(0);

	public void addedSensor(Sensor sensor) {
		// invoke sensor method
		sensor.getValues();
		int prevCount = count.getAndIncrement();
		if (prevCount == 0) {
			Monitor.event("Added first sensor");
		}

//		System.out.println("added " + sensor + " #" + count);
		if (prevCount + 1 == Constants.EXPECTED_SERVICE_COUNT) {
			System.out.println("Added all " + count + " sensors...");
			Monitor.event("Added all sensors");
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


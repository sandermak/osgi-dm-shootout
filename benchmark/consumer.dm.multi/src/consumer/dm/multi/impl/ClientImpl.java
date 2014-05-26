package consumer.dm.multi.impl;

import java.util.concurrent.atomic.AtomicInteger;

import consumer.dm.multi.Client;
import monitor.Monitor;
import sensors.api.Sensor;
import sensors.base.PostalCodes;

public class ClientImpl implements Client {
	
	AtomicInteger count = new AtomicInteger(0);

	void addedSensor(Sensor sensor) {
		int prevCount = count.getAndIncrement();
		if (prevCount == 0) {
			Monitor.event("Added first sensor");
		}

//		System.out.println("added " + sensor + " #" + count);
		if (prevCount + 1 == PostalCodes.getExpectedServiceCount()) {
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

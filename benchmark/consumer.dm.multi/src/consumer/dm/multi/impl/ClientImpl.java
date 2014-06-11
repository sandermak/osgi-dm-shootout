package consumer.dm.multi.impl;

import java.util.concurrent.atomic.AtomicInteger;

import monitor.Monitor;
import sensors.api.Sensor;
import consumer.dm.multi.Client;

public class ClientImpl implements Client {
	
	AtomicInteger count = new AtomicInteger(0);

	void addedSensor(Sensor sensor) {
		int prevCount = count.getAndIncrement();
		if (prevCount == 0) {
			Monitor.event("Added first sensor");
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

package consumer.dm.impl;

import java.util.concurrent.atomic.AtomicInteger;

import monitor.Constants;
import monitor.Monitor;
import sensors.api.Sensor;
import sensors.base.PostalCodes;
import consumer.dm.Client;

public class ClientImpl implements Client {
	
	AtomicInteger count = new AtomicInteger(0);

	void addedSensor(Sensor sensor) {
		// invoke sensor method
		sensor.getValues();
		int prevCount = count.getAndIncrement();
		if (prevCount == 0) {
			Monitor.event("Added first sensor", PostalCodes.getMaxSvcCount());
		}

		if (prevCount + 1 == Constants.EXPECTED_SERVICE_COUNT) {
			System.out.println("Added all " + count + " sensors...");
			Monitor.event("Added all sensors");
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

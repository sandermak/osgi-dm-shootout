package ipojo.client.impl;

import java.util.concurrent.atomic.AtomicInteger;

import ipojo.client.Client;
import monitor.Monitor;

import org.apache.felix.ipojo.annotations.Bind;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Modified;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Unbind;
import org.apache.felix.ipojo.annotations.Validate;

import sensors.api.Sensor;
import sensors.base.PostalCodes;

@Component
@Provides
@Instantiate
public class ClientImpl implements Client {
	
	AtomicInteger count = new AtomicInteger(0);

	@Bind(aggregate=true, filter="(&(province=Noord-Holland)(municipality=Amsterdam))")
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
	
	@Unbind
	void removedSensor(Sensor sensor) {
		
	}
	
	@Modified
	void modifiedSensor(Sensor sensor) {
		
	}
	
	@Validate
	public void start() {
		System.out.println("Activate... ");
	}
	
	@Invalidate
	public void deactivate() {
		System.out.println("Deactivate...");
	}
}

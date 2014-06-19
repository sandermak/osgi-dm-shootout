package consumer.ipojo.restarter.client;

import java.util.concurrent.atomic.AtomicInteger;

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

	@Bind(aggregate=true, filter="(id=0)")
	void addedSensor(Sensor sensor) {
		// invoke the service's method
		sensor.getValues();
		int prevCount = count.getAndIncrement();
		if (prevCount == 0) {
			Monitor.event("Added first sensor", PostalCodes.getMaxSvcCount());
		}
		Monitor.event("ipojo.added", new Object[] { count.get() });
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

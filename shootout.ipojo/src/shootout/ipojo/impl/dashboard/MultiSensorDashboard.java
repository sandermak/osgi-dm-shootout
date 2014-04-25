package shootout.ipojo.impl.dashboard;

import org.apache.felix.ipojo.annotations.Bind;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Modified;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Unbind;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.framework.ServiceReference;

import shootout.ipojo.Dashboard;
import shootout.ipojo.Sensor;

@Component
@Instantiate
@Provides
public class MultiSensorDashboard implements Dashboard {

	// TODO: What exactly does the proxy property do?
	@Bind(aggregate=true) // white board pattern / service discovery
	void bindSensor(Sensor sensor, ServiceReference ref) {
		System.out.println("[MultiSensorDashboard] bind " + sensor.getType() + " " + sensor + " " + ref);
	}
	
	@Unbind
	void unbindSensor(Sensor sensor) {
		System.out.println("[MultiSensorDashboard] unbind " + sensor.getType());
	}
	
	@Modified
	void modifiedSensor(Sensor sensor) {
		System.out.println("[MultiSensorDashboard] modified " + sensor.getType());
	}
	
	@Validate
	void start() {
		System.out.println("[MultiSensorDashboard] validate");
	}
	
	@Invalidate
	void stop() {
		System.out.println("[MultiSensorDashboard] invalidate");
	}
}

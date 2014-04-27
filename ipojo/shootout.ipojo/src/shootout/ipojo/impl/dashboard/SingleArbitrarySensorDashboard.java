package shootout.ipojo.impl.dashboard;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;

import shootout.ipojo.Dashboard;
import shootout.ipojo.Sensor;

@Component
@Provides
@Instantiate
public class SingleArbitrarySensorDashboard implements Dashboard {
	
	@Requires
	private volatile Sensor sensor;

	public SingleArbitrarySensorDashboard() {
		System.out.println("[DashboardImpl] constructor..");
	}
	
	@Validate
	void start() {
		System.out.println("[DashboardImpl] validate.. " + sensor);
	}
	
	@Invalidate
	void stop() {
		System.out.println("[DashboardImpl] invalidate..");
	}
}

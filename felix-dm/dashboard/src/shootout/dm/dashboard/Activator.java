package shootout.dm.dashboard;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import shootout.dm.dashboard.api.Dashboard;
import shootout.dm.sensor.api.Sensor;

public class Activator extends DependencyActivatorBase {

	public void init(BundleContext context, DependencyManager manager)
			throws Exception {

		manager.add(createComponent()
				.setInterface(Dashboard.class.getName(), null)
				.setImplementation(DashboardImpl.class)
				.add(createServiceDependency().setService(Sensor.class)
						.setCallbacks("sensorAdded", "sensorRemoved")));

	}

	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
	}

}

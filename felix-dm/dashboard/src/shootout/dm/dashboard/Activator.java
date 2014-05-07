package shootout.dm.dashboard;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationAdmin;

import shootout.dm.alerter.api.Alerter;
import shootout.dm.dashboard.api.Dashboard;
import shootout.dm.sensor.api.Sensor;

public class Activator extends DependencyActivatorBase {

	public void init(BundleContext context, DependencyManager manager)
			throws Exception {

		manager.add(createComponent()
				.setInterface(Dashboard.class.getName(), null)
				.setImplementation(DashboardImpl.class)
				.add(createServiceDependency().setService(Sensor.class)
						.setCallbacks("sensorAdded", "sensorRemoved"))
				.add(createServiceDependency().setService(Alerter.class))
				.add(createConfigurationDependency().setPid("dm.dashboard"))
        );				
		
		manager.add(createComponent().setImplementation(ConfigCreator.class)
				.add(createServiceDependency().setService(
						ConfigurationAdmin.class)));		


	}

	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
	}

}

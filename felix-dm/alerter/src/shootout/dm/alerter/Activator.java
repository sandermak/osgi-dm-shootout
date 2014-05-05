package shootout.dm.alerter;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import shootout.dm.alerter.api.Alerter;

public class Activator extends DependencyActivatorBase {

	public void init(BundleContext context, DependencyManager manager)
			throws Exception {

		manager.add(createComponent()
				.setInterface(Alerter.class.getName(), null)
				.setImplementation(AlerterImpl.class)
		);

	}

	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
	}

}

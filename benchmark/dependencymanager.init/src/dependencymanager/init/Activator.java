package dependencymanager.init;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		System.out.println("DependencyManager loaded...");
	}

	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {

	}

}

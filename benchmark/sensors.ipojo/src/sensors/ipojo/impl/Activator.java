package sensors.ipojo.impl;

import java.util.Dictionary;

import org.apache.felix.ipojo.ComponentInstance;
import org.apache.felix.ipojo.ConfigurationException;
import org.apache.felix.ipojo.Factory;
import org.apache.felix.ipojo.MissingHandlerException;
import org.apache.felix.ipojo.UnacceptableConfiguration;
import org.apache.felix.ipojo.api.PrimitiveComponentType;
import org.apache.felix.ipojo.api.Service;
import org.apache.felix.ipojo.api.ServiceProperty;
import org.apache.felix.ipojo.extender.DeclarationBuilderService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import sensors.base.PostalCodes;
import sensors.base.SensorImpl;

public class Activator implements BundleActivator, ServiceTrackerCustomizer<ServiceReference, Factory> {
	
private BundleContext context;

	@Override
	public void start(BundleContext context) throws Exception {
		
		this.context = context;
		System.out.println("start...");
		SensorImpl impl = new SensorImpl();
		
		System.out.println("ctx: " + PostalCodes.getBundleContext());

		new PrimitiveComponentType()
	    .setBundleContext(PostalCodes.getBundleContext())
	    .setClassName(SensorImpl.class.getName())
	    .addService(new Service()
	        .addProperty(new ServiceProperty().setName("province").setType("string"))
	        .addProperty(new ServiceProperty().setName("municipality").setType("string"))
	        .addProperty(new ServiceProperty().setName("city").setType("string"))
	        .addProperty(new ServiceProperty().setName("postalcode").setType("string"))
	        .setCreationStrategy(Service.INSTANCE_STRATEGY)).start();
		
		// Using a service tracker here to obtain a reference to the factory capable of providing the actual service
		Filter filter = context.createFilter("(&(objectClass=" + Factory.class.getName() + ")(factory.name=" + SensorImpl.class.getName() + "))");
		ServiceTracker<ServiceReference, DeclarationBuilderService> tracker = new ServiceTracker(context, filter, this);
		tracker.open();	
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}

	@Override
	public Factory addingService(ServiceReference ref) {
		final Factory factory = (Factory) context.getService(ref);
		
		PostalCodes.forEach(new PostalCodes.PostalCodeHandler() {
			@Override
			public void doWithPostalCode(Dictionary<String, String> properties) {
				ComponentInstance instance;
				try {
					instance = factory.createComponentInstance(properties);
					instance.start();					
				} catch (UnacceptableConfiguration
						| MissingHandlerException | ConfigurationException e) {
					throw new RuntimeException(e);
				}
			}
		});
		return factory;
	}

	@Override
	public void modifiedService(ServiceReference ref,
			Factory factory) {
		
	}

	@Override
	public void removedService(ServiceReference ref,
			Factory factory) {
		
	}

}

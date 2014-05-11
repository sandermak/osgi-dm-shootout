package shootout.ipojo.activator;

import java.util.Arrays;

import org.apache.felix.ipojo.ComponentInstance;
import org.apache.felix.ipojo.ConfigurationException;
import org.apache.felix.ipojo.Factory;
import org.apache.felix.ipojo.IPOJOServiceFactory;
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

import shootout.ipojo.Sensor;
import shootout.ipojo.impl.dashboard.MultiSensorDashboard;
import shootout.ipojo.impl.sensor.AmbientTemperatureSensor;

public class Activator implements BundleActivator, ServiceTrackerCustomizer<ServiceReference, Factory> {

	private BundleContext context;

	// not really straightforward 
	// Error message: ! Failed to start bundle shootout.ipojo.activator-0.0.0, exception activator error The factory associated with the component type is invalid (not started or missing handlers) from: org.apache.felix.ipojo.api.ComponentType:ensureFactory#189
	// http://mail-archives.apache.org/mod_mbox/felix-users/201401.mbox/%3C29C820C3F0D9864CBDAE1AFA0557E24125597E@KU-SH-MBX01.kustar.ac.ae%3E
	// solution:
	// http://felix.apache.org/documentation/subprojects/apache-felix-ipojo/apache-felix-ipojo-userguide/ipojo-advanced-topics/ipojo-factory-service.html#deleting-instances
	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
		System.out.println("Activator start...");
		
		// You can define the component type here, however, you cannot create actual component instances since the factory might not be
		// available yet.
		System.out.println("Register TemperatureSensor type");
		new PrimitiveComponentType()
	    .setBundleContext(context)
	    .setClassName(AmbientTemperatureSensor.class.getName())
	    .addService(new Service()
	        .addProperty(new ServiceProperty()
	            .setField("type")
	            .setName("sensor.type"))
	        .setCreationStrategy(Service.INSTANCE_STRATEGY)).start(); 
		// TODO: Dive into strategies
		// INSTANCE_STRATEGY creates one service object per requiring instance
		// SERVICE_STRATEGY service factory
		// SINGLETON_STRATEGY singleton
		// METHOD_STRATEGY delegates service creation to factory method on component
		
		System.out.println("Register MultiSensorDashboard type ");
		new PrimitiveComponentType()
	    .setBundleContext(context)
	    .setImmediate(true)
	    .setClassName(MultiSensorDashboard.class.getName())
	    .setValidateMethod("start")
	    .setInvalidateMethod("stop")
	    .addService(new Service()
	        .setCreationStrategy(Service.SINGLETON_STRATEGY)).start();
		
		// Using a service tracker here to obtain a reference to the factory capable of providing the actual service
		Filter filter = context.createFilter("(&(objectClass=" + Factory.class.getName() + ")(factory.name=" + AmbientTemperatureSensor.class.getName() + "))");
		ServiceTracker<ServiceReference, DeclarationBuilderService> tracker = new ServiceTracker(context, filter, this);
		System.out.println("Tracker open");
		tracker.open();		
	}

	@Override
	public void stop(BundleContext context) throws Exception {

	}

	@Override
	public Factory addingService(ServiceReference reference) {
		System.out.println("Adding service");
		Factory factory = (Factory) context.getService(reference);
		System.out.println("Got factory: " + reference.getProperty("factory.name"));
		
		// TODO: Configure sensor.type property
		try {
			ComponentInstance instance = factory.createComponentInstance(null);
			instance.start();
		} catch (UnacceptableConfiguration | MissingHandlerException
				| ConfigurationException e) {
			e.printStackTrace();
		}
		checkSensorAvailable();
		return factory;
	}

	@Override
	public void modifiedService(ServiceReference ref, Factory service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference ref, Factory service) {
		// TODO Auto-generated method stub
		
	}
	
	private void checkSensorAvailable() {
		System.out.println("Check sensor available...");
		ServiceReference ref = context.getServiceReference(Sensor.class.getName());
		if (ref != null) {
			System.out.println("ref: " + ref.getProperty("sensor.type"));
			System.out.println(Arrays.toString(ref.getPropertyKeys()));
			Object service = context.getService(ref);
			System.out.println("Service: " + service.getClass());
			IPOJOServiceFactory factory = (IPOJOServiceFactory) service;
			// TODO: Figure out ComponentInstance to be passed to factory.getService()
			Object instance = factory.getService(null);
			System.out.println("Instance: " + instance);
		} else {
			System.out.println("Sensor is unavailable!");
		}
	}

}

package shootout.ipojo.activator;

import org.apache.felix.ipojo.api.PrimitiveComponentType;
import org.apache.felix.ipojo.api.Service;
import org.apache.felix.ipojo.api.ServiceProperty;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import shootout.ipojo.impl.dashboard.MultiSensorDashboard;
import shootout.ipojo.impl.sensor.AmbientTemperatureSensor;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Activator start...");
		
		System.out.println("Register TemperatureSensor");
		new PrimitiveComponentType()
	    .setBundleContext(context)
	    .setClassName(AmbientTemperatureSensor.class.getName())
	    .addService(new Service()
	        .addProperty(new ServiceProperty()
	            .setField("type")
	            .setName("sensor.type"))
	        .setCreationStrategy(Service.INSTANCE_STRATEGY))
	    .createInstance();
		
		System.out.println("Register MultiSensorDashboard");
		new PrimitiveComponentType()
	    .setBundleContext(context)
	    .setImmediate(true)
	    .setClassName(MultiSensorDashboard.class.getName())
	    .setValidateMethod("start")
	    .setInvalidateMethod("stop")
	    .addService(new Service()
	        .setCreationStrategy(Service.SINGLETON_STRATEGY))
	    .createInstance();
	}

	@Override
	public void stop(BundleContext context) throws Exception {

	}

}

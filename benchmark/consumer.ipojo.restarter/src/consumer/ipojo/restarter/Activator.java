package consumer.ipojo.restarter;

import monitor.Listener;
import monitor.Monitor;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import sensors.base.PostalCodes;

public class Activator implements BundleActivator, Listener {

	private int max = 50;
	Bundle ipojoClientBundle;
	Bundle sensorsBundle;
	
	@Override
	public void start(BundleContext context) throws Exception {
		Monitor.addListener(this);
		PostalCodes.setMaxSvcCount(max);
		for (Bundle bundle : context.getBundles()) {
			if (bundle.getSymbolicName().equals("consumer.ipojo.restarter.client")) {
				ipojoClientBundle = bundle;
			}
			if (bundle.getSymbolicName().equals("producer.osgi")) {
				sensorsBundle = bundle;
			}
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(String name, Object[] properties) {
		if (name.equals("ipojo.added")) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					max += 50;
					System.out.println("Increment to " + max);
					PostalCodes.setMaxSvcCount(max);
					try {
						sensorsBundle.stop();
						ipojoClientBundle.stop();
						sensorsBundle.start();
						ipojoClientBundle.start();
					} catch (BundleException e) {
						e.printStackTrace();
					}					
				}
				
			}).start();

		}
	}

}

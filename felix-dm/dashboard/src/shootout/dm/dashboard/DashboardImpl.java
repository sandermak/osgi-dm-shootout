package shootout.dm.dashboard;

import java.util.Timer;
import java.util.TimerTask;

import org.osgi.framework.ServiceReference;

import shootout.dm.dashboard.api.Dashboard;
import shootout.dm.sensor.api.Sensor;

public class DashboardImpl implements Dashboard {

	private Sensor sensor;
	private Timer timer;

	public DashboardImpl() {
		System.out.println("DashboardImpl instantiated");
	}

	public void showDashboard() {
		System.out.println("This is the dashboard:");
		System.out.println("Reading: " + sensor.getReading());
	}

	// Special method called by Felix DM
	public void start() {
		System.out.println("DashboardImpl activated");
		timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				showDashboard();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 10000);

	}
	// Special method called by Felix DM
	public void stop() { 
		System.out.println("DashboardImpl deactivated");
		timer.cancel();
		timer = null;
	}
	
	public void sensorAdded(@SuppressWarnings("rawtypes") ServiceReference ref, Object obj) {
		System.out.println("Sensor added " + obj.toString());
	}
	
	public void sensorRemoved(@SuppressWarnings("rawtypes") ServiceReference ref, Object obj) {
		System.out.println("Sensor removed " + obj.toString());
	}
}

package shootout.dm.dashboard;

import java.util.Collections;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.ServiceReference;

import shootout.dm.dashboard.api.Dashboard;
import shootout.dm.sensor.api.Sensor;

public class DashboardImpl implements Dashboard {

	private Set<Sensor> sensors = Collections
			.newSetFromMap(new ConcurrentHashMap<Sensor, Boolean>());
	private Timer timer;

	public DashboardImpl() {
		System.out.println("DashboardImpl instantiated");
	}

	public void showDashboard() {
		System.out.println("This is the dashboard:");
		for (Sensor sensor : sensors) {
			System.out.println(sensor.getType() + ": " + sensor.getValue());
		}
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

	public void sensorAdded(@SuppressWarnings("rawtypes") ServiceReference ref,
			Object obj) {
		System.out.println("Sensor added " + obj.toString());
		sensors.add((Sensor) obj);
	}

	public void sensorRemoved(
			@SuppressWarnings("rawtypes") ServiceReference ref, Object obj) {
		System.out.println("Sensor removed " + obj.toString());
		sensors.remove((Sensor) obj);
	}
}

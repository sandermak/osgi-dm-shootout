package shootout.dashboard;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.ComponentContext;

import shootout.dashboard.api.Dashboard;
import shootout.ds.alerter.api.Alerter;
import shootout.ds.alerter.api.Reading;
import shootout.ds.alerter.api.Reading.ReadingType;
import shootout.ds.sensor.api.Sensor;

public class DashboardImpl implements Dashboard {

	private Set<Sensor> sensors = Collections
			.newSetFromMap(new ConcurrentHashMap<Sensor, Boolean>());

	private Alerter alerter;

	private Timer timer;

	public DashboardImpl() {
		System.out.println("DashboardImpl instantiated");
	}

	public void showDashboard() {
		System.out.println("This is the dashboard:");
		Set<Reading> readings = new HashSet<>();
		for (Sensor sensor : sensors) {
			Reading reading = new Reading(ReadingType.valueOf(sensor.getType()
					.toUpperCase()), sensor.getValue());
			System.out.println(sensor.getType() + ": " + reading.getValue());
			readings.add(reading);
		}

		String alert = alerter.getAlertMessage(readings);
		if (alert != null) {
			System.out.println("!!! Alert: " + alert);
		}
	}

	protected void activate(ComponentContext context) {
		System.out.println("DashboardImpl activated");
		timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				showDashboard();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 10000);

	}

	protected void deactivate(ComponentContext context) {
		timer.cancel();
		timer = null;
	}

	public void sensorRemoved(Sensor sensor) {
		System.out.println("Sensor removed " + sensor.toString());
		sensors.remove(sensor);
	}

	public void sensorAdded(Sensor sensor) {
		System.out.println("Sensor added " + sensor.toString());
		sensors.add(sensor);
	}

	public synchronized void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}

	public synchronized void unsetAlerter(Alerter alerter) {
		if (this.alerter == alerter) {
			this.alerter = null;
		}
	}
}

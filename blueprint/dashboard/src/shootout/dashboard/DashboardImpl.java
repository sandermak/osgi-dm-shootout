package shootout.dashboard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import shootout.blueprint.alerter.api.Alerter;
import shootout.blueprint.alerter.api.Reading;
import shootout.blueprint.alerter.api.Reading.ReadingType;
import shootout.blueprint.sensor.api.Sensor;
import shootout.dashboard.api.Dashboard;

public class DashboardImpl implements Dashboard {

	protected List<Sensor> sensors;
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

	// No special name, referenced with init-method property. Must be public.
	public void myActivate() {
		System.out.println("DashboardImpl activated");
		timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				showDashboard();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 10000);

	}

	// No special name, referenced with destroy-method property. Must be public.
	public void myDeactivate() { 
		System.out.println("DashboardImpl deactivated");
		timer.cancel();
		timer = null;
	}
	
	public synchronized void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public synchronized void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}

}

package shootout.dashboard;

import java.util.Timer;
import java.util.TimerTask;

import shootout.blueprint.alerter.api.Alerter;
import shootout.dashboard.api.Dashboard;
import shootout.sensor.api.Sensor;

public class DashboardImpl implements Dashboard {

	private Sensor sensor;
	private Alerter alerter;
	private Timer timer;

	public DashboardImpl() {
		System.out.println("DashboardImpl instantiated");
	}

	public void showDashboard() {
		System.out.println("This is the dashboard:");
		System.out.println("Reading: " + sensor.getReading());
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
	
	public synchronized void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public synchronized void unsetSensor(Sensor sensor) {
		if (this.sensor == sensor) {
			this.sensor = null;
		}
	}
	
	public synchronized void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}

}

package shootout.dashboard;

import java.util.Timer;
import java.util.TimerTask;

import org.osgi.service.component.ComponentContext;

import shootout.dashboard.api.Dashboard;
import shootout.sensor.api.Sensor;

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
	
	public synchronized void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public synchronized void unsetSensor(Sensor sensor) {
		if (this.sensor == sensor) {
			this.sensor = null;
		}
	}
}

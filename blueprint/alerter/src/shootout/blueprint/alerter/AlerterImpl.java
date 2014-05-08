package shootout.blueprint.alerter;

import java.util.Set;

import shootout.blueprint.alerter.api.Alerter;
import shootout.blueprint.alerter.api.Reading;

public class AlerterImpl implements Alerter {

	public String getAlertMessage(Set<Reading> readings) {
		Integer temp = null;
		Integer humidity = null;
		for (Reading reading : readings) {
			switch (reading.getType()) {
			case TEMPERATURE:
				temp = reading.getValue();
				break;
			case HUMIDITY:
				humidity = reading.getValue();
				break;
			}
		}

		if (temp != null && temp > 25 && humidity != null && humidity > 70) {
			return "Tropical climate detected, relax!";
		}

		return null;
	}

}

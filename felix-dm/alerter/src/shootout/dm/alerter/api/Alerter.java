package shootout.dm.alerter.api;

import java.util.Set;

public interface Alerter {
	
	String getAlertMessage(Set<Reading> sensors);
	
}

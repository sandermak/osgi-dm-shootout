package monitor;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class Monitor {

	private static AtomicLong pastEvent = new AtomicLong();
	private static AtomicLong firstEvent = new AtomicLong();
	
	public static void event(String name, Object[] properties) {
		long now = System.currentTimeMillis();
		if (firstEvent.get() == 0) {
			firstEvent.set(now);
		}
		if (pastEvent.get() != 0) {
			long duration = now - pastEvent.get();
			long durationSinceFirst = now - firstEvent.get();
			System.out.println("Event: " + name + " " + Arrays.toString(properties) + ". Elapsed since last event: " + duration + ", " + durationSinceFirst);
		} else {
			System.out.println("Event: " + name + " " + Arrays.toString(properties));
		}
		pastEvent.set(now);
	}
	
	public static void event(String name) {
		event(name, new Object[0]);
	}
	
	public static void event(String name, Object property) {
		event(name, new Object[] { property });
	}
}

package monitor;

import java.util.concurrent.atomic.AtomicLong;

public class Monitor {

	private static AtomicLong pastEvent = new AtomicLong();
	private static AtomicLong firstEvent = new AtomicLong();
	
	public static void event(String name) {
		long now = System.currentTimeMillis();
		if (firstEvent.get() == 0) {
			firstEvent.set(now);
		}
		System.out.println(now + " " + name);
		if (pastEvent.get() != 0) {
			long duration = now - pastEvent.get();
			long durationSinceFirst = now - firstEvent.get();
			System.out.println("Elapsed since last event: " + duration + " (" + durationSinceFirst + ")");
		}
		pastEvent.set(now);
	}
}

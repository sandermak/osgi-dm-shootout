package monitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Monitor {

	private static AtomicLong pastEvent = new AtomicLong();
	private static AtomicLong firstEvent = new AtomicLong();
	private static Set<Listener> listeners = new HashSet<>();
	
	public static void event(String name, Object[] properties) {
		long now = System.currentTimeMillis();
		if (firstEvent.get() == 0) {
			firstEvent.set(now);
		}
		if (pastEvent.get() != 0) {
			long duration = now - pastEvent.get();
			long durationSinceFirst = now - firstEvent.get();
//			System.out.println("Event: " + name + " " + Arrays.toString(properties) + ". Elapsed since last event: " + duration + ", " + durationSinceFirst);
			String msg = "";
			for (Object property : properties) {
				msg += property;
				msg += ";";
			}
			msg += duration;
			msg += ";";
			msg += durationSinceFirst;
			msg += ";";
			msg += name;
			System.out.println(msg);
		} else {
			System.out.println("Event: " + name + " " + Arrays.toString(properties));
		}
		pastEvent.set(now);
		notifyAllListeners(name, properties);
	}
	
	public static void event(String name) {
		event(name, new Object[0]);
	}
	
	public static void event(String name, Object property) {
		event(name, new Object[] { property });
	}
	
	public static void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	private static void notifyAllListeners(String name, Object[] properties) {
		for (Listener listener : listeners) {
			listener.onEvent(name, properties);
		}
	}
}

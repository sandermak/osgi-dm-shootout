package monitor;

public interface Listener {

	public void onEvent(String name, Object[] properties);
}

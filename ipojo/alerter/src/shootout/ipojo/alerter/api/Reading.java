package shootout.ipojo.alerter.api;

public class Reading {

	public enum ReadingType {
		TEMPERATURE, HUMIDITY;
	}
	
	private final ReadingType type;
	private final Integer value;
	
	public Reading(ReadingType type, String value) {
		this.type = type;
		this.value = Integer.parseInt(value);
	}

	public ReadingType getType() {
		return type;
	}

	public Integer getValue() {
		return value;
	}
	
}

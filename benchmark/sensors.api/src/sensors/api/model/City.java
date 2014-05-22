package sensors.api.model;

import java.util.ArrayList;
import java.util.List;

public class City {

	private String name;
	private List<String> postalCodes = new ArrayList<>();
	
	public City() {
	}
	
	public City(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getPostalCodes() {
		return postalCodes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
